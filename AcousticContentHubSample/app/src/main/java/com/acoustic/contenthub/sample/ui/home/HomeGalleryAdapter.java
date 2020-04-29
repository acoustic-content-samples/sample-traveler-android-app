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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.gallery.HomeGalleryItem;
import com.acoustic.contenthub.sample.ui.gallery.image_details.ImageDetailsActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.acoustic.contenthub.sample.Constants.BASE_URL;

public class HomeGalleryAdapter extends PagerAdapter {
    private final List<HomeGalleryItem> homeGalleryData = new ArrayList<>();

    /**
     * Sets new items list for showing in view pager.
     *
     * @param newData new items list to show.
     */
    void setHomeGalleryData(List<HomeGalleryItem> newData) {
        homeGalleryData.clear();
        homeGalleryData.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_home_image, container,false);

        TextView title = view.findViewById(R.id.title_gallery_home);
        TextView text = view.findViewById(R.id.text_gallery_home);
        ImageView image = view.findViewById(R.id.image_gallery_home);

        title.setText(homeGalleryData.get(position).getHomeTitle());
        text.setText(homeGalleryData.get(position).getDescription());

        Bundle args = new Bundle();
        args.putString(ImageDetailsActivity.GALLERY_IMAGE_ID, homeGalleryData.get(position).getId());
        view.setOnClickListener(v -> Navigation.findNavController(container).navigate(R.id.action_image_details, args));

        Glide.with(view.getContext())
                .load(BASE_URL + homeGalleryData.get(position).getImageUrl())
                .placeholder(R.drawable.ic_image_grey_24dp)
                .centerCrop()
                .into(image);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return homeGalleryData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
