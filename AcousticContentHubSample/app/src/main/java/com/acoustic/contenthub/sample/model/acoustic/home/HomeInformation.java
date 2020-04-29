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

package com.acoustic.contenthub.sample.model.acoustic.home;

import com.acoustic.contenthub.sample.model.data.elements.group.ArticleSettingsElement;
import com.acoustic.contenthub.sample.model.data.elements.group.ImageSettingsElement;

/**
 * Model representation of "home information" content data type.
 */
public class HomeInformation {
    private String homeTitle;
    private ArticleSettingsElement articleSettings;
    private ImageSettingsElement imageSettings;

    public HomeInformation(String homeTitle, ArticleSettingsElement articleSettings, ImageSettingsElement imageSettings) {
        this.homeTitle = homeTitle;
        this.articleSettings = articleSettings;
        this.imageSettings = imageSettings;
    }

    /**
     * Provides home title.
     *
     * @return home title.
     */
    public String getHomeTitle() {
        return homeTitle;
    }

    /**
     * Provides article settings.
     *
     * @return {@link ArticleSettingsElement} value.
     */
    public ArticleSettingsElement getArticleSettings() {
        return articleSettings;
    }

    /**
     * Provides image settings.
     *
     * @return {@link ImageSettingsElement} value.
     */
    public ImageSettingsElement getImageSettings() {
        return imageSettings;
    }
}
