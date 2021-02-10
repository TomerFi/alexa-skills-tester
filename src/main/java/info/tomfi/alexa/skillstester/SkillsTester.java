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
package info.tomfi.alexa.skillstester;

import com.amazon.ask.Skill;
import info.tomfi.alexa.skillstester.steps.GivenSkill;
import info.tomfi.alexa.skillstester.steps.impl.GivenSkillImpl;

/** Static tools used as starting points for the fluent api. */
public final class SkillsTester {
  private SkillsTester() {
    //
  }

  public static GivenSkill givenSkill(final Skill skill) {
    return new GivenSkillImpl(skill);
  }
}
