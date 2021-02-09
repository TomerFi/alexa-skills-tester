package info.tomfi.alexa.skillstester.steps.impl;

import static org.assertj.core.api.BDDAssertions.then;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import info.tomfi.alexa.skillstester.steps.WhenRequest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class GivenSkillImpl_Test {
  @Mock Skill skill;
  @InjectMocks GivenSkillImpl sut;

  @Test
  void retrieving_a_when_request_instance_and_verifying_the_fields(
      @Mock final RequestEnvelope requestEnvelope)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // when invoking for next step
    var whenStep = sut.whenRequestIs(requestEnvelope);
    // then verify the skill field
    var skillField = WhenRequest.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    then(skillField.get(whenStep)).isEqualTo(skill);
    // then verify requestEnvelope field
    var requestEnvelopeField = WhenRequest.class.getDeclaredField("requestEnvelope");
    requestEnvelopeField.setAccessible(true);
    then(requestEnvelopeField.get(whenStep)).isEqualTo(requestEnvelope);
  }
}
