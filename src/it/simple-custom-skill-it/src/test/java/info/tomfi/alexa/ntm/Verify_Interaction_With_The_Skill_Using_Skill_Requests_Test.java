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
import static java.nio.charset.StandardCharsets.UTF_8;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.request.SkillRequest;
import com.amazon.ask.request.impl.BaseSkillRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Verify interaction with the skill using the json content as skill request type. */
@Tag("integration-tests")
final class Verify_Interaction_With_The_Skill_Using_Skill_Requests_Test {
  private Skill sut;

  @BeforeEach
  void initializeSkill() {
    sut = Skills.standard()
        .addRequestHandler(new LaunchRequestHandlerImpl())
        .addRequestHandler(new MyNameIntentRequestHandler())
        .build();
  }

  @Test
  void using_a_skill_request_type() {
    // verify interaction with the skill
    givenSkill(sut)
        .whenRequestIs(buildLaunchSkillRequest())
        .thenResponseShould()
            .waitForFollowup()
            .haveOutputSpeechOf("What is your name?")
            .haveRepromptSpeechOf("Please tell me your name.")
        .followingUpWith(buildMyNameIntentSkillRequest("tomer"))
        .thenResponseShould()
            .haveOutputSpeechOf("Nice to meet you tomer!")
            .and()
            .notWaitForFollowup();
  }

  private SkillRequest buildLaunchSkillRequest() {
    var strRequest = "{"
        + "\"version\": \"1.0\","
        + "\"session\": {"
        + "\"new\": true"
          + "},"
        + "\"context\": {"
          + "\"System\": {}"
        + "},"
        + "\"request\": {"
          + "\"type\": \"LaunchRequest\","
          + "\"requestId\": \"amzn1.echo-api.request.fake-request-id4\","
          + "\"timestamp\": \"2021-02-11T15:30:00Z\","
          + "\"locale\": \"en-US\""
          + "}"
        + "}";
    return new BaseSkillRequest(strRequest.getBytes(UTF_8));
  }

  private SkillRequest buildMyNameIntentSkillRequest(final String name) {
    var strRequest = String.format("{"
        + "\"version\": \"1.0\","
        + "\"session\": {"
        + "\"new\": false"
          + "},"
        + "\"context\": {"
          + "\"System\": {}"
          + "},"
        + "\"request\": {"
          + "\"type\": \"IntentRequest\","
          + "\"requestId\": \"amzn1.echo-api.request.fake-request-id4\","
          + "\"timestamp\": \"2021-02-11T15:30:00Z\","
          + "\"locale\": \"en-US\","
          + "\"intent\": {"
            + "\"name\": \"MyNameIntent\","
            + "\"slots\": {"
              + "\"nameSlot\": {"
                + "\"name\": \"nameSlot\","
                + "\"value\": \"%s\""
                + "}"
              + "}"
            + "}"
          + "}"
        + "}", name);
    return new BaseSkillRequest(strRequest.getBytes(UTF_8));
  }
}
