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

package com.acoustic.contenthub.sample.network;

import android.util.Log;

import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.acoustic.contenthub.sample.Constants.DOUBLE_BRACKET;
import static com.acoustic.contenthub.sample.Constants.SPACE_SYMBOL;
import static com.acoustic.contenthub.sample.Constants.STAR_SYMBOL;

/**
 * Provides access to Watson Content Hub APIs.
 */
public class DeliverySearchAPI {
    private static final String TAG = DeliverySearchAPI.class.getSimpleName();

    private static final String TYPE_HOME_INFORMATION_PARAM = "\"Home Information\"";
    private static final String TYPE_ABOUT_INFORMATION_PARAM = "\"About Information\"";
    private static final String TYPE_CONTACT_INFORMATION_PARAM = "\"Contact Information\"";
    private static final String TYPE_GALLERY_INFORMATION_PARAM = "\"Gallery Image\"";
    private static final String TYPE_ARTICLE_INFORMATION_PARAM = "\"Travel Article\"";
    private static final String TYPE_DESTINATIONS_INFORMATION_PARAM = "\"Destinations\"";
    private static final String TYPE_REGION_INFORMATION_PARAM = "\"Region\"";
    private static final String TYPE_COUNTRY_INFORMATION_PARAM = "\"Country\"";
    private static final String SORT_MOST_RECENT_PARAM = "lastModified";
    private static final String SORT_TYPE_PARAM = "type";
    private static final String SORT_NAME_PARAM = "name";
    private static final String CLASSIFICATION_CATEGORY_PARAM = "category";

    private static final String TYPE_KEY = "type:";
    private static final String ID_KEY = "id:";
    private static final String CATEGORIES_KEY = "categories:";
    private static final String CLASSIFICATION_KEY = "classification:";
    private static final String TEXT_KEY = "text";

    private NetworkingCallsProvider networkingCallsProvider;

    public DeliverySearchAPI(RetrofitFactory retrofitFactory) {
        networkingCallsProvider = new NetworkingCallsProvider(retrofitFactory);
    }

