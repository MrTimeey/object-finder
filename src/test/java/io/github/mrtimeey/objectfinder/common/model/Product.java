package io.github.mrtimeey.objectfinder.common.model;

import java.util.List;

public record Product(Identifier id, List<CalculationPart> calculationParts, List<Property> properties) {
   public static Product of(Identifier id, List<CalculationPart> calculationParts, List<Property> properties) {
      return new Product(id, calculationParts, properties);
   }
}
