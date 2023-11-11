package io.github.mrtimeey.objectfinder.common.model;

public record Identifier(String value) {
   public static Identifier of(String value) {
      return new Identifier(value);
   }
}
