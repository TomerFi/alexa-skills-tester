/*
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
package info.tomfi.alexa.skillstester.steps.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.request.SkillRequest;
import info.tomfi.alexa.skillstester.steps.FollowingUp;
import info.tomfi.alexa.skillstester.steps.ThenResponse;
import java.util.Map;

/**
 * Then step, encapsulating the Skill, implementing assertion methods and the next optionally
 * Followup step logic.
 */
public final class ThenResponseImpl extends ThenResponse {
  public ThenResponseImpl(final Skill skill, final ResponseEnvelope responseEnvelope) {
    super(skill, responseEnvelope);
  }

  @Override
  public FollowingUp followingUpWith(final RequestEnvelope followupRequestEnvelope) {
    waitForFollowup();
    return new FollowingUpImpl(skill, responseEnvelope, followupRequestEnvelope);
  }

  @Override
  public FollowingUp followingUpWith(final String followupJsonString) {
    waitForFollowup();
    return new FollowingUpImpl(skill, responseEnvelope, followupJsonString);
  }

  @Override
  public FollowingUp followingUpWith(final byte[] followupJsonByte) {
    waitForFollowup();
    return new FollowingUpImpl(skill, responseEnvelope, followupJsonByte);
  }

  @Override
  public FollowingUp followingUpWith(final SkillRequest followupSkillRequest) {
    waitForFollowup();
    return new FollowingUpImpl(skill, responseEnvelope, followupSkillRequest);
  }

  @Override
  public ThenResponseImpl haveOutputSpeechOf(final String testSpeech) {
    var optText = extractOutputSpeech(responseEnvelope.getResponse().getOutputSpeech());
    assert optText.isPresent() : "Output speech is empty";
    assert optText.get().equals(testSpeech)
        : String.format("Output speech '%s' is not '%s'", optText.get(), testSpeech);
    return this;
  }

  @Override
  public ThenResponseImpl haveOutputSpeechThatStartsWith(final String testSpeech) {
    var optText = extractOutputSpeech(responseEnvelope.getResponse().getOutputSpeech());
    assert optText.isPresent() : "Output speech is empty";
    assert optText.get().startsWith(testSpeech)
        : String.format("Output speech '%s' should start with '%s'", optText.get(), testSpeech);
    return this;
  }

  @Override
  public ThenResponseImpl haveOutputSpeechThatEndsWith(final String testSpeech) {
    var optText = extractOutputSpeech(responseEnvelope.getResponse().getOutputSpeech());
    assert optText.isPresent() : "Output speech is empty";
    assert optText.get().endsWith(testSpeech)
        : String.format("Output speech '%s' should end with '%s'", optText.get(), testSpeech);
    return this;
  }

  @Override
  public ThenResponseImpl haveOutputSpeechThatContains(final String testSpeech) {
    var optText = extractOutputSpeech(responseEnvelope.getResponse().getOutputSpeech());
    assert optText.isPresent() : "Output speech is empty";
    assert optText.get().contains(testSpeech)
        : String.format("Output speech '%s' does not contain '%s'", optText.get(), testSpeech);
    return this;
  }

  @Override
  public ThenResponseImpl haveRepromptSpeechOf(final String testSpeech) {
    var reprompt = responseEnvelope.getResponse().getReprompt();
    assert nonNull(reprompt) : "Reprompt object is null";
    var optText = extractOutputSpeech(reprompt.getOutputSpeech());
    assert optText.isPresent() : "Reprompt speech is empty";
    assert optText.get().equals(testSpeech)
        : String.format("Reprompt speech '%s' is not '%s'", optText.get(), testSpeech);
    return this;
  }

  @Override
  public ThenResponseImpl haveRepromptSpeechThatStartsWith(final String testSpeech) {
    var reprompt = responseEnvelope.getResponse().getReprompt();
    assert nonNull(reprompt) : "Reprompt object is null";
    var optText = extractOutputSpeech(reprompt.getOutputSpeech());
    assert optText.isPresent() : "Reprompt speech is empty";
    assert optText.get().startsWith(testSpeech)
        : String.format("Reprompt speech '%s' should start with '%s'", optText.get(), testSpeech);
    return this;
  }

  @Override
  public ThenResponseImpl haveRepromptSpeechThatEndsWith(final String testSpeech) {
    var reprompt = responseEnvelope.getResponse().getReprompt();
    assert nonNull(reprompt) : "Reprompt object is null";
    var optText = extractOutputSpeech(reprompt.getOutputSpeech());
    assert optText.isPresent() : "Reprompt speech is empty";
    assert optText.get().endsWith(testSpeech)
        : String.format("Reprompt speech '%s' should end with '%s'", optText.get(), testSpeech);
    return this;
  }

  @Override
  public ThenResponseImpl haveRepromptSpeechThatContains(final String testSpeech) {
    var reprompt = responseEnvelope.getResponse().getReprompt();
    assert nonNull(reprompt) : "Reprompt object is null";
    var optText = extractOutputSpeech(reprompt.getOutputSpeech());
    assert optText.isPresent() : "Reprompt speech is empty";
    assert optText.get().contains(testSpeech)
        : String.format("Reprompt speech '%s' does not contain '%s'", optText.get(), testSpeech);
    return this;
  }

  @Override
  public ThenResponseImpl notWaitForFollowup() {
    assert responseEnvelope.getResponse().getShouldEndSession() : "Session is marked as open";
    return this;
  }

  @Override
  public ThenResponseImpl waitForFollowup() {
    assert !responseEnvelope.getResponse().getShouldEndSession() : "Session is marked as closed";
    return this;
  }

