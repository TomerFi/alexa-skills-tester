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
package info.tomfi.alexa.ntm;

import static info.tomfi.alexa.skillstester.SkillsTester.givenSkill;
import static java.util.stream.Collectors.joining;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Verify interaction with the skill using the json content as string type. */
@Tag("integration-tests")
final class Verify_Interaction_With_The_Skill_Using_Json_Strings_Test {
  private Skill sut;

  @BeforeEach
  void initializeSkill() {
    sut = Skills.standard().addRequestHandler(new LaunchRequestHandlerImpl())
        .addRequestHandler(new MyNameIntentRequestHandler()).addRequestHandler(new SessionEndedRequestHandlerImpl())
        .build();
  }

  @Test
  void following_up_with_an_intnet_request_json_as_string_type() throws IOException, URISyntaxException {
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
  void following_up_with_a_session_ended_request_json_as_string_type() throws IOException, URISyntaxException {
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

  private String readResourceFile(final String fileName) throws IOException, URISyntaxException {
    var lines = Files.readAllLines(
      Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));
    return lines.stream().collect(joining("\n"));
  }
}
