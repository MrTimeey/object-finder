package io.github.mrtimeey.objectfinder.core;

import io.github.mrtimeey.objectfinder.core.PathConversionUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class PathConversionUtilsTest {

   @ParameterizedTest(name = "Convert method for value {0} should return {1}")
   @MethodSource("pathData")
   void testValidData(String input, String expectedOutput) {
      Assertions.assertThat(PathConversionUtils.convert(input)).isEqualTo(expectedOutput);
   }

   private static Object[][] pathData() {
      return new Object[][] {
            {"id", "/id"},
            {"/id", "/id"},
            {".id", "/id"},
            {"metadata/reference", "/metadata/reference"},
            {"/metadata/reference", "/metadata/reference"},
            {"metadata.reference", "/metadata/reference"},
            {".metadata.reference", "/metadata/reference"},
            {".metadata/reference", "/metadata/reference"},
            {"/metadata.reference", "/metadata/reference"},
            {"", ""},
            {null, ""},
      };
   }

}