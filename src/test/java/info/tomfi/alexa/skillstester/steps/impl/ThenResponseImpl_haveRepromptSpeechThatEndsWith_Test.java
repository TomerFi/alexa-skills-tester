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

/** Assertion method haveRepromptSpeechThatEndsWith test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class ThenResponseImpl_haveRepromptSpeechThatEndsWith_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_reprompt_speech_ends_with_plain_text_will_keep_ongoing_assertion(
      @Mock final Response response,
      @Mock final PlainTextOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getText()).willReturn("fake speech");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveRepromptSpeechThatEndsWith("speech")).isEqualTo(sut);
  }

  @Test
  void asserting_reprompt_speech_ends_with_ssml_will_keep_ongoing_assertion(
      @Mock final Response response,
      @Mock final SsmlOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getSsml())
        .willReturn("<speak><prosody volume='x-loud'>fake</prosody> speech</speak>");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveRepromptSpeechThatEndsWith("speech")).isEqualTo(sut);
  }

  @Test
  void asserting_reprompt_speech_ends_with_no_reprompt_object_will_throw_an_assertion_error(
      @Mock final Response response) {
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatEndsWith("speech"))
        .withMessage("Reprompt object is null");
  }

  @Test
  void asserting_reprompt_speech_ends_with_unknown_speech_type_will_throw_an_assertion_error(
      @Mock final Response response,
      @Mock final OutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatEndsWith("speech"))
        .withMessage("Reprompt speech is empty");
  }

  @Test
  void asserting_reprompt_speech_ends_with_plain_text_with_wrong_text_throws_assertion_error(
      @Mock final Response response,
      @Mock final PlainTextOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getText()).willReturn("great fake speech number 1");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatEndsWith("number 231"))
        .withMessage("Reprompt speech 'great fake speech number 1' should end with 'number 231'");
  }

  @Test
  void asserting_reprompt_speech_ends_with_ssml_with_wrong_text_throws_assertion_error(
      @Mock final Response response,
      @Mock final SsmlOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getSsml()).willReturn("<speak>great <p>fake speech</p> number 1</speak>");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatEndsWith("number 231"))
        .withMessage("Reprompt speech 'great fake speech number 1' should end with 'number 231'");
  }
}
