package info.tomfi.alexa.skillstester.steps.impl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import info.tomfi.alexa.skillstester.steps.ThenResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** When step of the fluent api, implementing the next Then step logic test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class When_Request_Step_Implementation_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @InjectMocks WhenRequestImpl sut;

  @Test
  void the_next_then_step_will_instantiate_a_then_response_instance_encapsulating_the_arguments(
      @Mock ResponseEnvelope responseEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // stub skill with mock response envelope
    given(skill.invoke(eq(requestEnvelope))).willReturn(responseEnvelope);
    // when invoking for next step
    var thenStep = sut.thenResponseShould();
    // then verify the skill field
    var skillField = ThenResponse.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(thenStep)).isEqualTo(skill);
    // then verify requestEnvelope field
    var requestEnvelopeField = ThenResponse.class.getDeclaredField("requestEnvelope");
    requestEnvelopeField.setAccessible(true);
    then(requestEnvelopeField.get(thenStep)).isEqualTo(requestEnvelope);
    // then verify responseEnvelope field
    var responseEnvelopeField = ThenResponse.class.getDeclaredField("responseEnvelope");
    responseEnvelopeField.setAccessible(true);
    then(responseEnvelopeField.get(thenStep)).isEqualTo(responseEnvelope);
  }
}
