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
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The builder implementation for constructing {@link DeliverySearchQuery}.
 */
class DeliverySearchQueryBuilder {

    private static final String DEFAULT_QUERY_PARAM = "*:*";

    private static final String DEFAULT_ALL_FIELDS_PARAM = "*";
    private static final String DEFAULT_JSON_DOCUMENT_FIELD_PARAM = "document:[json]";

    private static final String ASC_VALUE = "asc";
    private static final String DESC_VALUE = "desc";
    private static final String AND_OPERATOR = " AND ";
    private static final String OR_OPERATOR = " OR ";

    private String q = DEFAULT_QUERY_PARAM;
    private List<String> fl = new ArrayList<>(2);
    {
        fl.add(DEFAULT_ALL_FIELDS_PARAM);
        fl.add(DEFAULT_JSON_DOCUMENT_FIELD_PARAM);
    }
    private List<String> filterQueryList = new LinkedList<>();

    //Using LinkedHashMap in order to support sort rules overlapping and ordering. 
    private Map<String, Boolean> sortRules = new LinkedHashMap<>(16, 0.75f, true);

    private Integer start;
    private Integer rows;

    DeliverySearchQueryBuilder() {
    }

    DeliverySearchQueryBuilder filterQuery(String field, String value) {
        return filterQuery(field + ":" + value);
    }

    DeliverySearchQueryBuilder filterQuery(String filterQuery) {
        filterQueryList.add(filterQuery);
        return this;
    }

    @Nullable
    List<String> getFilterQuery() {
        return new ArrayList<>(filterQueryList);
    }

    List<String> getFl() {
        return new ArrayList<>(fl);
    }

    DeliverySearchQueryBuilder sort(@NonNull String field, boolean asc) {
        sortRules.put(field, asc);
        return this;
    }

    private String getSortOrder(boolean asc) {
        return (asc) ? ASC_VALUE : DESC_VALUE;
    }

    @Nullable
    String getSortString() {
        if (sortRules.isEmpty()) {
            return null;
        }

        StringBuilder sortBuilder = new StringBuilder();
        for (Map.Entry<String, Boolean> rule : sortRules.entrySet()) {
            if (sortBuilder.length() > 0) {
                sortBuilder.append(",");
            }
            sortBuilder.append(rule.getKey())
                    .append(" ")
                    .append(getSortOrder(rule.getValue()));
        }
        return sortBuilder.toString();
    }

    DeliverySearchQueryBuilder query(String... query) {
        if (query.length > 1) {
            StringBuilder builder = new StringBuilder(query[0]);
            for (int i = 1; i < query.length; i++) {
                builder.append(AND_OPERATOR).append(query[i]);
            }
            this.q = builder.toString();
        } else if (query.length == 1){
            this.q = query[0];
        }
        return this;
    }

    DeliverySearchQueryBuilder queryOr(String... query) {
        if (query.length > 1) {
            StringBuilder builder = new StringBuilder(query[0]);
            for (int i = 1; i < query.length; i++) {
                builder.append(OR_OPERATOR).append(query[i]);
            }
            this.q = builder.toString();
        } else if (query.length == 1){
            this.q = query[0];
        }
        return this;
    }

    @NonNull
    String getQString() {
        return q;
    }

    DeliverySearchQueryBuilder start(int start) {
        this.start = start;
        return this;
    }

    @Nullable
    Integer getStart() {
        return start;
    }

    DeliverySearchQueryBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }

    @Nullable
    Integer getRows() {
        return rows;
    }

    DeliverySearchQuery build() {
        return new DeliverySearchQuery(this);
    }
}
