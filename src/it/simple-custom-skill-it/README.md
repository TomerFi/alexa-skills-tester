# Nice to meet you skill

A basic custom skill with two handlers:

- [LaunchRequestHandlerImpl](src/main/java/info/tomfi/alexa/ntm/LaunchRequestHandlerImpl.java),
  Can handle `LaunchRequest` instances and return a `What is your name?` speech output,</br>
  With a `Please tell me your name.` reprompt speech and wait for a followup.

- [MyNameIntentRequestHandler](src/main/java/info/tomfi/alexa/ntm/MyNameIntentRequestHandler.java)
  Can handle `IntentRequest` instances with the `Intent` name of `MyNameIntent`.</br>
  I takes a slot named `nameSlot` holding the user spoken name and return a
  `Nice to meet you x!` speech output (x being the value from the `nameSlot`) and not wait for a
  followup.

The basic user interaction with the skill is:

- The user launches the skill.
- The skill replies `What is your name?` (with a `Please tell me your name.` reprompt) and waits.
- The user replies his name (e,g, tomer).
- The skill replies `Nice to meet you tomer!` and end the interaction.

Test cases verifying the above interaction:

- [Verify_Interaction_With_The_Skill_Using_DSL_Test](src/test/java/info/tomfi/alexa/ntm/Verify_Interaction_With_The_Skill_Using_DSL_Test.java),</br>
  Verifies the interaction building the `RequestEnvelope` using `ask sdk`'s builders.

- [Verify_Interaction_With_The_Skill_Using_Json_Byte_Array_Test](src/test/java/info/tomfi/alexa/ntm/Verify_Interaction_With_The_Skill_Using_Json_Byte_Array_Test.java),</br>
  Verifies the interaction using a `Json` `String` converted into `byte array`.

- [Verify_Interaction_With_The_Skill_Using_Json_Strings_Test](src/test/java/info/tomfi/alexa/ntm/Verify_Interaction_With_The_Skill_Using_Json_Strings_Test.java),</br>
  Verifies the interaction using a `Json` `String`.

- [Verify_Interaction_With_The_Skill_Using_Skill_Requests_Test](src/test/java/info/tomfi/alexa/ntm/Verify_Interaction_With_The_Skill_Using_Skill_Requests_Test.java),</br>
  Verifies the interaction constructing a `SkillRequest` from a `Json` `String` converted into
  `byte array`.
