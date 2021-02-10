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

import static java.nio.charset.StandardCharsets.UTF_8;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.request.SkillRequest;
import com.amazon.ask.request.impl.BaseSkillRequest;

/**
 * Then followup step, encapsulating the Skill, the ResponseEnvelope, the previous RequestEnvelope,
 * and the followup Request.
 */
public abstract class ThenFollowup {
  protected final Skill skill;
  protected final ResponseEnvelope responseEnvelope;
  protected final RequestEnvelope followupRequestEnvelope;
  protected final SkillRequest followupSkillRequest;
  protected final boolean inEnvelopeMode;

  protected ThenFollowup(
      final Skill setSkill,
      final ResponseEnvelope setResponseEnvelope,
      final RequestEnvelope setFollowupRequestEnvelope) {
    skill = setSkill;
    responseEnvelope = setResponseEnvelope;
    followupRequestEnvelope = setFollowupRequestEnvelope;
    followupSkillRequest = null;
    inEnvelopeMode = true;
  }

  protected ThenFollowup(
      final Skill setSkill,
      final ResponseEnvelope setResponseEnvelope,
      final String followupJsonString) {
    skill = setSkill;
    responseEnvelope = setResponseEnvelope;
    followupRequestEnvelope = null;
    followupSkillRequest = new BaseSkillRequest(followupJsonString.getBytes(UTF_8));
    inEnvelopeMode = false;
  }

  protected ThenFollowup(
      final Skill setSkill,
      final ResponseEnvelope setResponseEnvelope,
      final byte[] followupJsonByte) {
    skill = setSkill;
    responseEnvelope = setResponseEnvelope;
    followupRequestEnvelope = null;
    followupSkillRequest = new BaseSkillRequest(followupJsonByte);
    inEnvelopeMode = false;
  }

  protected ThenFollowup(
      final Skill setSkill,
      final ResponseEnvelope setResponseEnvelope,
      final SkillRequest setSkillRequest) {
    skill = setSkill;
    responseEnvelope = setResponseEnvelope;
    followupRequestEnvelope = null;
    followupSkillRequest = setSkillRequest;
    inEnvelopeMode = false;
  }

  /**
   * Process the arguments, create a new RequestEnvelope and send a followup Request to the Skill.
   *
   * @return the next Then step of the fluent api.
   */
  public abstract ThenResponse thenResponseShould();
}