  @Override
  public ThenResponseImpl beEmpty() {
    assert isNull(responseEnvelope.getResponse()) : "Response object is not empty";
    return this;
  }

  @Override
  public ThenResponseImpl haveNoOutputSpeech() {
    assert isNull(responseEnvelope.getResponse().getOutputSpeech()) : "Found output speech object";
    return this;
  }

  @Override
  public ThenResponseImpl haveNoReprompt() {
    assert isNull(responseEnvelope.getResponse().getReprompt()) : "Found reprompt object";
    return this;
  }

  @Override
  public ThenResponseImpl haveNoCard() {
    assert isNull(responseEnvelope.getResponse().getCard()) : "Found card object";
    return this;
  }

  @Override
  public ThenResponseImpl haveCardTitleOf(final String testTitle) {
    var card = responseEnvelope.getResponse().getCard();
    assert nonNull(card) : "Card object is null";
    var optTitle = extractCardTitle(card);
    assert optTitle.isPresent() : "Card title is empty";
    assert optTitle.get().equals(testTitle)
        : String.format("Card title '%s' is not '%s'", optTitle.get(), testTitle);
    return this;
  }

  @Override
  public ThenResponseImpl haveCardTitleThatStartsWith(final String testTitle) {
    var card = responseEnvelope.getResponse().getCard();
    assert nonNull(card) : "Card object is null";
    var optTitle = extractCardTitle(card);
    assert optTitle.isPresent() : "Card title is empty";
    assert optTitle.get().startsWith(testTitle)
        : String.format("Card title '%s' should start with '%s'", optTitle.get(), testTitle);
    return this;
  }

  @Override
  public ThenResponseImpl haveCardTitleThatEndsWith(final String testTitle) {
    var card = responseEnvelope.getResponse().getCard();
    assert nonNull(card) : "Card object is null";
    var optTitle = extractCardTitle(card);
    assert optTitle.isPresent() : "Card title is empty";
    assert optTitle.get().endsWith(testTitle)
        : String.format("Card title '%s' should end with '%s'", optTitle.get(), testTitle);
    return this;
  }

  @Override
  public ThenResponseImpl haveCardTitleThatContains(final String testTitle) {
    var card = responseEnvelope.getResponse().getCard();
    assert nonNull(card) : "Card object is null";
    var optTitle = extractCardTitle(card);
    assert optTitle.isPresent() : "Card title is empty";
    assert optTitle.get().contains(testTitle)
        : String.format("Card title '%s' does not contain '%s'", optTitle.get(), testTitle);
    return this;
  }

  @Override
  public ThenResponseImpl haveCardTextOf(final String testText) {
    var card = responseEnvelope.getResponse().getCard();
    assert nonNull(card) : "Card object is null";
    var optText = extractCardText(card);
    assert optText.isPresent() : "Card text is empty";
    assert optText.get().equals(testText)
        : String.format("Card text '%s' is not '%s'", optText.get(), testText);
    return this;
  }

  @Override
  public ThenResponseImpl haveCardTextThatStartsWith(final String testText) {
    var card = responseEnvelope.getResponse().getCard();
    assert nonNull(card) : "Card object is null";
    var optText = extractCardText(card);
    assert optText.isPresent() : "Card text is empty";
    assert optText.get().startsWith(testText)
        : String.format("Card text '%s' should start with '%s'", optText.get(), testText);
    return this;
  }

  @Override
  public ThenResponseImpl haveCardTextThatEndsWith(final String testText) {
    var card = responseEnvelope.getResponse().getCard();
    assert nonNull(card) : "Card object is null";
    var optText = extractCardText(card);
    assert optText.isPresent() : "Card text is empty";
    assert optText.get().endsWith(testText)
        : String.format("Card text '%s' should end with '%s'", optText.get(), testText);
    return this;
  }

  @Override
  public ThenResponseImpl haveCardTextThatContains(final String testText) {
    var card = responseEnvelope.getResponse().getCard();
    assert nonNull(card) : "Card object is null";
    var optText = extractCardText(card);
    assert optText.isPresent() : "Card text is empty";
    assert optText.get().contains(testText)
        : String.format("Card text '%s' does not contain '%s'", optText.get(), testText);
    return this;
  }

  @Override
  public ThenResponseImpl haveNoSessionAttributes() {
    assert isNull(responseEnvelope.getSessionAttributes())
        || responseEnvelope.getSessionAttributes().isEmpty()
        : "Found session attributes";
    return this;
  }

  @Override
  public ThenResponseImpl haveSessionAttributeOf(final String key, final Object value) {
    var attribs = responseEnvelope.getSessionAttributes();
    assert !attribs.isEmpty() : "Session attributes map is empty";
    assert attribs.containsKey(key)
        : String.format("Session attributes map does not contain key '%s'", key);
    assert attribs.get(key).equals(value)
        : String.format("Session attribute value of '%s', is not '%s'", key, value.toString());
    return this;
  }

  @Override
  public ThenResponseImpl haveSessionAttributesOf(final Map<String, Object> values) {
    assert values.size() > 0 : "Entries to verify are empty";
    var attribs = responseEnvelope.getSessionAttributes();
    assert !attribs.isEmpty() : "Session attributes map is empty";
    for (var entry : values.entrySet()) {
      assert attribs.containsKey(entry.getKey())
          : String.format("Session attributes map does not contain key '%s'", entry.getKey());
      assert attribs.get(entry.getKey()).equals(entry.getValue())
          : String.format(
              "Session attribute value of '%s', is not '%s'",
              entry.getKey(), entry.getValue().toString());
    }
    return this;
  }

  @Override
  public ThenResponseImpl and() {
    return this;
  }
}
