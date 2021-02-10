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

/** Then response step, assertion method haveOutputSpeechOf test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Assertion_Method_haveOutputSpeechOf_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_a_correct_output_speech_with_a_plain_type_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final PlainTextOutputSpeech speech) {
    given(speech.getText()).willReturn("fake speech");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveOutputSpeechOf("fake speech")).isEqualTo(sut);
  }

  @Test
  void asserting_a_correct_output_speech_with_an_ssml_type_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final SsmlOutputSpeech speech) {
    given(speech.getSsml()).willReturn("<speak>fake<break time='3s'/> speech</speak>");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveOutputSpeechOf("fake speech")).isEqualTo(sut);
  }

  @Test
  void asserting_an_output_speech_with_an_unknown_speech_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final OutputSpeech speech) {
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveOutputSpeechOf("fake speech"))
        .withMessage("Output speech is empty");
  }

  @Test
  void asserting_a_wrong_output_speech_with_a_plain_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final PlainTextOutputSpeech speech) {
    given(speech.getText()).willReturn("great fake speech number 1");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveOutputSpeechOf("wrong fake speech"))
        .withMessage("Output speech 'great fake speech number 1' is not 'wrong fake speech'");
  }

  @Test
  void asserting_a_wrong_output_speech_with_an_ssml_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final SsmlOutputSpeech speech) {
    given(speech.getSsml())
        .willReturn(
            "<speak>great <emphasis level='strong'>fake speech</emphasis> number 1</speak>");
    given(response.getOutputSpeech()).willReturn(speech);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveOutputSpeechOf("wrong fake speech"))
        .withMessage("Output speech 'great fake speech number 1' is not 'wrong fake speech'");
  }
}
