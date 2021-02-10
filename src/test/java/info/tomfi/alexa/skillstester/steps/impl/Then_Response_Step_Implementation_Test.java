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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.amazon.ask.Skill;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ResponseEnvelope;
import info.tomfi.alexa.skillstester.steps.ThenFollowup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Then step, encapsulating the Skill, implementing assertion methods and the next optionally
 * Followup step logic test cases.
 */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Then_Response_Step_Implementation_Test {
  @Mock private Skill skill;
  @Mock private RequestEnvelope requestEnvelope;
  @Mock private ResponseEnvelope responseEnvelope;
  @Spy @InjectMocks private ThenResponseImpl sut;

  @Test
  void following_up_with_an_open_session_will_return_then_followup_instance_encapsulating_the_args(
      @Mock final Response response, @Mock final Request followupRequest)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // stub response to keep session open waiting for followup
    given(response.getShouldEndSession()).willReturn(false);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step
    var followupStep = sut.thenFollowupWith(followupRequest);
    // then verify the skill field
    var skillField = ThenFollowup.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(followupStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = ThenFollowup.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(followupStep)).isEqualTo(responseEnvelope);
    // then verify previousRequestEnvelope field
    var previousRequestEnvelopeField =
        ThenFollowup.class.getDeclaredField("previousRequestEnvelope");
    previousRequestEnvelopeField.setAccessible(true);
    then(previousRequestEnvelopeField.get(followupStep)).isEqualTo(requestEnvelope);
    // then verify followupRequest field
    var followupRequestField = ThenFollowup.class.getDeclaredField("followupRequest");
    followupRequestField.setAccessible(true);
    then(followupRequestField.get(followupStep)).isEqualTo(followupRequest);
  }

  @Test
  void following_up_with_a_closed_session_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final Request followupRequest) {
    // stub response with closed session not waiting for followups
    given(response.getShouldEndSession()).willReturn(true);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step, then an assertion error is thrown
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.thenFollowupWith(followupRequest))
        .withMessage("Session is marked as closed");
  }

  @Test
  void invoking_the_sugar_method_named_and_will_do_nothing_but_return_the_same_instance() {
    then(sut.and()).isEqualTo(sut);
    verify(sut).and();
    verifyNoMoreInteractions(sut);
  }
}
