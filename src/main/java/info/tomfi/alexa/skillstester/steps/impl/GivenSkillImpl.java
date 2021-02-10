package info.tomfi.alexa.skillstester.steps.impl;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
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
}
