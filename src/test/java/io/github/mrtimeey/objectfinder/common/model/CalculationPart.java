package io.github.mrtimeey.objectfinder.common.model;

import java.math.BigDecimal;
import java.util.List;

public record CalculationPart(String id, Information information, Integer price) {
   public static CalculationPart of(String id, Information information, Integer price) {
      return new CalculationPart(id, information, price);
   }
}
