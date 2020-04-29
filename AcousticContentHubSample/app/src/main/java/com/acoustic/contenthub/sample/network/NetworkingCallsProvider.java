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

import androidx.annotation.NonNull;

import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;

import retrofit2.Call;

/**
 * Provides particular instances of {@link Call}.
 */
class NetworkingCallsProvider {
    private RetrofitFactory retrofitFactory;

    NetworkingCallsProvider(@NonNull RetrofitFactory retrofitFactory) {
        this.retrofitFactory = retrofitFactory;
    }

    /**
     * Provides {@link Call} representing acoustic information data.
     *
     * @param query - the instance of {@link DeliverySearchQuery}, represents query required for call.
     * @return new {@link Call} instance.
     */
    Call<DocumentResponse> getSearchCall(@NonNull DeliverySearchQuery query) {
        return provideDeliverySearchNetworkService().documentSearch(
                query.q,
                query.fq,
                query.sort,
                query.fl,
                query.start,
                query.rows
        );
    }

    private DeliverySearchNetworkService provideDeliverySearchNetworkService() {
        return retrofitFactory
                .acousticApiService(RetrofitFactory.INTERCEPT_FLAG_LOG)
                .create(DeliverySearchNetworkService.class);
    }
}
