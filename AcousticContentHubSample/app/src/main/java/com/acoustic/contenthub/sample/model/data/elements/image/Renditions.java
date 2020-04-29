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

/**
 * Renditions data represents raw data of renditions list section in acoustic document
 *
 * Each section represent values related to each document type
 */
public class Renditions {
    // Article elements part start
    @SerializedName("card")
    @Expose
    private Rendition card;

    public Rendition getCard() {
        return card;
    }
    // Article elements part end

    // Country elements part start
    @SerializedName("countryCard")
    @Expose
    private Rendition countryCard;

    @SerializedName("destinationsCard")
    @Expose
    private Rendition destinationsCard;

    public Rendition getCountryCard() {
        return countryCard;
    }

    public Rendition getDestinationsCard() {
        return destinationsCard;
    }
    // Country elements part end

    // Gallery elements part start
    @SerializedName("squareCard")
    @Expose
    private Rendition squareCard;

    @SerializedName("rectangleCard")
    @Expose
    private Rendition rectangleCard;

    public Rendition getSquareCard() {
        return squareCard;
    }

    public Rendition getRectangleCard() {
        return rectangleCard;
    }
    // Gallery elements part end

    @SerializedName("default")
    @Expose
    private Rendition defaultValue;

    public Rendition getDefault() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return "Renditions{" +
                "card=" + card +
                ", countryCard=" + countryCard +
                ", destinationsCard=" + destinationsCard +
                ", squareCard=" + squareCard +
                ", rectangleCard=" + rectangleCard +
                ", defaultValue=" + defaultValue +
                '}';
    }
}
