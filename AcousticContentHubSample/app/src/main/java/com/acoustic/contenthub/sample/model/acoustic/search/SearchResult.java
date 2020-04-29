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

package com.acoustic.contenthub.sample.model.acoustic.search;

import com.acoustic.contenthub.sample.model.acoustic.destinations.Article;
import com.acoustic.contenthub.sample.model.acoustic.gallery.GalleryItem;

/**
 * Model representation of search results.
 */
public class SearchResult {
    private SearchType searchType;
    private GalleryItem galleryItem;
    private Article articlesItem;

    public SearchResult(SearchType searchType, GalleryItem galleryItem, Article articlesItem) {
        this.searchType = searchType;
        this.galleryItem = galleryItem;
        this.articlesItem = articlesItem;
    }

    /**
     * Provides search result type for item.
     *
     * @return {@link SearchType} type related to search result.
     */
    public SearchType getSearchType() {
        return searchType;
    }

    /**
     * Provides searched gallery item.
     *
     * @return searched {@link GalleryItem} value.
     * @see GalleryItem
     */
    public GalleryItem getGalleryItem() {
        return galleryItem;
    }

    /**
     * Provides searched article item.
     *
     * @return searched {@link Article} value.
     * @see Article
     */
    public Article getArticlesItem() {
        return articlesItem;
    }
}
