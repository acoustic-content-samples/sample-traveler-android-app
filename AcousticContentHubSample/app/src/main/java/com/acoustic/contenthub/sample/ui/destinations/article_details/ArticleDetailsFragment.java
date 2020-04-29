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

package com.acoustic.contenthub.sample.ui.destinations.article_details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.utils.Utils;
import com.bumptech.glide.Glide;

import static com.acoustic.contenthub.sample.Constants.BASE_URL;

/**
 * Article details screen.
 */
public class ArticleDetailsFragment extends Fragment {
    private static final String TAG = ArticleDetailsFragment.class.getSimpleName();
    public static final String TRAVEL_ARTICLE_ID = "TRAVEL_ARTICLE_ID";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArticleDetailsViewModel articleDetailsViewModel =
                ViewModelProviders.of(this).get(ArticleDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_article_details, container, false);

        TextView title = root.findViewById(R.id.title_article_details);
        TextView author = root.findViewById(R.id.author_article_details);
        TextView date = root.findViewById(R.id.date_article_details);
        ImageView image = root.findViewById(R.id.image_article_details);
        WebView webView = root.findViewById(R.id.text_article_details);
        webView.setInitialScale(1);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        if (getArguments() != null) {
            String id = getArguments().getString(TRAVEL_ARTICLE_ID, "");

            articleDetailsViewModel.getArticleDetails(id).observe(this, item -> {
                if (actionBar != null) {
                    actionBar.setTitle(item.getTitle());
                }

                title.setText(item.getTitle());
                author.setText(getResources().getString(R.string.home_author_text, item.getAuthor()));
                date.setText(Utils.formatToShortDate(Utils.parseDate(item.getDate())));
                Glide.with(requireContext())
                        .load(BASE_URL + item.getImageUrl())
                        .placeholder(R.drawable.ic_image_grey_24dp)
                        .into(image);
                webView.loadDataWithBaseURL(BASE_URL, item.getContent(), null, null, null);
            });
        }  else {
            Log.i(TAG,"No id found for travel article");
        }
        return root;
    }
}