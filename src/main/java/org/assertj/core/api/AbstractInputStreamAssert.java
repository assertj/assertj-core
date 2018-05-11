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
 * Copyright 2012-2018 the original author or authors.
 */
package org.assertj.core.api;

import org.assertj.core.internal.InputStreams;
import org.assertj.core.internal.InputStreamsException;
import org.assertj.core.util.VisibleForTesting;

import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Base class for all implementations of assertions for {@link InputStream}s.
 * @param <SELF> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <ACTUAL> the type of the "actual" value.
 *
 * @author Matthieu Baechler
 * @author Mikhail Mazursky
 */
public abstract class AbstractInputStreamAssert<SELF extends AbstractInputStreamAssert<SELF, ACTUAL>, ACTUAL extends InputStream>
    extends AbstractAssert<SELF, ACTUAL> {

  @VisibleForTesting
  InputStreams inputStreams = InputStreams.instance();

  public AbstractInputStreamAssert(ACTUAL actual, Class<?> selfType) {
    super(actual, selfType);
  }

  /**
   * Verifies that the content of the actual {@code InputStream} is equal to the content of the given one.
   *
   * @param expected the given {@code InputStream} to compare the actual {@code InputStream} to.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given {@code InputStream} is {@code null}.
   * @throws AssertionError if the actual {@code InputStream} is {@code null}.
   * @throws AssertionError if the content of the actual {@code InputStream} is not equal to the content of the given one.
   * @throws InputStreamsException if an I/O error occurs.
   *
   * @deprecated use {@link #hasSameContentAs(InputStream)} instead
   */
  @Deprecated
  public SELF hasContentEqualTo(InputStream expected) {
    inputStreams.assertSameContentAs(info, actual, expected);
    return myself;
  }

  /**
   * Verifies that the content of the actual {@code InputStream} is equal to the content of the given one.
   * <p>
   * Example:
   * <pre><code class='java'> // assertion will pass
   * assertThat(new ByteArrayInputStream(new byte[] {0xa})).hasSameContentAs(new ByteArrayInputStream(new byte[] {0xa}));
   *
   * // assertions will fail
   * assertThat(new ByteArrayInputStream(new byte[] {0xa})).hasSameContentAs(new ByteArrayInputStream(new byte[] {}));
   * assertThat(new ByteArrayInputStream(new byte[] {0xa})).hasSameContentAs(new ByteArrayInputStream(new byte[] {0xa, 0xc, 0xd}));</code></pre>
   * 
   * @param expected the given {@code InputStream} to compare the actual {@code InputStream} to.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given {@code InputStream} is {@code null}.
   * @throws AssertionError if the actual {@code InputStream} is {@code null}.
   * @throws AssertionError if the content of the actual {@code InputStream} is not equal to the content of the given one.
   * @throws InputStreamsException if an I/O error occurs.
   */
  public SELF hasSameContentAs(InputStream expected) {
    inputStreams.assertSameContentAs(info, actual, expected);
    return myself;
  }

  /**
   * Verifies that the content of the tested {@link InputStream} (which must be a readable file) has digest calculated
   * by given algorithm equal to the given one.
   * <p>
   * Examples:
   * <pre><code class="java">
   * // the current directory is supposed to have file downloaded from 'https://repo1.maven.org/maven2/org/assertj/assertj-core/2.9.0/assertj-core-2.9.0.jar'.
   * final File tested = new File("assertj-core-2.9.0.jar");
   *
   * // The following assertion succeeds:
   * assertThat(new FileInputStream(tested)).hasDigest(MessageDigest.getInstance("SHA1"), new byte[]{92, 90, -28, 91, 88, -15, 32, 35, -127, 122, -66, 73, 36, 71, -51, -57, -111, 44, 26, 44});
   * assertThat(new FileInputStream(tested)).hasDigest(MessageDigest.getInstance("MD5"), new byte[]{-36, -77, 1, 92, -46, -124, 71, 100, 76, -127, 10, -13, 82, -125, 44, 25});
   *
   * // The following assertion fails:
   * assertThat(new FileInputStream(tested)).hasDigest(MessageDigest.getInstance("SHA1"), new byte[]{-109, -71, -50, -46, -18, 91, 63, 15, 76, -114, 100, 14, 119, 71, 13, -85, 3, 29, 76, -83});
   * assertThat(new FileInputStream(tested)).hasDigest(MessageDigest.getInstance("MD5"), new byte[]{55, 53, -33, -8, -31, -7, -33, 4, -110, -93, 78, -16, 117, 32, 91, -113});
   * </code></pre>
   *
   * @param expected the expected binary content to compare the actual {@code File}'s content to.
   * @return {@code this} assertion object.
   * @throws NullPointerException  if the given algorithm is {@code null}.
   * @throws NullPointerException  if the given digest is {@code null}.
   * @throws AssertionError        if the actual {@code File} is {@code null}.
   * @throws AssertionError        if the actual {@code File} is not exist.
   * @throws AssertionError        if the actual {@code File} is not an file.
   * @throws AssertionError        if the actual {@code File} is not readable.
   * @throws InputStreamsException if an I/O error occurs.
   * @throws AssertionError        if the content of the tested {@code File} has digest which is not equal to the given one.
   * @since 3.10.0
   */
  public SELF hasDigest(MessageDigest digest, byte[] expected) {
    inputStreams.assertHasDigest(info, actual, digest, expected);
    return myself;
  }

  /**
   * Verifies that the content of the tested {@link InputStream} (which must be a readable file) has digest calculated
   * by given algorithm equal to the given one.
   * <p>
   * Examples:
   * <pre><code class="java">
   * // the current directory is supposed to have file downloaded from 'https://repo1.maven.org/maven2/org/assertj/assertj-core/2.9.0/assertj-core-2.9.0.jar'.
   * final File tested = new File("assertj-core-2.9.0.jar");
   *
   * // The following assertion succeeds:
   * assertThat(new FileInputStream(tested)).hasDigest(MessageDigest.getInstance("SHA1"), "5c5ae45b58f12023817abe492447cdc7912c1a2c");
   * assertThat(new FileInputStream(tested)).hasDigest(MessageDigest.getInstance("MD5"), "dcb3015cd28447644c810af352832c19");
   *
   * // The following assertion fails:
   * assertThat(new FileInputStream(tested)).hasDigest(MessageDigest.getInstance("SHA1"), "93b9ced2ee5b3f0f4c8e640e77470dab031d4cad");
   * assertThat(new FileInputStream(tested)).hasDigest(MessageDigest.getInstance("MD5"), "3735dff8e1f9df0492a34ef075205b8f");
   * </code></pre>
   *
   * @param expected the expected binary content to compare the actual {@code File}'s content to.
   * @return {@code this} assertion object.
   * @throws NullPointerException  if the given algorithm is {@code null}.
   * @throws NullPointerException  if the given digest is {@code null}.
   * @throws AssertionError        if the actual {@code File} is {@code null}.
   * @throws AssertionError        if the actual {@code File} is not exist.
   * @throws AssertionError        if the actual {@code File} is not an file.
   * @throws AssertionError        if the actual {@code File} is not readable.
   * @throws InputStreamsException if an I/O error occurs.
   * @throws AssertionError        if the content of the tested {@code File} has digest which is not equal to the given one.
   * @since 3.10.0
   */
  public SELF hasDigest(MessageDigest digest, String expected) {
    inputStreams.assertHasDigest(info, actual, digest, expected);
    return myself;
  }

  /**
   * Verifies that the content of the tested {@link InputStream} (which must be a readable file) has digest calculated
   * by given algorithm equal to the given one.
   * <p>
   * Examples:
   * <pre><code class="java">
   * // the current directory is supposed to have file downloaded from 'https://repo1.maven.org/maven2/org/assertj/assertj-core/2.9.0/assertj-core-2.9.0.jar'.
   * final File tested = new File("assertj-core-2.9.0.jar");
   *
   * // The following assertion succeeds:
   * assertThat(new FileInputStream(tested)).hasDigest("SHA1", new byte[]{92, 90, -28, 91, 88, -15, 32, 35, -127, 122, -66, 73, 36, 71, -51, -57, -111, 44, 26, 44});
   * assertThat(new FileInputStream(tested)).hasDigest("MD5", new byte[]{-36, -77, 1, 92, -46, -124, 71, 100, 76, -127, 10, -13, 82, -125, 44, 25});
   *
   * // The following assertion fails:
   * assertThat(new FileInputStream(tested)).hasDigest("SHA1", new byte[]{-109, -71, -50, -46, -18, 91, 63, 15, 76, -114, 100, 14, 119, 71, 13, -85, 3, 29, 76, -83});
   * assertThat(new FileInputStream(tested)).hasDigest("MD5", new byte[]{55, 53, -33, -8, -31, -7, -33, 4, -110, -93, 78, -16, 117, 32, 91, -113});
   * </code></pre>
   *
   * @param expected the expected binary content to compare the actual {@code File}'s content to.
   * @return {@code this} assertion object.
   * @throws NullPointerException  if the given algorithm is {@code null}.
   * @throws NullPointerException  if the given digest is {@code null}.
   * @throws AssertionError        if the actual {@code File} is {@code null}.
   * @throws AssertionError        if the actual {@code File} is not exist.
   * @throws AssertionError        if the actual {@code File} is not an file.
   * @throws AssertionError        if the actual {@code File} is not readable.
   * @throws InputStreamsException if an I/O error occurs.
   * @throws AssertionError        if the content of the tested {@code File} has digest which is not equal to the given one.
   * @since 3.10.0
   */
  public SELF hasDigest(String algorithm, byte[] expected) {
    inputStreams.assertHasDigest(info, actual, algorithm, expected);
    return myself;
  }

  /**
   * Verifies that the content of the tested {@link InputStream} (which must be a readable file) has digest calculated
   * by given algorithm equal to the given one.
   * <p>
   * Examples:
   * <pre><code class="java">
   * // the current directory is supposed to have file downloaded from 'https://repo1.maven.org/maven2/org/assertj/assertj-core/2.9.0/assertj-core-2.9.0.jar'.
   * final File tested = new File("assertj-core-2.9.0.jar");
   *
   * // The following assertion succeeds:
   * assertThat(new FileInputStream(tested)).hasDigest("SHA1", "5c5ae45b58f12023817abe492447cdc7912c1a2c");
   * assertThat(new FileInputStream(tested)).hasDigest("MD5", "dcb3015cd28447644c810af352832c19");
   *
   * // The following assertion fails:
   * assertThat(new FileInputStream(tested)).hasDigest("SHA1", "93b9ced2ee5b3f0f4c8e640e77470dab031d4cad");
   * assertThat(new FileInputStream(tested)).hasDigest("MD5", "3735dff8e1f9df0492a34ef075205b8f");
   * </code></pre>
   *
   * @param expected the expected binary content to compare the actual {@code File}'s content to.
   * @return {@code this} assertion object.
   * @throws NullPointerException  if the given algorithm is {@code null}.
   * @throws NullPointerException  if the given digest is {@code null}.
   * @throws AssertionError        if the actual {@code File} is {@code null}.
   * @throws AssertionError        if the actual {@code File} is not exist.
   * @throws AssertionError        if the actual {@code File} is not an file.
   * @throws AssertionError        if the actual {@code File} is not readable.
   * @throws InputStreamsException if an I/O error occurs.
   * @throws AssertionError        if the content of the tested {@code File} has digest which is not equal to the given one.
   * @since 3.10.0
   */
  public SELF hasDigest(String algorithm, String expected) {
    inputStreams.assertHasDigest(info, actual, algorithm, expected);
    return myself;
  }
}
