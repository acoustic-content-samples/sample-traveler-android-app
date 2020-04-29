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

package com.acoustic.contenthub.sample.model.data.document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("elements")
    @Expose
    private Elements elements;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("lastModified")
    @Expose
    private String lastModified;

    public String getName() {
        return name;
    }

    public Elements getElements() {
        return elements;
    }

    public String getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", elements=" + elements +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", created='" + created + '\'' +
                ", lastModified='" + lastModified + '\'' +
                '}';
    }
}
