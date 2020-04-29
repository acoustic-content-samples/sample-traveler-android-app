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

package com.acoustic.contenthub.sample.model;

import com.acoustic.contenthub.sample.model.acoustic.about.AboutInformation;
import com.acoustic.contenthub.sample.model.acoustic.contacts.ContactInformation;
import com.acoustic.contenthub.sample.model.acoustic.contacts.ContactItem;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Article;
import com.acoustic.contenthub.sample.model.acoustic.destinations.ArticleDetails;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Country;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Region;
import com.acoustic.contenthub.sample.model.acoustic.gallery.GalleryItem;
import com.acoustic.contenthub.sample.model.acoustic.gallery.ImageDetails;
import com.acoustic.contenthub.sample.model.acoustic.gallery.HomeGalleryItem;
import com.acoustic.contenthub.sample.model.acoustic.home.HomeInformation;
import com.acoustic.contenthub.sample.model.acoustic.search.SearchResult;
import com.acoustic.contenthub.sample.model.acoustic.search.SearchType;
import com.acoustic.contenthub.sample.model.data.document.Document;
import com.acoustic.contenthub.sample.model.data.document.DocumentHolder;
import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;
import com.acoustic.contenthub.sample.model.data.document.Elements;
import com.acoustic.contenthub.sample.model.data.elements.TextElementType;
import com.acoustic.contenthub.sample.model.data.elements.option.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides converting data to model items mechanism.
 */
public class DataToModelConverter {

    /**
     * Converts data to about information model.
     *
     * @param documentResponse received data.
     * @return new {@link AboutInformation} instance.
     */
    public static AboutInformation convertAboutDataToModel(DocumentResponse documentResponse) {
        Elements elements = documentResponse.getDocuments().get(0).getDocument().getElements();
        return new AboutInformation(
                elements.getPageTitle().getValue(),
                TextElementType.valueOf(elements.getPageTitle().getElementType()),
                elements.getAboutPageText().getValue(),
                TextElementType.valueOf(elements.getAboutPageText().getElementType())
        );
    }

    /**
     * Converts data to contact information model.
     *
     * @param documentResponse received data.
     * @return new {@link ContactInformation} instance.
     */
    public static ContactInformation convertContactsDataToViewModel(DocumentResponse documentResponse) {
        Elements elements = documentResponse.getDocuments().get(0).getDocument().getElements();
        List<ContactItem> items = new ArrayList<>();
        for (Option option : elements.getContactOptions().getValues()) {
            items.add(new ContactItem(
                    option.getContactOptionName().getValue(),
                    option.getContactOptionValue().getValue(),
                    TextElementType.valueOf(option.getContactOptionValue().getElementType())
            ));
        }
        return new ContactInformation(elements.getContactInformationTitle().getValue(), items);
    }

    /**
     * Converts data to gallery items model.
     *
     * @param documentResponse received data.
     * @return {@link List} of {@link GalleryItem} items.
     */
    public static List<GalleryItem> convertGalleryDataToViewModel(DocumentResponse documentResponse) {
        List<GalleryItem> galleryElementsList = new ArrayList<>();

        if (documentResponse.getDocuments() != null) {
            for (DocumentHolder data : documentResponse.getDocuments()) {
                galleryElementsList.add(new GalleryItem(
                        data.getDocument().getId(),
                        data.getDocument().getElements().getImageTitle().getValue(),
                        data.getDocument().getElements().getGalleryImage().getRenditions().getRectangleCard().getUrl()
                ));
            }
        }
        return galleryElementsList;
    }

    /**
     * Converts data to image details.
     *
     * @param documentResponse received data.
     * @return new {@link ImageDetails} item instance.
     */
    public static ImageDetails convertImageDetailsDataToViewModel(DocumentResponse documentResponse) {
        Elements elements = documentResponse.getDocuments().get(0).getDocument().getElements();
        return new ImageDetails(
                elements.getImageTitle().getValue(),
                elements.getImageDescription().getValue(),
                TextElementType.valueOf(elements.getImageDescription().getElementType()),
                elements.getGalleryImage().getRenditions().getDefault().getUrl()
        );
    }

    /**
     * Converts data to article details.
     *
     * @param documentResponse received data.
     * @return new {@link ArticleDetails} item instance.
     */
    public static ArticleDetails convertArticleDetailsDataToViewModel(DocumentResponse documentResponse) {
        Document document = documentResponse.getDocuments().get(0).getDocument();

        return new ArticleDetails(
                document.getElements().getTravelArticleTitle().getValue(),
                document.getElements().getArticleAuthor().getValue(),
                document.getLastModified(),
                document.getElements().getTravelArticleImage().getRenditions().getDefault().getUrl(),
                document.getElements().getTravelArticleText().getValue()
        );
    }

