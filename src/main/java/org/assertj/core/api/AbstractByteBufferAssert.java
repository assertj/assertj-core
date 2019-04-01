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

import org.assertj.core.internal.*;
import org.assertj.core.util.VisibleForTesting;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import static org.assertj.core.error.bytebuffer.ContentsShouldBeEqualTo.contentsShouldBeEqualTo;
import static org.assertj.core.error.bytebuffer.ContentsShouldContain.contentsShouldContain;
import static org.assertj.core.error.bytebuffer.ContentsShouldEndWith.contentsShouldEndWith;
import static org.assertj.core.error.bytebuffer.ContentsShouldStartWith.contentsShouldStartWith;

public class AbstractByteBufferAssert<SELF extends AbstractByteBufferAssert<SELF>> extends AbstractBufferAssert<SELF, ByteBuffer> {

  @VisibleForTesting
  Comparables comparables = new Comparables();

  @VisibleForTesting
  ByteArrays byteArrays = ByteArrays.instance();

  public AbstractByteBufferAssert(ByteBuffer buffer, Class<?> selfType) {
    super(buffer, selfType);
  }

  public SELF isEqualTo(String expected) {
    isNotNull();
    isFlipped();

    String contentString = new String(getContent(actual));
    if (!contentString.equals(expected)) throwAssertionError(contentsShouldBeEqualTo(expected, actual));
    return myself;
  }

  public SELF isEqualTo(String expected, Charset charset) {
    isNotNull();
    isFlipped();

    String contentString = new String(getContent(actual), charset);
    if (!contentString.equals(expected)) throwAssertionError(contentsShouldBeEqualTo(expected, actual));
    return myself;
  }

  public SELF isEqualTo(byte[] expected) {
    isNotNull();
    isFlipped();

    byteArrays.assertContainsExactly(info, getContent(actual), expected);
    return myself;
  }

  public SELF isEqualTo(ByteBuffer expected) {
    isNotNull();
    isFlipped();

    comparables.assertEqual(info, actual, expected);
    return myself;
  }

  public SELF contains(String expected) {
    isNotNull();
    isFlipped();

    String contentString = new String(getContent(actual));
    if (!contentString.contains(expected)) throwAssertionError(contentsShouldContain(expected, actual));
    return myself;
  }

  public SELF contains(String expected, Charset charset) {
    isNotNull();
    isFlipped();

    String contentString = new String(getContent(actual), charset);
    if (!contentString.contains(expected)) throwAssertionError(contentsShouldContain(expected, actual));
    return myself;
  }

  public SELF contains(byte[] expected) {
    isNotNull();
    isFlipped();

    byteArrays.assertContains(info, getContent(actual), expected);
    return myself;
  }

  public SELF contains(ByteBuffer expected) {
    isNotNull();
    isFlipped();

    byteArrays.assertContains(info, getContent(actual), getContent(expected));
    return myself;
  }

  public SELF startsWith(String expected) {
    isNotNull();
    isFlipped();

    String contentString = new String(getContent(actual));
    if (!contentString.startsWith(expected)) throwAssertionError(contentsShouldStartWith(expected, actual));
    return myself;
  }

  public SELF startsWith(String expected, Charset charset) {
    isNotNull();
    isFlipped();

    String contentString = new String(getContent(actual), charset);
    if (!contentString.startsWith(expected)) throwAssertionError(contentsShouldStartWith(expected, actual));
    return myself;
  }

  public SELF startsWith(byte[] expected) {
    isNotNull();
    isFlipped();

    byteArrays.assertStartsWith(info, getContent(actual), expected);
    return myself;
  }

  public SELF startsWith(ByteBuffer expected) {
    isNotNull();
    isFlipped();

    byteArrays.assertStartsWith(info, getContent(actual), getContent(expected));
    return myself;
  }

  public SELF endsWith(String expected) {
    isNotNull();
    isFlipped();

    String contentString = new String(getContent(actual));
    if (!contentString.endsWith(expected)) throwAssertionError(contentsShouldEndWith(expected, actual));
    return myself;
  }

  public SELF endsWith(String expected, Charset charset) {
    isNotNull();
    isFlipped();

    String contentString = new String(getContent(actual), charset);
    if (!contentString.endsWith(expected)) throwAssertionError(contentsShouldEndWith(expected, actual));
    return myself;
  }

  public SELF endsWith(byte[] expected) {
    isNotNull();
    isFlipped();

    byteArrays.assertEndsWith(info, getContent(actual), expected);
    return myself;
  }

  public SELF endsWith(ByteBuffer expected) {
    isNotNull();
    isFlipped();

    byteArrays.assertEndsWith(info, getContent(actual), getContent(expected));
    return myself;
  }

  private byte[] getContent(ByteBuffer buffer) {
    byte[] content = new byte[buffer.limit()];
    for (int i = 0; i < content.length; i++) {
      content[i] = buffer.get();
    }
    buffer.rewind();
    return content;
  }
}
