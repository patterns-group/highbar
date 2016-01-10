package com.highbar.function;

/**
 * Defines a runnable that checks an exception.
 */
@FunctionalInterface
public interface CheckedRunnable {
  /**
   * Runs this function.
   * @throws Exception
   */
  void run() throws Exception;
}
