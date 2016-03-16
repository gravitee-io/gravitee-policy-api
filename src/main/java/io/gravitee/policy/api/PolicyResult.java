/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.policy.api;

import io.gravitee.common.http.HttpStatusCode;

/**
 * @author David BRASSELY (brasseld at gmail.com)
 */
public interface PolicyResult {

    boolean isFailure();

    int httpStatusCode();

    String message();

    static PolicyResult build(boolean isFailure, int statusCode, String message) {
        return new PolicyResult() {
            @Override
            public boolean isFailure() {
                return isFailure;
            }

            @Override
            public int httpStatusCode() {
                return statusCode;
            }

            @Override
            public String message() {
                return message;
            }
        };
    }

    static PolicyResult failure(int statusCode, String message) {
        return build(true, statusCode, message);
    }

    static PolicyResult failure(String message) {
        return failure(HttpStatusCode.INTERNAL_SERVER_ERROR_500, message);
    }
}
