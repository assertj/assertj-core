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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.core.api;

import org.assertj.core.internal.Urls;

import java.net.URL;

import static org.mockito.Mockito.mock;

/**
 * Base class for {@link org.assertj.core.api.UrlAssert} tests.
 */
public abstract class UrlAssertBaseTest extends BaseTestTemplate<UrlAssert, URL> {

  protected Urls urls;

  @Override
  protected UrlAssert create_assertions() {
    return new UrlAssert(mock(URL.class));
  }

  @Override
  protected void inject_internal_objects() {
    super.inject_internal_objects();
    urls = mock(Urls.class);
    assertions.urls = urls;
  }

  protected Urls getUrls(UrlAssert someAssertions) {
    return someAssertions.urls;
  }
}
