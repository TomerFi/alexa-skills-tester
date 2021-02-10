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
package info.tomfi.alexa.skillstester.steps.impl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;
import static org.mockito.BDDMockito.given;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.model.ui.OutputSpeech;
import com.amazon.ask.model.ui.PlainTextOutputSpeech;
import com.amazon.ask.model.ui.Reprompt;
import com.amazon.ask.model.ui.SsmlOutputSpeech;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Then response step, assertion method haveRepromptSpeechThatStartsWith test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Assertion_Method_haveRepromptSpeechThatStartsWith_Test {
  @Mock private Skill skill;
  @Mock private RequestEnvelope requestEnvelope;
  @Mock private ResponseEnvelope responseEnvelope;
  @InjectMocks private ThenResponseImpl sut;

  @Test
  void asserting_a_correct_reprompt_speech_with_a_plain_type_will_keep_ongoing_assertion(
      @Mock final Response response,
      @Mock final PlainTextOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getText()).willReturn("fake speech");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveRepromptSpeechThatStartsWith("fake")).isEqualTo(sut);
  }

  @Test
  void asserting_a_correct_reprompt_speech_with_an_ssml_type_will_keep_ongoing_assertion(
      @Mock final Response response,
      @Mock final SsmlOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getSsml()).willReturn("<speak>fake<break time='3s'/> speech</speak>");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveRepromptSpeechThatStartsWith("fake")).isEqualTo(sut);
  }

  @Test
  void asserting_a_reprompt_specch_with_no_reprompt_object_will_throw_an_assertion_error(
      @Mock final Response response) {
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatStartsWith("speech"))
        .withMessage("Reprompt object is null");
  }

  @Test
  void asserting_a_reprompt_speech_with_an_unknown_speech_type_will_throw_an_assertion_error(
      @Mock final Response response,
      @Mock final OutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatStartsWith("fake"))
        .withMessage("Reprompt speech is empty");
  }

  @Test
  void asserting_a_wrong_reprompt_speech_with_a_plain_type_will_throw_an_assertion_error(
      @Mock final Response response,
      @Mock final PlainTextOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getText()).willReturn("great fake speech number 1");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatStartsWith("wrong fake"))
        .withMessage("Reprompt speech 'great fake speech number 1' should start with 'wrong fake'");
  }

  @Test
  void asserting_a_wrong_reprompt_speech_with_an_ssml_type_will_throw_an_assertion_error(
      @Mock final Response response,
      @Mock final SsmlOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getSsml())
        .willReturn(
            "<speak>great <emphasis level='strong'>fake speech</emphasis> number 1</speak>");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatStartsWith("wrong fake"))
        .withMessage("Reprompt speech 'great fake speech number 1' should start with 'wrong fake'");
  }
}
