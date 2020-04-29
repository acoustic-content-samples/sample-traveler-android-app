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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.destinations.Article;
import com.acoustic.contenthub.sample.utils.Utils;
import com.acoustic.contenthub.sample.ui.destinations.article_details.ArticleDetailsFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import static com.acoustic.contenthub.sample.Constants.BASE_URL;

/**
 *  {@link RecyclerView.Adapter} implementation for showing list of {@link Article} items.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {
    private List<Article> articleData = new ArrayList<>();

    /**
     * Sets new items list for showing in recycler view.
     *
     * @param newData new items list to show.
     */
    public void setArticleData(List<Article> newData) {
        articleData = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticlesAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.ArticleViewHolder holder, int position) {
        holder.title.setText(articleData.get(position).getTitle());
        holder.author.setText(holder.itemView.getResources().getString(R.string.home_author_text, articleData.get(position).getAuthor()));
        holder.date.setText(Utils.formatToShortDate(Utils.parseDate(articleData.get(position).getDate())));
        holder.county.setText(articleData.get(position).getCountry());

        Bundle args = new Bundle();
        args.putString(ArticleDetailsFragment.TRAVEL_ARTICLE_ID, articleData.get(position).getId());
        holder.itemView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_article_details, args));

        Glide.with(holder.itemView.getContext())
                .load(BASE_URL + articleData.get(position).getImageUrl())
                .placeholder(R.drawable.ic_image_grey_24dp)
                .fitCenter()
                .apply(new RequestOptions().optionalTransform(new RoundedCorners(16)))
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return articleData.size();
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
}
