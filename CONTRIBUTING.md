# Contributing to *alexa-skills-tester*

:clap: First off, thank you for taking the time to contribute. :clap:

- Fork the repository
- Create a new branch on your fork
- Commit your changes
- Create a pull request against the `master` branch

## Code walkthrough

> For a full usage example, check out the [integration tests](#integration-tests).

The user is expected to use the static method
[SkillTester#givenSkill](https://github.com/TomerFi/alexa-skills-tester/blob/master/src/main/java/info/tomfi/alexa/skillstester/SkillsTester.java), to get a new instance of
[GivenSkillImpl](https://github.com/TomerFi/alexa-skills-tester/blob/master/src/main/java/info/tomfi/alexa/skillstester/steps/impl/GivenSkillImpl.java)
encapsulating the skill instance.

The user is then expected to use one of the various `whenRequestIs` methods to load the request and get a new instance of
[WhenRequestImpl](https://github.com/TomerFi/alexa-skills-tester/blob/master/src/main/java/info/tomfi/alexa/skillstester/steps/impl/WhenRequestImpl.java)
encapsulating the skill instance and the request.

The user can then use one of the various `thenResponseShould` methods to send the loaded request and get a new instance of
[ThenResponseImpl](https://github.com/TomerFi/alexa-skills-tester/blob/master/src/main/java/info/tomfi/alexa/skillstester/steps/impl/ThenResponseImpl.java)
encapsulating the skill instance, the response, and exposing various assertion methods for verifying the response.

For multi-step interactions, the user can use one of the various `followingUpWith` methods to load a followup request
and get a new instance of [FollowingUpImpl](https://github.com/TomerFi/alexa-skills-tester/blob/master/src/main/java/info/tomfi/alexa/skillstester/steps/impl/FollowingUpImpl.java)
encapsulating the skill instance, the previous response, and the loaded followup request.

The user can then use the various `thenResponseShould` method to send the loaded followup request,</br>
And get a new instance of
[ThenResponseImpl](https://github.com/TomerFi/alexa-skills-tester/blob/master/src/main/java/info/tomfi/alexa/skillstester/steps/impl/ThenResponseImpl.java).

## Integration tests

- [simple-custom-skill-it](https://github.com/TomerFi/alexa-skills-tester/tree/master/src/it/simple-custom-skill-it) is
  a simple multi-turn custom skill used for integration tests.

## Run super-linter locally

```shell
docker run --rm -e RUN_LOCAL=true -e IGNORE_GITIGNORED_FILES=true -e IGNORE_GENERATED_FILES=true \
-e VALIDATE_EDITORCONFIG=true -e VALIDATE_GITHUB_ACTIONS=true -e VALIDATE_JAVA=true \
-e VALIDATE_MARKDOWN=true -e VALIDATE_XML=true -e VALIDATE_YAML=true \
-v ${PWD}:/tmp/lint ghcr.io/github/super-linter:slim-v4
```
