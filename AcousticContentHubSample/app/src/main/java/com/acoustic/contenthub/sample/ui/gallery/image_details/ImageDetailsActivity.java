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

package com.acoustic.contenthub.sample.ui.gallery.image_details;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.data.elements.TextElementType;
import com.bumptech.glide.Glide;

import static com.acoustic.contenthub.sample.Constants.BASE_URL;

/**
 * Image details activity.
 */
public class ImageDetailsActivity extends AppCompatActivity {
    private static final String TAG = ImageDetailsActivity.class.getSimpleName();
    public static final String GALLERY_IMAGE_ID = "GALLERY_IMAGE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageDetailsViewModel imageDetailsViewModel =
                ViewModelProviders.of(this).get(ImageDetailsViewModel.class);

        final TextView imageDescription = findViewById(R.id.text_image_details);
        final ImageView image = findViewById(R.id.image_details);

        if (getIntent() != null && getIntent().getExtras() != null) {
            String id = getIntent().getExtras().getString(GALLERY_IMAGE_ID, "");

            imageDetailsViewModel.getImageDetails(id).observe(this, item -> {
                if (item.getDescriptionType() == TextElementType.text) {
                    imageDescription.setText(item.getDescription());
                } else if (item.getDescriptionType() == TextElementType.formattedtext) {
                    imageDescription.setText(Html.fromHtml(item.getDescription()));
                }

                toolbar.setTitle(item.getTitle());

                Glide.with(ImageDetailsActivity.this)
                        .load(BASE_URL + item.getImageUrl())
                        .placeholder(R.drawable.ic_image_grey_24dp)
                        .into(image);
            });
        } else {
            Log.i(TAG,"No id found for image details");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.navigation_back) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
