/**
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
package info.tomfi.alexa.ntm;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.LaunchRequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import java.util.Optional;

/**
 * LaunchRequest handler returning 'What is your name?' and 'Please tell me your name.' as a
 * repromopt, not ending the session.
 */
public final class LaunchRequestHandlerImpl implements LaunchRequestHandler {
  @Override
  public boolean canHandle(final HandlerInput input, final LaunchRequest request) {
    return true;
  }

  @Override
  public Optional<Response> handle(final HandlerInput input, final LaunchRequest request) {
    return input.getResponseBuilder()
        .withSpeech("What is your name?")
        .withReprompt("Please tell me your name.")
        .withShouldEndSession(false)
        .build();
  }
}
