/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.core.api;

import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Assertions for {@link LongPredicate}.
 *
 * @author Filip Hrisafov
 * @since 3.5.0
 */
public class LongPredicateAssert extends AbstractPredicateLikeAssert<LongPredicateAssert, LongPredicate, Long> {

  public LongPredicateAssert(LongPredicate actual) {
    super(actual, toPredicate(actual), LongPredicateAssert.class);
  }

  private static Predicate<Long> toPredicate(LongPredicate actual) {
    return actual != null ? actual::test : null;
  }

  /**
   * <p>
   * Verifies that the {@link LongPredicate} evaluates {@code true} for the given value.
   * </p>
   * Assertion will pass :
   * <pre><code class='java'> assertThat(predicate -> predicate < 2)
   *            .accepts(1);</code></pre>
   * <p>
   * Assertion will fail :
   * <pre><code class='java'> assertThat(predicate -> predicate < 2)
   *            .accepts(2);</code></pre>
   *
   * @return this assertion object.
   */
  public LongPredicateAssert accepts(long value) {
    return acceptsInternal(value);
  }

  /**
   * <p>
   * Verifies that the {@link LongPredicate} evaluates {@code true} for the given value,
   * the {@link String} parameter is used in the error message.
   * </p>
   * Assertion will pass :
   * <pre><code class='java'> assertThat(predicate -> predicate < 2)
   *            .matches(2, "expected '2' to match");</code></pre>
   *
   * Assertion will fail :
   * <pre><code class='java'> assertThat(predicate -> predicate.equals("something"))
   *            .matches("something else", "expected 'something else' to match");</code></pre>
   *
   * @return this assertion object.
  public LongPredicateAssert matches(long value, String description) {
  return matchesInternal(value, description);
  }

   */
  /**
   * Verifies that the {@link LongPredicate} evaluates {@code true} for the given value.
   * </p>
   * Assertion will pass :
   * <pre><code class='java'> assertThat(predicate -> predicate < 2)
   *            .doesNotAccept(3);</code></pre>
   * <p>
   * Assertion will fail :
   * <pre><code class='java'> assertThat(predicate -> predicate < 2)
   *            .doesNotAccept(1);</code></pre>
   *
   * @return this assertion object.
   */
  public LongPredicateAssert doesNotAccept(long value) {
    return doesNotAcceptInternal(value);
  }

  /**
   * Verifies that the {@link LongPredicate} evaluates {@code true} for the given value,
   * the {@link String} parameter is used in the error message.
   * </p>
   * Assertion will pass :
   * <pre><code class='java'> assertThat(predicate -> predicate < 2)
   *            .doesNotMatch(3, "expected '2 else' not to match");</code></pre>
   * <p>
   * Assertion will fail :
   * <pre><code class='java'> assertThat(predicate -> predicate < 2)
   *            .matches(1, "expected '1' not to match");</code></pre>
   *
   * @return this assertion object.
   */
  public LongPredicateAssert doesNotMatch(long value, String description) {
    return doesNotMatchInternal(value, description);
  }

  /**
   * <p>
   * Verifies that {@link LongPredicate} evaluates to {@code true} for all the elements from the {@code values}.
   * </p>
   * Assertion will pass:
   * <pre><code class='java'>
   *     assertThat(value -> value < 2).acceptsAll(0, 1);</code></pre>
   * <p>
   * Assertion will fail:
   * <pre><code class='java'>
   *     assertThat(value -> value < 2).acceptsAll(1, 2);</code></pre>
   *
   * @return this assertion object
   */
  public LongPredicateAssert acceptsAll(long... values) {
    return acceptsAllInternal(LongStream.of(values).boxed().collect(Collectors.toList()));
  }

  /**
   * <p>
   * Verifies that {@link LongPredicate} evaluates to {@code false} for all the elements from the {@code values}.
   * </p>
   * Assertion will pass:
   * <pre><code class='java'>
   *     assertThat(value -> value < 2).noneAccepted(0, 1);</code></pre>
   * <p>
   * Assertion will fail:
   * <pre><code class='java'>
   *     assertThat(value -> value < 2).noneAccepted(1, 2);</code></pre>
   *
   * @return this assertion object
   */
  public LongPredicateAssert noneAccepted(long... values) {
    return noneAcceptedInternal(LongStream.of(values).boxed().collect(Collectors.toList()));
  }
}
