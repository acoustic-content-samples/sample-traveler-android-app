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

package com.acoustic.contenthub.sample.model.acoustic.gallery;

import com.acoustic.contenthub.sample.model.data.elements.TextElementType;

/**
 * Model representation of Gallery item detailed data type representation.
 */
public class ImageDetails {
    private String title;
    private String description;
    private TextElementType descriptionType;
    private String imageUrl;

    public ImageDetails(String title, String description, TextElementType descriptionType, String imageUrl) {
        this.title = title;
        this.description = description;
        this.descriptionType = descriptionType;
        this.imageUrl = imageUrl;
    }

    /**
     * Provides gallery item title.
     *
     * @return gallery item title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides gallery item description.
     *
     * @return description value.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Provides gallery item description text element type.
     *
     * @return {@link TextElementType} type related to description.
     */
    public TextElementType getDescriptionType() {
        return descriptionType;
    }

    /**
     * Provides gallery item detailed image url.
     *
     * @return image url.
     */
    public String getImageUrl() {
        return imageUrl;
    }
}
