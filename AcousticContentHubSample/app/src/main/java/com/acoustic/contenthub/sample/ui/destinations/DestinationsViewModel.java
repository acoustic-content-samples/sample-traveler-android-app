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

package com.acoustic.contenthub.sample.ui.destinations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acoustic.contenthub.sample.AcousticNativeApplication;
import com.acoustic.contenthub.sample.model.DataToModelConverter;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Region;
import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;
import com.acoustic.contenthub.sample.network.ReceiveDataListener;

import java.util.List;

import static com.acoustic.contenthub.sample.Constants.DOUBLE_DASH;

/**
 * {@link ViewModel} implementation for observing {@link List<Region>} updates.
 */
public class DestinationsViewModel extends ViewModel {
    private MutableLiveData<List<Region>> regionList;

    /**
     * Provides {@link Region} item list.
     *
     * @return {@link LiveData<List>} for observing data updates in view.
     */
    LiveData<List<Region>> getRegionList() {
        regionList = new MutableLiveData<>();
        AcousticNativeApplication.getDeliverySearchApi().fetchDestinationsInformation(new ReceiveDataListener() {
            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                regionList.setValue(DataToModelConverter.convertRegionDataToViewModel(documentResponse));
            }

            @Override
            public void onDataFailed() { }
        });
        return regionList;
    }

    /**
     * Provides Region name by category.
     *
     * @param category item category name.
     * @return {@link LiveData<String>} for observing region name updates in view.
     */
    LiveData<String> getRegionName(String category) {
        MutableLiveData<String> regionName = new MutableLiveData<>();

        AcousticNativeApplication.getDeliverySearchApi().fetchRegionInformation(new ReceiveDataListener() {
            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                if (documentResponse.getDocuments() != null && !documentResponse.getDocuments().isEmpty()) {
                    String name = documentResponse.getDocuments().get(0).
                            getDocument().getElements().getRegionTitle().getValue();

                    regionName.setValue(name != null ? name : DOUBLE_DASH);
                } else {
                    regionName.setValue(null);
                }
            }

            @Override
            public void onDataFailed() { }
        }, category);
        return regionName;
    }
}