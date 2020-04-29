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

package com.acoustic.contenthub.sample;

import android.app.Application;

import com.acoustic.contenthub.sample.network.DeliverySearchAPI;
import com.acoustic.contenthub.sample.network.RetrofitFactory;
import com.google.gson.GsonBuilder;

import static com.acoustic.contenthub.sample.Constants.API_URL;
import static com.acoustic.contenthub.sample.Constants.HTTP_CACHE_SIZE_MB;

/**
 * Acoustic native sample application implementation.
 */
public class AcousticNativeApplication extends Application {

    private static DeliverySearchAPI deliverySearchApi;

    @Override
    public void onCreate() {
        super.onCreate();

        deliverySearchApi = new DeliverySearchAPI(
                new RetrofitFactory(
                        new GsonBuilder().setLenient().create(),
                        AcousticConfig.builder().
                                setApiUrl(API_URL)
                                .setHttpCacheParams(getCacheDir(), HTTP_CACHE_SIZE_MB)
                                .build()
                ));
    }

    /**
     * Provides instance of {@link DeliverySearchAPI} for content search.
     *
     * @return instance of delivery search api.
     */
    public static DeliverySearchAPI getDeliverySearchApi() {
        return deliverySearchApi;
    }
}
