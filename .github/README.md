<!-- markdownlint-disable MD013 -->
# Testing tools for Alexa skills written in Java</br>[![maven-central-version]][3] [![java-min-version]][4] [![javadoc-io-badge]][5]</br>[![gh-build-status]][0] [![codecov-coverage]][1] <!-- editorconfig-checker-disable-line -->

Ever wanted to test your alexa skill with real requests?</br>
No deploying, hosting, or even configuring the skill interface should be needed for this.

All you need is your Java skill and this dependency,</br>
And you can fluently test your skill within the context of integration, or even unit tests.

Head on over to the [Wiki section][7] for more information.</br>
Take a look at the [Javadoc][5] for API documentation.</br>
Please also see this [Blog post][8] I've written about using the dependency.</br>

For more usage examples,
I use this tool for [integration tests][10] as part of the build process in [my own live skill][11].

## Usage example

```xml
<!-- declare the dependency -->
<dependency>
  <groupId>info.tomfi.alexa</groupId>
  <artifactId>alexa-skills-tester</artifactId>
  <version>0.2.10</version>
  <scope>test</scope>
</dependency>
```

```java
// Use the bdd style fluent dsl in your test code
givenSkill(myCustomSkill) // load your custom skill
    .whenRequestIs(launchRequestJson) // load a request, takes json or envelopes
    .thenResponseShould() // send the request to the skill and verify the response
        .waitForFollowup() // verify the session is open, the skill is waiting for a followup
        .haveOutputSpeechOf("What is your name?") // verify the response speech output
        .haveRepromptSpeechOf("Please tell me your name.") // verify the response repormpt speech
    .followingUpWith(myNameIntentJson) // load a followup request
    .thenResponseShould() // send the request to the skill and verify the response
        .haveOutputSpeechOf("Nice to meet you omer!") // verify the response speech output
        .and() // just a sugar method for readability
        .notWaitForFollowup(); // verify the session is closed, the skill not waiting for a followup
```

> You can add as many `followingUpWith(x).thenResponseShould()` as you need.

## Links

- [Alexa Json Reference][6]

## Disclaimer

This repository and/or the tool deployed with its sources has no direct relation with Amazon.</br>
This an open-source tool based on the documentation for [Alexa developers][6].

<!-- Real Links -->
[0]: https://github.com/TomerFi/alexa-skills-tester/actions/workflows/stage.yml
[1]: https://codecov.io/gh/TomerFi/alexa-skills-tester
[3]: https://search.maven.org/artifact/info.tomfi.alexa/alexa-skills-tester
[4]: https://openjdk.java.net/projects/jdk/11/
[5]: https://javadoc.io/doc/info.tomfi.alexa/alexa-skills-tester
[6]: https://developer.amazon.com/en-US/docs/alexa/custom-skills/request-and-response-json-reference.html
[7]: https://github.com/TomerFi/alexa-skills-tester/wiki
[8]: https://dev.to/tomerfi/alexa-skills-testing-4pfd
[9]: https://developer.amazon.com/en-US/docs/alexa/alexa-skills-kit-sdk-for-java/overview.html
[10]: https://github.com/TomerFi/alexa-skill-shabbat-times/tree/master/lambda/src/it/shabbat-times-interaction-it/src/test/java/info/tomfi/alexa/shabbattimes/it
[11]: https://github.com/TomerFi/alexa-skill-shabbat-times
<!-- Badges Links -->
[codecov-coverage]: https://codecov.io/gh/TomerFi/alexa-skills-tester/branch/master/graph/badge.svg
[gh-build-status]: https://github.com/TomerFi/alexa-skills-tester/actions/workflows/stage.yml/badge.svg
[maven-central-version]: https://badgen.net/maven/v/maven-central/info.tomfi.alexa/alexa-skills-tester?icon=maven&label=Maven%20Central
[javadoc-io-badge]: https://javadoc.io/badge2/info.tomfi.alexa/alexa-skills-tester/Javadoc.io.svg
[java-min-version]: https://badgen.net/badge/Java%20Version/11/5382a1
