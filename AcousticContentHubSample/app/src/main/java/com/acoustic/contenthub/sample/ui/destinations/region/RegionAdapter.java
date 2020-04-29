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

package com.acoustic.contenthub.sample.ui.destinations.region;

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
import com.acoustic.contenthub.sample.model.acoustic.destinations.Country;
import com.acoustic.contenthub.sample.ui.destinations.articles.ArticlesFragment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.acoustic.contenthub.sample.Constants.BASE_URL;

/**
 *  {@link RecyclerView.Adapter} implementation for showing list of {@link Country} items.
 */
class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.CountryViewHolder> {

    private final List<Country> countryData = new ArrayList<>();

    /**
     * Sets new items list for showing in recycler view.
     *
     * @param newData new items list to show.
     */
    void setCountryData(List<Country> newData) {
        countryData.clear();
        countryData.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destinations_country, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.title.setText(countryData.get(position).getTitle());

        Bundle args = new Bundle();
        args.putString(ArticlesFragment.COUNTRY_NAME_ID, countryData.get(position).getTitle());
        args.putString(ArticlesFragment.COUNTRY_CATEGORY_ID, countryData.get(position).getCategory());
        holder.itemView.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_articles, args));

        Glide.with(holder.itemView.getContext())
                .load(BASE_URL + countryData.get(position).getImageUrl())
                .placeholder(R.drawable.ic_image_grey_24dp)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return countryData.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;

        CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_country);
            image = itemView.findViewById(R.id.image_country);
        }

    }
}
