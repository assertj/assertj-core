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
 * Copyright 2012-2021 the original author or authors.
 */
package org.assertj.core.internal.maps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.data.MapEntry.entry;
import static org.assertj.core.error.ShouldContainValues.shouldContainValues;
import static org.assertj.core.test.Maps.mapOf;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Sets.newLinkedHashSet;
import static org.mockito.Mockito.verify;

import java.util.HashMap;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.MapsBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link org.assertj.core.internal.Maps#assertContainsValues(org.assertj.core.api.AssertionInfo, java.util.Map, Object[])}</code>.
 *
 * @author Nicolas François
 * @author Joel Costigliola
 * @author Alexander Bischof
 */
class Maps_assertContainsValues_Test extends MapsBaseTest {

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    actual = mapOf(entry("name", "Yoda"), entry("type", "Jedi"), entry("color", "green"), entry(null, null));
  }

  @Test
  void should_pass_if_actual_contains_given_values() {
    maps.assertContainsValues(someInfo(), actual, new String[] { "Yoda", "Jedi" });
  }

  @Test
  void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> maps.assertContainsValues(someInfo(), null, new String[] { "Yoda" }))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_fail_if_value_is_null() {
    assertThatNullPointerException().isThrownBy(() -> maps.assertContainsValues(someInfo(), actual, (String[]) null))
                                    .withMessage("The array of values to look for should not be null");
  }

  @Test
  void should_pass_if_actual_and_given_values_are_empty() {
    actual = new HashMap<>();
    maps.assertContainsValues(someInfo(), actual, new String[0]);
  }
  
  @Test
  void should_success_if_values_contains_null() {
	maps.assertContainsValues(someInfo(), actual, new String[] { "Yoda", null });
  }

  @Test
  void should_fail_if_actual_does_not_contain_value() {
    AssertionInfo info = someInfo();
    String value = "veryOld";
    String value2 = "veryOld2";

    Throwable error = catchThrowable(() -> maps.assertContainsValues(info, actual, new String[] { value, value2 }));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldContainValues(actual, newLinkedHashSet(value, value2)));
  }
}
