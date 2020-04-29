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

package com.acoustic.contenthub.sample.ui.destinations;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Pager Adapter for sliding fragments with titles.
 */
public class DestinationsViewPagerAdapter extends FragmentPagerAdapter {
    private List<RegionItem> itemsList = new ArrayList<>();

    DestinationsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Adds item to adapter.
     *
     * @param item region item instance to add
     */
    void addItem(RegionItem item) {
        itemsList.add(item);
    }

    /**
     * Removes item from adapter.
     *
     * @param item region item instance to remove
     */
    void removeItem(RegionItem item) {
        itemsList.remove(item);
    }

    @Override
    public Fragment getItem(int position) {
        return itemsList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itemsList.get(position).getTitle();
    }
}
