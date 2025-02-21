package io.swagger.client;

import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.AlbumInfo;
import io.swagger.client.model.AlbumsProfile;
import io.swagger.client.model.ImageMetaData;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Chelsea on 2025-02-07
 */
public class ApiPerformanceService {

  public static final String GO_URL = "http://35.94.169.151:8086";
  public static final String TOMCAT_URL = "http://35.94.169.151:8080/java-album-server_Web";
  private static final int REQUEST_COUNT = 1000;
  private static final int INITIAL_THREADS = 10;
  private static AtomicLong totalRequests = new AtomicLong(0);

  public static void main(String[] args) throws InterruptedException {

    DefaultApi apiInstance = new DefaultApi();
//    if (args.length != 4) {
//      System.out.println("Usage: java LoadTester <threadGroupSize> <numThreadGroups> <delay> <IPAddr>");
//      return;
//    }
//
//    int threadGroupSize = Integer.parseInt(args[0]);
//    int numThreadGroups = Integer.parseInt(args[1]);
//    int delay = Integer.parseInt(args[2]);
//    String ipAddr = args[3];
    stressTest(10, 30, 2, TOMCAT_URL);
  }
  private static void stressTest(int threadGroupSize, int numThreadGroups, int delay, String url)
      throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(100);
    DefaultApi apiInstance = new DefaultApi();
    // start 10 thread
    System.out.println("Starting initial 10 threads...");
    long startTime = System.currentTimeMillis();
    CountDownLatch initialLatch = new CountDownLatch(INITIAL_THREADS);
    for (int i = 0; i < INITIAL_THREADS; i++) {
      executor.submit(() -> {
        sendRequests(apiInstance, url, 100);
        initialLatch.countDown();
      });
    }
    initialLatch.await();
    // start stress test
    System.out.printf("Starting %d thread groups with delay %d seconds...\n", numThreadGroups, delay);
    CountDownLatch testLatch = new CountDownLatch(threadGroupSize * numThreadGroups);

    for (int i = 0; i < numThreadGroups; i++) {
      System.out.printf("Starting thread group %d/%d...\n", i + 1, numThreadGroups);
      for (int j = 0; j < threadGroupSize; j++) {
        executor.submit(() -> {
          sendRequests(apiInstance, url, REQUEST_COUNT);
          testLatch.countDown();
        });
      }
      Thread.sleep(delay * 1000L);
    }

    testLatch.await();
    long endTime = System.currentTimeMillis();

    // get result
    double wallTime = (endTime - startTime) / 1000.0;
    double throughput = totalRequests.get() / wallTime;
    System.out.printf("\nWall Time: %.2f seconds\n", wallTime);
    System.out.printf("Throughput: %.2f requests/second\n", throughput);

    executor.shutdown();
  }

  private static void sendRequests(DefaultApi api, String url, int numRequests) {
    for (int i = 0; i < numRequests; i++) {
      try {
        ApiClient apiClient = new ApiClient();
        executeGet(api, url, apiClient);
        totalRequests.incrementAndGet();
        executePost(api, url, apiClient);
        totalRequests.incrementAndGet();
      } catch (Exception e) {
        System.out.println("send request error api= " + api + " url= " + url + " ex=" + e);
      }

    }
  }
  private static void executeGet(DefaultApi apiInstance, String url, ApiClient apiClient) {
    String albumID = "albumID_example"; // String | path  parameter is album key to retrieve
    apiInstance.setApiClient(apiClient);
    apiInstance.getApiClient().setBasePath(url);
    try {
      AlbumInfo result = apiInstance.getAlbumByKey(albumID);
      //System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getAlbumByKey" + e);
      //e.printStackTrace();
    }
  }

  private static void executePost(DefaultApi apiInstance, String url, ApiClient apiClient) {
    apiInstance.setApiClient(apiClient);
    apiInstance.getApiClient().setBasePath(url);
    File image = new File("/Users/Chelsea/Documents/CS6650/HW1/HW4a/post_pic.jpg"); // File |
    AlbumsProfile profile = new AlbumsProfile(); // AlbumsProfile |
    profile.setArtist("Chelsea");
    profile.setTitle("firework");
    profile.setYear("2025");
    try {
      ImageMetaData result = apiInstance.newAlbum(image, profile);
//      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#newAlbum" + e);
//      e.printStackTrace();
    }
  }
}
