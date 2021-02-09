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
import com.amazon.ask.model.ui.SsmlOutputSpeech;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Assertion method haveOutputSpeechThatContains test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class ThenResponseImpl_haveOutputSpeechThatContains_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_output_speech_contains_plain_text_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final PlainTextOutputSpeech speech) {
    given(speech.getText()).willReturn("fake speech test");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveOutputSpeechThatContains("speech")).isEqualTo(sut);
  }

  @Test
  void asserting_output_speech_contains_with_ssml_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final SsmlOutputSpeech speech) {
    given(speech.getSsml()).willReturn("<speak><say-as interpret-as='spell-out'>fake</say-as> speech test</speak>");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveOutputSpeechThatContains("speech")).isEqualTo(sut);
  }

  @Test
  void asserting_output_speech_contains_with_unknown_speech_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final OutputSpeech speech) {
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveOutputSpeechThatContains("speech"))
        .withMessage("Output speech is empty");
  }

  @Test
  void asserting_output_speech_contains_with_plain_text_with_wrong_text_throws_assertion_error(
      @Mock final Response response, @Mock final PlainTextOutputSpeech speech) {
    given(speech.getText()).willReturn("great fake speech number 1");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveOutputSpeechThatContains("number 231"))
        .withMessage("Output speech 'great fake speech number 1' does not contain 'number 231'");
  }

  @Test
  void asserting_output_speech_contains_with_ssml_with_wrong_text_throws_assertion_error(
      @Mock final Response response, @Mock final SsmlOutputSpeech speech) {
    given(speech.getSsml()).willReturn("<speak>great <s>fake speech</s> number 1</speak>");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveOutputSpeechThatContains("number 231"))
        .withMessage("Output speech 'great fake speech number 1' does not contain 'number 231'");
  }
}
