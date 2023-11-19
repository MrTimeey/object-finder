package io.github.mrtimeey.objectfinder.common.model;

public record Information(String key) {
   public static Information of(String key) {
      return new Information(key);
   }
}
