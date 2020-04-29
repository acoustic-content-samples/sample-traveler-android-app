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

package com.acoustic.contenthub.sample.ui.destinations.articles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acoustic.contenthub.sample.AcousticNativeApplication;
import com.acoustic.contenthub.sample.model.DataToModelConverter;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Article;
import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;
import com.acoustic.contenthub.sample.network.ReceiveDataListener;

import java.util.List;

/**
 * {@link ViewModel} implementation for observing {@link List<Article>} updates.
 */
public class ArticlesViewModel extends ViewModel {
    private MutableLiveData<List<Article>> articleItems;

    /**
     * Provides {@link Article} item list by category.
     *
     * @param category chosen category.
     * @return {@link LiveData<List>} for observing data updates in view.
     */
    LiveData<List<Article>> getArticleItems(String category) {
        articleItems = new MutableLiveData<>();

        AcousticNativeApplication.getDeliverySearchApi().fetchArticleCountryList(new ReceiveDataListener() {

            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                articleItems.setValue(DataToModelConverter.convertArticleDataToViewModel(documentResponse));
            }
            @Override
            public void onDataFailed() { }
        }, category);

        return articleItems;
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
