package info.tomfi.alexa.skillstester;

import com.amazon.ask.Skill;
import info.tomfi.alexa.skillstester.steps.GivenSkill;
import info.tomfi.alexa.skillstester.steps.impl.GivenSkillImpl;

public final class SkillsTester {
  public static GivenSkill givenSkill(Skill skill) {
    return new GivenSkillImpl(skill);
  }
}
