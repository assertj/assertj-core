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
package org.assertj.core.internal.files;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Files;
import org.assertj.core.internal.FilesBaseTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldBeDirectory.shouldBeDirectory;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Tests for <code>{@link Files#assertIsDirectoryNotEmpty(AssertionInfo, File)}</code>
 *
 * @author Valeriy Vyrva
 */
@SuppressWarnings({"WeakerAccess", "ThrowableNotThrown"})
public class Files_assertIsDirectoryNotEmpty_Test extends FilesBaseTest {

  @Test
  public void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class)
      .isThrownBy(() -> files.assertIsDirectoryNotEmpty(INFO, null))
      .withMessage(actualIsNull());
  }

  @Test
  public void should_fail_with_should_be_directory_error_if_actual_does_not_exist() {
    // GIVEN
    given(actual.exists()).willReturn(false);
    // WHEN
    Throwable error = catchThrowable(() ->
      files.assertIsDirectoryNotEmpty(INFO, actual)
    );
    // THEN
    verify(failures).failure(INFO, shouldBeDirectory(actual));
    assertThat(error).isInstanceOf(AssertionError.class);
  }

  @Test
  public void should_fail_if_actual_exists_but_is_not_directory() {
    // GIVEN
    given(actual.exists()).willReturn(true);
    given(actual.isDirectory()).willReturn(false);
    // WHEN
    Throwable error = catchThrowable(() ->
      files.assertIsDirectoryNotEmpty(INFO, actual)
    );
    // THEN
    verify(failures).failure(INFO, shouldBeDirectory(actual));
    assertThat(error).isInstanceOf(AssertionError.class);
  }

  @Test
  public void should_throw_error_on_null_listing() {
    // GIVEN
    given(actual.exists()).willReturn(true);
    given(actual.isDirectory()).willReturn(true);
    given(actual.list()).willReturn(null);
    given(actual.listFiles(any(FileFilter.class))).willCallRealMethod();
    // WHEN
    Throwable error = catchThrowable(() ->
      files.assertIsDirectoryNotEmpty(INFO, actual)
    );
    // THEN
    assertThat(error)
      .isInstanceOf(NullPointerException.class)
      .hasMessage("Directory listing should not be null");
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    // GIVEN
    List<File> items = emptyList();
    File actual = mockDirectory(items, "root");
    // WHEN
    Throwable error = catchThrowable(() ->
      files.assertIsDirectoryNotEmpty(INFO, actual)
    );
    // THEN
    assertThat(error)
      .isInstanceOf(AssertionError.class)
      .hasMessageContaining(shouldNotBeEmpty().create());
    verify(failures).failure(INFO, shouldNotBeEmpty());
  }

  @Test
  public void should_pass_if_actual_is_not_empty() {
    // GIVEN
    File file = mockRegularFile("root", "Test.class");
    List<File> items = singletonList(file);
    File actual = mockDirectory(items, "root");
    // WHEN
    Throwable error = catchThrowable(() ->
      files.assertIsDirectoryNotEmpty(INFO, actual)
    );
    // THEN
    assertThat(error).isNull();
  }
}