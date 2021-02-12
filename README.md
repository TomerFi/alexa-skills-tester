<!-- markdownlint-disable MD013 -->
# Testing tools for Alexa skills written in Java</br>[![maven-central-version]][3] [![java-min-version]][4] [![javadoc-io-badge]][5]</br>[![gh-build-status]][0] [![codecov-coverage]][1] [![conventional-commits]][2]

Ever wanted to test you alexa skill with real requests?</br>
No deploying, hosting, or even configuring the skill interface is needed.

All you need is your Java skill and this dependency,</br>
And you can fluently test your skill within the context of integration, or even unit tests.

Head on over to the [Wiki section][7] for more information.</br>
Take a look at the [Javadoc][5] for API documentation.</br>

## Usage example

```xml
<!-- declare the dependency -->
<dependency>
  <groupId>info.tomfi.alexa</groupId>
  <artifactId>alexa-skills-tester</artifactId>
  <version>VERSION</version>
  <scope>test</scope>
</dependency>
```

```java
// Use the fluent api in your test code
givenSkill(myCustomSkill) // load your custom skill
    .whenRequestIs(buildLaunchRequestJsonString()) // load a request, takes json or envelopes
    .thenResponseShould() // send the request to the skill and verify the response
        .waitForFollowup() // verify the session is open, the skill is waiting for a followup
        .haveOutputSpeechOf("What is your name?") // verify the response speech output
        .haveRepromptSpeechOf("Please tell me your name.") // verify the response repormpt speech
    .followupWith(buildMyNameIntentRequestJsonString("tomer")) // load a followup request
    .thenResponseShould() // send the request to the skill and verify the response
        .haveOutputSpeechOf("Nice to meet you tomer!") // verify the response speech output
        .and() // just a sugar method for readability
        .notWaitForFollowup(); // verify the session is closed, the skill not waiting for a followup
```

> You can add as many `followupWith(x).thenResponseShould()` as you need.

## Links

- [Alexa Json Reference][6]

<!-- Real Links -->
[0]: https://github.com/TomerFi/alexa-skills-tester/actions?query=workflow%3APre-release
[1]: https://codecov.io/gh/TomerFi/alexa-skills-tester
[2]: https://conventionalcommits.org
[3]: https://search.maven.org/artifact/info.tomfi.alexa/alexa-skills-tester
[4]: https://openjdk.java.net/projects/jdk/11/
[5]: https://javadoc.io/doc/info.tomfi.alexa/alexa-skills-tester
[6]: https://developer.amazon.com/en-US/docs/alexa/custom-skills/request-and-response-json-reference.html
[7]: https://github.com/TomerFi/alexa-skills-tester/wiki
<!-- Badges Links -->
[codecov-coverage]: https://codecov.io/gh/TomerFi/alexa-skills-tester/branch/master/graph/badge.svg
[conventional-commits]: https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg
[gh-build-status]: https://github.com/TomerFi/alexa-skills-tester/workflows/Release/badge.svg
[maven-central-version]: https://badgen.net/maven/v/maven-central/info.tomfi.alexa/alexa-skills-tester?icon=maven&label=Maven%20Central
[javadoc-io-badge]: https://javadoc.io/badge2/info.tomfi.alexa/alexa-skills-tester/Javadoc.io.svg
[java-min-version]: https://badgen.net/badge/Java%20Version/11/5382a1
