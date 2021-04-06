/*
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

import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.Reprompt;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/** Then response step, assertion method notWaitForFollowup test cases. */
@Tag("unit-tests")
final class Assertion_Method_haveNoReprompt_Test extends AssertionMethodsFixtures {
  @Test
  void asserting_with_no_reprompt_object_will_keep_ongoing_assertion(
      @Mock final Response response) {
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveNoReprompt()).isEqualTo(sut);
  }

  @Test
  void asserting_with_an_existing_reprompt_object_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final Reprompt reprompt) {
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveNoReprompt())
        .withMessage("Found reprompt object");
  }
}
