package io.github.mrtimeey.objectfinder.core;

import io.github.mrtimeey.objectfinder.common.model.CalculationPart;
import io.github.mrtimeey.objectfinder.common.model.Identifier;
import io.github.mrtimeey.objectfinder.common.model.Offer;
import io.github.mrtimeey.objectfinder.common.model.Property;
import io.github.mrtimeey.objectfinder.common.TestDataFactory;
import io.github.mrtimeey.objectfinder.core.core.FindObjectUtils;
import io.github.mrtimeey.objectfinder.core.type.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
   void testFind_withIdentifier() {
      Optional<CalculationPart> result = FindObjectUtils.find(offer, Pair.of("id", Identifier.of("firstProductId_firstCalculationPart")), CalculationPart.class);

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo(Identifier.of("firstProductId_firstCalculationPart"));
      assertThat(result.get().price()).isEqualTo(BigDecimal.valueOf(11));
   }

   @Test
   void testFind_withIdentifier_valuePath() {
      Optional<CalculationPart> result = FindObjectUtils.find(offer, Pair.of("/id/value", "firstProductId_firstCalculationPart"), CalculationPart.class);

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo(Identifier.of("firstProductId_firstCalculationPart"));
      assertThat(result.get().price()).isEqualTo(BigDecimal.valueOf(11));
   }

   @Test
   void testFind_withDotPath() {
      Optional<Property> result = FindObjectUtils.find(offer, Pair.of("metadata.reference.key.value", "D"), Property.class);

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo(Identifier.of("firstProductId_firstProp"));
      assertThat(result.get().value()).isEqualTo("Lorem ipsum dolor sit amet");
   }

   @Test
   void testFind_withMixedPath() {
      Optional<Property> result = FindObjectUtils.find(offer, Pair.of("metadata/reference.key.value", "F"), Property.class);

      assertThat(result).isPresent();
      assertThat(result.get().id()).isEqualTo(Identifier.of("offerId_firstProp"));
      assertThat(result.get().value()).isEqualTo("Lorem ipsum dolor sit amet");
   }

   @Test
   void testFind_withoutMatch() {
      Optional<Property> result = FindObjectUtils.find(offer, Pair.of("/metadata/reference/key/value", "BULLSHIT_KEY"), Property.class);

      assertThat(result).isEmpty();
   }

   @Test
   void testFindAll() {
      List<CalculationPart> result = FindObjectUtils.findAll(offer, Pair.of("/metadata/reference/key/value", "RISK_ADDITION"), CalculationPart.class);

      assertThat(result).hasSize(2);
      assertThat(result.get(0).id()).isEqualTo(Identifier.of("firstProductId_secondCalculationPart"));
      assertThat(result.get(1).id()).isEqualTo(Identifier.of("secondProductId_secondCalculationPart"));
   }

   @Test
   void testFindAll_withoutMatch() {
      List<CalculationPart> result = FindObjectUtils.findAll(offer, Pair.of("/metadata/reference/key/value", "BULLSHIT_KEY"), CalculationPart.class);

      assertThat(result).isEmpty();
   }

}