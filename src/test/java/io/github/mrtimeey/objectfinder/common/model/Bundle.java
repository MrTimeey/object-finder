package io.github.mrtimeey.objectfinder.common.model;

import java.util.List;

public record Bundle(Identifier id, List<Product> products, List<Property> properties) {
   public static Bundle of(Identifier id, List<Product> products, List<Property> properties) {
      return new Bundle(id, products, properties);
   }
}
