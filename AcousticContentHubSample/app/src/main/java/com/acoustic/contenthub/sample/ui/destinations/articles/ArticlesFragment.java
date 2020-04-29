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

package com.acoustic.contenthub.sample.ui.destinations.articles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Article;

/**
 * Articles fragment. Shows list of articles for chosen country.
 */
public class ArticlesFragment extends Fragment {
    public static final String COUNTRY_NAME_ID = "COUNTRY_NAME_ID";
    public static final String COUNTRY_CATEGORY_ID = "COUNTRY_CATEGORY_ID";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArticlesViewModel articlesViewModel =
                ViewModelProviders.of(this).get(ArticlesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_articles, container, false);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        RecyclerView articleRecyclerView = root.findViewById(R.id.recycler_view_articles);
        articleRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        ArticlesAdapter articleAdapter = new ArticlesAdapter();
        articleRecyclerView.setAdapter(articleAdapter);

        if (getArguments() != null) {
            String category = getArguments().getString(COUNTRY_CATEGORY_ID);
            articlesViewModel.getArticleItems(category).observe(this, articles -> {
                articleAdapter.setArticleData(articles);

                for (Article article : articles) {
                    articlesViewModel.getCountry(article.getCategoryId()).observe(ArticlesFragment.this, country -> {
                        article.setCountry(country);
                        articleAdapter.notifyItemChanged(articles.indexOf(article));
                    });
                }

            });

            String title = getArguments().getString(COUNTRY_NAME_ID, "");
            if (actionBar != null && !title.isEmpty()) {
                actionBar.setTitle(title);
            }
        }
        return root;
    }
}