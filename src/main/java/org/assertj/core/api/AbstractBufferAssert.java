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
package org.assertj.core.api;

import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.core.error.buffer.ShouldHaveCapacity.shouldHaveCapacity;
import static org.assertj.core.error.buffer.ShouldHaveLength.shouldHaveLength;
import static org.assertj.core.error.buffer.ShouldHaveRemaining.shouldHaveRemaining;

import java.nio.Buffer;

/**
 * Base class for all implementations of assertions for {@link Buffer}s.
 *
 * @param <SELF> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <ACTUAL> the type of the "actual" buffer.
 *
 * @author Jean de Leeuw
 */
public abstract class AbstractBufferAssert<SELF extends AbstractBufferAssert<SELF, ACTUAL>, ACTUAL extends Buffer>
    extends AbstractAssert<SELF, ACTUAL> {

  public AbstractBufferAssert(ACTUAL actual, Class<?> selfType) {
    super(actual, selfType);
  }

  /**
   * Verifies that the actual {@code Buffer} is empty.
   *
   * A buffer is considered as empty when the limit of the buffer
   * is equal to zero (and therefore there is nothing to read).
   *
   * Example:
   *
   * <pre><code class='java'> // this assertion succeeds ...
   * Buffer buffer = ByteBuffer.wrap("".getBytes());
   * assertThat(buffer).isEmpty();
   *
   * // ... but this one fails as "buffer" is not empty.
   * Buffer buffer = ByteBuffer.wrap("test".getBytes());
   * assertThat(buffer).isEmpty();</code></pre>
   *
   * Note: if the buffer is not flipped then IsEmpty may display weird behaviour.
   * For example:
   * <pre><code class='java'> // this assertion fails because not flipping the
   * // buffer causes the limit of the buffer to be different than expected.
   * byte[] bytes = "".getBytes();
   * Buffer buffer = ByteBuffer.allocate(bytes.length);
   * buffer.put(bytes);
   * assertThat(buffer).isEmpty();
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code Buffer} is {@code null}.
   * @throws AssertionError if the actual {@code Buffer} is not empty.
   */
  public SELF isEmpty() {
    isNotNull();
    if (actual.position() != actual.limit()) throwAssertionError(shouldBeEmpty(actual));
    return myself;
  }

  /**
   * Verifies that the actual {@code Buffer} is not empty.
   *
   * A buffer is considered to not be empty when there is data that can
   * be consumed, or in other words when the limit is not equal to zero.
   *
   * Example:
   *
   * <pre><code class='java'> // this assertion succeeds ...
   * Buffer buffer = ByteBuffer.wrap("test".getBytes());
   * assertThat(buffer).isNotEmpty();
   *
   * // ... but this one fails as "buffer" is empty.
   * Buffer buffer = ByteBuffer.wrap("".getBytes());
   * assertThat(buffer).isNotEmpty();</code></pre>
   *
   * Note: if the buffer is not flipped then IsNotEmpty may display weird behaviour.
   * For example:
   * <pre><code class='java'> // this assertion succeeds because not flipping the
   * // buffer causes the limit of the buffer to be different than expected.
   * byte[] bytes = "".getBytes();
   * Buffer buffer = ByteBuffer.allocate(bytes.length);
   * buffer.put(bytes);
   * assertThat(buffer).isNotEmpty();
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code Buffer} is {@code null}.
   * @throws AssertionError if the actual {@code Buffer} is empty.
   */
  public SELF isNotEmpty() {
    isNotNull();
    if (actual.position() == actual.limit()) throwAssertionError(shouldNotBeEmpty());
    return myself;
  }

  /**
   * Verifies that the actual {@code Buffer} has a length equal to the given expected value.
   *
   * Example:
   *
   * <pre><code class='java'> byte[] testArray = "test".getBytes();
   * Buffer buffer = ByteBuffer.wrap(testArray);
   *
   * // this assertion succeeds ...
   * assertThat(buffer).hasLength(testArray.length);
   *
   * // ... but this one fails as "buffer" has a different length than the given expected value.
   * assertThat(buffer).hasLength(testArray.length - 1);</code></pre>
   *
   * @param expected integer value representing the expected length of the buffer.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code Buffer} is {@code null}.
   * @throws AssertionError if the actual {@code Buffer} is empty.
   * @throws AssertionError if the actual {@code Buffer} length is different than the given expected value.
   */
  public SELF hasLength(int expected) {
    isNotEmpty();
    if (actual.limit() != expected) throwAssertionError(shouldHaveLength(expected, actual.limit(), actual));
    return myself;
  }

  /**
   * Verifies that the actual {@code Buffer} has a capacity equal to the given expected value.
   *
   * Example:
   *
   * <pre><code class='java'> ByteBuffer buffer = ByteBuffer.wrap("test".getBytes());
   *
   * // this assertion succeeds ...
   * assertThat(buffer).hasCapacity(4);
   *
   * // ... but this one fails as "buffer" has a different capacity than the given expected value.
   * assertThat(buffer).hasCapacity(3);</code></pre>
   *
   * @param expected integer value representing the expected capacity of the buffer.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code Buffer} is {@code null}.
   * @throws AssertionError if the actual {@code Buffer} is empty.
   * @throws AssertionError if the actual {@code Buffer}s capacity is different than the given expected value.
   */
  public SELF hasCapacity(int expected) {
    isNotEmpty();
    if (actual.capacity() != expected) throwAssertionError(shouldHaveCapacity(expected, actual.capacity(), actual));
    return myself;
  }

  /**
   * Verifies that the actual {@code Buffer} has a remaining number of elements until the limit equal to the given
   * expected value.
   *
   * Example:
   *
   * <pre><code class='java'> byte[] testArray = "test".getBytes();
   * ByteBuffer buffer = ByteBuffer.allocate(10);
   * buffer.put(testArray);
   * buffer.flip();
   *
   * // this assertion succeeds ...
   * buffer.get();
   * assertThat(buffer).hasRemaining(testArray.length - 1);
   *
   * // ... but this one fails as "buffer" has a different number of remaining elements until the limit than the
   * // given expected value.
   * assertThat(buffer).hasRemaining(10);</code></pre>
   *
   * @param expected integer value representing the expected remaining capacity of the buffer.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code Buffer} is {@code null}.
   * @throws AssertionError if the actual {@code Buffer} is empty.
   * @throws AssertionError if the actual {@code Buffer}s remaining number of elements is different than the given
   *  expected value.
   */
  public SELF hasRemaining(int expected) {
    isNotEmpty();

    int remainingLength = actual.limit() - actual.position();
    if (remainingLength != expected) throwAssertionError(shouldHaveRemaining(expected, remainingLength, actual));
    return myself;
  }
}
