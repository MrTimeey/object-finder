package io.github.mrtimeey.objectfinder.common.model;

import java.util.List;

public record Offer(Identifier id, Identifier offerNumber, Bundle bundle, List<Property> properties) {
   public static Offer of(Identifier id, Identifier offerNumber, Bundle bundle, List<Property> properties) {
      return new Offer(id, offerNumber, bundle, properties);
   }
}
