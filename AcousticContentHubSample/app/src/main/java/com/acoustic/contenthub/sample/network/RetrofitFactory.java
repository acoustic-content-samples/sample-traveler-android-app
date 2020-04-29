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

import com.acoustic.contenthub.sample.AcousticConfig;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The factory pattern implementation for creating {@link Retrofit} instances that setup for working with Acoustic API.
 */
public class RetrofitFactory {
    private static final int TIMEOUT_SECONDS = 20;

    public static final int INTERCEPT_FLAG_NONE = 0;
    public static final int INTERCEPT_FLAG_LOG = 1;

    private final Gson gson;

    private final AcousticConfig acousticConfig;
    private final InterceptorsFactory interceptorsFactory = new InterceptorsFactory();

    public RetrofitFactory(@NonNull Gson gson, @NonNull AcousticConfig acousticConfig) {
        this.gson = gson;
        this.acousticConfig = acousticConfig;
    }

    /**
     * Creates new instance of {@link Retrofit}, that setup for working with Acoustic API.
     *
     * @param interceptFlags the set of flags for intercepting url and providing required modifications in it.
     * @return new instance {@link Retrofit}.
     */
    Retrofit acousticApiService(int interceptFlags) {
        return new Retrofit
                .Builder()
                .baseUrl(acousticConfig.getApiUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(createOkHttpClient(interceptFlags))
                .build();
    }

    /**
     * Creates new instance of {@link OkHttpClient}.
     *
     * @param interceptFlags the set of flags for intercepting url and providing required modifications in it.
     * @return new instance {@link OkHttpClient}.
     */
    private OkHttpClient createOkHttpClient(int interceptFlags) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .cache(new Cache(acousticConfig.getAppCacheDir(), acousticConfig.getHttpCacheSizeMb() * 1024 * 1024));

        if ((interceptFlags | INTERCEPT_FLAG_LOG) == interceptFlags) {
            builder.addInterceptor(interceptorsFactory.createLoggingInterceptor(HttpLoggingInterceptor.Level.BODY));
        }

        return builder.build();
    }
}