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

import static com.acoustic.contenthub.sample.Constants.DOUBLE_DASH;

/**
 * Model representation of Article data type.
 */
public class Article {
    private String id;
    private String title;
    private String author;
    private String date;
    private String country;
    private String imageUrl;
    private String categoryId;

    public Article(String id, String title, String author, String date, String imageUrl, String categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.country = DOUBLE_DASH;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    /**
     * Provides article id.
     *
     * @return article id value.
     */
    public String getId() {
        return id;
    }

    /**
     * Provides article title.
     *
     * @return article title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides article author name.
     *
     * @return article author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Provides article creation date.
     *
     * @return date value.
     */
    public String getDate() {
        return date;
    }

    /**
     * Provides article country.
     *
     * @return article country value.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Provides article country.
     *
     * @param country article country value.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Provides article image url.
     *
     * @return image url.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Provides article category id.
     *
     * @return category id.
     */
    public String getCategoryId() {
        return categoryId;
    }
}
