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

package com.acoustic.contenthub.sample.model.acoustic.contacts;

import com.acoustic.contenthub.sample.model.data.elements.TextElementType;

/**
 * Model representation of contact option.
 */
public class ContactItem {
    private String title;
    private String value;
    private TextElementType valueType;

    public ContactItem(String title, String value, TextElementType valueType) {
        this.title = title;
        this.value = value;
        this.valueType = valueType;
    }

    /**
     * Provides contact option title.
     *
     * @return option title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides contact option value.
     *
     * @return option value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Provides contact option value text element type.
     *
     * @return {@link TextElementType} type related to option value.
     */
    public TextElementType getValueType() {
        return valueType;
    }
}
