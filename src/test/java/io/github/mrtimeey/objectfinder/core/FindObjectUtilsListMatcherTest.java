package io.github.mrtimeey.objectfinder.core;

import io.github.mrtimeey.objectfinder.common.TestDataFactory;
import io.github.mrtimeey.objectfinder.common.model.CalculationPart;
import io.github.mrtimeey.objectfinder.common.model.Information;
import io.github.mrtimeey.objectfinder.common.model.Offer;
import io.github.mrtimeey.objectfinder.type.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class FindObjectUtilsListMatcherTest {

   private Offer offer;

   @BeforeEach
   void setup() {
      offer = TestDataFactory.createOffer();
   }

   @Test
   void testFind_matchingStringInList() {
      Optional<CalculationPart> result = FindObjectUtils.find(offer, Pair.of("names[]", "specialDiscount"), CalculationPart.class);

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo("02f26e1b-e548-440d-8bfc-559d7c9fb1bd");
      assertThat(result.get().price()).isEqualTo(-5);
   }

   @Test
   void testFind_matchingNestedElementInList() {
      Optional<CalculationPart> result = FindObjectUtils.find(offer, Pair.of("properties[id]", "d9c40d29-e828-4c15-9519-29891496ec8e"), CalculationPart.class);

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo("02f26e1b-e548-440d-8bfc-559d7c9fb1bd");
      assertThat(result.get().price()).isEqualTo(-5);
   }

}