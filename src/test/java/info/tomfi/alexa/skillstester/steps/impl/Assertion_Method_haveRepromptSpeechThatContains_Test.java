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

/** Then response step, assertion method haveRepromptSpeechThatContains test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Assertion_Method_haveRepromptSpeechThatContains_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_a_correct_reprompt_speech_with_a_plain_type_will_keep_ongoing_assertion(
      @Mock final Response response,
      @Mock final PlainTextOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getText()).willReturn("fake speech test");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveRepromptSpeechThatContains("speech")).isEqualTo(sut);
  }

  @Test
  void asserting_a_correct_reprompt_speech_with_an_ssml_type_will_keep_ongoing_assertion(
      @Mock final Response response,
      @Mock final SsmlOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getSsml())
        .willReturn("<speak><say-as interpret-as='spell-out'>fake</say-as> speech test</speak>");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveRepromptSpeechThatContains("speech")).isEqualTo(sut);
  }

  @Test
  void asserting_a_reprompt_specch_with_no_reprompt_object_will_throw_an_assertion_error(
      @Mock final Response response) {
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatContains("speech"))
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
        .isThrownBy(() -> sut.haveRepromptSpeechThatContains("speech"))
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
        .isThrownBy(() -> sut.haveRepromptSpeechThatContains("number 231"))
        .withMessage("Reprompt speech 'great fake speech number 1' does not contain 'number 231'");
  }

  @Test
  void asserting_a_wrong_reprompt_speech_with_an_ssml_type_will_throw_an_assertion_error(
      @Mock final Response response,
      @Mock final SsmlOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getSsml()).willReturn("<speak>great <s>fake speech</s> number 1</speak>");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechThatContains("number 231"))
        .withMessage("Reprompt speech 'great fake speech number 1' does not contain 'number 231'");
  }
}
