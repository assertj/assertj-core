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
package org.assertj.core.internal.strings;

import static org.assertj.core.error.ShouldBeBlank.shouldBeBlank;
import static org.assertj.core.test.TestData.someInfo;

import org.assertj.core.internal.StringsBaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;

@RunWith(DataProviderRunner.class)
public class Strings_assertBlank_Test extends StringsBaseTest {

  @Test
  @DataProvider(value = {
      "null",
      "",
      " ",
      "\u005Ct", // tab
      "\u005Cn", // line feed
      "\u005Cr", // carriage return
      " \u005Cn\u005Cr  "
  }, trimValues = false)
  public void should_pass_if_string_is_blank(String actual) {
    strings.assertBlank(someInfo(), actual);
  }

  @Test
  @DataProvider(value = {
      "a",
      " bc ",
      "\u00A0", // non-breaking space
      "\u2007", // non-breaking space
      "\u202F", // non-breaking space
  }, trimValues = false)
  public void should_fail_if_string_is_not_blank(String actual) {
    thrown.expectAssertionError(shouldBeBlank(actual).create());
    strings.assertBlank(someInfo(), actual);
  }
}
