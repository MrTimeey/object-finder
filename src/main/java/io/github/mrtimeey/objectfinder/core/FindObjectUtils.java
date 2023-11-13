package io.github.mrtimeey.objectfinder.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mrtimeey.objectfinder.type.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class to find specific objects in complex data structures.
 */
public final class FindObjectUtils {

   private FindObjectUtils() {
      throw new IllegalStateException("Do not instantiate this class");
   }

   private static final ObjectMapper om = ObjectMapperFactory.objectMapper();

   /**
    * Searches for tall occurrence (top down) of provided search pattern. <br>
    * Left side of search pattern can be a string or a json pointer. <br>
    * <br>
    * Search pattern examples:<br>
    * <pre>{@code
    *  Pair.of("clientId", Identifier.of("uuid");
    *  Pair.of("clientId.value", "uuid");
    *  Pair.of("/information/metadata/key", "InsuranceStart");
    *  Pair.of(".metadata.key", "X_FREISCHUSS");
    *  }</pre>
    *
    * @param base          Base {@link Object} to traverse
    * @param searchPattern The pattern to identify the searched object
    * @param toClazz       The searched {@link Class} type
    * @param <T>           Target object type
    * @return The serialized object wrapped in an {@link Optional} or {@link Optional#empty()}
    */
   public static <T> List<T> findAll(Object base, Pair<String, Object> searchPattern, Class<T> toClazz) {
      return internalFind(base, searchPattern, toClazz, true);
   }

   /**
    * Searches for the first occurrence (top down) of provided search pattern. <br>
    * Left side of search pattern can be a string or a json pointer. <br>
    * <br>
    * Search pattern examples:<br>
    * <pre>{@code
    *  Pair.of("clientId", Identifier.of("uuid");
    *  Pair.of("clientId.value", "uuid");
    *  Pair.of("/information/metadata/key", "InsuranceStart");
    *  Pair.of(".metadata.key", "X_FREISCHUSS");
    *  }</pre>
    *
    * @param base          Base {@link Object} to traverse
    * @param searchPattern The pattern to identify the searched object
    * @param toClazz       The searched {@link Class} type
    * @param <T>           Target object type
    * @return The serialized object wrapped in an {@link Optional} or {@link Optional#empty()}
    */
   public static <T> Optional<T> find(Object base, Pair<String, Object> searchPattern, Class<T> toClazz) {
      List<T> result = internalFind(base, searchPattern, toClazz, false);
      return Optional.of(result).filter(l -> !l.isEmpty()).map(l -> l.get(0));
   }

   private static <T> List<T> internalFind(Object base, Pair<String, Object> searchedPathValue, Class<T> toClazz, boolean findAll) {
      Deque<JsonNode> stack = new ArrayDeque<>();
      List<T> result = new LinkedList<>();
      JsonNode baseRoot = JsNodeUtils.toJsNode(base, om).orElseThrow();
      String jsonPointer = PathConversionUtils.convert(searchedPathValue.first());
      if (jsonPointer.isEmpty()) return result;
      JsonNode searchedNode = JsNodeUtils.toJsNode(searchedPathValue.second(), om).orElseThrow();
      stack.push(baseRoot);
      while (!stack.isEmpty()) {
         JsonNode temp = stack.pop();
         if (temp.isObject()) {
            Optional<T> matchingObject = getMatchingObject(toClazz, temp, jsonPointer, searchedNode);
            matchingObject.ifPresent(result::add);
            if (!result.isEmpty() && !findAll) {
               return result;
            }
            toMap(temp.fields()).values().stream().filter(n -> n.isObject() || n.isArray()).forEach(stack::push);
         }
         if (temp.isArray()) {
            stack.addAll(toList(temp.elements()));
         }
      }
      return result;
   }

   private static <T> Optional<T> getMatchingObject(Class<T> toClazz, JsonNode temp, String jsonPointer, JsonNode searchedNode) {
      JsonNode valueNode = temp.at(jsonPointer);
      if (!valueNode.isMissingNode() && !valueNode.isNull() && searchedNode.equals(valueNode)) {
         T elem = JsNodeUtils.toClass(temp, toClazz, om).orElseThrow();
         return Optional.of(elem);
      }
      return Optional.empty();
   }

   private static List<JsonNode> toList(Iterator<JsonNode> it) {
      List<JsonNode> xs = new ArrayList<>();
      it.forEachRemaining(xs::add);
      return xs;
   }

   private static Map<String, JsonNode> toMap(Iterator<Map.Entry<String, JsonNode>> it) {
      Map<String, JsonNode> res = new HashMap<>();
      it.forEachRemaining(e -> res.put(e.getKey(), e.getValue()));
      return res;
   }

}
