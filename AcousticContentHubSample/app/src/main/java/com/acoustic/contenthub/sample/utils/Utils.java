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

package com.acoustic.contenthub.sample.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class Utils {
    private static final String INITIAL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; // 2020-02-06T19:39:58.059Z
    private static final String SHORT_FORMAT = "MMM dd yyyy"; // Oct 24 2019

    public static Date parseDate(String dateValue) {
        SimpleDateFormat format = new SimpleDateFormat(INITIAL_FORMAT, Locale.getDefault());
        try {
            return format.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Calendar.getInstance().getTime();
    }

    public static String formatToShortDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(SHORT_FORMAT, Locale.getDefault());
        return format.format(date);
    }
}
