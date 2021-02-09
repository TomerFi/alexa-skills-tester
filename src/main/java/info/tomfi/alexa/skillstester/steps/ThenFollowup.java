package info.tomfi.alexa.skillstester.steps;

import com.amazon.ask.Skill;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;

public abstract class ThenFollowup {
  protected final Skill skill;
  protected final ResponseEnvelope responseEnvelope;
  protected final RequestEnvelope previousRequestEnvelope;
  protected final Request followupRequest;

  protected ThenFollowup(final Skill setSkill, final ResponseEnvelope setResponseEnvelope, final RequestEnvelope setPreviousRequestEnvelope, final Request setFollowupRequest) {
    skill = setSkill;
    responseEnvelope = setResponseEnvelope;
    previousRequestEnvelope = setPreviousRequestEnvelope;
    followupRequest = setFollowupRequest;
  }

  public abstract ThenResponse thenResponseShould();
}
