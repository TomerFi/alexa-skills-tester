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

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.request.SkillRequest;
import info.tomfi.alexa.skillstester.steps.ThenFollowup;
import info.tomfi.alexa.skillstester.steps.ThenResponse;

/** Then followup step, encapsulating the Skill, implementing the next Then step logic. */
public final class ThenFollowupImpl extends ThenFollowup {
  protected ThenFollowupImpl(
      final Skill skill,
      final ResponseEnvelope responseEnvelope,
      final RequestEnvelope followupRequestEnvelope) {
    super(skill, responseEnvelope, followupRequestEnvelope);
  }

  protected ThenFollowupImpl(
      final Skill skill,
      final ResponseEnvelope responseEnvelope,
      final String followupJsonString) {
    super(skill, responseEnvelope, followupJsonString);
  }

  protected ThenFollowupImpl(
      final Skill skill,
      final ResponseEnvelope responseEnvelope,
      final byte[] followupJsonByte) {
    super(skill, responseEnvelope, followupJsonByte);
  }

  protected ThenFollowupImpl(
      final Skill skill,
      final ResponseEnvelope responseEnvelope,
      final SkillRequest setSkillRequest) {
    super(skill, responseEnvelope, setSkillRequest);
  }

  @Override
  public ThenResponse thenResponseShould() {
    var responseEnvelope = inEnvelopeMode ? skill.invoke(followupRequestEnvelope) : skill.execute(followupSkillRequest).getResponse();
    return new ThenResponseImpl(skill, responseEnvelope);
  }
}
