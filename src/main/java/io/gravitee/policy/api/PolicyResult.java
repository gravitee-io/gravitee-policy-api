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
import io.gravitee.common.http.MediaType;

import java.util.Map;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
public interface PolicyResult {

    int statusCode();

    String message();

    String key();

    Map<String, Object> parameters();

    String contentType();

    static PolicyResult build(int statusCode, String key, String message, Map<String, Object> parameters, String contentType) {
        return new PolicyResult() {
            @Override
            public int statusCode() {
                return statusCode;
            }

            @Override
            public String message() {
                return message;
            }

            @Override
            public String key() {
                return key;
            }

            @Override
            public Map<String, Object> parameters() {
                return parameters;
            }

            @Override
            public String contentType() {
                return contentType;
            }
        };
    }

    static PolicyResult failure(String message) {
        return failure(HttpStatusCode.INTERNAL_SERVER_ERROR_500, message);
    }

    static PolicyResult failure(int statusCode, String message) {
        return build(statusCode, null, message, null, MediaType.TEXT_PLAIN);
    }

    static PolicyResult failure(String key, int statusCode, String message) {
        return build(statusCode, key, message, null, MediaType.TEXT_PLAIN);
    }

    static PolicyResult failure(String key, int statusCode, String message, Map<String, Object> parameters) {
        return build(statusCode, key, message, parameters, MediaType.TEXT_PLAIN);
    }

    static PolicyResult failure(String key, String message) {
        return failure(key, HttpStatusCode.INTERNAL_SERVER_ERROR_500, message);
    }

    static PolicyResult failure(String key, String message, Map<String, Object> parameters) {
        return failure(key, HttpStatusCode.INTERNAL_SERVER_ERROR_500, message, parameters);
    }

    static PolicyResult failure(int statusCode, String message, String contentType) {
        return build(statusCode, null, message, null, contentType);
    }
}