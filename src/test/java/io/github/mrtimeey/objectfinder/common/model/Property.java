package io.github.mrtimeey.objectfinder.common.model;

public record Property(Identifier id, Metadata metadata, String label, String value) {
   public static Property of(Identifier id, Metadata metadata, String label, String value) {
      return new Property(id, metadata, label, value);
   }
}
