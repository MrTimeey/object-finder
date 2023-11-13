package io.github.mrtimeey.objectfinder.common.model;

import java.util.List;

public record Offer(String id, ProductBundle productBundle) {
   public static Offer of(String id, ProductBundle productBundle) {
      return new Offer(id, productBundle);
   }
}
