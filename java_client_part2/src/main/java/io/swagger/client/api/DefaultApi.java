/*
 * Album Store API
 * CS6650 Fall 2023
 *
 * OpenAPI spec version: 1.0.0
 * Contact: i.gorton@northeasern.edu
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import com.google.gson.Gson;
import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.swagger.client.model.AlbumInfo;
import io.swagger.client.model.AlbumsProfile;
import io.swagger.client.model.ErrorMsg;
import java.io.File;
import io.swagger.client.model.ImageMetaData;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultApi {
    private ApiClient apiClient;
    private Map<String, String> headers;
    private Gson gson = new Gson();

    public DefaultApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DefaultApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void setHeadersOverrides(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Build call for getAlbumByKey
     * @param albumID path  parameter is album key to retrieve (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getAlbumByKeyCall(String albumID, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/albums/{albumID}"
            .replaceAll("\\{" + "albumID" + "\\}", apiClient.escapeString(albumID.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        if (headers != null) {
            localVarHeaderParams.putAll(headers);
        }
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getAlbumByKeyValidateBeforeCall(String albumID, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'albumID' is set
        if (albumID == null) {
            throw new ApiException("Missing the required parameter 'albumID' when calling getAlbumByKey(Async)");
        }
        
        com.squareup.okhttp.Call call = getAlbumByKeyCall(albumID, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * get album by key
     * 
     * @param albumID path  parameter is album key to retrieve (required)
     * @return AlbumInfo
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public AlbumInfo getAlbumByKey(String albumID) throws ApiException {
        ApiResponse<AlbumInfo> resp = getAlbumByKeyWithHttpInfo(albumID);
        return resp.getData();
    }

    /**
     * get album by key
     * 
     * @param albumID path  parameter is album key to retrieve (required)
     * @return ApiResponse&lt;AlbumInfo&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<AlbumInfo> getAlbumByKeyWithHttpInfo(String albumID) throws ApiException {
        com.squareup.okhttp.Call call = getAlbumByKeyValidateBeforeCall(albumID, null, null);
        Type localVarReturnType = new TypeToken<AlbumInfo>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * get album by key (asynchronously)
     * 
     * @param albumID path  parameter is album key to retrieve (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getAlbumByKeyAsync(String albumID, final ApiCallback<AlbumInfo> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getAlbumByKeyValidateBeforeCall(albumID, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<AlbumInfo>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for newAlbum
     * @param image  (required)
     * @param profile  (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call newAlbumCall(File image, AlbumsProfile profile, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/albums";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (image != null)
        localVarFormParams.put("image", image);
        if (profile != null)
        localVarFormParams.put("profile", gson.toJson(profile));

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        if (headers != null) {
            localVarHeaderParams.putAll(headers);
        }
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call newAlbumValidateBeforeCall(File image, AlbumsProfile profile, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'image' is set
        if (image == null) {
            throw new ApiException("Missing the required parameter 'image' when calling newAlbum(Async)");
        }
        // verify the required parameter 'profile' is set
        if (profile == null) {
            throw new ApiException("Missing the required parameter 'profile' when calling newAlbum(Async)");
        }
        
        com.squareup.okhttp.Call call = newAlbumCall(image, profile, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Returns the new key and size of an image in bytes.
     * 
     * @param image  (required)
     * @param profile  (required)
     * @return ImageMetaData
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ImageMetaData newAlbum(File image, AlbumsProfile profile) throws ApiException {
        ApiResponse<ImageMetaData> resp = newAlbumWithHttpInfo(image, profile);
        return resp.getData();
    }

    /**
     * Returns the new key and size of an image in bytes.
     * 
     * @param image  (required)
     * @param profile  (required)
     * @return ApiResponse&lt;ImageMetaData&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ImageMetaData> newAlbumWithHttpInfo(File image, AlbumsProfile profile) throws ApiException {
        com.squareup.okhttp.Call call = newAlbumValidateBeforeCall(image, profile, null, null);
        Type localVarReturnType = new TypeToken<ImageMetaData>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Returns the new key and size of an image in bytes. (asynchronously)
     * 
     * @param image  (required)
     * @param profile  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call newAlbumAsync(File image, AlbumsProfile profile, final ApiCallback<ImageMetaData> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = newAlbumValidateBeforeCall(image, profile, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ImageMetaData>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
