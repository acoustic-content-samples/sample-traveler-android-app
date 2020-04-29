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

package com.acoustic.contenthub.sample.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acoustic.contenthub.sample.AcousticNativeApplication;
import com.acoustic.contenthub.sample.model.DataToModelConverter;
import com.acoustic.contenthub.sample.model.acoustic.search.SearchResult;
import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;
import com.acoustic.contenthub.sample.network.ReceiveDataListener;

import java.util.List;

/**
 * {@link ViewModel} implementation for observing {@link List<SearchResult>} updates.
 */
public class SearchViewModel extends ViewModel {
    private MutableLiveData<List<SearchResult>> searchItems;

    /**
     * Provides {@link SearchResult} item list by text.
     *
     * @param text user input text for searching.
     * @return {@link LiveData<List>} for observing searching results updates in view.
     */
    LiveData<List<SearchResult>> getSearchItems(String text) {
        searchItems = new MutableLiveData<>();

        AcousticNativeApplication.getDeliverySearchApi().searchByText(new ReceiveDataListener() {
            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                if (documentResponse.getDocuments() != null && !documentResponse.getDocuments().isEmpty()) {
                    searchItems.setValue(DataToModelConverter.convertSearchDataToViewModel(documentResponse));
                } else {
                    searchItems.setValue(null);
                }
            }

            @Override
            public void onDataFailed() {
                searchItems.setValue(null);
            }
        }, text);

        return searchItems;
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
                country.setValue(documentResponse.getDocuments().get(0).getDocument().getName());
            }
            @Override
            public void onDataFailed() { }
        }, categoryId);

        return country;
    }
}