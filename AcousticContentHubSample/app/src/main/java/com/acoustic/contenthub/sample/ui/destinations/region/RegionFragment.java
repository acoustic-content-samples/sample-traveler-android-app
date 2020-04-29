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

package com.acoustic.contenthub.sample.ui.destinations.region;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Region;

/**
 * Region tab fragment. Shows list of countries for chosen region.
 */
public class RegionFragment extends Fragment {
    private static final String CATEGORY_KEY = "CATEGORY_KEY";

    /**
     * Provides new instance of fragment with arguments.
     *
     * @param region region model item for showing.
     * @return new instance of fragment.
     */
    public static RegionFragment newInstance(Region region) {
        RegionFragment fragment = new RegionFragment();

        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_KEY, region.getRegionCategory());
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegionViewModel regionViewModel = ViewModelProviders.of(this).get(RegionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_destinations_region, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_country);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        final RegionAdapter adapter = new RegionAdapter();
        recyclerView.setAdapter(adapter);

        if (getArguments() != null) {
            String category = getArguments().getString(CATEGORY_KEY, "");

            regionViewModel.getCountryList(category).observe(this, adapter::setCountryData);
        }
        return root;
    }
}