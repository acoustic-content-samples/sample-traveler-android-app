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

import com.acoustic.contenthub.sample.model.data.document.DocumentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Defines contract for delivery search from Watson Content Hub api.
 */
interface DeliverySearchNetworkService {

    @GET("delivery/v1/search")
    Call<DocumentResponse> documentSearch(
            @Query("q") String q,
            @Query("fq") List<String> fq,
            @Query("sort") String sort,
            @Query("fl") List<String> fl,
            @Query("start") Integer start,
            @Query("rows") Integer rows
    );
}