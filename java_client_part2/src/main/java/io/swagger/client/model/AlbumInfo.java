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

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * AlbumInfo
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2025-02-07T04:54:14.600684144Z[GMT]")

public class AlbumInfo {
  @SerializedName("artist")
  private String artist = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("year")
  private String year = null;

  public AlbumInfo artist(String artist) {
    this.artist = artist;
    return this;
  }

   /**
   * album performers
   * @return artist
  **/
  @Schema(example = "Sex Pistols", description = "album performers")
  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public AlbumInfo title(String title) {
    this.title = title;
    return this;
  }

   /**
   * album title
   * @return title
  **/
  @Schema(example = "Never Mind The Bollocks!", description = "album title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public AlbumInfo year(String year) {
    this.year = year;
    return this;
  }

   /**
   * year released
   * @return year
  **/
  @Schema(example = "1977", description = "year released")
  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlbumInfo albumInfo = (AlbumInfo) o;
    return Objects.equals(this.artist, albumInfo.artist) &&
        Objects.equals(this.title, albumInfo.title) &&
        Objects.equals(this.year, albumInfo.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artist, title, year);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlbumInfo {\n");
    
    sb.append("    artist: ").append(toIndentedString(artist)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
