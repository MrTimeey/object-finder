package io.github.mrtimeey.objectfinder.core.type;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public sealed interface Try<A> permits Try.Failure, Try.Success {
  static <A> Try<A> success(A value) {
    return new Success<>(value);
  }

  static <A> Try<A> failure(Throwable cause) {
    return new Failure<>(cause);
  }

  static <A> Try<A> of(Supplier<A> supplier) {
    Objects.requireNonNull(supplier);
    try {
      return new Success<>(supplier.get());
    } catch (Throwable cause) {
      return new Failure<>(cause);
    }
  }

  boolean isFailure();

  default boolean isSuccess() {
    return !isFailure();
  }

  Optional<A> toOptional();

  record Success<T>(T value) implements Try<T> {

    @Override
    public boolean isFailure() {
      return false;
    }

    @Override
    public Optional<T> toOptional() {
      return Optional.of(value());
    }
  }

  record Failure<T>(Throwable cause) implements Try<T> {

    @Override
    public boolean isFailure() {
      return true;
    }

    @Override
    public Optional<T> toOptional() {
      return Optional.empty();
    }
  }
}
