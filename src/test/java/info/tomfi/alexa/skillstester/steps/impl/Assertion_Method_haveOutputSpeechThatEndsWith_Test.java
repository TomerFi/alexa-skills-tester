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

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;
import static org.mockito.BDDMockito.given;

import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.OutputSpeech;
import com.amazon.ask.model.ui.PlainTextOutputSpeech;
import com.amazon.ask.model.ui.SsmlOutputSpeech;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/** Then response step, assertion method haveOutputSpeechThatEndsWith test cases. */
@Tag("unit-tests")
final class Assertion_Method_haveOutputSpeechThatEndsWith_Test extends AssertionMethodsFixtures {
  @Test
  void asserting_a_correct_output_speech_with_a_plain_type_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final PlainTextOutputSpeech speech) {
    given(speech.getText()).willReturn("fake speech");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveOutputSpeechThatEndsWith("speech")).isEqualTo(sut);
  }

  @Test
  void asserting_a_correct_output_speech_with_an_ssml_type_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final SsmlOutputSpeech speech) {
    given(speech.getSsml())
        .willReturn("<speak><prosody volume='x-loud'>fake</prosody> speech</speak>");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveOutputSpeechThatEndsWith("speech")).isEqualTo(sut);
  }

  @Test
  void asserting_an_output_speech_with_an_unknown_speech_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final OutputSpeech speech) {
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveOutputSpeechThatEndsWith("speech"))
        .withMessage("Output speech is empty");
  }

  @Test
  void asserting_a_wrong_output_speech_with_a_plain_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final PlainTextOutputSpeech speech) {
    given(speech.getText()).willReturn("great fake speech number 1");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveOutputSpeechThatEndsWith("number 231"))
        .withMessage("Output speech 'great fake speech number 1' should end with 'number 231'");
  }

  @Test
  void asserting_a_wrong_output_speech_with_an_ssml_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final SsmlOutputSpeech speech) {
    given(speech.getSsml()).willReturn("<speak>great <p>fake speech</p> number 1</speak>");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveOutputSpeechThatEndsWith("number 231"))
        .withMessage("Output speech 'great fake speech number 1' should end with 'number 231'");
  }
}
