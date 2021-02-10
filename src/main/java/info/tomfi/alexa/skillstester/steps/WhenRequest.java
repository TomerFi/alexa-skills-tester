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

/** When request step, encapsulating the Skill and the RequestEnvelope. */
public abstract class WhenRequest {
  protected final Skill skill;
  protected final RequestEnvelope requestEnvelope;

  protected WhenRequest(final Skill setSkill, final RequestEnvelope setRequestEnvelope) {
    skill = setSkill;
    requestEnvelope = setRequestEnvelope;
  }

  /**
   * Send the RequestEnvelope to the Skill and load the next Then step with response.
   *
   * @return the next Then step of the fluent api.
   */
  public abstract ThenResponse thenResponseShould();
}
