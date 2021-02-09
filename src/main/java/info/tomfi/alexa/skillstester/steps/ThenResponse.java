package info.tomfi.alexa.skillstester.steps;

import com.amazon.ask.Skill;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.model.ui.Card;
import com.amazon.ask.model.ui.OutputSpeech;
import com.amazon.ask.model.ui.PlainTextOutputSpeech;
import com.amazon.ask.model.ui.SimpleCard;
import com.amazon.ask.model.ui.SsmlOutputSpeech;
import com.amazon.ask.model.ui.StandardCard;
import java.util.Optional;

public abstract class ThenResponse {
  protected final Skill skill;
  protected final RequestEnvelope requestEnvelope;
  protected final ResponseEnvelope responseEnvelope;

  public abstract ThenFollowup thenFollowupWith(Request request);

  protected ThenResponse(
      final Skill setSkill,
      final RequestEnvelope setRequestEnvelope,
      final ResponseEnvelope setResponseEnvelope) {
    requestEnvelope = setRequestEnvelope;
    skill = setSkill;
    responseEnvelope = setResponseEnvelope;
  }

  protected Optional<String> extractOutputSpeech(final OutputSpeech speechObject) {
    String retText = null;
    if (speechObject instanceof SsmlOutputSpeech) {
      retText = ((SsmlOutputSpeech) speechObject).getSsml().replaceAll("\\<.*?\\>", "");
    }
    if (speechObject instanceof PlainTextOutputSpeech) {
      retText = ((PlainTextOutputSpeech) speechObject).getText();
    }
    return Optional.ofNullable(retText);
  }

  protected Optional<String> extractCardTitle(final Card responseCard) {
    String retText = null;
    if (responseCard instanceof SimpleCard) {
      retText = ((SimpleCard) responseCard).getTitle();
    } else if (responseCard instanceof StandardCard) {
      retText = ((StandardCard) responseCard).getTitle();
    }
    return Optional.ofNullable(retText);
  }

  protected Optional<String> extractCardText(final Card responseCard) {
    String retText = null;
    if (responseCard instanceof SimpleCard) {
      retText = ((SimpleCard) responseCard).getContent();
    } else if (responseCard instanceof StandardCard) {
      retText = ((StandardCard) responseCard).getText();
    }
    return Optional.ofNullable(retText);
  }
}
