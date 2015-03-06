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
package org.assertj.core.util;

import static java.io.File.separator;
import static org.assertj.core.util.Strings.append;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for <code>{@link Files#temporaryFolderPath()}</code>.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Files_temporaryFolderPath_Test extends Files_TestCase {

  @Test
  public void should_find_path_of_temporary_folder() {
    String a = Files.temporaryFolderPath();
    String e = append(separator).to(systemTemporaryFolder());
    assertEquals(e, a);
  }
}
