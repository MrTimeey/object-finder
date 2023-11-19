package io.github.mrtimeey.objectfinder.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class PathConversionUtils {

   private PathConversionUtils() {
      throw new IllegalStateException("Do not instantiate this class");
   }

   static Path convert(String providedPath) {
      Pattern pattern = Pattern.compile("(.*)\\[(.*)\\]");
      if (providedPath != null) {
         Matcher matcher = pattern.matcher(providedPath);
         if (matcher.matches()) {
            return Path.of(convertString(matcher.group(1)), convertString(matcher.group(2)), true);
         }
      }
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

   record Path(String path, String nestedPath, boolean isArray) {
      Path(String path) {
         this(path, "", false);
      }

      static Path of(String path, String nestedPath, boolean isArray){
         return new Path(path, nestedPath, isArray);
      }
      static Path of(String path){
         return new Path(path);
      }

      boolean isEmpty() {
         return path.isEmpty();
      }
   }

}
