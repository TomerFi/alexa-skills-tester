package info.tomfi.alexa.skillstester.steps.impl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;
import java.util.Map;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Assertion method notWaitForFollowup test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class ThenResponseImpl_haveNoSessionAttributes_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_skill_response_has_no_session_attribs_with_empty_map_will_keep_ongoing_assert() {
    given(responseEnvelope.getSessionAttributes()).willReturn(new HashMap<String, Object>());
    then(sut.haveNoSessionAttributes()).isEqualTo(sut);
  }

  @Test
  void asserting_skill_response_has_no_session_attribs_with_non_empty_map_will_throw_assert_err() {
    given(responseEnvelope.getSessionAttributes()).willReturn(Map.of("Key1", (Object) "Value1"));
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveNoSessionAttributes())
        .withMessage("Found session attributes");
  }
}
