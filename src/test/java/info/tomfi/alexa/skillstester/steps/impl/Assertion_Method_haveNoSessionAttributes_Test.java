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

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Then response step, assertion method notWaitForFollowup test cases. */
@Tag("unit-tests")
final class Assertion_Method_haveNoSessionAttributes_Test extends AssertionMethodsFixtures {
  @Test
  void asserting_with_an_empty_session_attributes_map_will_keep_ongoing_assertion() {
    given(responseEnvelope.getSessionAttributes()).willReturn(new HashMap<String, Object>());
    then(sut.haveNoSessionAttributes()).isEqualTo(sut);
  }

  @Test
  void asserting_with_a_null_session_attributes_object_will_keep_ongoing_assertion() {
    then(sut.haveNoSessionAttributes()).isEqualTo(sut);
  }

  @Test
  void asserting_with_a_non_empty_session_attributes_map_will_throw_an_assertion_error() {
    given(responseEnvelope.getSessionAttributes()).willReturn(Map.of("Key1", (Object) "Value1"));
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveNoSessionAttributes())
        .withMessage("Found session attributes");
  }
}
