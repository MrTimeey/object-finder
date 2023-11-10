package io.github.mrtimeey.objectfinder.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mrtimeey.objectfinder.core.core.ObjectMapperFactory;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class FindObjectUtilsTest {

   private Object offer;
   private final ObjectMapper om = ObjectMapperFactory.objectMapper();

   @BeforeEach
   void setup() throws IOException {
      try (InputStream resource = this.getClass().getResourceAsStream("offer.json")) {
         String inputObject = IOUtils.toString(resource, StandardCharsets.UTF_8);
         offer = om.readValue(inputObject, Object.class);
      }
   }

   @Test
   void testFind_withIdentifier() {
      assertThat(true).isTrue();
   }

}