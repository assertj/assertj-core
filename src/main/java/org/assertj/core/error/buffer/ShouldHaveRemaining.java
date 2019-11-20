/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2019 the original author or authors.
 */
package org.assertj.core.error.buffer;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

import java.nio.Buffer;

/**
 * Creates an error message indicating that buffer's expected remaining number of elements until its limit and its
 * actual remaining number of elements until its limit do not match.
 *
 * @author Jean de Leeuw
 */
public class ShouldHaveRemaining extends BasicErrorMessageFactory {

  private static final String SHOULD_HAVE_REMAINING = "%nExpected%n  <%s>%nto have%n  <%s>%nremaining number of elements but was%n  <%s>%n";

  /**
   * Creates a new <code>{@link ShouldHaveRemaining}</code>.
   *
   * @param expected the expected remaining number of elements of the buffer in the failed assertion.
   * @param actual the actual remaining number of elements of the buffer in the failed assertion.
   * @param buffer the actual buffer in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveRemaining(int expected, int actual, Buffer buffer) {
    return new ShouldHaveRemaining(expected, actual, buffer);
  }

  private ShouldHaveRemaining(int expected, int actual, Buffer buffer) {
    super(SHOULD_HAVE_REMAINING, buffer, expected, actual);
  }
}
