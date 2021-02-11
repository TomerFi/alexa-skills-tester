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

import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.request.SkillRequest;
import info.tomfi.alexa.skillstester.steps.WhenRequest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/** Given step of the fluent api, implementing the next When step logic test cases. */
@Tag("unit-tests")
final class Given_Skill_Step_Implementation_Test extends FluentStepsFixtures {
  @InjectMocks private GivenSkillImpl sut;

  @Test
  void retrieving_next_step_with_a_request_envelope_will_return_when_instance_for_request_envelopes(
      @Mock final RequestEnvelope requestEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // when invoking for next step with a RequestEnvelope
    var whenStep = sut.whenRequestIs(requestEnvelope);
    // then verify the skill field
    var skillField = WhenRequest.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(whenStep)).isEqualTo(skill);
    // then verify requestEnvelope field is the RequestEnvelope argument passed
    var requestEnvelopeField = WhenRequest.class.getDeclaredField("requestEnvelope");
    requestEnvelopeField.setAccessible(true);
    then(requestEnvelopeField.get(whenStep)).isEqualTo(requestEnvelope);
    // then verify skillRequest field is of null value
    var skillRequestField = WhenRequest.class.getDeclaredField("skillRequest");
    skillRequestField.setAccessible(true);
    then(skillRequestField.get(whenStep)).isNull();
    // then verify inEnvelopeMode field is true, setting the when instance for RequestEnvelope usage
    var inEnvelopeModeField = WhenRequest.class.getDeclaredField("inEnvelopeMode");
    inEnvelopeModeField.setAccessible(true);
    then((boolean) inEnvelopeModeField.get(whenStep)).isTrue();
  }

  @Test
  void retrieving_next_step_with_a_skill_request_will_return_when_instance_for_skill_requests(
      @Mock final SkillRequest skillRequest)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // when invoking for next step with a SkillRequest
    var whenStep = sut.whenRequestIs(skillRequest);
    // then verify the skill field
    var skillField = WhenRequest.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(whenStep)).isEqualTo(skill);
    // then verify requestEnvelope field is of null value
    var requestEnvelopeField = WhenRequest.class.getDeclaredField("requestEnvelope");
    requestEnvelopeField.setAccessible(true);
    then(requestEnvelopeField.get(whenStep)).isNull();
    // then verify skillRequest field is the SkillRequest argument passed
    var skillRequestField = WhenRequest.class.getDeclaredField("skillRequest");
    skillRequestField.setAccessible(true);
    then(skillRequestField.get(whenStep)).isEqualTo(skillRequest);
    // then verify inEnvelopeMode field is false, setting the when instance for SkillRequest usage
    var inEnvelopeModeField = WhenRequest.class.getDeclaredField("inEnvelopeMode");
    inEnvelopeModeField.setAccessible(true);
    then((boolean) inEnvelopeModeField.get(whenStep)).isFalse();
  }

  @Test
  void retrieving_next_step_with_a_json_string_will_return_when_instance_for_skill_requests()
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // when invoking for next step with a json String
    var whenStep = sut.whenRequestIs(dummyJsonRequestString);
    // then verify the skill field
    var skillField = WhenRequest.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(whenStep)).isEqualTo(skill);
    // then verify requestEnvelope field is of null value
    var requestEnvelopeField = WhenRequest.class.getDeclaredField("requestEnvelope");
    requestEnvelopeField.setAccessible(true);
    then(requestEnvelopeField.get(whenStep)).isNull();
    // then verify skillRequest field as raw is the String argument passed
    var skillRequestField = WhenRequest.class.getDeclaredField("skillRequest");
    skillRequestField.setAccessible(true);
    then(((SkillRequest) skillRequestField.get(whenStep)).getRawRequest())
        .isEqualTo(dummyJsonRequestByte);
    // then verify inEnvelopeMode field is false, setting the when instance for SkillRequest usage
    var inEnvelopeModeField = WhenRequest.class.getDeclaredField("inEnvelopeMode");
    inEnvelopeModeField.setAccessible(true);
    then((boolean) inEnvelopeModeField.get(whenStep)).isFalse();
  }

  @Test
  void retrieving_next_step_with_a_json_byte_array_will_return_when_instance_for_skill_requests()
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // when invoking for next step a json byte array
    var whenStep = sut.whenRequestIs(dummyJsonRequestByte);
    // then verify the skill field
    var skillField = WhenRequest.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(whenStep)).isEqualTo(skill);
    // then verify requestEnvelope field is of null value
    var requestEnvelopeField = WhenRequest.class.getDeclaredField("requestEnvelope");
    requestEnvelopeField.setAccessible(true);
    then(requestEnvelopeField.get(whenStep)).isNull();
    // then verify skillRequest field as raw is the byte[] argument passed
    var skillRequestField = WhenRequest.class.getDeclaredField("skillRequest");
    skillRequestField.setAccessible(true);
    then(((SkillRequest) skillRequestField.get(whenStep)).getRawRequest())
        .isEqualTo(dummyJsonRequestByte);
    // then verify inEnvelopeMode field is false, setting the when instance for SkillRequest usage
    var inEnvelopeModeField = WhenRequest.class.getDeclaredField("inEnvelopeMode");
    inEnvelopeModeField.setAccessible(true);
    then((boolean) inEnvelopeModeField.get(whenStep)).isFalse();
  }
}
