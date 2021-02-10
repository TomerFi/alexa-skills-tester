package info.tomfi.alexa.skillstester.steps;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;

/** When request step, encapsulating the Skill and the RequestEnvelope. */
public abstract class WhenRequest {
  protected final Skill skill;
  protected final RequestEnvelope requestEnvelope;

  protected WhenRequest(final Skill setSkill, final RequestEnvelope setRequestEnvelope) {
    skill = setSkill;
    requestEnvelope = setRequestEnvelope;
  }

  /**
   * Send the RequestEnvelope to the Skill and load the next Then step with response.
   *
   * @return the next Then step of the fluent api.
   */
  public abstract ThenResponse thenResponseShould();
}
