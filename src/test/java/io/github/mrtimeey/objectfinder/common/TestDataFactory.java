package io.github.mrtimeey.objectfinder.common;

import io.github.mrtimeey.objectfinder.common.model.Information;
import io.github.mrtimeey.objectfinder.common.model.ProductBundle;
import io.github.mrtimeey.objectfinder.common.model.CalculationPart;
import io.github.mrtimeey.objectfinder.common.model.Offer;
import io.github.mrtimeey.objectfinder.common.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public final class TestDataFactory {

   public void test() {
      Offer offer = createOffer();
      Optional<CalculationPart> result = Optional.ofNullable(offer)
            .map(Offer::productBundle)
            .map(ProductBundle::products)
            .orElse(List.of())
            .stream()
            .flatMap(product -> product.calculationParts().stream())
            .filter(calculationPart -> "02f26e1b-e548-440d-8bfc-559d7c9fb1bd".equals(calculationPart.id()))
            .findFirst();
   }

   public static Offer createOffer() {
      return Offer.of(
            "e97f6d09-ff23-41e8-94a2-f35f6a99eb9c",
            ProductBundle.of(
                  "886644d1-97b5-4698-b6ee-3ed18d352d95",
                  List.of(
                        Product.of(
                              "bdce24b8-d1ae-4fca-b993-e7d509129ada",
                              List.of(
                                    CalculationPart.of(
                                          "02f26e1b-e548-440d-8bfc-559d7c9fb1bd",
                                          Information.of("SPECIAL_DISCOUNT"),
                                          -5
                                    ),
                                    CalculationPart.of(
                                          "9f1f0368-29b8-40d7-a223-dfb672cc2077",
                                          Information.of("PRICE"),
                                          11
                                    )
                              )
                        ),
                        Product.of(
                              "92d7b2db-7ed5-4cfb-bc06-9622e3078e57\n",
                              List.of(
                                    CalculationPart.of(
                                          "9598a3af-9935-442c-b5d1-ae280e726b00",
                                          Information.of("SPECIAL_DISCOUNT"),
                                          -10
                                    ),
                                    CalculationPart.of(
                                          "8301d426-2b8e-4b10-b4a2-1781ea7c1ad5",
                                          Information.of("PRICE"),
                                          42
                                    )
                              )
                        )
                  )
            )
      );
   }
}
