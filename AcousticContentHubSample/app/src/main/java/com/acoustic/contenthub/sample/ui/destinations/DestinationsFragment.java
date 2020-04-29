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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Region;
import com.acoustic.contenthub.sample.ui.destinations.region.RegionFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * Destinations fragment.
 */
public class DestinationsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DestinationsViewModel destinationsViewModel =
                ViewModelProviders.of(this).get(DestinationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_destinations, container, false);

        ViewPager regionsPager = root.findViewById(R.id.region_viewpager);
        TabLayout tabs = root.findViewById(R.id.region_tab_layout);
        tabs.setupWithViewPager(regionsPager);

        destinationsViewModel.getRegionList().observe(this, regions -> {
            DestinationsViewPagerAdapter adapter = new DestinationsViewPagerAdapter(getChildFragmentManager());
            for (Region region : regions) {
                RegionItem RegionItem = new RegionItem(RegionFragment.newInstance(region));
                adapter.addItem(RegionItem);

                destinationsViewModel.getRegionName(region.getRegionCategory()).observe(DestinationsFragment.this, title -> {
                    if (title != null) {
                        RegionItem.setTitle(title);
                    } else {
                        adapter.removeItem(RegionItem);
                    }
                    adapter.notifyDataSetChanged();
                });
            }
            regionsPager.setAdapter(adapter);
        });
        return root;
    }
}