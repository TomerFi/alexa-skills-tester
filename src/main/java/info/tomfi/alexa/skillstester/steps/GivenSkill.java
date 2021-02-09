package info.tomfi.alexa.skillstester.steps;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;

public abstract class GivenSkill {
  protected final Skill skill;

  protected GivenSkill(final Skill setSkill) {
    skill = setSkill;
  }

  public abstract WhenRequest whenRequestIs(RequestEnvelope requestEnvelope);
}
