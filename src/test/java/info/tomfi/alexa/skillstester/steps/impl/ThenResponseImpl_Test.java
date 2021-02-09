package info.tomfi.alexa.skillstester.steps.impl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.amazon.ask.Skill;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ResponseEnvelope;
import info.tomfi.alexa.skillstester.steps.ThenFollowup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class ThenResponseImpl_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @Spy @InjectMocks ThenResponseImpl sut;

  @Test
  void retrieving_a_then_followup_instance_with_an_opened_session_and_verifying_the_fields(
      @Mock final Response response, @Mock final Request followupRequest)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    // stub response to keep session open waiting for followup
    given(response.getShouldEndSession()).willReturn(false);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step
    var followupStep = sut.thenFollowupWith(followupRequest);
    // then verify the skill field
    var skillField = ThenFollowup.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(followupStep)).isEqualTo(skill);
    // then verify responseEnvelope field
    var responseEnvelopeField = ThenFollowup.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(followupStep)).isEqualTo(responseEnvelope);
    // then verify previousRequestEnvelope field
    var previousRequestEnvelopeField = ThenFollowup.class.getDeclaredField("previousRequestEnvelope");
    previousRequestEnvelopeField.setAccessible(true);
    then(previousRequestEnvelopeField.get(followupStep)).isEqualTo(requestEnvelope);
    // then verify followupRequest field
    var followupRequestField = ThenFollowup.class.getDeclaredField("followupRequest");
    followupRequestField.setAccessible(true);
    then(followupRequestField.get(followupStep)).isEqualTo(followupRequest);
  }

  @Test
  void retrieving_a_then_followup_instance_with_a_closed_session_throws_assertion_error(
      @Mock final Response response, @Mock final Request followupRequest) {
    // stub response with closed session not waiting for followups
    given(response.getShouldEndSession()).willReturn(true);
    given(responseEnvelope.getResponse()).willReturn(response);
    // when invoking for next step, then an assertion error is thrown
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.thenFollowupWith(followupRequest))
        .withMessage("Session is marked as closed");
  }

  @Test
  void invoking_sugar_method_and_will_only_return_the_sut() {
    then(sut.and()).isEqualTo(sut);
    verify(sut).and();
    verifyNoMoreInteractions(sut);
  }
}
