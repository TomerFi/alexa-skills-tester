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

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Verify interaction with the skill using the json content as string type. */
@Tag("integration-tests")
final class Verify_Interaction_With_The_Skill_Using_Json_Strings_Test {
  private Skill sut;

  @BeforeEach
  void initializeSkill() {
    sut = Skills.standard()
        .addRequestHandler(new LaunchRequestHandlerImpl())
        .addRequestHandler(new MyNameIntentRequestHandler())
        .build();
  }

  @Test
  void using_request_json_as_string_type() {
    // verify interaction with the skill
    givenSkill(sut)
        .whenRequestIs(buildLaunchRequestJsonString())
        .thenResponseShould()
            .waitForFollowup()
            .haveOutputSpeechOf("What is your name?")
            .haveRepromptSpeechOf("Please tell me your name.")
        .followupWith(buildMyNameIntentRequestJsonString("tomer"))
        .thenResponseShould()
            .haveOutputSpeechOf("Nice to meet you tomer!")
            .and()
            .notWaitForFollowup();
  }

  private String buildLaunchRequestJsonString() {
    return "{"
        + "\"version\": \"1.0\","
        + "\"session\": {"
        + "\"new\": true"
          + "},"
        + "\"context\": {"
          + "\"System\": {}"
        + "},"
        + "\"request\": {"
          + "\"type\": \"LaunchRequest\","
          + "\"requestId\": \"amzn1.echo-api.request.fake-request-id3\","
          + "\"timestamp\": \"2021-02-11T15:30:00Z\","
          + "\"locale\": \"en-US\""
          + "}"
        + "}";
  }

  private String buildMyNameIntentRequestJsonString(final String name) {
    return String.format("{"
        + "\"version\": \"1.0\","
        + "\"session\": {"
        + "\"new\": false"
          + "},"
        + "\"context\": {"
          + "\"System\": {}"
          + "},"
        + "\"request\": {"
          + "\"type\": \"IntentRequest\","
          + "\"requestId\": \"amzn1.echo-api.request.fake-request-id3\","
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
  }
}
