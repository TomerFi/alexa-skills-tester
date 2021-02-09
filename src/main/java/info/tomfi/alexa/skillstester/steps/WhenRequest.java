package info.tomfi.alexa.skillstester.steps;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;

public abstract class WhenRequest {
  protected final Skill skill;
  protected final RequestEnvelope requestEnvelope;

  protected WhenRequest(final Skill setSkill, final RequestEnvelope setRequestEnvelope) {
    skill = setSkill;
    requestEnvelope = setRequestEnvelope;
  }

  public abstract ThenResponse thenResponseShould();
}
