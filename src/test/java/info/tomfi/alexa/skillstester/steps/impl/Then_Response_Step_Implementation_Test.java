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

import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.request.SkillRequest;
import info.tomfi.alexa.skillstester.steps.FollowingUp;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Then step, encapsulating the Skill, implementing assertion methods and the next optionally
 * Followup step logic test cases.
 */
@Tag("unit-tests")
final class Then_Response_Step_Implementation_Test extends FluentStepsFixtures {
  @Mock private ResponseEnvelope responseEnvelope;
  @InjectMocks private ThenResponseImpl sut;

  @Test
  void following_an_open_session_with_a_request_envelope_will_return_a_followup_for_request_envlps(
      @Mock final Response response, @Mock final RequestEnvelope followupRequestEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // stub response to keep session open waiting for followup
    given(response.getShouldEndSession()).willReturn(false);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step
    var followupStep = sut.followingUpWith(followupRequestEnvelope);
    // then verify the skill field
    var skillField = FollowingUp.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(followupStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = FollowingUp.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(followupStep)).isEqualTo(responseEnvelope);
    // then verify followupRequestEnvelope field is the RequestEnvelope argument passed
    var followupRequestEnvelopeField =
        FollowingUp.class.getDeclaredField("followupRequestEnvelope");
    followupRequestEnvelopeField.setAccessible(true);
    then(followupRequestEnvelopeField.get(followupStep)).isEqualTo(followupRequestEnvelope);
    // then verify followupSkillRequest field is of null value
    var followupSkillRequestField = FollowingUp.class.getDeclaredField("followupSkillRequest");
    followupSkillRequestField.setAccessible(true);
    then(followupSkillRequestField.get(followupStep)).isNull();
    // then verify inEnvelopeMode field is true, setting the followup instance for RequestEnvelope
    // usage
    var inEnvelopeModeField = FollowingUp.class.getDeclaredField("inEnvelopeMode");
    inEnvelopeModeField.setAccessible(true);
    then((boolean) inEnvelopeModeField.get(followupStep)).isTrue();
  }

