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

package com.acoustic.contenthub.sample.model.data.elements.group;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupElement<T> {

    @SerializedName("elementType")
    @Expose
    private String elementType;

    @SerializedName("value")
    @Expose
    private T value;

    public String getElementType() {
        return elementType;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "GroupElement{" +
                "elementType='" + elementType + '\'' +
                ", value=" + value +
                '}';
    }
}
