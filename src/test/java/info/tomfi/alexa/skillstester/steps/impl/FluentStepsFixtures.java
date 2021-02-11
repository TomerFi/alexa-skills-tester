/**
 * Copyright Tomer Figenblat.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.tomfi.alexa.skillstester.steps.impl;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.amazon.ask.Skill;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FluentStepsFixtures {
  protected final String dummyJsonRequestString =
      "{\"version\": \"1.0\", "
          + "\"session\": {\"new\": true}, "
          + "\"context\": {\"System\": {}}, "
          + "\"request\": {"
          + "\"type\": \"LaunchRequest\", "
          + "\"requestId\": \"amzn1.echo-api.request.fake-request-id\", "
          + "\"timestamp\": \"2019-10-01T10:20:30Z\", "
          + "\"locale\": \"en-US\"}}";

  protected final byte[] dummyJsonRequestByte = dummyJsonRequestString.getBytes(UTF_8);

  @Mock protected Skill skill;
}