  @Test
  void following_a_closed_session_with_a_request_envelope_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final RequestEnvelope followupRequestEnvelope) {
    // stub response with closed session not waiting for followups
    given(response.getShouldEndSession()).willReturn(true);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step, then an assertion error is thrown
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.followingUpWith(followupRequestEnvelope))
        .withMessage("Session is marked as closed");
  }

  @Test
  void following_an_open_session_with_a_skill_request_will_return_a_followup_for_request_envlps(
      @Mock final Response response, @Mock final SkillRequest followupSkillRequest)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // stub response to keep session open waiting for followup
    given(response.getShouldEndSession()).willReturn(false);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step
    var followupStep = sut.followingUpWith(followupSkillRequest);
    // then verify the skill field
    var skillField = FollowingUp.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(followupStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = FollowingUp.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(followupStep)).isEqualTo(responseEnvelope);
    // then verify followupRequestEnvelope field is of null value
    var followupRequestEnvelopeField =
        FollowingUp.class.getDeclaredField("followupRequestEnvelope");
    followupRequestEnvelopeField.setAccessible(true);
    then(followupRequestEnvelopeField.get(followupStep)).isNull();
    // then verify followupSkillRequest field the SkillRequest argument passed
    var followupSkillRequestField = FollowingUp.class.getDeclaredField("followupSkillRequest");
    followupSkillRequestField.setAccessible(true);
    then(followupSkillRequestField.get(followupStep)).isEqualTo(followupSkillRequest);
    // then verify inEnvelopeMode field is false, setting the followup instance for SkillRequest
    // usage
    var inEnvelopeModeField = FollowingUp.class.getDeclaredField("inEnvelopeMode");
    inEnvelopeModeField.setAccessible(true);
    then((boolean) inEnvelopeModeField.get(followupStep)).isFalse();
  }

  @Test
  void following_a_closed_session_with_a_skill_request_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final SkillRequest followupSkillRequest) {
    // stub response with closed session not waiting for followups
    given(response.getShouldEndSession()).willReturn(true);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step, then an assertion error is thrown
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.followingUpWith(followupSkillRequest))
        .withMessage("Session is marked as closed");
  }

  @Test
  void following_an_open_session_with_a_json_string_will_return_a_followup_for_request_envlps(
      @Mock final Response response)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // stub response to keep session open waiting for followup
    given(response.getShouldEndSession()).willReturn(false);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step
    var followupStep = sut.followingUpWith(dummyJsonRequestString);
    // then verify the skill field
    var skillField = FollowingUp.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(followupStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = FollowingUp.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(followupStep)).isEqualTo(responseEnvelope);
    // then verify followupRequestEnvelope field is of null value
    var followupRequestEnvelopeField =
        FollowingUp.class.getDeclaredField("followupRequestEnvelope");
    followupRequestEnvelopeField.setAccessible(true);
    then(followupRequestEnvelopeField.get(followupStep)).isNull();
    // then verify followupSkillRequest field the SkillRequest argument passed
    var followupSkillRequestField = FollowingUp.class.getDeclaredField("followupSkillRequest");
    followupSkillRequestField.setAccessible(true);
    then(((SkillRequest) followupSkillRequestField.get(followupStep)).getRawRequest())
        .isEqualTo(dummyJsonRequestByte);
    // then verify inEnvelopeMode field is false, setting the followup instance for SkillRequest
    // usage
    var inEnvelopeModeField = FollowingUp.class.getDeclaredField("inEnvelopeMode");
    inEnvelopeModeField.setAccessible(true);
    then((boolean) inEnvelopeModeField.get(followupStep)).isFalse();
  }

  @Test
  void following_a_closed_session_with_a_json_string_will_throw_an_assertion_error(
      @Mock final Response response) {
    // stub response with closed session not waiting for followups
    given(response.getShouldEndSession()).willReturn(true);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step, then an assertion error is thrown
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.followingUpWith(dummyJsonRequestString))
        .withMessage("Session is marked as closed");
  }

  @Test
  void following_an_open_session_with_a_json_byte_array_will_return_a_followup_for_request_envlps(
      @Mock final Response response)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // stub response to keep session open waiting for followup
    given(response.getShouldEndSession()).willReturn(false);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step
    var followupStep = sut.followingUpWith(dummyJsonRequestByte);
    // then verify the skill field
    var skillField = FollowingUp.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(followupStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = FollowingUp.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(followupStep)).isEqualTo(responseEnvelope);
    // then verify followupRequestEnvelope field is of null value
    var followupRequestEnvelopeField =
        FollowingUp.class.getDeclaredField("followupRequestEnvelope");
    followupRequestEnvelopeField.setAccessible(true);
    then(followupRequestEnvelopeField.get(followupStep)).isNull();
    // then verify followupSkillRequest field the SkillRequest argument passed
    var followupSkillRequestField = FollowingUp.class.getDeclaredField("followupSkillRequest");
    followupSkillRequestField.setAccessible(true);
    then(((SkillRequest) followupSkillRequestField.get(followupStep)).getRawRequest())
        .isEqualTo(dummyJsonRequestByte);
    // then verify inEnvelopeMode field is false, setting the followup instance for SkillRequest
    // usage
    var inEnvelopeModeField = FollowingUp.class.getDeclaredField("inEnvelopeMode");
    inEnvelopeModeField.setAccessible(true);
    then((boolean) inEnvelopeModeField.get(followupStep)).isFalse();
  }

  @Test
  void following_a_closed_session_with_a_json_byte_array_will_throw_an_assertion_error(
      @Mock final Response response) {
    // stub response with closed session not waiting for followups
    given(response.getShouldEndSession()).willReturn(true);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step, then an assertion error is thrown
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.followingUpWith(dummyJsonRequestByte))
        .withMessage("Session is marked as closed");
  }
}
