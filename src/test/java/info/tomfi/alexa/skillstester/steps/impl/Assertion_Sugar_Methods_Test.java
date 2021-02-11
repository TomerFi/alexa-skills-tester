package info.tomfi.alexa.skillstester.steps.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit-tests")
final class Assertion_Sugar_Methods_Test extends AssertionMethodsFixtures {
  @Test
  void invoking_the_sugar_method_named_and_will_do_nothing_but_return_the_same_instance() {
    assertThat(sut.and()).isEqualTo(sut);
    then(sut).should().and();
    then(sut).shouldHaveNoMoreInteractions();
  }
}
