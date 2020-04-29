/*
 * Copyright 2020 Acoustic, L.P.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Apache License, Version 2.0
 *  www.apache.org
 *  Home page of The Apache Software Foundation
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.acoustic.contenthub.sample;

/**
 * Holds constant values.
 */
public interface Constants {

    /**
     * Represents Watson Content Hub domain name.
     */
    String BASE_URL = "https://my7.content-cms.com";

    /**
     * Represents Watson Content Hub api id.
     */
    String CONTENT_HUB_ID = "ae6a1610-fd30-4b81-8871-0f7f11f95426";

    String API_URL = BASE_URL + "/api/" + CONTENT_HUB_ID + "/";

    String DOUBLE_DASH = "--";
    String DOUBLE_BRACKET = "\"";
    String STAR_SYMBOL = "*";
    String SPACE_SYMBOL = " ";

    int HTTP_CACHE_SIZE_MB = 10;
}
