package io.github.mrtimeey.objectfinder.core.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public final class ObjectMapperFactory {

   private ObjectMapperFactory() {
      // Hide default constructor
   }

   public static ObjectMapper objectMapper() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new Jdk8Module());
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      mapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true);
      return mapper;
   }

}
