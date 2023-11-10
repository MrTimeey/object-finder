package io.github.mrtimeey.objectfinder.core.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ObjectMapperFactory {

   private ObjectMapperFactory() {
      // Hide default constructor
   }

   /**
    * Default {@link ObjectMapper} for handling the domain model.
    *
    * @return The configured {@link ObjectMapper}
    */
   public static ObjectMapper objectMapper() {
      ObjectMapper mapper = new ObjectMapper();
      enableLocalDateMapping(mapper);
      enableOptionalMapping(mapper);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return mapper;
   }

   private static void enableOptionalMapping(ObjectMapper mapper) {
      mapper.registerModule(new Jdk8Module());
   }

   private static void enableLocalDateMapping(ObjectMapper mapper) {
      JavaTimeModule javaTimeModule = new JavaTimeModule();
      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm:ss");
      javaTimeModule.addSerializer(new LocalDateSerializer(dateFormatter));
      javaTimeModule.addSerializer(new LocalDateTimeSerializer(dateTimeFormatter));
      javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
      javaTimeModule.addDeserializer(
            LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
      mapper.registerModule(javaTimeModule);
   }
}
