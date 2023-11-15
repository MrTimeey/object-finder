package io.github.mrtimeey.objectfinder.core;

import io.github.mrtimeey.objectfinder.common.model.CalculationPart;
import io.github.mrtimeey.objectfinder.common.model.Offer;
import io.github.mrtimeey.objectfinder.common.model.ProductBundle;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static io.github.mrtimeey.objectfinder.common.TestDataFactory.createOffer;
import static org.assertj.core.api.Assertions.assertThat;

public class DocumentationTest {

   @Test
   void testClassicWay() {
      Offer offer = createOffer();
      Optional<CalculationPart> result = Optional.of(offer)
            .map(Offer::productBundle)
            .map(ProductBundle::products)
            .orElse(List.of())
            .stream()
            .flatMap(product -> product.calculationParts().stream())
            .filter(calculationPart -> "02f26e1b-e548-440d-8bfc-559d7c9fb1bd".equals(calculationPart.id()))
            .findFirst();

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo("02f26e1b-e548-440d-8bfc-559d7c9fb1bd");
   }
}
