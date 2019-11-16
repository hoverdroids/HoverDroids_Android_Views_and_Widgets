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

package com.hoverdroids.gridviews.itemview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.hoverdroids.gridviews.util.ViewUtils;
import com.hoverdroids.gridviews.viewitem.ViewItem;

import java.util.HashMap;
import java.util.Map;

public class ImageTextItemView extends LinearLayoutCompat implements AdapterItemView, ItemView
{
    private Map<Integer, View> viewIds = new HashMap<Integer, View>();

    private ViewItem item;

    public ImageTextItemView(Context context) {
        super(context);
        init();
        initViews();
    }

    public ImageTextItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageTextItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected void init(){
        //TODO Use Dagger to inject singletons we need - e.g. EventBus
    }

    protected void initViews() {
        /** Map all views with IDs for quicker reference - especially in AdapterViews. */
        viewIds = ViewUtils.getViewIdMapping(this);
    }

    /**
     * Set the view item from an AdapterView - e.g. when used in a ListView.
     * @param position The items's position in the adapter.
     * @param isFirst Is it the first position in the adapter.
     * @param isLast Is it the last position in the adapter.
     * @param item The item for the given position in the adapter.
     */
    @Override
    public void updateViews(final int position, final boolean isFirst, final boolean isLast, final ViewItem item) {
        //TODO handle case where isFirst, isLast. Typically, this allows the item to display as a header or footer.
        setViewItem(item);
    }

    /**
     * Set viewItem.
     * @param item The view item
     */
    @Override
    public void setViewItem(ViewItem item) {
        this.item = item;

        //Update own attrs
        ViewUtils.setBackgroundColor(this, item);

        //Update child attrs
        ViewUtils.setChildrenViewItems(viewIds, item);
    }
}