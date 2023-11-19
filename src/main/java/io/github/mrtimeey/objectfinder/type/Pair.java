package io.github.mrtimeey.objectfinder.type;

public record Pair<T, R>(T first, R second) {
   public static <T, R> Pair<T, R> of(T first, R second) {
      return new Pair<>(first, second);
   }

}
