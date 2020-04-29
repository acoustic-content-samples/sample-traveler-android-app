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

import androidx.annotation.NonNull;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * Provides acoustic native sample app configuration.
 */
public class AcousticConfig {
    private final URL apiUrl;

    private final File appCacheDir;
    private final Integer httpCacheSizeMb;

    private AcousticConfig(Builder builder) throws IllegalArgumentException {
        apiUrl = builder.getApiUrl();
        appCacheDir = builder.getAppCacheDir();
        httpCacheSizeMb = builder.getHttpCacheSizeMb();
    }

    /**
     * Provides API URL.
     *
     * @return url value.
     */
    public URL getApiUrl() {
        return apiUrl;
    }

    /**
     * Provides app caching directory.
     *
     * @return caching {@link File} instance.
     */
    public File getAppCacheDir() {
        return appCacheDir;
    }

    /**
     * Provides caching size for http requests.
     *
     * @return caching size value in mb.
     */
    public Integer getHttpCacheSizeMb() {
        return httpCacheSizeMb;
    }

    static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcousticConfig config = (AcousticConfig) o;

        if (!Objects.equals(apiUrl, config.apiUrl))
            return false;
        if (!Objects.equals(appCacheDir, config.appCacheDir))
            return false;
        return Objects.equals(httpCacheSizeMb, config.httpCacheSizeMb);
    }

    @Override
    public int hashCode() {
        int result = apiUrl != null ? apiUrl.hashCode() : 0;
        result = 31 * result + (appCacheDir != null ? appCacheDir.hashCode() : 0);
        result = 31 * result + (httpCacheSizeMb != null ? httpCacheSizeMb.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Config{" +
                "apiUrl=" + apiUrl +
                ", appCacheDir='" + appCacheDir + '\'' +
                ", httpCacheSizeMb='" + httpCacheSizeMb + '\'' +
                '}';
    }

    /**
     * The builder implementation for constructing AcousticConfig.
     */
    public static class Builder {
        private URL apiUrl = null;

        private File appCacheDir = null;
        private Integer httpCacheSizeMb = null;

        private Builder() {
        }

        /**
         * Sets API URL for the config that will be build.
         *
         * @param apiUrl the {@link String} that represents acoustic api url. The default to {@code null}.
         *               Cannot be {@code null} or empty string.
         * @return the current {@link Builder} instance, to continue building.
         * @see #getApiUrl()
         */
        Builder setApiUrl(@NonNull String apiUrl) {
            try {
                this.apiUrl = new URL(apiUrl);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Invalid API URL", e);
            }
            return this;
        }

        /**
         * Get's API URL for SDK config that will be build.
         *
         * @see #setApiUrl(String)
         */
        URL getApiUrl() {
            return apiUrl;
        }

        /**
         * Sets http cache parameters for SDK config that will be build.
         *
         * @param appCacheDir     the {@link File} that represents application cache directory.
         * @param httpCacheSizeMb the size of cache for http requests in Mb.
         *
         * @return the current {@link Builder} instance, to continue building.
         * @see #getAppCacheDir()
         * @see #getHttpCacheSizeMb()
         */
        Builder setHttpCacheParams(File appCacheDir, Integer httpCacheSizeMb) {
            this.appCacheDir = appCacheDir;
            this.httpCacheSizeMb = httpCacheSizeMb;
            return this;
        }

        File getAppCacheDir() {
            return appCacheDir;
        }

        Integer getHttpCacheSizeMb() {
            return httpCacheSizeMb;
        }

        /**
         * Creates new instance of {@link AcousticConfig}.
         */
        @NonNull
        AcousticConfig build() {
            return new AcousticConfig(this);
        }
    }
}
