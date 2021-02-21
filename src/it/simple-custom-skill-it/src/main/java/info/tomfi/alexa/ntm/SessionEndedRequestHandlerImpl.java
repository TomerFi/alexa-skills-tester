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
import com.amazon.ask.dispatcher.request.handler.impl.SessionEndedRequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import java.util.Optional;

/** SessionEndedRequest handler returning and empty response. */
public final class SessionEndedRequestHandlerImpl implements SessionEndedRequestHandler {
  @Override
  public boolean canHandle(final HandlerInput input, final SessionEndedRequest request) {
    return true;
  }

  @Override
  public Optional<Response> handle(final HandlerInput input, final SessionEndedRequest request) {
    return Optional.empty();
  }
}
