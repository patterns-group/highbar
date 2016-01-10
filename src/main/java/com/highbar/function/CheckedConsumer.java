package com.highbar.function;

import java.util.Objects;

/**
 * Defines a consumer that checks an exception.
 */
@FunctionalInterface
public interface CheckedConsumer<T> {
  /**
   * Accepts the provided data.
   * @param data The data to accept.
   * @throws Exception
   */
  void accept(T data) throws Exception;

  /**
   * Returns a composed {@code CheckedConsumer} that performs, in sequence, this
   * operation followed by the {@code after} operation. If performing either
   * operation throws an exception, it is relayed to the caller of the
   * composed operation.  If performing this operation throws an exception,
   * the {@code after} operation will not be performed.
   *
   * @param after the operation to perform after this operation
   * @return a composed {@code CheckedConsumer} that performs in sequence this
   * operation followed by the {@code after} operation
   * @throws NullPointerException if {@code after} is null
   */
  default CheckedConsumer<T> andThen(CheckedConsumer<? super T> after) {
    Objects.requireNonNull(after);

    return (T t) -> {
      accept(t);
      after.accept(t);
    };
  }
}
