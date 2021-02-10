package info.tomfi.alexa.skillstester.steps.impl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;
import static org.mockito.BDDMockito.given;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Then response step, assertion method notWaitForFollowup test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Assertion_Method_haveNoSessionAttributes_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_with_an_empty_session_attributes_map_will_keep_ongoing_assertion() {
    given(responseEnvelope.getSessionAttributes()).willReturn(new HashMap<String, Object>());
    then(sut.haveNoSessionAttributes()).isEqualTo(sut);
  }

  @Test
  void asserting_with_a_non_empty_session_attributes_map_will_throw_an_assertion_error() {
    given(responseEnvelope.getSessionAttributes()).willReturn(Map.of("Key1", (Object) "Value1"));
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveNoSessionAttributes())
        .withMessage("Found session attributes");
  }
}
