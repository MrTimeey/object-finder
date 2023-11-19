package io.github.mrtimeey.objectfinder.core;

import io.github.mrtimeey.objectfinder.common.model.CalculationPart;
import io.github.mrtimeey.objectfinder.common.model.Offer;
import io.github.mrtimeey.objectfinder.common.model.ProductBundle;
import io.github.mrtimeey.objectfinder.type.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static io.github.mrtimeey.objectfinder.common.TestDataFactory.createOffer;
import static org.assertj.core.api.Assertions.assertThat;

public class DocExamples {

   private Offer baseObject;

   @BeforeEach
   void setup() {
      baseObject = createOffer();
   }

   @Test
   void classicWay() {
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

   @Test
   void getObjectByField() {
      Pair<String, Object> location = Pair.of("id", "fancyId");
      Optional<CalculationPart> result = FindObjectUtils.find(baseObject, location, CalculationPart.class);
      assertThat(result).isPresent();
   }

   @Test
   void getObjectByNestedObjectValue() {
      Pair<String, Object> location = Pair.of("metadata/reference/key", "nestedKey");
      Optional<CalculationPart> result = FindObjectUtils.find(baseObject, location, CalculationPart.class);
      assertThat(result).isPresent();
   }

   @Test
   void getObjectByStringInList() {
      Pair<String, Object> location = Pair.of("names[]", "specialDiscount");
      Optional<CalculationPart> result = FindObjectUtils.find(baseObject, location, CalculationPart.class);
      assertThat(result).isPresent();
   }

   @Test
   void getObjectByNestedObjectValueInList() {
      Pair<String, Object> location = Pair.of("names[id]", "d9c40d29-e828-4c15-9519-29891496ec8e");
      Optional<CalculationPart> result = FindObjectUtils.find(baseObject, location, CalculationPart.class);
      assertThat(result).isPresent();
   }

   @Test
   void getAllObjectsByNestedObjectValue() {
      Pair<String, Object> location = Pair.of("metadata/reference/key", "nestedKey");
      List<CalculationPart> result = FindObjectUtils.findAll(baseObject, location, CalculationPart.class);
      assertThat(result).hasSize(1);
   }

}
