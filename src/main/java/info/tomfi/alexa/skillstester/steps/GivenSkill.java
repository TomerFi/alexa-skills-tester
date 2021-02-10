package info.tomfi.alexa.skillstester.steps;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;

/** Given skill step, encapsulating the Skill. */
public abstract class GivenSkill {
  protected final Skill skill;

  protected GivenSkill(final Skill setSkill) {
    skill = setSkill;
  }

  /**
   * Configure the initial request to send to the Skill.
   *
   * @param requestEnvelope the request.
   * @return the next When step of the fluent api.
   */
  public abstract WhenRequest whenRequestIs(RequestEnvelope requestEnvelope);
}
