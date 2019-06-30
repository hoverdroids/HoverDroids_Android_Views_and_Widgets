/*
 * Copyright (C) 2012 - 2019. HoverDroids and Christopher Sprague
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hoverdroids.gridviews.ViewGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hoverdroids.gridviews.Model.adapter.GenericItem;
import com.hoverdroids.gridviews.Util.GenericViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GenericAdapter extends BaseAdapter
{
    private Context context;
    private List<GenericItem> items;
    private List<Integer> layouts = new ArrayList<Integer>();
    private LayoutInflater inflater;

    public GenericAdapter(Context context, List<GenericItem> items){
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);

        resetLayouts();
    }

    @Override
    public int getViewTypeCount() {
        return layouts.size();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public GenericItem getItem(int position) {
        return items.get(position);
    }

    /** Get an item based on a UniqueId that is applied to each item and is project-specific.*/
    @Override
    public long getItemId(int position) {
        return getItem(position).getItemId();
    }

    /**
     * Get the view type used by the current item.
     * @param position The position of the item.
     * @return The view type index, which MUST be greater or equal to 1.
     */
    @Override
    public int getItemViewType(int position) {
        final int layoutResId = getItem(position).getLayoutResourceId();
        for(int i = 0; i < layouts.size(); i++){
            if(layoutResId == layouts.get(i)){
                return i;
            }
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final GenericItem item = getItem(position);
        final GenericViewHolder viewHolder;

        if (convertView == null){
            convertView = inflater.inflate(item.getLayoutResourceId(), parent, false);

            viewHolder = getViewHolder(convertView, position);
            if (viewHolder != null){
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = convertView.getTag() != null ? (GenericViewHolder) convertView.getTag() : null;
        }

        final boolean isLast = position == items.size() - 1;
        final boolean isFirst = position == 0;

        if (viewHolder != null) {
            viewHolder.updateViews(position, isFirst, isLast, getItem(position));
        }

        return convertView;
    }

    /** Override this to implement your own ViewHolder pattern. Otherwise, let's assume you're using one of the
     * generic items from the library, all of which implement the GenericViewHolder themselves.
     * @param convertView
     * @param position
     * @return
     */
    protected GenericViewHolder getViewHolder(View convertView, int position){
        return convertView instanceof GenericViewHolder ? (GenericViewHolder) convertView : null;
    }

    @Override
    public void notifyDataSetChanged() {
        //The data has already been updated but not the adapterView.So, update
        //the layoutTypes before updating the adapterView
        resetLayouts();

        //Update the adapterView
        super.notifyDataSetChanged();
    }

    public void setItems(List<GenericItem> items){
        this.items = items;
        notifyDataSetChanged();
    }

    private void resetLayouts(){
        layouts.clear();//TODO CHRIS-make sure this doesn't break things

        //Add unique layoutIds
        for (GenericItem item : items){
            final int layoutId = item.getLayoutResourceId();
            if (!layouts.contains(layoutId)){
                layouts.add(layoutId);
            }
        }
    }
}
