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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.core.api.future;

import org.assertj.core.api.BaseTest;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;
import static org.assertj.core.error.future.ShouldNotBeCompleted.shouldNotBeCompleted;
import static org.assertj.core.error.future.ShouldNotHaveCompletedExceptionally.shouldNotHaveCompletedExceptionally;
import static org.assertj.core.util.FailureMessages.actualIsNull;

public class CompletableFutureAssert_isNotCompleted_Test extends BaseTest {

  @Test
  public void should_pass_if_completable_future_is_incomplete() throws Exception {
    CompletableFuture<String> future = new CompletableFuture<>();

    assertThat(future).isNotCompleted();
  }

  @Test
  public void should_pass_if_completable_future_has_failed() throws Exception {
    CompletableFuture<String> future = new CompletableFuture<>();
    future.completeExceptionally(new RuntimeException());

    assertThat(future).isNotCompleted();
  }

  @Test
  public void should_pass_if_completable_future_was_cancelled() throws Exception {
    CompletableFuture<String> future = new CompletableFuture<>();
    future.cancel(true);

    assertThat(future).isNotCompleted();
  }

  @Test
  public void should_fail_when_completable_future_is_null() throws Exception {
    assertThatThrownBy(() -> assertThat((CompletableFuture<String>) null).isNotCompleted())
        .isInstanceOf(AssertionError.class)
        .hasMessage(format(actualIsNull()));
  }

  @Test
  public void should_fail_if_completable_future_is_completed() throws Exception {
    CompletableFuture<String> future = CompletableFuture.completedFuture("done");

    assertThatThrownBy(() -> assertThat(future).isNotCompleted())
        .isInstanceOf(AssertionError.class)
        .hasMessage(shouldNotBeCompleted(future).create());
  }
}
