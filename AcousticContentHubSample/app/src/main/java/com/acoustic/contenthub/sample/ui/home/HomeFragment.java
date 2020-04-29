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

package com.acoustic.contenthub.sample.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Article;
import com.acoustic.contenthub.sample.ui.destinations.articles.ArticlesAdapter;
import com.acoustic.contenthub.sample.utils.AutoAdvanceTimer;
import com.google.android.material.tabs.TabLayout;

/**
 * Home screen fragment. Shows auto scrolling pager for gallery items and the list of articles.
 */
public class HomeFragment extends Fragment {
    private AutoAdvanceTimer autoAdvanceTimer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager galleryViewPager = root.findViewById(R.id.gallery_viewpager);
        HomeGalleryAdapter galleryAdapter = new HomeGalleryAdapter();
        galleryViewPager.setAdapter(galleryAdapter);
        galleryViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    autoAdvanceTimer.pauseTimer();
                } else if (state == ViewPager.SCROLL_STATE_IDLE) {
                    autoAdvanceTimer.resumeTimer();
                }
            }
        });

        TabLayout tabLayout = root.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(galleryViewPager, true);

        RecyclerView articleRecyclerView = root.findViewById(R.id.recycler_view_articles);
        articleRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

        ArticlesAdapter articleAdapter = new ArticlesAdapter();
        articleRecyclerView.setAdapter(articleAdapter);

        homeViewModel.getDisplayTime().observe(this, displayTime -> autoAdvanceTimer.startTimer(displayTime));
        homeViewModel.getGalleryItemList().observe(this, galleryAdapter::setHomeGalleryData);
        homeViewModel.getArticleItemList().observe(this, articles -> {
            articleAdapter.setArticleData(articles);

            for (Article article : articles) {
                homeViewModel.getCountry(article.getCategoryId()).observe(HomeFragment.this, country -> {
                    article.setCountry(country);
                    articleAdapter.notifyItemChanged(articles.indexOf(article));
                });
            }
        });

        Handler handler = new Handler(Looper.getMainLooper());
        autoAdvanceTimer = new AutoAdvanceTimer(() -> handler.post(() -> {
            if (galleryViewPager.getAdapter() != null
                    && galleryViewPager.getAdapter().getCount() > 0) {
                int numPages = galleryViewPager.getAdapter().getCount();
                int page = (galleryViewPager.getCurrentItem() + 1) % numPages;
                galleryViewPager.setCurrentItem(page);
            }
        }));

        homeViewModel.fetchHomeInformation();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        autoAdvanceTimer.resumeTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        autoAdvanceTimer.pauseTimer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        autoAdvanceTimer.cancelTimer();
    }
}