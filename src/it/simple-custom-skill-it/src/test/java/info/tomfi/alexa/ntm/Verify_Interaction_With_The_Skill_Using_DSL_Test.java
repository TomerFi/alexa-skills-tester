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
package info.tomfi.alexa.ntm;

import static info.tomfi.alexa.skillstester.SkillsTester.givenSkill;
import static java.time.OffsetDateTime.now;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.model.Context;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Session;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.interfaces.system.SystemState;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Verify interaction with the skill using the ask sdk builders to create request envelopes. */
@Tag("integration-tests")
final class Verify_Interaction_With_The_Skill_Using_DSL_Test {
  private Skill sut;

  @BeforeEach
  void initializeSkill() {
    sut = Skills.standard()
        .addRequestHandler(new LaunchRequestHandlerImpl())
        .addRequestHandler(new MyNameIntentRequestHandler())
        .build();
  }

  @Test
  void using_ask_sdk_builders_to_create_request_envelopes() {
    // verify interaction with the skill
    givenSkill(sut)
        .whenRequestIs(buildLaunchRequestEnvelope())
        .thenResponseShould()
            .waitForFollowup()
            .haveOutputSpeechOf("What is your name?")
            .haveRepromptSpeechOf("Please tell me your name.")
        .followingUpWith(buildMyNameIntentRequestEnvelope("master"))
        .thenResponseShould()
            .haveOutputSpeechOf("Nice to meet you master!")
            .and()
            .notWaitForFollowup();
  }

  private RequestEnvelope buildLaunchRequestEnvelope() {
    var launchRequest = LaunchRequest.builder()
        .withLocale("en-US")
        .withRequestId("amzn1.echo-api.request.fake-request-id")
        .withTimestamp(now())
        .build();
    var newSession = Session.builder().withNew(true).build();
    return makeRequestEnvelope(newSession, launchRequest);
  }

  private RequestEnvelope buildMyNameIntentRequestEnvelope(final String name) {
    var nameSlot = Slot.builder().withName("nameSlot").withValue(name).build();
    var myNameIsIntent = Intent.builder()
        .withName("MyNameIntent")
        .withSlots(Map.of("nameSlot", nameSlot))
        .build();
    var followupRequest = IntentRequest.builder()
        .withLocale("en-US")
        .withRequestId("amzn1.echo-api.request.fake-request-id")
        .withIntent(myNameIsIntent)
        .withTimestamp(now())
        .build();
    var followupSession = Session.builder().withNew(false).build();
    return makeRequestEnvelope(followupSession, followupRequest);
  }

  private RequestEnvelope makeRequestEnvelope(final Session session, final Request request) {
    var system = SystemState.builder().build();
    var context = Context.builder().withSystem(system).build();
    return RequestEnvelope.builder()
        .withVersion("1.0")
        .withContext(context)
        .withSession(session)
        .withRequest(request)
        .build();
  }
}
