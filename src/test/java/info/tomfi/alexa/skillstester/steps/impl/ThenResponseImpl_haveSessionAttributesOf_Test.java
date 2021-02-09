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

/** Assertion method notWaitForFollowup test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class ThenResponseImpl_haveSessionAttributesOf_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_skill_response_has_existing_session_attributes_will_keep_ongoing_assertion() {
    given(responseEnvelope.getSessionAttributes())
        .willReturn(
            Map.of(
                "Key1", (Object) "Value1", "Key2", (Object) "Value2", "Key3", (Object) "Value3"));
    then(sut.haveSessionAttributesOf(Map.of("Key1", (Object) "Value1", "Key2", (Object) "Value2")))
        .isEqualTo(sut);
  }

  @Test
  void asserting_skill_response_has_non_existing_session_attributes_will_throw_assertion_error() {
    given(responseEnvelope.getSessionAttributes())
        .willReturn(
            Map.of(
                "Key1", (Object) "Value1", "Key2", (Object) "Value2", "Key3", (Object) "Value3"));
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(
            () ->
                sut.haveSessionAttributesOf(
                    Map.of("Key1", (Object) "Value1", "Key4", (Object) "Value4")))
        .withMessage("Session attributes map does not contain key 'Key4'");
  }

  @Test
  void asserting_skill_response_has_session_attribs_with_empty_attribs_will_throw_assert_error() {
    given(responseEnvelope.getSessionAttributes()).willReturn(new HashMap<String, Object>());
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(
            () ->
                sut.haveSessionAttributesOf(
                    Map.of("Key1", (Object) "Value1", "Key2", (Object) "Value2")))
        .withMessage("Session attributes map is empty");
  }

  @Test
  void asserting_skill_response_has_session_attribs_with_empty_args_will_throw_assert_error() {
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveSessionAttributesOf(new HashMap<String, Object>()))
        .withMessage("Entries to verify are empty");
  }
}
