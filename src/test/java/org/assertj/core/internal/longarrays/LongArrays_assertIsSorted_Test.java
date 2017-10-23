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
 * Copyright 2012-2017 the original author or authors.
 */
package org.assertj.core.internal.longarrays;

import static org.assertj.core.error.ShouldBeSorted.shouldBeSorted;
import static org.assertj.core.error.ShouldBeSorted.shouldBeSortedAccordingToGivenComparator;
import static org.assertj.core.test.LongArrays.*;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;


import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.LongArrays;
import org.assertj.core.internal.LongArraysBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link LongArrays#assertIsSorted(AssertionInfo, Object[])}</code>.
 * 
 * @author Joel Costigliola
 */
public class LongArrays_assertIsSorted_Test extends LongArraysBaseTest {

  @Override
  protected void initActualArray() {
    actual = arrayOf(1L, 2L, 3L, 4L, 4L);
  }

  @Test
  public void should_pass_if_actual_is_sorted_in_ascending_order() {
    arrays.assertIsSorted(someInfo(), actual);
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    arrays.assertIsSorted(someInfo(), emptyArray());
  }

  @Test
  public void should_pass_if_actual_contains_only_one_element() {
    arrays.assertIsSorted(someInfo(), arrayOf(1L));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    arrays.assertIsSorted(someInfo(), (long[]) null);
  }

  @Test
  public void should_fail_if_actual_is_not_sorted_in_ascending_order() {
    AssertionInfo info = someInfo();
    actual = arrayOf(1L, 3L, 2L);
    try {
      arrays.assertIsSorted(info, actual);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeSorted(1, actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_sorted_in_ascending_order_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertIsSorted(someInfo(), arrayOf(1L, -2L, 3L, -4L, 4L));
  }

  @Test
  public void should_pass_if_actual_is_empty_whatever_custom_comparison_strategy_is() {
    arraysWithCustomComparisonStrategy.assertIsSorted(someInfo(), emptyArray());
  }

  @Test
  public void should_pass_if_actual_contains_only_one_element_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertIsSorted(someInfo(), arrayOf(1L));
  }

  @Test
  public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectAssertionError(actualIsNull());
    arraysWithCustomComparisonStrategy.assertIsSorted(someInfo(), (long[]) null);
  }

  @Test
  public void should_fail_if_actual_is_not_sorted_in_ascending_order_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    actual = arrayOf(1L, 3L, 2L);
    try {
      arraysWithCustomComparisonStrategy.assertIsSorted(info, actual);
    } catch (AssertionError e) {
      verify(failures)
          .failure(info, shouldBeSortedAccordingToGivenComparator(1, actual, comparatorForCustomComparisonStrategy()));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

}
