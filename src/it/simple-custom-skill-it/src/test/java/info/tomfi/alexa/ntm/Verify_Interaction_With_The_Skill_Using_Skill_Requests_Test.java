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
import com.amazon.ask.request.SkillRequest;
import com.amazon.ask.request.impl.BaseSkillRequest;
import java.io.IOException;
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
        .addRequestHandler(new SessionEndedRequestHandlerImpl())
        .build();
  }

  @Test
  void following_up_with_an_intnet_request_json_as_skill_request_type() throws IOException {
    // verify interaction with the skill
    givenSkill(sut)
        .whenRequestIs(readResourceFile("launch_request.json"))
        .thenResponseShould()
            .waitForFollowup()
            .haveOutputSpeechOf("What is your name?")
            .haveRepromptSpeechOf("Please tell me your name.")
        .followingUpWith(readResourceFile("my_name_intent.json"))
        .thenResponseShould()
            .haveOutputSpeechOf("Nice to meet you master!")
            .and()
            .notWaitForFollowup();
  }

  @Test
  void following_up_with_a_session_ended_request_json_as_skill_request_type() throws IOException {
    // verify interaction with the skill
    givenSkill(sut)
        .whenRequestIs(readResourceFile("launch_request.json"))
        .thenResponseShould()
            .waitForFollowup()
            .haveOutputSpeechOf("What is your name?")
            .haveRepromptSpeechOf("Please tell me your name.")
        .followingUpWith(readResourceFile("session_ended.json"))
        .thenResponseShould().beEmpty();
  }

  private SkillRequest readResourceFile(final String fileName) throws IOException {
    return new BaseSkillRequest(
      getClass().getClassLoader().getResourceAsStream(fileName).readAllBytes());
  }
}
