package io.github.mrtimeey.objectfinder.common.model;

public record Reference(Key key) {
   public static Reference of(Key key) {
      return new Reference(key);
   }
}