    /**
     * Converts data to list of countries.
     *
     * @param documentResponse received data.
     * @return {@link List} of {@link Country} items.
     */
    public static List<Country> convertCountryDataToViewModel(DocumentResponse documentResponse) {
        List<Country> countryItems = new ArrayList<>();

        if (documentResponse.getDocuments() != null) {
            for (DocumentHolder data : documentResponse.getDocuments()) {
                countryItems.add(new Country(
                        data.getDocument().getElements().getCountryTitle().getValue(),
                        data.getDocument().getElements().getCountryImage().getRenditions().getDefault().getUrl(),
                        data.getDocument().getElements().getCountryValueForPage().getCategories().get(0)
                ));
            }
        }
        return countryItems;
    }

    /**
     * Converts data to list of regions.
     *
     * @param documentResponse received data.
     * @return {@link List} of {@link Region} items.
     */
    public static List<Region> convertRegionDataToViewModel(DocumentResponse documentResponse) {
        Document document = documentResponse.getDocuments().get(0).getDocument();
        List<Region> regionItems = new ArrayList<>();
        for (String category : document.getElements().getRegionList().getCategories()) {
            regionItems.add(new Region(category));
        }
        return regionItems;
    }

    /**
     * Converts data to article items model.
     *
     * @param documentResponse received data.
     * @return {@link List} of {@link Article} items.
     */
    public static List<Article> convertArticleDataToViewModel(DocumentResponse documentResponse) {
        List<Article> articleElementsList = new ArrayList<>();

        if (documentResponse.getDocuments() != null) {
            for (DocumentHolder data : documentResponse.getDocuments()) {
                Article item = new Article(
                        data.getDocument().getId(),
                        data.getDocument().getElements().getTravelArticleTitle().getValue(),
                        data.getDocument().getElements().getArticleAuthor().getValue(),
                        data.getDocument().getLastModified(),
                        data.getDocument().getElements().getTravelArticleImage().getRenditions().getCard().getUrl(),
                        data.getDocument().getElements().getCountryOfTravelArticle().getCategoryIds().get(0)
                );
                articleElementsList.add(item);
            }
        }
        return articleElementsList;
    }

    /**
     * Converts data to search results items items model.
     *
     * @param documentResponse received data.
     * @return {@link List} of {@link SearchResult} items.
     */
    public static List<SearchResult> convertSearchDataToViewModel(DocumentResponse documentResponse) {
        List<SearchResult> searchResults = new ArrayList<>();

        for (DocumentHolder documentHolder : documentResponse.getDocuments()) {
            if (documentHolder.getDocument().getType().equals(SearchType.ARTICLE.getType())) {
                searchResults.add(new SearchResult(SearchType.ARTICLE, null,
                        new Article(
                                documentHolder.getDocument().getId(),
                                documentHolder.getDocument().getElements().getTravelArticleTitle().getValue(),
                                documentHolder.getDocument().getElements().getArticleAuthor().getValue(),
                                documentHolder.getDocument().getLastModified(),
                                documentHolder.getDocument().getElements().getTravelArticleImage().getRenditions().getCard().getUrl(),
                                documentHolder.getDocument().getElements().getCountryOfTravelArticle().getCategoryIds().get(0))
                        ));
            } else if (documentHolder.getDocument().getType().equals(SearchType.GALLERY.getType())) {
                searchResults.add(new SearchResult(
                        SearchType.GALLERY,
                        new GalleryItem(
                                documentHolder.getDocument().getId(),
                                documentHolder.getDocument().getElements().getImageTitle().getValue(),
                                documentHolder.getDocument().getElements().getGalleryImage().getRenditions().getRectangleCard().getUrl()
                        ), null));
            }
        }
        return searchResults;
    }

    /**
     * Converts data to Home information model.
     *
     * @param documentResponse received data.
     * @return new {@link HomeInformation} instance.
     */
    public static HomeInformation convertHomeDataToViewModel(DocumentResponse documentResponse) {
        Document document = documentResponse.getDocuments().get(0).getDocument();
        return new HomeInformation(
                document.getElements().getWebsiteTitle().getValue(),
                document.getElements().getArticlePreviewsSettings().getValue(),
                document.getElements().getImageSliderSettings().getValue());
    }

    /**
     * Converts data to gallery items model related to home.
     *
     * @param documentResponse received data.
     * @return {@link List} of {@link HomeGalleryItem} items.
     */
    public static List<HomeGalleryItem> convertHomeGalleryDataToViewModel(DocumentResponse documentResponse, String homeTitle) {
        List<HomeGalleryItem> galleryElementsList = new ArrayList<>();

        if (documentResponse.getDocuments() != null) {
            for (DocumentHolder data : documentResponse.getDocuments()) {
                galleryElementsList.add(new HomeGalleryItem(
                        data.getDocument().getId(),
                        data.getDocument().getElements().getImageTitle().getValue(),
                        homeTitle,
                        data.getDocument().getElements().getGalleryImage().getAsset().getAltText(),
                        data.getDocument().getElements().getGalleryImage().getRenditions().getRectangleCard().getUrl()

                ));
            }
        }
        return galleryElementsList;
    }

    private DataToModelConverter() {}
}
