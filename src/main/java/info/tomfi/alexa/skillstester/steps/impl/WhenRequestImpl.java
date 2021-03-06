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
package info.tomfi.alexa.skillstester.steps.impl;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.request.SkillRequest;
import info.tomfi.alexa.skillstester.steps.ThenResponse;
import info.tomfi.alexa.skillstester.steps.WhenRequest;

/** When step implementation, implementing the next Then step logic. */
public final class WhenRequestImpl extends WhenRequest {
  protected WhenRequestImpl(final Skill skill, final RequestEnvelope requestEnvelope) {
    super(skill, requestEnvelope);
  }

  protected WhenRequestImpl(final Skill skill, final String requestJsonString) {
    super(skill, requestJsonString);
  }

  protected WhenRequestImpl(final Skill skill, final byte[] requestJsonByte) {
    super(skill, requestJsonByte);
  }

  protected WhenRequestImpl(final Skill skill, final SkillRequest setSkillRequest) {
    super(skill, setSkillRequest);
  }

  @Override
  public ThenResponse thenResponseShould() {
    var responseEnvelope =
        inEnvelopeMode ? skill.invoke(requestEnvelope) : skill.execute(skillRequest).getResponse();
    return new ThenResponseImpl(skill, responseEnvelope);
  }
}
