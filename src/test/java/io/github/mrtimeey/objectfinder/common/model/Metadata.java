package io.github.mrtimeey.objectfinder.common.model;

public record Metadata(Reference reference) {
   public static Metadata of(Reference reference) {
      return new Metadata(reference);
   }
}
