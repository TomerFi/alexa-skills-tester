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

@ExtendWith(MockitoExtension.class)
@Tag("unit-tests")
final class SkillsTester_Test {
  @Test
  void instantiate_utility_class_with_default_ctor_throws_illegal_access_exception() {
    assertThatExceptionOfType(IllegalAccessException.class)
        .isThrownBy(() -> SkillsTester.class.getDeclaredConstructor().newInstance());
  }

  @Test
  void invole_static_tool_to_retrieve_an_initial_given_skill_instance_and_verify_the_fields(
      @Mock Skill skill)
      throws NoSuchFieldException, SecurityException, IllegalArgumentException,
          IllegalAccessException {
    var givenStep = SkillsTester.givenSkill(skill);
    // then verify the skill field
    var skillField = GivenSkill.class.getDeclaredField("skill");
    skillField.setAccessible(true);
    assertThat(skillField.get(givenStep)).isEqualTo(skill);
  }
}
