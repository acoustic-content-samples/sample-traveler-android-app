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

package com.acoustic.contenthub.sample.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.acoustic.contenthub.sample.AcousticNativeApplication;
import com.acoustic.contenthub.sample.model.DataToModelConverter;
import com.acoustic.contenthub.sample.model.acoustic.contacts.ContactInformation;
import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;
import com.acoustic.contenthub.sample.network.ReceiveDataListener;

/**
 * {@link ViewModel} implementation for observing {@link ContactInformation} updates.
 */
public class ContactsViewModel extends ViewModel {
    private MutableLiveData<ContactInformation> contactInformation;

    /**
     * Provides {@link ContactInformation} item.
     *
     * @return {@link LiveData<ContactInformation>} for observing data updates in view.
     */
    LiveData<ContactInformation> getContactInformation() {
        contactInformation = new MutableLiveData<>();

        AcousticNativeApplication.getDeliverySearchApi().fetchContactInformation(new ReceiveDataListener() {

            @Override
            public void onDataReceived(DocumentResponse documentResponse) {
                if (documentResponse.getDocuments() != null && !documentResponse.getDocuments().isEmpty()) {
                    contactInformation.setValue(DataToModelConverter.convertContactsDataToViewModel(documentResponse));
                }
            }

            @Override
            public void onDataFailed() { }
        });
        return contactInformation;
    }

}
