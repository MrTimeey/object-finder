package io.github.mrtimeey.objectfinder.core;

final class PathConversionUtils {

   private PathConversionUtils() {
      throw new IllegalStateException("Do not instantiate this class");
   }

   static String convert(String providedPath) {
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

}
