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

package com.acoustic.contenthub.sample.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.gallery.GalleryItem;
import com.acoustic.contenthub.sample.ui.gallery.image_details.ImageDetailsActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.acoustic.contenthub.sample.Constants.BASE_URL;

/**
 *  {@link RecyclerView.Adapter} implementation for showing list of {@link GalleryItem} items.
 */
class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private final List<GalleryItem> galleryData = new ArrayList<>();

    /**
     * Sets new items list for showing in recycler view.
     *
     * @param newData new items list to show.
     */
    void setGalleryData(List<GalleryItem> newData) {
        galleryData.clear();
        galleryData.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GalleryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        holder.title.setText(galleryData.get(position).getTitle());

        Bundle args = new Bundle();
        args.putString(ImageDetailsActivity.GALLERY_IMAGE_ID, galleryData.get(position).getId());
        holder.itemView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_image_details, args));

        Glide.with(holder.itemView.getContext())
                .load(BASE_URL + galleryData.get(position).getImageUrl())
                .placeholder(R.drawable.ic_image_grey_24dp)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return galleryData.size();
    }

    static class GalleryViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_gallery);
            image = itemView.findViewById(R.id.image_gallery);
        }
    }
}
