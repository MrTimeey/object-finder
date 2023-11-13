package io.github.mrtimeey.objectfinder.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.sfrick.jcontrols.Try;

import java.util.Optional;

final class JsNodeUtils {

  private JsNodeUtils() {
     throw new IllegalStateException("Do not instantiate this class");
  }

  static Optional<JsonNode> toJsNode(Object root, ObjectMapper om) {
    if (root instanceof JsonNode n) {
      return Optional.of(n);
    }
    return Optional.ofNullable(root)
        .flatMap(
            o ->
                Try.<JsonNode>of(() -> om.valueToTree(root))
                    .toOptional()
                    .flatMap(
                        node -> {
                          if (node.isNull()) {
                            return Optional.<JsonNode>empty();
                          } else {
                            return Optional.<JsonNode>of(node);
                          }
                        }));
  }

  static <T> Optional<T> toClass(JsonNode root, Class<T> type, ObjectMapper om) {
    return Try.of(() -> om.convertValue(root, type)).toOptional();
  }
}
