# Contributing to `alexa-skills-tester`</br>[![conventional-commits]][0]

:clap: First off, thank you for taking the time to contribute. :clap:

Contributing is pretty straight-forward:

- Fork the repository
- Commit your changes
- Create a pull request against the `master` branch

## Run Superlinter Locally

```shell
docker run --rm -e RUN_LOCAL=true -e IGNORE_GITIGNORED_FILES=true -e IGNORE_GENERATED_FILES=true \
-e VALIDATE_EDITORCONFIG=true -e VALIDATE_GITHUB_ACTIONS=true -e VALIDATE_JAVA=true \
-e VALIDATE_MARKDOWN=true -e VALIDATE_XML=true -e VALIDATE_YAML=true \
-v ${PWD}:/tmp/lint ghcr.io/github/super-linter:slim-v4
```

## Code walkthrough

- The user uses the static tool
  [SkillTester#givenSkill](../src/main/java/info/tomfi/alexa/skillstester/SkillsTester.java),</br>
  To get a new instance of
  [GivenSkillImpl](../src/main/java/info/tomfi/alexa/skillstester/steps/impl/GivenSkillImpl.java)
  encapsulating the skill instance.

- The user can then use the various `whenRequestIs` overload methods to load the request,</br>
  And get a new instance of
  [WhenRequestImpl](../src/main/java/info/tomfi/alexa/skillstester/steps/impl/WhenRequestImpl.java)
  encapsulating the skill instance and the request.

- The user can then use the various `thenResponseShould` method to send the loaded request,</br>
  And get a new instance of
  [ThenResponseImpl](../src/main/java/info/tomfi/alexa/skillstester/steps/impl/ThenResponseImpl.java)
  encapsulating the skill instance and the response,</br>
  And exposing various assertion methods for verifying the response.

- The user can either stop here, if no followup is required,</br>
  Or use the various `followingUpWith` overload methods to load a followup request,</br>
  And get a new instance of
  [FollowingUpImpl](../src/main/java/info/tomfi/alexa/skillstester/steps/impl/FollowingUpImpl.java)
  encapsulating the skill instance, the previous response, and the loaded folloup request.

- The user can then use the various `thenResponseShould` method to send the loaded followup request,</br>
  And get a new instance of
  [ThenResponseImpl](../src/main/java/info/tomfi/alexa/skillstester/steps/impl/ThenResponseImpl.java)
  encapsulating the skill instance and the response,</br>
  And exposing various assertion methods for verifying the response.

The concept here is that the user can actually bounce from
[ThenResponseImpl](../src/main/java/info/tomfi/alexa/skillstester/steps/impl/ThenResponseImpl.java) to
[FollowingUpImpl](../src/main/java/info/tomfi/alexa/skillstester/steps/impl/FollowingUpImpl.java),</br>
As many times needed for verifying the multi-turn interaction with the skill.

## Unit Testing

Unit testing is written with [Junit 5](https://junit.org/junit5/) and a bunch of other cool tools and
frameworks,</br>
and executed with the [Platform Plugin](https://github.com/sormuras/junit-platform-maven-plugin).

## Integration tests

- [simple-custom-skill-it](../src/it/simple-custom-skill-it/README.md) is a simple multi-turn custom
  skill used for integration tests.

## Commit messages

Commit messages must:

- adhere the [Conventional Commits Specification][0]
- be signed-off based on the [Developer Certificate of Origin][1]

## Code of Conduct

Please check the [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) document before contributing.

<!-- Real Links -->
[0]: https://conventionalcommits.org
[1]: https://developercertificate.org
<!-- Badges Links -->
[conventional-commits]: https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg
