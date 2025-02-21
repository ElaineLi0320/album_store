package com.chelsea;

import lombok.Data;

/**
 * Created by Chelsea on 2025-02-06
 * {
 *   "artist": "Sex Pistols",
 *   "title": "Never Mind The Bollocks!",
 *   "year": "1977"
 * }
 */
@Data
public class AlbumInfo {
  String artist;
  String title;
  String year;
}
