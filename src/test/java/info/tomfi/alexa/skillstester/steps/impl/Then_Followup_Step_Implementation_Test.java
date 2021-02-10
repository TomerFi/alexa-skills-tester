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
import static org.mockito.BDDMockito.given;

import com.amazon.ask.Skill;
import com.amazon.ask.model.Application;
import com.amazon.ask.model.Context;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.model.Session;
import com.amazon.ask.model.User;
import info.tomfi.alexa.skillstester.steps.ThenResponse;
import java.util.Map;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Then followup step, encapsulating the Skill, implementing the next Then step logic test cases.
 */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Then_Followup_Step_Implementation_Test {
  @Mock private Skill skill;
  @Mock private ResponseEnvelope responseEnvelope;
  @Mock private RequestEnvelope previousRequestEnvelope;
  @Mock private Request followupRequest;
  @InjectMocks private ThenFollowupImpl sut;

  @Test
  void the_next_then_step_will_instantiate_a_then_response_instance_encapsulating_the_arguments(
      @Mock final Session previousSession,
      @Mock final Context previousContext,
      @Mock final Application previousApplication,
      @Mock final User previousUser,
      @Mock final ResponseEnvelope nextResponseEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // stub response envelope with a fake session attributes map
    var responseSessionAttributes = Map.of("SessionAttribKey1", (Object) "SessionAttribValue1");
    given(responseEnvelope.getSessionAttributes()).willReturn(responseSessionAttributes);
    // the previous session
    given(previousSession.getApplication()).willReturn(previousApplication);
    given(previousSession.getSessionId()).willReturn("amzn1.echo-api.session.fake-session-id");
    given(previousSession.getUser()).willReturn(previousUser);
    // stub previous request envelope
    given(previousRequestEnvelope.getContext()).willReturn(previousContext);
    given(previousRequestEnvelope.getSession()).willReturn(previousSession);
    given(previousRequestEnvelope.getVersion()).willReturn("1.0");
    // create argument matcher for stubbing the expected new request envelope
    ArgumentMatcher<RequestEnvelope> argMatcher =
        re ->
            re.getContext().equals(previousContext)
                && re.getRequest().equals(followupRequest)
                && re.getVersion().equals("1.0")
                && !re.getSession().getNew()
                && re.getSession().getApplication().equals(previousApplication)
                && re.getSession().getAttributes().containsKey("SessionAttribKey1")
                && re.getSession()
                    .getAttributes()
                    .get("SessionAttribKey1")
                    .equals("SessionAttribValue1")
                && re.getSession().getSessionId().equals("amzn1.echo-api.session.fake-session-id")
                && re.getSession().getUser().equals(previousUser);
    // stub skill with mock response envelope when matched with the expected argument
    given(skill.invoke(argThat(argMatcher))).willReturn(nextResponseEnvelope);
    // when invoking for next step
    var thenStep = sut.thenResponseShould();
    // then verify the skill field
    var skillField = ThenResponse.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(thenStep)).isEqualTo(skill);
    // then verify requestEnvelope field
    var requestEnvelopeField = ThenResponse.class.getDeclaredField("requestEnvelope");
    requestEnvelopeField.setAccessible(true);
    then(argMatcher.matches((RequestEnvelope) requestEnvelopeField.get(thenStep))).isTrue();
    // then verify responseEnvelope field
    var responseEnvelopeField = ThenResponse.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(thenStep)).isEqualTo(nextResponseEnvelope);
  }
}
