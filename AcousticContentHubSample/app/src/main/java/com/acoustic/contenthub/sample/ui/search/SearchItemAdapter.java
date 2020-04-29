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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.search.SearchResult;
import com.acoustic.contenthub.sample.model.acoustic.search.SearchType;
import com.acoustic.contenthub.sample.ui.destinations.article_details.ArticleDetailsFragment;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Article;
import com.acoustic.contenthub.sample.model.acoustic.gallery.GalleryItem;
import com.acoustic.contenthub.sample.ui.gallery.image_details.ImageDetailsActivity;
import com.acoustic.contenthub.sample.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import static com.acoustic.contenthub.sample.Constants.BASE_URL;

/**
 *  {@link RecyclerView.Adapter} implementation for showing list of {@link SearchResult} items.
 *  Contains two item view types: one represents {@link Article} model data, another - {@link GalleryItem} model data.
 */
public class SearchItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int TYPE_GALLERY = 1;
    private static int TYPE_ARTICLE = 2;

    private List<SearchResult> searchItemData = new ArrayList<>();

    /**
     * Sets new searching results for showing in recycler view.
     *
     * @param newData new searching results.
     */
    void setSearchItemData(List<SearchResult> newData) {
        searchItemData = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (searchItemData.get(position).getSearchType() == SearchType.ARTICLE) {
            return TYPE_ARTICLE;
        } else {
            return TYPE_GALLERY;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ARTICLE) {
            return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_article, parent, false));
        } else {
            return new GalleryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_gallery, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_GALLERY) {
            setGalleryData((GalleryViewHolder) holder, searchItemData.get(position).getGalleryItem());
        } else {
            setArticleData((ArticleViewHolder) holder, searchItemData.get(position).getArticlesItem());
        }

    }

    private void setGalleryData(GalleryViewHolder holder, GalleryItem data) {
        holder.title.setText(data.getTitle());

        Bundle args = new Bundle();
        args.putString(ImageDetailsActivity.GALLERY_IMAGE_ID, data.getId());
        holder.itemView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_image_details, args));

        Glide.with(holder.itemView.getContext())
                .load(BASE_URL + data.getImageUrl())
                .placeholder(R.drawable.ic_image_grey_24dp)
                .centerCrop()
                .into(holder.image);
    }

    private void setArticleData(ArticleViewHolder holder, Article data) {
        holder.title.setText(data.getTitle());
        holder.author.setText(holder.itemView.getResources().getString(R.string.home_author_text, data.getAuthor()));
        holder.date.setText(Utils.formatToShortDate(Utils.parseDate(data.getDate())));
        holder.county.setText(data.getCountry());

        Bundle args = new Bundle();
        args.putString(ArticleDetailsFragment.TRAVEL_ARTICLE_ID, data.getId());
        holder.itemView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_article_details, args));

        Glide.with(holder.itemView.getContext())
                .load(BASE_URL + data.getImageUrl())
                .placeholder(R.drawable.ic_image_grey_24dp)
                .fitCenter()
                .apply(new RequestOptions().optionalTransform(new RoundedCorners(16)))
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return searchItemData.size();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        TextView date;
        TextView county;
        ImageView image;

        ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_article);
            author = itemView.findViewById(R.id.author_article);
            date = itemView.findViewById(R.id.date_article);
            county = itemView.findViewById(R.id.country_article);
            image = itemView.findViewById(R.id.image_article);
        }
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
