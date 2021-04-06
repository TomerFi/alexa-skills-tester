/*
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
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import java.util.Optional;

/**
 * `MyNameIntent` handler returning 'Nice to meet you' concatenated with the `nameSlot` slot value
 * and ending the session.
 */
public final class MyNameIntentRequestHandler implements IntentRequestHandler {
  @Override
  public boolean canHandle(final HandlerInput input, final IntentRequest request) {
    return request.getIntent().getName().equals("MyNameIntent");
  }

  @Override
  public Optional<Response> handle(final HandlerInput input, final IntentRequest request) {
    var name = request.getIntent().getSlots().get("nameSlot").getValue();
    return input.getResponseBuilder()
        .withSpeech(String.format("Nice to meet you %s!", name))
        .withShouldEndSession(true)
        .build();
  }
}
