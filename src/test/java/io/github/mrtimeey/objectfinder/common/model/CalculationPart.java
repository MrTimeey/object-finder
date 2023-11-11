package io.github.mrtimeey.objectfinder.common.model;

import java.math.BigDecimal;
import java.util.List;

public record CalculationPart(Identifier id, Metadata metadata, BigDecimal price, List<Property> properties) {
   public static CalculationPart of(Identifier id, Metadata metadata, BigDecimal price, List<Property> properties) {
      return new CalculationPart(id, metadata, price, properties);
   }
}
