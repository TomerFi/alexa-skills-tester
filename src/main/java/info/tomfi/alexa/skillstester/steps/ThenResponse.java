/**
 * Copyright Tomer Figenblat.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/** Then response step, encapsulating the Skill, the RequestEnvelope, and the ResponseEnvelope. */
public abstract class ThenResponse {
  protected final Skill skill;
  protected final RequestEnvelope requestEnvelope;
  protected final ResponseEnvelope responseEnvelope;

  /**
   * Optionally configure a followup Request to the current response.
   *
   * @param request the followup Request.
   * @return the next Then step of the fluent api.
   */
  public abstract ThenFollowup thenFollowupWith(Request request);

  protected ThenResponse(
      final Skill setSkill,
      final RequestEnvelope setRequestEnvelope,
      final ResponseEnvelope setResponseEnvelope) {
    requestEnvelope = setRequestEnvelope;
    skill = setSkill;
    responseEnvelope = setResponseEnvelope;
  }

  /**
   * Utility method for extracting plain text from either a PlainTextOutputSpeech or an
   * SsmlOutputSpeech instances.
   *
   * @param speechObject the OutputSpeech implementation to extract the text from.
   * @return an optionally nullable String of the extracted text.
   */
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

  /**
   * Utility method for extracting card title from either a SimpleCard or a StandardCard instances.
   *
   * @param responseCard the Card implementation to extract the title from.
   * @return an optionally nullable String of the extracted title.
   */
  protected Optional<String> extractCardTitle(final Card responseCard) {
    String retText = null;
    if (responseCard instanceof SimpleCard) {
      retText = ((SimpleCard) responseCard).getTitle();
    } else if (responseCard instanceof StandardCard) {
      retText = ((StandardCard) responseCard).getTitle();
    }
    return Optional.ofNullable(retText);
  }

  /**
   * Utility method for extracting card text from either a SimpleCard or a StandardCard instances.
   *
   * @param responseCard the Card implementation to extract the text from.
   * @return an optionally nullable String of the extracted text.
   */
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
