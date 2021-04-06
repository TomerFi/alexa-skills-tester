/*
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Then response step, sugar methods test cases. */
@Tag("unit-tests")
final class Assertion_Sugar_Methods_Test extends AssertionMethodsFixtures {
  @Test
  void invoking_the_sugar_method_named_and_will_do_nothing_but_return_the_same_instance() {
    assertThat(sut.and()).isEqualTo(sut);
    then(sut).should().and();
    then(sut).shouldHaveNoMoreInteractions();
  }
}
