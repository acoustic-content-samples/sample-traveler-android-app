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

package com.acoustic.contenthub.sample.model.acoustic.about;

import com.acoustic.contenthub.sample.model.data.elements.TextElementType;

/**
 * Model representation of "about information" content data type.
 */
public class AboutInformation {
    private String title;
    private TextElementType titleType;
    private String content;
    private TextElementType contentType;

    public AboutInformation(String title, TextElementType titleType, String content, TextElementType contentType) {
        this.title = title;
        this.titleType = titleType;
        this.content = content;
        this.contentType = contentType;
    }

    /**
     * Provides about us page title.
     *
     * @return page title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides about us page title text element type.
     *
     * @return {@link TextElementType} type related to title.
     */
    public TextElementType getTitleType() {
        return titleType;
    }

    /**
     * Provides about us page content.
     *
     * @return page content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Provides about us page content text element type.
     *
     * @return {@link TextElementType} type related to content.
     */
    public TextElementType getContentType() {
        return contentType;
    }
}
