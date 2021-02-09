package info.tomfi.alexa.skillstester.steps.impl;

import com.amazon.ask.Skill;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.model.Session;
import info.tomfi.alexa.skillstester.steps.ThenFollowup;
import info.tomfi.alexa.skillstester.steps.ThenResponse;

public final class ThenFollowupImpl extends ThenFollowup {
  protected ThenFollowupImpl(final Skill skill, final ResponseEnvelope responseEnvelope, final RequestEnvelope previousRequestEnvelope, final Request followupRequest) {
    super(skill, responseEnvelope, previousRequestEnvelope, followupRequest);
  }

  @Override
  public ThenResponse thenResponseShould() {
    var previousSession = previousRequestEnvelope.getSession();

    var newSession = Session.builder()
        .withApplication(previousSession.getApplication())
        .withAttributes(responseEnvelope.getSessionAttributes())
        .withNew(false)
        .withSessionId(previousSession.getSessionId())
        .withUser(previousSession.getUser())
        .build();

    var newRequestEnvelope = RequestEnvelope.builder()
        .withContext(previousRequestEnvelope.getContext())
        .withRequest(followupRequest)
        .withSession(newSession)
        .withVersion(previousRequestEnvelope.getVersion())
        .build();

    return new ThenResponseImpl(skill, newRequestEnvelope, skill.invoke(newRequestEnvelope));
  }
}
