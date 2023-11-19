package io.github.mrtimeey.objectfinder.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

final class ObjectMapperFactory {

   private ObjectMapperFactory() {
      throw new IllegalStateException("Do not instantiate this class");
   }

   static ObjectMapper objectMapper() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new Jdk8Module());
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      mapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true);
      return mapper;
   }

}
