package com.chelsea;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name ="AlbumServlet", value = "/albums/*" )
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class AlbumServlet extends HttpServlet {
  public static final AlbumInfo GET_RESPONSE;
  public static final ImageMetaData POST_RESPONSE;
  static {
    AlbumInfo albumInfo = new AlbumInfo();
    albumInfo.setYear("1997");
    albumInfo.setArtist("Sex Pistols");
    albumInfo.setTitle("Never Mind The Bollocks!");
    GET_RESPONSE = albumInfo;
    ImageMetaData imageMetaData = new ImageMetaData();
    imageMetaData.setAlbumID("123");
    imageMetaData.setImageSize("1024");
    POST_RESPONSE = imageMetaData;
  }

  public void init() throws ServletException {
    // Initialization
  }

  private final Gson gson = new Gson();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Extract the image part
    Part imagePart = request.getPart("image");
    if (imagePart == null || imagePart.getSize() == 0) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"Image file is required.\"}");
      return;
    }

    // Extract the profile JSON part
    Part profilePart = request.getPart("profile");
    if (profilePart == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"Profile information is required.\"}");
      return;
    }
    AlbumInfo info;
    try {
      info = gson.fromJson(new String(profilePart.getInputStream().readAllBytes()), AlbumInfo.class);
    } catch (JsonSyntaxException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"Invalid JSON format in profile.\"}");
      return;
    }

    if (info.getArtist() == null || info.getTitle() == null || info.getYear() == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"All fields (artist, title, year) are required.\"}");
      return;
    }
    // If validation passes, return a fixed album key
    response.setStatus(HttpServletResponse.SC_CREATED);
    response.setContentType("application/json");
    response.getWriter().write(gson.toJson(POST_RESPONSE));
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Extract albumKey from query parameter
    String pathInfo = request.getPathInfo(); // get "/{albumID}"

    if (pathInfo == null || pathInfo.equals("/")) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"Album ID is required.\"}");
      return;
    }
    String albumID = pathInfo.substring(1); // remove "/"

    if (albumID == null || albumID.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"Missing 'albumID' query parameter.\"}");
      return;
    }
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("application/json");
    response.getWriter().write(gson.toJson(GET_RESPONSE));
  }

  public void destroy() {
    // nothing to do here
  }
}