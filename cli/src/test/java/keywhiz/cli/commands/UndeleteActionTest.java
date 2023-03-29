/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package keywhiz.cli.commands;

import com.google.common.base.Throwables;
import java.io.IOException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import keywhiz.cli.configs.UndeleteActionConfig;
import keywhiz.client.KeywhizClient;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.slf4j.Logger;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class UndeleteActionTest {
  @Rule public MockitoRule mockito = MockitoJUnit.rule();

  @Rule public ExpectedException thrown = ExpectedException.none();

  @Mock KeywhizClient keywhizClient;
  @Mock Logger logger;

  UndeleteActionConfig undeleteActionConfig;
  UndeleteAction undeleteAction;

  @Before
  public void setUp() {
    undeleteActionConfig = new UndeleteActionConfig();
    undeleteAction = new UndeleteAction(undeleteActionConfig, keywhizClient);
  }

  @Test
  public void undeletesSecret() {
    undeleteActionConfig.objectType = "secret";
    undeleteActionConfig.id = 123L;
    undeleteAction.run();
    try {
      verify(keywhizClient).undeleteSecret(123L);
    } catch (Exception e) {
      throw Throwables.propagate(e);
    }
  }

  @Test
  public void rejectsUnsupportedObjectType() {
    undeleteActionConfig.objectType = "group";
    undeleteActionConfig.id = 123L;
    thrown.expect(IllegalArgumentException.class);
    undeleteAction.run();
  }

  @Test
  public void throwsAssertionErrorIfCannotUndelete() throws IOException {
    doThrow(new BadRequestException(
        "Cannot undelete secret since there is already a non-deleted secret with the same name")).when(
        keywhizClient).undeleteSecret(123L);
    undeleteActionConfig.objectType = "secret";
    undeleteActionConfig.id = 123L;
    Throwable exception = assertThrows(AssertionError.class, () -> {
      undeleteAction.run();
    });
    assertThat(exception.getMessage()).isEqualTo(
        "Bad Request: Cannot undelete secret since there is already a non-deleted secret with the same name");
  }

  @Test
  public void throwsAssertionErrorIfSecretNotFound() throws IOException {
    doThrow(new NotFoundException("Secret not found.")).when(keywhizClient).undeleteSecret(123L);
    undeleteActionConfig.objectType = "secret";
    undeleteActionConfig.id = 123L;
    Throwable exception = assertThrows(AssertionError.class, () -> {
      undeleteAction.run();
    });
    assertThat(exception.getMessage()).isEqualTo(
        "No soft-deleted secret with the provided ID was found.");
  }

  @Test
  public void propagatesUnexpectedExceptions() throws IOException {
    doThrow(new InternalError("Server error 123")).when(keywhizClient).undeleteSecret(123L);
    undeleteActionConfig.objectType = "secret";
    undeleteActionConfig.id = 123L;
    Throwable exception = assertThrows(InternalError.class, () -> {
      undeleteAction.run();
    });
    assertThat(exception.getMessage()).isEqualTo("Server error 123");
  }
}
