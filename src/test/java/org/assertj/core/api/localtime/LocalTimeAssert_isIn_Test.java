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
 * Copyright 2012-2020 the original author or authors.
 */
package org.assertj.core.api.localtime;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.error.ShouldBeIn.shouldBeIn;
import static org.assertj.core.util.AssertionsUtil.assertThatAssertionErrorIsThrownBy;

import java.time.LocalTime;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Only test String based assertion (tests with {@link LocalTime} are already defined in assertj-core)
 * 
 * @author Joel Costigliola
 * @author Marcin Zajączkowski
 */
@DisplayName("LocalTimeAssert isIn")
class LocalTimeAssert_isIn_Test extends LocalTimeAssertBaseTest {

  @Test
  void should_pass_if_actual_is_in_localTimes_as_string_array_parameter() {
    assertThat(REFERENCE).isIn(REFERENCE.toString(), AFTER.toString());
  }

  @Test
  void should_fail_if_actual_is_not_in_localTimes_as_string_array_parameter() {
    // WHEN
    ThrowingCallable code = () -> assertThat(REFERENCE).isIn(AFTER.toString(), BEFORE.toString());
    // THEN
    assertThatAssertionErrorIsThrownBy(code).withMessage(shouldBeIn(REFERENCE, asList(AFTER, BEFORE)).create());
  }

  @Test
  void should_fail_if_localTimes_as_string_array_parameter_is_null() {
    // GIVEN
    String[] otherLocalTimesAsString = null;
    // WHEN
    ThrowingCallable code = () -> assertThat(LocalTime.now()).isIn(otherLocalTimesAsString);
    // THEN
    assertThatIllegalArgumentException().isThrownBy(code)
                                        .withMessage("The given LocalTime array should not be null");
  }

  @Test
  void should_fail_if_localTimes_as_string_array_parameter_is_empty() {
    // GIVEN
    String[] otherLocalTimesAsString = new String[0];
    // WHEN
    ThrowingCallable code = () -> assertThat(LocalTime.now()).isIn(otherLocalTimesAsString);
    // THEN
    assertThatIllegalArgumentException().isThrownBy(code)
                                        .withMessage("The given LocalTime array should not be empty");
  }

}
