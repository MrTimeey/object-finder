package io.github.mrtimeey.objectfinder.common.model;

import java.util.List;

public record Product(String id, List<CalculationPart> calculationParts) {
   public static Product of(String id, List<CalculationPart> calculationParts) {
      return new Product(id, calculationParts);
   }
}
