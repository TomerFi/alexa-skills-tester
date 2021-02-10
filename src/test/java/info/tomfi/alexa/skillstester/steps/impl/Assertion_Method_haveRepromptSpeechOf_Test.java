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

/** Then response step, assertion method haveRepromptSpeechOf test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Assertion_Method_haveRepromptSpeechOf_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_a_correct_reprompt_speech_with_a_plain_type_will_keep_ongoing_assertion(
      @Mock final Response response,
      @Mock final PlainTextOutputSpeech speech,
      @Mock final Reprompt reprompt) {
    given(speech.getText()).willReturn("fake speech");
    given(reprompt.getOutputSpeech()).willReturn(speech);
    given(response.getReprompt()).willReturn(reprompt);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveRepromptSpeechOf("fake speech")).isEqualTo(sut);
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
    then(sut.haveRepromptSpeechOf("fake speech")).isEqualTo(sut);
  }

  @Test
  void asserting_a_reprompt_specch_with_no_reprompt_object_will_throw_an_assertion_error(
      @Mock final Response response) {
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveRepromptSpeechOf("speech"))
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
        .isThrownBy(() -> sut.haveRepromptSpeechOf("fake speech"))
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
        .isThrownBy(() -> sut.haveRepromptSpeechOf("wrong fake speech"))
        .withMessage("Reprompt speech 'great fake speech number 1' is not 'wrong fake speech'");
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
        .isThrownBy(() -> sut.haveRepromptSpeechOf("wrong fake speech"))
        .withMessage("Reprompt speech 'great fake speech number 1' is not 'wrong fake speech'");
  }
}
