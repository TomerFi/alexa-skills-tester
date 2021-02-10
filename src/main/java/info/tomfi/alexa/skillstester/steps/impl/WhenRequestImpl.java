package info.tomfi.alexa.skillstester.steps.impl;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import info.tomfi.alexa.skillstester.steps.ThenResponse;
import info.tomfi.alexa.skillstester.steps.WhenRequest;

/** When step implementation, implementing the next Then step logic. */
public final class WhenRequestImpl extends WhenRequest {
  protected WhenRequestImpl(final Skill skill, final RequestEnvelope requestEnvelope) {
    super(skill, requestEnvelope);
  }

  @Override
  public ThenResponse thenResponseShould() {
    return new ThenResponseImpl(skill, requestEnvelope, skill.invoke(requestEnvelope));
  }
}
