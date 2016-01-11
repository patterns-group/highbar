package com.highbar.function;

/**
 * Defines a predicate that checks a function.
 */
@FunctionalInterface
public interface CheckedPredicate<T> {
  boolean test(T t) throws Exception;
}
