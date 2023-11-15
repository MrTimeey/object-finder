package io.github.mrtimeey.objectfinder.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PathConversionUtilsTest {

   @ParameterizedTest(name = "Convert method for value {0} should return {1}")
   @MethodSource("pathData")
   void testValidData(String input, String expectedPath, boolean shouldBeArray) {
      PathConversionUtils.Path result = PathConversionUtils.convert(input);
      Assertions.assertThat(result.path()).isEqualTo(expectedPath);
      Assertions.assertThat(result.isArray()).isEqualTo(shouldBeArray);
   }

   private static Object[][] pathData() {
      return new Object[][] {
            {"id", "/id", false},
            {"/id", "/id", false},
            {".id", "/id", false},
            {"metadata/reference", "/metadata/reference", false},
            {"/metadata/reference", "/metadata/reference", false},
            {"metadata.reference", "/metadata/reference", false},
            {".metadata.reference", "/metadata/reference", false},
            {".metadata/reference", "/metadata/reference", false},
            {"/metadata.reference", "/metadata/reference", false},
            {"", "", false},
            {null, "", false},
            {"id[]", "/id", true},
            {"id[id]", "/id", true},
      };
   }

}