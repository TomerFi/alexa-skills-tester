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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import info.tomfi.alexa.skillstester.steps.ThenResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Then followup step, encapsulating the Skill, implementing the next Then step logic test cases.
 */
@Tag("unit-tests")
final class Then_Followup_Step_Implementation_Test extends FluentStepsFixtures {
  @Mock private ResponseEnvelope responseEnvelope;
  @Mock private RequestEnvelope followRequestEnvelope;
  @InjectMocks private ThenFollowupImpl sut;

  @Test
  void the_next_then_step_will_instantiate_a_then_response_instance_encapsulating_the_arguments(
      @Mock final ResponseEnvelope responseEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // stub skill with mock response envelope when matched with the expected argument
    given(skill.invoke(eq(followRequestEnvelope))).willReturn(responseEnvelope);
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
