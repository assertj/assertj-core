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
package org.assertj.core.api.chararray;

import static org.assertj.core.test.CharArrays.arrayOf;

import org.assertj.core.api.CharArrayAssert;
import org.assertj.core.api.CharArrayAssertBaseTest;

import static org.mockito.Mockito.verify;


/**
 * Tests for <code>{@link CharArrayAssert#doesNotContain(char...)}</code>.
 * 
 * @author Alex Ruiz
 */
public class CharArrayAssert_doesNotContain_Test extends CharArrayAssertBaseTest {

  @Override
  protected CharArrayAssert invoke_api_method() {
    return assertions.doesNotContain('a', 'b');
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertDoesNotContain(getInfo(assertions), getActual(assertions), arrayOf('a', 'b'));
  }
}
