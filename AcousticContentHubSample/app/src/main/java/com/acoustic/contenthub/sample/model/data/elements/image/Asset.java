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

package com.acoustic.contenthub.sample.model.data.elements.image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Asset {

    @SerializedName("fileName")
    @Expose
    private String fileName;

    @SerializedName("altText")
    @Expose
    private String altText;

    @SerializedName("resourceUri")
    @Expose
    private String resourceUri;

    public String getFileName() {
        return fileName;
    }

    public String getAltText() {
        return altText;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "fileName='" + fileName + '\'' +
                ", altText='" + altText + '\'' +
                ", resourceUri='" + resourceUri + '\'' +
                '}';
    }
}
