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
package org.assertj.core.error.bytebuffer;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.core.util.Hexadecimals;

import java.nio.ByteBuffer;

public class ContentsShouldBeEqualTo extends BasicErrorMessageFactory {

  private static final String CONTENTS_SHOULD_EQUAL = "%nExpected the contents of%n  <%s>%nto be equal to%n  <%s>";

  public static ErrorMessageFactory contentsShouldBeEqualTo(String expected, ByteBuffer actual) {
    expected = Hexadecimals.byteArrayToHexString(expected.getBytes(), " ");
    return new ContentsShouldBeEqualTo(expected, actual);
  }

  private ContentsShouldBeEqualTo(String expected, ByteBuffer actual) {
    super(CONTENTS_SHOULD_EQUAL, actual, expected);
  }
}
