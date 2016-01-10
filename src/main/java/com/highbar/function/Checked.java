package com.highbar.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Provides checked function wrappers.
 */
public class Checked {
  public static Runnable runnable(CheckedRunnable runnable) {
    return () -> safeRun(input -> {
      runnable.run();
      return null;
    }, null);
  }

  public static <T> Consumer<T> consumer(CheckedConsumer<T> consumer) {
    return data -> safeRun(input -> {
      consumer.accept(input);
      return null;
    }, data);
  }

  public static <I, O> Function<I, O> function(CheckedFunction<I, O> function) {
    return input -> safeRun(function, input);
  }

  public static <T> Predicate<T> predicate(CheckedPredicate<T> predicate) {
    return input -> safeRun(i -> {
      return predicate.test(i);
    }, input);
  }

  private static <I,O> O safeRun(CheckedFunction<I,O> function, I input) {
    try {
      return function.apply(input);
    } catch (Error | RuntimeException error) {
      throw error;
    } catch (Throwable error) {
      throw new RuntimeException(error);
    }
  }
}
