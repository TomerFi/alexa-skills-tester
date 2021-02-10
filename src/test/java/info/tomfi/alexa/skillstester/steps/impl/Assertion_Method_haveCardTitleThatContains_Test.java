/**
 * Copyright Tomer Figenblat.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/** Then response step, assertion method haveCardTitleThatContains test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Assertion_Method_haveCardTitleThatContains_Test {
  @Mock private Skill skill;
  @Mock private RequestEnvelope requestEnvelope;
  @Mock private ResponseEnvelope responseEnvelope;
  @InjectMocks private ThenResponseImpl sut;

  @Test
  void asserting_a_correct_card_title_with_a_simple_type_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final SimpleCard card) {
    given(card.getTitle()).willReturn("great fake card title 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveCardTitleThatContains("fake card")).isEqualTo(sut);
  }

  @Test
  void asserting_a_correct_card_title_with_a_standard_type_will_keep_ongoing_assertion(
      @Mock final Response response, @Mock final StandardCard card) {
    given(card.getTitle()).willReturn("great fake card title 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    then(sut.haveCardTitleThatContains("fake card")).isEqualTo(sut);
  }

  @Test
  void asserting_a_card_title_with_no_card_object_will_throw_an_assertion_error(
      @Mock final Response response) {
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTitleThatContains("card title"))
        .withMessage("Card object is null");
  }

  @Test
  void asserting_a_card_title_with_an_unknown_card_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final Card card) {
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTitleThatContains("fake"))
        .withMessage("Card title is empty");
  }

  @Test
  void asserting_a_wrong_card_title_with_a_simple_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final SimpleCard card) {
    given(card.getTitle()).willReturn("great fake card title 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTitleThatContains("wrong card title"))
        .withMessage("Card title 'great fake card title 1' does not contain 'wrong card title'");
  }

  @Test
  void asserting_a_wrong_card_title_with_a_standard_type_will_throw_an_assertion_error(
      @Mock final Response response, @Mock final StandardCard card) {
    given(card.getTitle()).willReturn("great fake card title 1");
    given(response.getCard()).willReturn(card);
    given(responseEnvelope.getResponse()).willReturn(response);
    thenExceptionOfType(AssertionError.class)
        .isThrownBy(() -> sut.haveCardTitleThatContains("wrong card title"))
        .withMessage("Card title 'great fake card title 1' does not contain 'wrong card title'");
  }
}
