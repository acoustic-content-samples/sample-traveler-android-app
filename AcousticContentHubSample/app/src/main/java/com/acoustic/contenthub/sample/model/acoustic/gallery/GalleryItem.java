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

/**
 * Model representation of Gallery item data type.
 */
public class GalleryItem {
    private String id;
    private String title;
    private String imageUrl;

    public GalleryItem(String id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    /**
     * Provides gallery item id.
     *
     * @return gallery item id value.
     */
    public String getId() {
        return id;
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
     * Provides gallery image url.
     *
     * @return image url.
     */
    public String getImageUrl() {
        return imageUrl;
    }
}
