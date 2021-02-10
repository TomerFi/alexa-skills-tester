package info.tomfi.alexa.skillstester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.amazon.ask.Skill;
import info.tomfi.alexa.skillstester.steps.GivenSkill;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** Static tools used as starting points for the fluent api test cases. */
@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class Skills_Tester_Utility_Test {
  @Test
  void instantiating_with_default_ctor_will_throw_illegal_access_exception() {
    assertThatExceptionOfType(IllegalAccessException.class)
        .isThrownBy(() -> SkillsTester.class.getDeclaredConstructor().newInstance());
  }

  @Test
  void starting_the_fluent_api_will_instantiate_a_given_skill_instance_encapsulating_the_skill(
      @Mock Skill skill)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    // when invoking the starting point of the fluent api
    var givenStep = SkillsTester.givenSkill(skill);
    // then verify the skill field
    var skillField = GivenSkill.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    assertThat(skillField.get(givenStep)).isEqualTo(skill);
  }
}
