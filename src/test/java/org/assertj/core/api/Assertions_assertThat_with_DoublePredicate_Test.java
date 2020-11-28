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
package org.assertj.core.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.DoublePredicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Assertions#assertThat(DoublePredicate)}</code>.
 *
 * @author Filip Hrisafov
 */
class Assertions_assertThat_with_DoublePredicate_Test {

  private DoublePredicate actual;

  @BeforeEach
  void before() {
    actual = value -> value == 1;
  }

  @Test
  void should_create_Assert() {
    DoublePredicateAssert assertions = Assertions.assertThat(actual);
    assertThat(assertions).isNotNull();
  }

  @Test
  void should_pass_actual() {
    DoublePredicateAssert assertions = Assertions.assertThat(actual);
    assertThat(actual).isSameAs(assertions.actual);
  }
}
