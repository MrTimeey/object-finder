package io.github.mrtimeey.objectfinder.common.model;

import java.util.List;

public record CalculationPart(String id, Information information, Integer price, List<String> names, List<Property> properties) {
   public static CalculationPart of(String id, Information information, Integer price, List<String> names, List<Property> properties) {
      return new CalculationPart(id, information, price, names, properties);
   }
   public static CalculationPart of(String id, Information information, Integer price) {
      return new CalculationPart(id, information, price, List.of(), List.of());
   }
}
