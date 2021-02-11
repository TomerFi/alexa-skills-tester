package info.tomfi.alexa.skillstester.steps.impl;

import com.amazon.ask.Skill;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FluentStepsFixtures {
  protected static String DUMMY_JSON_REQUEST =
      "{\"version\": \"1.0\", "
          + "\"session\": {\"new\": true}, "
          + "\"context\": {\"System\": {}}, "
          + "\"request\": {"
          + "\"type\": \"LaunchRequest\", "
          + "\"requestId\": \"amzn1.echo-api.request.fake-request-id\", "
          + "\"timestamp\": \"2019-10-01T10:20:30Z\", "
          + "\"locale\": \"en-US\"}}";

  @Mock protected Skill skill;
}
