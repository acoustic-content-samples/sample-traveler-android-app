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

package com.acoustic.contenthub.sample.ui.destinations.article_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acoustic.contenthub.sample.AcousticNativeApplication;
import com.acoustic.contenthub.sample.model.DataToModelConverter;
import com.acoustic.contenthub.sample.model.acoustic.destinations.ArticleDetails;
import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;
import com.acoustic.contenthub.sample.network.ReceiveDataListener;

/**
 * {@link ViewModel} implementation for observing {@link ArticleDetails} updates.
 */
public class ArticleDetailsViewModel extends ViewModel {
    private MutableLiveData<ArticleDetails> articleDetails;

    /**
     * Provides {@link ArticleDetails} item by id.
     *
     * @param id item id for fetching.
     * @return {@link LiveData<ArticleDetails>} for observing data updates in view.
     */
    LiveData<ArticleDetails> getArticleDetails(String id) {
        articleDetails = new MutableLiveData<>();

        AcousticNativeApplication.getDeliverySearchApi().fetchArticleItem(new ReceiveDataListener() {
            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                if (documentResponse.getDocuments() != null && !documentResponse.getDocuments().isEmpty()) {
                    articleDetails.setValue(DataToModelConverter.convertArticleDetailsDataToViewModel(documentResponse));
                }
            }

            @Override
            public void onDataFailed() { }
        }, id);
        return articleDetails;
    }
}
