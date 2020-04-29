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

package com.acoustic.contenthub.sample.model.data.document;

import com.acoustic.contenthub.sample.model.data.elements.CategoryElement;
import com.acoustic.contenthub.sample.model.data.elements.TextElement;
import com.acoustic.contenthub.sample.model.data.elements.image.ImageElement;
import com.acoustic.contenthub.sample.model.data.elements.option.ContactOptions;
import com.acoustic.contenthub.sample.model.data.elements.group.ArticleSettingsElement;
import com.acoustic.contenthub.sample.model.data.elements.group.GroupElement;
import com.acoustic.contenthub.sample.model.data.elements.group.ImageSettingsElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Elements data represents raw data of elements section in acoustic document
 *
 * Each section represent values related to each document type
 */
public class Elements {
    // About screen elements part start
    @SerializedName("pageTitle")
    @Expose
    private TextElement pageTitle;

    @SerializedName("aboutPageText")
    @Expose
    private TextElement aboutPageText;

    public TextElement getPageTitle() {
        return pageTitle;
    }

    public TextElement getAboutPageText() {
        return aboutPageText;
    }
    // About screen elements part end

    // Article elements part start
    @SerializedName("countryOfTravelArticle")
    @Expose
    private CategoryElement countryOfTravelArticle;

    @SerializedName("travelArticleTitle")
    @Expose
    private TextElement travelArticleTitle;

    @SerializedName("travelArticleImage")
    @Expose
    private ImageElement travelArticleImage;

    @SerializedName("articleAuthor")
    @Expose
    private TextElement articleAuthor;

    @SerializedName("travelArticleText")
    @Expose
    private TextElement travelArticleText;

    public CategoryElement getCountryOfTravelArticle() {
        return countryOfTravelArticle;
    }

    public TextElement getTravelArticleTitle() {
        return travelArticleTitle;
    }

    public ImageElement getTravelArticleImage() {
        return travelArticleImage;
    }

    public TextElement getArticleAuthor() {
        return articleAuthor;
    }

    public TextElement getTravelArticleText() {
        return travelArticleText;
    }
    // Article elements part end

    // Contacts elements part start
    @SerializedName("contactOptions")
    @Expose
    private ContactOptions contactOptions;

    @SerializedName("contactInformationTitle")
    @Expose
    private TextElement contactInformationTitle;

    public ContactOptions getContactOptions() {
        return contactOptions;
    }

    public TextElement getContactInformationTitle() {
        return contactInformationTitle;
    }
    // Contacts elements part end

    // Destinations elements part start
    @SerializedName("destinationsTitle")
    @Expose
    private TextElement destinationsTitle;

    @SerializedName("regionList")
    @Expose
    private CategoryElement regionList;


    public TextElement getDestinationsTitle() {
        return destinationsTitle;
    }

    public CategoryElement getRegionList() {
        return regionList;
    }
    // Destinations elements part end

    // Region elements part start
    @SerializedName("regionTitle")
    @Expose
    private TextElement regionTitle;

    @SerializedName("countryList")
    @Expose
    private CategoryElement countryList;

    public TextElement getRegionTitle() {
        return regionTitle;
    }

    public CategoryElement getCountryList() {
        return countryList;
    }
    // Region elements part end

    // Country elements part start
    @SerializedName("countryTitle")
    @Expose
    private TextElement countryTitle;

    @SerializedName("countryImage")
    @Expose
    private ImageElement countryImage;


    @SerializedName("countryValueForPage")
    @Expose
    private CategoryElement countryValueForPage;

    public TextElement getCountryTitle() {
        return countryTitle;
    }

    public ImageElement getCountryImage() {
        return countryImage;
    }

    public CategoryElement getCountryValueForPage() {
        return countryValueForPage;
    }
    // Country elements part end

    // Gallery elements part start
    @SerializedName("ImageTitle")
    @Expose
    private TextElement imageTitle;

    @SerializedName("galleryImage")
    @Expose
    private ImageElement galleryImage;

    @SerializedName("imageDescription")
    @Expose
    private TextElement imageDescription;

    public TextElement getImageTitle() {
        return imageTitle;
    }

    public ImageElement getGalleryImage() {
        return galleryImage;
    }

    public TextElement getImageDescription() {
        return imageDescription;
    }
    // Gallery elements part end

    // Home elements part start
    @SerializedName("websiteTitle")
    @Expose
    private TextElement websiteTitle;

    @SerializedName("imageSliderSettings")
    @Expose
    private GroupElement<ImageSettingsElement> imageSliderSettings;

    @SerializedName("articlePreviewsSettings")
    @Expose
    private GroupElement<ArticleSettingsElement> articlePreviewsSettings;

    public TextElement getWebsiteTitle() {
        return websiteTitle;
    }

    public GroupElement<ImageSettingsElement> getImageSliderSettings() {
        return imageSliderSettings;
    }

    public GroupElement<ArticleSettingsElement> getArticlePreviewsSettings() {
        return articlePreviewsSettings;
    }
    // Home elements part end

    @Override
    public String toString() {
        return "Elements{" +
                "pageTitle=" + pageTitle +
                ", aboutPageText=" + aboutPageText +
                ", countryOfTravelArticle=" + countryOfTravelArticle +
                ", travelArticleTitle=" + travelArticleTitle +
                ", travelArticleImage=" + travelArticleImage +
                ", articleAuthor=" + articleAuthor +
                ", travelArticleText=" + travelArticleText +
                ", contactOptions=" + contactOptions +
                ", contactInformationTitle=" + contactInformationTitle +
                ", destinationsTitle=" + destinationsTitle +
                ", regionList=" + regionList +
                ", regionTitle=" + regionTitle +
                ", countryList=" + countryList +
                ", countryTitle=" + countryTitle +
                ", countryImage=" + countryImage +
                ", countryValueForPage=" + countryValueForPage +
                ", imageTitle=" + imageTitle +
                ", galleryImage=" + galleryImage +
                ", imageDescription=" + imageDescription +
                ", websiteTitle=" + websiteTitle +
                ", imageSliderSettings=" + imageSliderSettings +
                ", articlePreviewsSettings=" + articlePreviewsSettings +
                '}';
    }
}
