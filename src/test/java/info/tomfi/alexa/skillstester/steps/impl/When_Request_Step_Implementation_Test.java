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
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.request.SkillRequest;
import com.amazon.ask.response.SkillResponse;
import info.tomfi.alexa.skillstester.steps.ThenResponse;
import java.util.Arrays;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/** When step of the fluent api, implementing the next Then step logic test cases. */
@Tag("unit-tests")
final class When_Request_Step_Implementation_Test extends FluentStepsFixtures {
  @Test
  void instantiating_with_a_request_envelope_and_retrieving_next_step_will_return_a_then_instance(
      @Mock final RequestEnvelope requestEnvelope, @Mock final ResponseEnvelope responseEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    var sut = new WhenRequestImpl(skill, requestEnvelope);
    // stub skill with mock response envelope
    given(skill.invoke(eq(requestEnvelope))).willReturn(responseEnvelope);
    // when invoking for next step
    var thenStep = sut.thenResponseShould();
    // then verify the skill field
    var skillField = ThenResponse.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(thenStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = ThenResponse.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(thenStep)).isEqualTo(responseEnvelope);
  }

  @Test
  void instantiating_with_a_skill_request_and_retrieving_next_step_will_return_a_then_instance(
      @Mock final SkillRequest skillRequest,
      @Mock final SkillResponse<ResponseEnvelope> skillResponse,
      @Mock final ResponseEnvelope responseEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    var sut = new WhenRequestImpl(skill, skillRequest);
    // stub skill response with response envelope
    given(skillResponse.getResponse()).willReturn(responseEnvelope);
    // stub skill with mock response envelope
    given(skill.execute(eq(skillRequest))).willReturn(skillResponse);
    // when invoking for next step
    var thenStep = sut.thenResponseShould();
    // then verify the skill field
    var skillField = ThenResponse.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(thenStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = ThenResponse.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(thenStep)).isEqualTo(responseEnvelope);
  }

  @Test
  void instantiating_with_a_json_string_and_retrieving_next_step_will_return_a_then_instance(
      @Mock final SkillResponse<ResponseEnvelope> skillResponse,
      @Mock final ResponseEnvelope responseEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    var sut = new WhenRequestImpl(skill, dummyJsonRequestString);
    // stub skill response with response envelope
    given(skillResponse.getResponse()).willReturn(responseEnvelope);
    // stub skill with mock response envelope
    given(skill.execute(argThat(sr -> Arrays.equals(sr.getRawRequest(), dummyJsonRequestByte))))
        .willReturn(skillResponse);
    // when invoking for next step
    var thenStep = sut.thenResponseShould();
    // then verify the skill field
    var skillField = ThenResponse.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(thenStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = ThenResponse.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(thenStep)).isEqualTo(responseEnvelope);
  }

  @Test
  void instantiating_with_a_json_byte_array_and_retrieving_next_step_will_return_a_then_instance(
      @Mock final SkillResponse<ResponseEnvelope> skillResponse,
      @Mock final ResponseEnvelope responseEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    var sut = new WhenRequestImpl(skill, dummyJsonRequestByte);
    // stub skill response with response envelope
    given(skillResponse.getResponse()).willReturn(responseEnvelope);
    // stub skill with mock response envelope
    given(skill.execute(argThat(sr -> Arrays.equals(sr.getRawRequest(), dummyJsonRequestByte))))
        .willReturn(skillResponse);
    // when invoking for next step
    var thenStep = sut.thenResponseShould();
    // then verify the skill field
    var skillField = ThenResponse.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(thenStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = ThenResponse.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(thenStep)).isEqualTo(responseEnvelope);
  }
}
