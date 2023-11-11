package io.github.mrtimeey.objectfinder.common.model;

public record Key(String value) {
   public static Key of(String value) {
      return new Key(value);
   }
}
