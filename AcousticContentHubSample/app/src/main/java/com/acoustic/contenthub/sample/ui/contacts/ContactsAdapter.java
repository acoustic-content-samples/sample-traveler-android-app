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

package com.acoustic.contenthub.sample.ui.contacts;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.acoustic.contenthub.sample.R;
import com.acoustic.contenthub.sample.model.acoustic.contacts.ContactItem;
import com.acoustic.contenthub.sample.model.data.elements.TextElementType;

import java.util.ArrayList;
import java.util.List;

/**
 *  {@link RecyclerView.Adapter} implementation for showing list of {@link ContactItem} items.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private final List<ContactItem> contactsData = new ArrayList<>();

    /**
     * Sets new items list for showing in recycler view.
     *
     * @param newData new items list to show.
     */
    void setContactsData(List<ContactItem> newData) {
        contactsData.clear();
        contactsData.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.title.setText(contactsData.get(position).getTitle());

        if (contactsData.get(position).getValueType() == TextElementType.text) {
            holder.value.setText(contactsData.get(position).getValue());
        } else if (contactsData.get(position).getValueType() == TextElementType.formattedtext) {
            holder.value.setText(Html.fromHtml(contactsData.get(position).getValue()));
        }
    }

    @Override
    public int getItemCount() {
        return contactsData.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView value;

        ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_contacts_title);
            value = itemView.findViewById(R.id.text_contacts_value);
        }

    }
}
