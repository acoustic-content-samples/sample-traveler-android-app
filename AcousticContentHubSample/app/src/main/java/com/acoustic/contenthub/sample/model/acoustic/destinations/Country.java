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

package com.acoustic.contenthub.sample.model.acoustic.destinations;

/**
 * Model representation of Country data type.
 */
public class Country {

    private String title;
    private String imageUrl;
    private String category;

    public Country(String title, String imageUrl, String category) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    /**
     * Provides country title.
     *
     * @return country title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides country image url.
     *
     * @return image url.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Provides country category.
     *
     * @return category value.
     */
    public String getCategory() {
        return category;
    }
}
