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

package com.acoustic.contenthub.sample.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.acoustic.contenthub.sample.AcousticNativeApplication;
import com.acoustic.contenthub.sample.model.DataToModelConverter;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Article;
import com.acoustic.contenthub.sample.model.acoustic.gallery.HomeGalleryItem;
import com.acoustic.contenthub.sample.model.acoustic.home.HomeInformation;
import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;
import com.acoustic.contenthub.sample.network.ReceiveDataListener;

import java.util.List;

/**
 * {@link ViewModel} implementation for observing {@link List<HomeGalleryItem>} and {@link List<Article>} updates.
 */
public class HomeViewModel extends ViewModel {
    private MutableLiveData<HomeInformation> homeInformation = new MutableLiveData<>();
    private MutableLiveData<Integer> displayTime = new MutableLiveData<>();

    /**
     * {@link Article} list value. Uses {@link Transformations} for switching data from {@link HomeInformation} model.
     *
     * @see #fetchHomeInformation().
     */
    private LiveData<List<Article>> articleItemList = Transformations.switchMap(homeInformation, homeInfo -> {
        MutableLiveData<List<Article>> articleItem = new MutableLiveData<>();
        AcousticNativeApplication.getDeliverySearchApi().fetchArticleSortedList(new ReceiveDataListener() {
            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                articleItem.setValue(DataToModelConverter.convertArticleDataToViewModel(documentResponse));
            }

            @Override
            public void onDataFailed() { }
        }, homeInfo.getArticleSettings().getNumberOfListItems().getValue());
        return articleItem;
    });

    /**
     * {@link HomeGalleryItem} list value. Uses {@link Transformations} for switching data from {@link HomeInformation} model.
     *
     * @see #fetchHomeInformation().
     */
    private LiveData<List<HomeGalleryItem>> galleryItemList = Transformations.switchMap(homeInformation, homeInfo -> {
        MutableLiveData<List<HomeGalleryItem>> galleryItem = new MutableLiveData<>();
        AcousticNativeApplication.getDeliverySearchApi().fetchGallerySortedList(new ReceiveDataListener() {
            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                galleryItem.setValue(DataToModelConverter.convertHomeGalleryDataToViewModel(documentResponse, homeInfo.getHomeTitle()));
                displayTime.setValue(homeInfo.getImageSettings().getDisplayTime().getValue());
            }

            @Override
            public void onDataFailed() { }
        }, homeInfo.getImageSettings().getNumberOfListItems().getValue());
        return galleryItem;
    });

    /**
     * Fetches home screen information. Fetched data will be transformed to other models.
     * @see #articleItemList
     * @see #galleryItemList
     */
    void fetchHomeInformation() {
        AcousticNativeApplication.getDeliverySearchApi().fetchHomeInformation(new ReceiveDataListener() {
            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                homeInformation.setValue(DataToModelConverter.convertHomeDataToViewModel(documentResponse));
            }

            @Override
            public void onDataFailed() { }
        });
    }

    /**
     * Provides {@link Article} item list.
     *
     * @return {@link LiveData<List>} for observing data updates in view.
     */
    LiveData<List<Article>> getArticleItemList() {
        return articleItemList;
    }

    /**
     * Provides {@link HomeGalleryItem} item list.
     *
     * @return {@link LiveData<List>} for observing data updates in view.
     */
    LiveData<List<HomeGalleryItem>> getGalleryItemList() {
        return galleryItemList;
    }

    /**
     * Provides display time for gallery pager.
     *
     * @return {@link LiveData<Integer>} for observing display time updates in view.
     */
    LiveData<Integer> getDisplayTime() {
        return displayTime;
    }

    /**
     * Provides country name for article by category id.
     *
     * @param categoryId item category id.
     * @return {@link LiveData<String>} for observing country name updates in view.
     */
    LiveData<String> getCountry(String categoryId) {
        MutableLiveData<String> country = new MutableLiveData<>();

        AcousticNativeApplication.getDeliverySearchApi().fetchCategoryById(new ReceiveDataListener() {

            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                if (documentResponse.getDocuments() != null && !documentResponse.getDocuments().isEmpty()) {
                    country.setValue(documentResponse.getDocuments().get(0).getDocument().getName());
                }
            }
            @Override
            public void onDataFailed() { }
        }, categoryId);

        return country;
    }
}