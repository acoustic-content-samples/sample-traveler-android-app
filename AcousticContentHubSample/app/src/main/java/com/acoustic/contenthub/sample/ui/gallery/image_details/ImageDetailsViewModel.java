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

package com.acoustic.contenthub.sample.ui.gallery.image_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acoustic.contenthub.sample.AcousticNativeApplication;
import com.acoustic.contenthub.sample.model.DataToModelConverter;
import com.acoustic.contenthub.sample.model.acoustic.gallery.ImageDetails;
import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;
import com.acoustic.contenthub.sample.network.ReceiveDataListener;

import java.util.List;

/**
 * {@link ViewModel} implementation for observing {@link ImageDetails} updates.
 */
public class ImageDetailsViewModel extends ViewModel {
    private MutableLiveData<ImageDetails> imageDetails;

    /**
     * Provides {@link ImageDetails} item by id.
     *
     * @param id item id.
     * @return {@link LiveData<ImageDetails>} for observing data updates in view.
     */
    LiveData<ImageDetails> getImageDetails(String id) {
        imageDetails = new MutableLiveData<>();
        AcousticNativeApplication.getDeliverySearchApi().fetchGalleryItem(new ReceiveDataListener() {

            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                if (documentResponse.getDocuments() != null && !documentResponse.getDocuments().isEmpty()) {
                    imageDetails.setValue(DataToModelConverter.convertImageDetailsDataToViewModel(documentResponse));
                }
            }

            @Override
            public void onDataFailed() { }
        }, id);
        return imageDetails;
    }
}
