package io.github.mrtimeey.objectfinder.common.model;

public record Property(String id) {
   public static Property of(String id) {
      return new Property(id);
   }
}