    /**
     * Fetching "Home Screen" information data.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     */
    public void fetchHomeInformation(final ReceiveDataListener listener) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_HOME_INFORMATION_PARAM)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching "About Us" information data.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     */
    public void fetchAboutInformation(final ReceiveDataListener listener) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_ABOUT_INFORMATION_PARAM)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching "Contact Us" information data.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     */
    public void fetchContactInformation(final ReceiveDataListener listener) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_CONTACT_INFORMATION_PARAM)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching "Destinations screen" information data.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     */
    public void fetchDestinationsInformation(final ReceiveDataListener listener) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_DESTINATIONS_INFORMATION_PARAM)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching region's information data.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     * @param category category name for fetching data.
     */
    public void fetchRegionInformation(final ReceiveDataListener listener, String category) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_REGION_INFORMATION_PARAM,
                        CATEGORIES_KEY + DOUBLE_BRACKET + category + DOUBLE_BRACKET)
                .rows(Integer.MAX_VALUE)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching county's information data.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     * @param category name for fetching data.
     */
    public void fetchCountryInformation(final ReceiveDataListener listener, String category) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_COUNTRY_INFORMATION_PARAM,
                        CATEGORIES_KEY + DOUBLE_BRACKET + category + DOUBLE_BRACKET)
                .sort(SORT_NAME_PARAM, true)
                .rows(Integer.MAX_VALUE)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching Gallery image item data.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     * @param itemId   id for fetching data.
     */
    public void fetchGalleryItem(final ReceiveDataListener listener, String itemId) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_GALLERY_INFORMATION_PARAM, ID_KEY + itemId)
                .rows(Integer.MAX_VALUE)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching Gallery image items sorted list.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     * @param rows     number of row to search.
     */
    public void fetchGallerySortedList(final ReceiveDataListener listener, int rows) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_GALLERY_INFORMATION_PARAM)
                .sort(SORT_MOST_RECENT_PARAM, false)
                .rows(rows)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching all Gallery image items.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     */
    public void fetchGalleryItemList(final ReceiveDataListener listener) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_GALLERY_INFORMATION_PARAM)
                .sort(SORT_MOST_RECENT_PARAM, false)
                .rows(Integer.MAX_VALUE)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching Article item data.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     * @param itemId   id for fetching.
     */
    public void fetchArticleItem(final ReceiveDataListener listener, String itemId) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_ARTICLE_INFORMATION_PARAM, ID_KEY + itemId)
                .rows(Integer.MAX_VALUE)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching Article items sorted list.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     * @param rows     number of rows to fetching data.
     */
    public void fetchArticleSortedList(final ReceiveDataListener listener, int rows) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_ARTICLE_INFORMATION_PARAM)
                .sort(SORT_MOST_RECENT_PARAM, false)
                .rows(rows)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching Article items list for category.
     *
     * @param listener instance of {@link ReceiveDataListener} for result listening.
     * @param category name for fetching articles.
     */
    public void fetchArticleCountryList(final ReceiveDataListener listener, String category) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(TYPE_KEY + TYPE_ARTICLE_INFORMATION_PARAM,
                        CATEGORIES_KEY + DOUBLE_BRACKET + category + DOUBLE_BRACKET)
                .sort(SORT_MOST_RECENT_PARAM, false)
                .rows(Integer.MAX_VALUE)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Fetching category by id.
     *
     * @param listener   instance of {@link ReceiveDataListener} for result listening.
     * @param categoryId value for fetching data.
     */
    public void fetchCategoryById(final ReceiveDataListener listener, String categoryId) {
        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .query(ID_KEY + categoryId,
                        CLASSIFICATION_KEY + CLASSIFICATION_CATEGORY_PARAM)
                .rows(Integer.MAX_VALUE)
                .build();

        fetchData(searchQuery, listener);
    }

    /**
     * Searching articles and gallery data by user's input.
     *
     * @param listener   instance of {@link ReceiveDataListener} for result listening.
     * @param searchText user's input for search.
     */
    public void searchByText(final ReceiveDataListener listener, String searchText) {
        String textForSearch;
        if (searchText.split(SPACE_SYMBOL).length == 1) {
            textForSearch = STAR_SYMBOL + searchText + STAR_SYMBOL;
        } else {
            textForSearch = DOUBLE_BRACKET + searchText + DOUBLE_BRACKET;
        }

        DeliverySearchQuery searchQuery = DeliverySearchQuery.builder()
                .filterQuery(TEXT_KEY, textForSearch)
                .queryOr(TYPE_KEY + TYPE_ARTICLE_INFORMATION_PARAM,
                        TYPE_KEY + TYPE_GALLERY_INFORMATION_PARAM)
                .sort(SORT_TYPE_PARAM, true)
                .rows(Integer.MAX_VALUE)
                .build();

        fetchData(searchQuery, listener);
    }

    private void fetchData(DeliverySearchQuery searchQuery, final ReceiveDataListener listener) {
        Call<DocumentResponse> articleSearchCall = networkingCallsProvider.getSearchCall(searchQuery);

        articleSearchCall.enqueue(new Callback<DocumentResponse>() {
            @Override
            public void onResponse(Call<DocumentResponse> call, Response<DocumentResponse> response) {
                if (response.body() != null) {
                    Log.i(TAG,"onResponse :: element = "+response.body());
                    listener.onDataReceived(response.body());
                } else {
                    Log.i(TAG,"onResponse :: body == null");
                    listener.onDataFailed();
                }
            }

            @Override
            public void onFailure(Call<DocumentResponse> call, Throwable t) {
                Log.i(TAG,"onFailure :: error = "+t.getMessage());
                listener.onDataFailed();
            }
        });
    }
}
