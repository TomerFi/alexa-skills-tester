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
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.model.ui.Card;
import com.amazon.ask.model.ui.OutputSpeech;
import com.amazon.ask.model.ui.PlainTextOutputSpeech;
import com.amazon.ask.model.ui.SimpleCard;
import com.amazon.ask.model.ui.SsmlOutputSpeech;
import com.amazon.ask.model.ui.StandardCard;
import com.amazon.ask.request.SkillRequest;
import java.util.Map;
import java.util.Optional;

/** Then response step, encapsulating the Skill, the RequestEnvelope, and the ResponseEnvelope. */
public abstract class ThenResponse {
  protected final Skill skill;
  protected final ResponseEnvelope responseEnvelope;

  protected ThenResponse(final Skill setSkill, final ResponseEnvelope setResponseEnvelope) {
    skill = setSkill;
    responseEnvelope = setResponseEnvelope;
  }

  /**
   * Optionally configure a followup Request to the current response.
   *
   * @param requestEnvelope the followup request envelope.
   * @return the next Then step of the fluent api.
   */
  public abstract ThenFollowup thenFollowupWith(RequestEnvelope requestEnvelope);

  /**
   * Optionally configure a followup Request to the current response.
   *
   * @param requestJsonString the followup request json String.
   * @return the next Then step of the fluent api.
   */
  public abstract ThenFollowup thenFollowupWith(String requestJsonString);

  /**
   * Optionally configure a followup Request to the current response.
   *
   * @param requestJsonByte the followup request json byte array.
   * @return the next Then step of the fluent api.
   */
  public abstract ThenFollowup thenFollowupWith(byte[] requestJsonByte);

  /**
   * Optionally configure a followup Request to the current response.
   *
   * @param skillRequest the followup skill request.
   * @return the next Then step of the fluent api.
   */
  public abstract ThenFollowup thenFollowupWith(SkillRequest skillRequest);

  /**
   * Assert the skill response has output speech that is equal to testSpeech.
   *
   * @param testSpeech the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveOutputSpeechOf(String testSpeech);

  /**
   * Assert the skill response has output speech that starts with testSpeech.
   *
   * @param testSpeech the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveOutputSpeechThatStartsWith(String testSpeech);

  /**
   * Assert the skill response has output speech that ends with testSpeech.
   *
   * @param testSpeech the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveOutputSpeechThatEndsWith(String testSpeech);

  /**
   * Assert the skill response has output speech that contains testSpeech.
   *
   * @param testSpeech the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveOutputSpeechThatContains(String testSpeech);

  /**
   * Assert the skill response has reprompt speech that is equal to testSpeech.
   *
   * @param testSpeech the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveRepromptSpeechOf(String testSpeech);

  /**
   * Assert the skill response has reprompt speech that starts with testSpeech.
   *
   * @param testSpeech the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveRepromptSpeechThatStartsWith(String testSpeech);

  /**
   * Assert the skill response has reprompt speech that ends with testSpeech.
   *
   * @param testSpeech the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveRepromptSpeechThatEndsWith(String testSpeech);

  /**
   * Assert the skill response has reprompt speech that contains testSpeech.
   *
   * @param testSpeech the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveRepromptSpeechThatContains(String testSpeech);

  /**
   * Assert the session is closed and not waiting for followup requests (ShouldEndSession is true).
   *
   * @return this instance for further assertions.
   */
  public abstract ThenResponse notWaitForFollowup();

  /**
   * Assert the session is open and waiting for followup requests (ShouldEndSession is false).
   *
   * @return this instance for further assertions.
   */
  public abstract ThenResponse waitForFollowup();

  /**
   * Assert the response has no reprompt object.
   *
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveNoReprompt();

  /**
   * Assert the response has no card object.
   *
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveNoCard();

  /**
   * Assert the skill response has a card with a title that is equal to testTitle.
   *
   * @param testTitle the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveCardTitleOf(String testTitle);

  /**
   * Assert the skill response has a card with a title that starts with testTitle.
   *
   * @param testTitle the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveCardTitleThatStartsWith(String testTitle);

  /**
   * Assert the skill response has a card with a title that ends with testTitle.
   *
   * @param testTitle the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveCardTitleThatEndsWith(String testTitle);

  /**
   * Assert the skill response has a card with a title that contains testTitle.
   *
   * @param testTitle the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveCardTitleThatContains(String testTitle);

  /**
   * Assert the skill response has a card with a text content that is equal to testTitle.
   *
   * @param testText the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveCardTextOf(String testText);

  /**
   * Assert the skill response has a card with a text content that starts with testText.
   *
   * @param testText the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveCardTextThatStartsWith(String testText);

  /**
   * Assert the skill response has a card with a text content that ends with testText.
   *
   * @param testText the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveCardTextThatEndsWith(String testText);

  /**
   * Assert the skill response has a card with a text content that contains testText.
   *
   * @param testText the String to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveCardTextThatContains(String testText);

  /**
   * Assert the skill response has no session attributes.
   *
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveNoSessionAttributes();

  /**
   * Assert the response's session attributes map contains a key with a value.
   *
   * @param key the key to lookup.
   * @param value the value to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveSessionAttributeOf(String key, Object value);

  /**
   * Assert the response's session attributes map contains all the entries in values.
   *
   * @param values the map of entries to match against.
   * @return this instance for further assertions.
   */
  public abstract ThenResponse haveSessionAttributesOf(Map<String, Object> values);

  /**
   * A syntax sugar method, does nothing but return this instance.
   *
   * @return this instance for further assertions.
   */
  public abstract ThenResponse and();

  /**
   * Utility method for extracting plain text from either a PlainTextOutputSpeech or an
   * SsmlOutputSpeech instances.
   *
   * @param speechObject the OutputSpeech implementation to extract the text from.
   * @return an optionally nullable String of the extracted text.
   */
  protected final Optional<String> extractOutputSpeech(final OutputSpeech speechObject) {
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
  protected final Optional<String> extractCardTitle(final Card responseCard) {
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
  protected final Optional<String> extractCardText(final Card responseCard) {
    String retText = null;
    if (responseCard instanceof SimpleCard) {
      retText = ((SimpleCard) responseCard).getContent();
    } else if (responseCard instanceof StandardCard) {
      retText = ((StandardCard) responseCard).getText();
    }
    return Optional.ofNullable(retText);
  }
}
