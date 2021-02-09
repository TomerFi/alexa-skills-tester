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

/** Assertion method haveCardTitleOf test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class ThenResponseImpl_haveCardTitleOf_Test {
  @Mock Skill skill;
  @Mock RequestEnvelope requestEnvelope;
  @Mock ResponseEnvelope responseEnvelope;
  @InjectMocks ThenResponseImpl sut;

  @Test
  void asserting_card_title_of_simple_card_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final SimpleCard card) {
    given(card.getTitle()).willReturn("fake card title");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveCardTitleOf("fake card title")).isEqualTo(sut);
  }

  @Test
  void asserting_card_title_of_standard_card_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final StandardCard card) {
    given(card.getTitle()).willReturn("fake card title");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveCardTitleOf("fake card title")).isEqualTo(sut);
  }

  @Test
  void asserting_card_title_of_no_card_object_will_throw_an_assertion_error(
      @Mock final Response response) {
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTitleOf("fake card title"))
        .withMessage("Card object is null");
  }

  @Test
  void asserting_card_title_of_unknown_card_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final Card card) {
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTitleOf("fake speech"))
        .withMessage("Card title is empty");
  }

  @Test
  void asserting_card_title_of_simple_card_with_wrong_title_throws_assertion_error(
      @Mock final Response response, @Mock final SimpleCard card) {
    given(card.getTitle()).willReturn("great fake card title 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTitleOf("wrong fake card title"))
        .withMessage("Card title 'great fake card title 1' is not 'wrong fake card title'");
  }

  @Test
  void asserting_card_title_of_standard_card_with_wrong_title_throws_assertion_error(
      @Mock final Response response, @Mock final StandardCard card) {
    given(card.getTitle()).willReturn("great fake card title 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTitleOf("wrong fake card title"))
        .withMessage("Card title 'great fake card title 1' is not 'wrong fake card title'");
  }
}
