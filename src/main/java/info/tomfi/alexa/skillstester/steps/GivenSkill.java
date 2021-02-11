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
package info.tomfi.alexa.skillstester.steps;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.request.SkillRequest;

/** Given skill step, encapsulating the Skill. */
public abstract class GivenSkill {
  protected final Skill skill;

  protected GivenSkill(final Skill setSkill) {
    skill = setSkill;
  }

  /**
   * Configure the initial request to send to the Skill.
   *
   * @param requestEnvelope the request envelope.
   * @return the next When step of the fluent api.
   */
  public abstract WhenRequest whenRequestIs(RequestEnvelope requestEnvelope);

  /**
   * Configure the initial request to send to the Skill.
   *
   * @param requestJsonString the request json String.
   * @return the next When step of the fluent api.
   */
  public abstract WhenRequest whenRequestIs(String requestJsonString);

  /**
   * Configure the initial request to send to the Skill.
   *
   * @param requestJsonByte the request json byte array.
   * @return the next When step of the fluent api.
   */
  public abstract WhenRequest whenRequestIs(byte[] requestJsonByte);

  /**
   * Configure the initial request to send to the Skill.
   *
   * @param skillRequest the skill request.
   * @return the next When step of the fluent api.
   */
  public abstract WhenRequest whenRequestIs(SkillRequest skillRequest);
}
