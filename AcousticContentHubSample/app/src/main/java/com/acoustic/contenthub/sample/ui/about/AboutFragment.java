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

package com.acoustic.contenthub.sample.ui.about;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.data.elements.TextElementType;

/**
 * About information fragment.
 */
public class AboutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AboutViewModel aboutViewModel =
                ViewModelProviders.of(this).get(AboutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        final TextView aboutTitleView = root.findViewById(R.id.text_about_title);
        final TextView aboutContentView = root.findViewById(R.id.text_about_content);

        aboutViewModel.getAboutInformation().observe(this, aboutInformation -> {
            if (aboutInformation.getTitleType() == TextElementType.text) {
                aboutTitleView.setText(aboutInformation.getTitle());
            } else if (aboutInformation.getTitleType() == TextElementType.formattedtext) {
                aboutTitleView.setText(Html.fromHtml(aboutInformation.getTitle()));
            }

            if (aboutInformation.getContentType() == TextElementType.text) {
                aboutContentView.setText(aboutInformation.getContent());
            } else if (aboutInformation.getContentType() == TextElementType.formattedtext) {
                aboutContentView.setText(Html.fromHtml(aboutInformation.getContent()));
            }
        });

        return root;
    }
}
