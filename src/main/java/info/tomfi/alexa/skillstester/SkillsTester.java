package info.tomfi.alexa.skillstester;

import com.amazon.ask.Skill;
import info.tomfi.alexa.skillstester.steps.GivenSkill;
import info.tomfi.alexa.skillstester.steps.impl.GivenSkillImpl;

/** Static tools used as starting points for the fluent api. */
public final class SkillsTester {
  private SkillsTester() {
    //
  }

  public static GivenSkill givenSkill(Skill skill) {
    return new GivenSkillImpl(skill);
  }
}
