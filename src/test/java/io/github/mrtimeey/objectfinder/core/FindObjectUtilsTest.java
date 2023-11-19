package io.github.mrtimeey.objectfinder.core;

import io.github.mrtimeey.objectfinder.common.TestDataFactory;
import io.github.mrtimeey.objectfinder.common.model.CalculationPart;
import io.github.mrtimeey.objectfinder.common.model.Information;
import io.github.mrtimeey.objectfinder.common.model.Offer;
import io.github.mrtimeey.objectfinder.core.FindObjectUtils;
import io.github.mrtimeey.objectfinder.type.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class FindObjectUtilsTest {

   private Offer offer;

   @BeforeEach
   void setup() {
      offer = TestDataFactory.createOffer();
   }

   @Test
   void testFind() {
      Optional<CalculationPart> result = FindObjectUtils.find(offer, Pair.of("id", "02f26e1b-e548-440d-8bfc-559d7c9fb1bd"), CalculationPart.class);

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo("02f26e1b-e548-440d-8bfc-559d7c9fb1bd");
      assertThat(result.get().price()).isEqualTo(-5);
   }

   @Test
   void testFind_withNestedPath() {
      Optional<CalculationPart> result = FindObjectUtils.find(offer, Pair.of("/information/key", "SPECIAL_DISCOUNT"), CalculationPart.class);

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo("02f26e1b-e548-440d-8bfc-559d7c9fb1bd");
      assertThat(result.get().price()).isEqualTo(-5);
   }

   @Test
   void testFind_withObject() {
      Optional<CalculationPart> result = FindObjectUtils.find(offer, Pair.of("/information", Information.of("SPECIAL_DISCOUNT")), CalculationPart.class);

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo("02f26e1b-e548-440d-8bfc-559d7c9fb1bd");
      assertThat(result.get().price()).isEqualTo(-5);
   }

   @Test
   void testFind_withoutMatch() {
      Optional<CalculationPart> result = FindObjectUtils.find(offer, Pair.of("/information/key", "BULLSHIT_KEY"), CalculationPart.class);

      assertThat(result).isEmpty();
   }

   @Test
   void testFindAll() {
      List<CalculationPart> result = FindObjectUtils.findAll(offer, Pair.of("/information/key", "SPECIAL_DISCOUNT"), CalculationPart.class);

      assertThat(result).hasSize(2);
      assertThat(result.get(0).id()).isEqualTo("02f26e1b-e548-440d-8bfc-559d7c9fb1bd");
      assertThat(result.get(1).id()).isEqualTo("9598a3af-9935-442c-b5d1-ae280e726b00");
   }

   @Test
   void testFindAll_withoutMatch() {
      List<CalculationPart> result = FindObjectUtils.findAll(offer, Pair.of("/metadata/reference/key/value", "BULLSHIT_KEY"), CalculationPart.class);

      assertThat(result).isEmpty();
   }

}