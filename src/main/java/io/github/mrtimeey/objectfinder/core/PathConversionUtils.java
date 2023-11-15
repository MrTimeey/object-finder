package io.github.mrtimeey.objectfinder.core;

import io.github.mrtimeey.objectfinder.type.Path;

final class PathConversionUtils {

   private PathConversionUtils() {
      throw new IllegalStateException("Do not instantiate this class");
   }

   static Path convert(String providedPath) {
      String stringPath = convertString(providedPath);
      return Path.of(stringPath);
   }

   private static String convertString(String providedPath) {
      if (providedPath == null || providedPath.isEmpty()) {
         return "";
      }
      if (providedPath.contains("/") && !providedPath.contains(".")) {
         if (!providedPath.startsWith("/")) {
            return String.format("/%s", providedPath);
         }
         return providedPath;
      }
      if (providedPath.contains(".")) {
         String convertedPath = providedPath.replace(".", "/");
         if (!convertedPath.startsWith("/")) {
            return String.format("/%s", convertedPath);
         }
         return convertedPath;
      }
      return String.format("/%s", providedPath);
   }

   record Path(String path) {
      static Path of(String path){
         return new Path(path);
      }

      boolean isEmpty() {
         return path.isEmpty();
      }
   }

}
