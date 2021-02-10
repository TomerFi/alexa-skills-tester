package info.tomfi.alexa.skillstester.steps.impl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;
import static org.mockito.BDDMockito.given;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ResponseEnvelope;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Then response step, assertion method waitForFollowup test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Assertion_Method_waitForFollowup_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_with_an_open_session_will_keep_ongoing_assertion(
      @Mock final Response response) {
    given(response.getShouldEndSession()).willReturn(false);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.waitForFollowup()).isEqualTo(sut);
  }

  @Test
  void asserting_with_a_closed_session_will_throw_an_assertion_error(
      @Mock final Response response) {
    given(response.getShouldEndSession()).willReturn(true);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.waitForFollowup())
        .withMessage("Session is marked as closed");
  }
}
