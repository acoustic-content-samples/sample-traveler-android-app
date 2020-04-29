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

package com.acoustic.contenthub.sample.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.search.SearchResult;
import com.acoustic.contenthub.sample.model.acoustic.search.SearchType;

/**
 * Search fragment.
 */
public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private RecyclerView searchResultsRecyclerView;
    private SearchItemAdapter adapter;
    private View noData;

    private final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (!newText.isEmpty()) {
                performSearch(newText);
            } else {
                showNoData(true);
            }
            return false;
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        noData = root.findViewById(R.id.no_data);
        searchResultsRecyclerView = root.findViewById(R.id.search_results_recycler);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new SearchItemAdapter();
        searchResultsRecyclerView.setAdapter(adapter);

        SearchView searchView = root.findViewById(R.id.search_view);
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(queryTextListener);
        return root;
    }

    private void performSearch(String text) {
        searchViewModel.getSearchItems(text).observe(this, searchResults -> {
            if (searchResults == null) {
                showNoData(true);
            } else {
                showNoData(false);
                adapter.setSearchItemData(searchResults);

                for (SearchResult searchResult : searchResults) {
                    if (searchResult.getSearchType() == SearchType.ARTICLE) {
                        searchViewModel.getCountry(searchResult.
                                getArticlesItem().getCategoryId()).
                                observe(SearchFragment.this, country -> {
                                    searchResult.getArticlesItem().setCountry(country);
                                    adapter.notifyItemChanged(searchResults.indexOf(searchResult));
                        });
                    }
                }
            }
        });
    }

    private void showNoData(boolean isNoData) {
        searchResultsRecyclerView.setVisibility(isNoData ? View.GONE : View.VISIBLE);
        noData.setVisibility(isNoData ? View.VISIBLE : View.GONE);
    }
}