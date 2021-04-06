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
import info.tomfi.alexa.skillstester.steps.GivenSkill;
import info.tomfi.alexa.skillstester.steps.WhenRequest;

/** Given step implementation, implementing the next When step logic. */
public final class GivenSkillImpl extends GivenSkill {
  public GivenSkillImpl(final Skill skill) {
    super(skill);
  }

  @Override
  public WhenRequest whenRequestIs(final RequestEnvelope requestEnvelope) {
    return new WhenRequestImpl(skill, requestEnvelope);
  }

  @Override
  public WhenRequest whenRequestIs(final String requestJsonString) {
    return new WhenRequestImpl(skill, requestJsonString);
  }

  @Override
  public WhenRequest whenRequestIs(final byte[] requestJsonByte) {
    return new WhenRequestImpl(skill, requestJsonByte);
  }

  @Override
  public WhenRequest whenRequestIs(final SkillRequest skillRequest) {
    return new WhenRequestImpl(skill, skillRequest);
  }
}
