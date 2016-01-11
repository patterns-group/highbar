package com.highbar.function;

import java.util.Objects;

/**
 * Defines a function that checks an exception.
 */
@FunctionalInterface
public interface CheckedFunction<I, O> {
  /**
   * Applies this function to the given argument.
   *
   * @param input the function argument
   * @return the function result
   */
  O apply(I input) throws Exception;

  /**
   * Returns a composed function that first applies the {@code before}
   * function to its input, and then applies this function to the result.
   * If evaluation of either function throws an exception, it is relayed to
   * the caller of the composed function.
   *
   * @param <V> the type of input to the {@code before} function, and to the
   *           composed function
   * @param before the function to apply before this function is applied
   * @return a composed function that first applies the {@code before}
   * function and then applies this function
   * @throws NullPointerException if before is null
   *
   * @see #andThen(CheckedFunction)
   */
  default <V> CheckedFunction<V, O> compose(CheckedFunction<? super V, ? extends I> before) {
      Objects.requireNonNull(before);
      return (V v) -> apply(before.apply(v));
  }

  /**
   * Returns a composed function that first applies this function to
   * its input, and then applies the {@code after} function to the result.
   * If evaluation of either function throws an exception, it is relayed to
   * the caller of the composed function.
   *
   * @param <V> the type of output of the {@code after} function, and of the
   *           composed function
   * @param after the function to apply after this function is applied
   * @return a composed function that first applies this function and then
   * applies the {@code after} function
   * @throws NullPointerException if after is null
   *
   * @see #compose(CheckedFunction)
   */
  default <V> CheckedFunction<I, V> andThen(CheckedFunction<? super O, ? extends V> after) {
      Objects.requireNonNull(after);
      return (I input) -> after.apply(apply(input));
  }

  /**
   * Returns a function that always returns its input argument.
   *
   * @param <T> the type of the input and output objects to the function
   * @return a function that always returns its input argument
   */
  static <T> CheckedFunction<T, T> identity() {
      return t -> t;
  }
}
