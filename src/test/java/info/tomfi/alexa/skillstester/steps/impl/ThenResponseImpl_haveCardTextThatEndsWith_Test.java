package info.tomfi.alexa.skillstester.steps.impl;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;
import static org.mockito.BDDMockito.given;

import com.amazon.ask.Skill;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.model.ui.Card;
import com.amazon.ask.model.ui.SimpleCard;
import com.amazon.ask.model.ui.StandardCard;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Assertion method haveCardTextThatEndsWith test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class ThenResponseImpl_haveCardTextThatEndsWith_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_card_text_ends_with_simple_card_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final SimpleCard card) {
    given(card.getContent()).willReturn("great fake card text 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveCardTextThatEndsWith("card text 1")).isEqualTo(sut);
  }

  @Test
  void asserting_card_text_ends_with_standard_card_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final StandardCard card) {
    given(card.getText()).willReturn("great fake card text 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveCardTextThatEndsWith("card text 1")).isEqualTo(sut);
  }

  @Test
  void asserting_card_text_ends_with_no_card_object_will_throw_an_assertion_error(
      @Mock final Response response) {
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTextThatEndsWith("card text"))
        .withMessage("Card object is null");
  }

  @Test
  void asserting_card_text_ends_with_unknown_card_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final Card card) {
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTextThatEndsWith("fake"))
        .withMessage("Card text is empty");
  }

  @Test
  void asserting_card_text_ends_with_simple_card_with_wrong_title_throws_assertion_error(
      @Mock final Response response, @Mock final SimpleCard card) {
    given(card.getContent()).willReturn("great fake card text 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTextThatEndsWith("wrong card text"))
        .withMessage("Card text 'great fake card text 1' should end with 'wrong card text'");
  }

  @Test
  void asserting_card_text_ends_with_standard_card_with_wrong_title_throws_assertion_error(
      @Mock final Response response, @Mock final StandardCard card) {
    given(card.getText()).willReturn("great fake card text 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTextThatEndsWith("wrong card text"))
        .withMessage("Card text 'great fake card text 1' should end with 'wrong card text'");
  }
}
