package io.github.mrtimeey.objectfinder.common;

import io.github.mrtimeey.objectfinder.common.model.Bundle;
import io.github.mrtimeey.objectfinder.common.model.CalculationPart;
import io.github.mrtimeey.objectfinder.common.model.Identifier;
import io.github.mrtimeey.objectfinder.common.model.Key;
import io.github.mrtimeey.objectfinder.common.model.Metadata;
import io.github.mrtimeey.objectfinder.common.model.Offer;
import io.github.mrtimeey.objectfinder.common.model.Product;
import io.github.mrtimeey.objectfinder.common.model.Property;
import io.github.mrtimeey.objectfinder.common.model.Reference;

import java.math.BigDecimal;
import java.util.List;

public final class TestDataFactory {

   public static Offer createOffer() {
      return Offer.of(
            Identifier.of("offerId"),
            Identifier.of("offerNumber"),
            Bundle.of(
                  Identifier.of("bundleId"),
                  List.of(
                        Product.of(
                              Identifier.of("firstProductId"),
                              List.of(
                                    CalculationPart.of(
                                          Identifier.of("firstProductId_firstCalculationPart"),
                                          Metadata.of(Reference.of(Key.of("PRICE"))),
                                          BigDecimal.valueOf(11),
                                          List.of(
                                                Property.of(
                                                      Identifier.of("firstProductId_firstCalculationPart_firstProp"),
                                                      Metadata.of(Reference.of(Key.of("A"))),
                                                      "Prop One",
                                                      "Lorem ipsum dolor sit amet"
                                                ),
                                                Property.of(
                                                      Identifier.of("firstProductId_firstCalculationPart_secondProp"),
                                                      Metadata.of(Reference.of(Key.of("B"))),
                                                      "Prop Two",
                                                      "Lorem ipsum dolor sit amet"
                                                )
                                          )
                                    ),
                                    CalculationPart.of(
                                          Identifier.of("firstProductId_secondCalculationPart"),
                                          Metadata.of(Reference.of(Key.of("RISK_ADDITION"))),
                                          BigDecimal.valueOf(5),
                                          List.of(
                                                Property.of(
                                                      Identifier.of("firstProductId_secondCalculationPart_firstProp"),
                                                      Metadata.of(Reference.of(Key.of("C"))),
                                                      "Prop One",
                                                      "Lorem ipsum dolor sit amet"
                                                )
                                          )
                                    )
                              ),
                              List.of(
                                    Property.of(
                                          Identifier.of("firstProductId_firstProp"),
                                          Metadata.of(Reference.of(Key.of("D"))),
                                          "Prop One",
                                          "Lorem ipsum dolor sit amet"
                                    )
                              )
                        ),
                        Product.of(
                              Identifier.of("secondProductId"),
                              List.of(
                                    CalculationPart.of(
                                          Identifier.of("secondProductId_firstCalculationPart"),
                                          Metadata.of(Reference.of(Key.of("PRICE"))),
                                          BigDecimal.valueOf(155),
                                          List.of()
                                    ),
                                    CalculationPart.of(
                                          Identifier.of("secondProductId_secondCalculationPart"),
                                          Metadata.of(Reference.of(Key.of("RISK_ADDITION"))),
                                          BigDecimal.valueOf(42),
                                          List.of()
                                    )
                              ),
                              List.of()
                        )
                  ),
                  List.of()),
            List.of(
                  Property.of(
                        Identifier.of("offerId_firstProp"),
                        Metadata.of(Reference.of(Key.of("F"))),
                        "Prop Two",
                        "Lorem ipsum dolor sit amet"
                  )));
   }
}
