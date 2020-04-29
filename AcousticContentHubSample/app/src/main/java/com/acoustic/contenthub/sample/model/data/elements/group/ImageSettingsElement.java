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

import com.acoustic.contenthub.sample.model.data.elements.ValueElement;
import com.acoustic.contenthub.sample.model.data.elements.selection.SelectionElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageSettingsElement {

    @SerializedName("numberOfListItems")
    @Expose
    private ValueElement numberOfListItems;

    @SerializedName("DisplayTime")
    @Expose
    private ValueElement displayTime;

    @SerializedName("contentTypeToDisplay")
    @Expose
    private SelectionElement contentTypeToDisplay;

    @SerializedName("displayOrder")
    @Expose
    private SelectionElement displayOrder;

    public ValueElement getNumberOfListItems() {
        return numberOfListItems;
    }

    public ValueElement getDisplayTime() {
        return displayTime;
    }

    public SelectionElement getContentTypeToDisplay() {
        return contentTypeToDisplay;
    }

    public SelectionElement getDisplayOrder() {
        return displayOrder;
    }

    @Override
    public String toString() {
        return "ImageSettingsElement{" +
                "numberOfListItems=" + numberOfListItems +
                ", displayTime=" + displayTime +
                ", contentTypeToDisplay=" + contentTypeToDisplay +
                ", displayOrder=" + displayOrder +
                '}';
    }
}
