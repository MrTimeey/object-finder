package io.github.mrtimeey.objectfinder.common.model;

import java.util.List;

public record ProductBundle(String id, List<Product> products) {
   public static ProductBundle of(String id, List<Product> products) {
      return new ProductBundle(id, products);
   }
}
