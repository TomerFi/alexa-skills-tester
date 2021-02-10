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

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import info.tomfi.alexa.skillstester.steps.WhenRequest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Given step of the fluent api, implementing the next When step logic test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Given_Skill_Step_Implementation_Test {
  @Mock Skill skill;
  @InjectMocks GivenSkillImpl sut;

  @Test
  void the_next_when_step_will_instantiate_a_when_request_instance_encapsulating_the_arguments(
      @Mock final RequestEnvelope requestEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // when invoking for next step
    var whenStep = sut.whenRequestIs(requestEnvelope);
    // then verify the skill field
    var skillField = WhenRequest.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(whenStep)).isEqualTo(skill);
    // then verify requestEnvelope field
    var requestEnvelopeField = WhenRequest.class.getDeclaredField("requestEnvelope");
    requestEnvelopeField.setAccessible(true);
    then(requestEnvelopeField.get(whenStep)).isEqualTo(requestEnvelope);
  }
}
