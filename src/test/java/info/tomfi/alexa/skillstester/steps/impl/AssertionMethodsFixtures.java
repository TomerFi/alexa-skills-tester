package info.tomfi.alexa.skillstester.steps.impl;

import com.amazon.ask.Skill;
import com.amazon.ask.model.ResponseEnvelope;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AssertionMethodsFixtures {
  @Mock protected Skill skill;
  @Mock protected ResponseEnvelope responseEnvelope;
  @Spy @InjectMocks protected ThenResponseImpl sut;
}
