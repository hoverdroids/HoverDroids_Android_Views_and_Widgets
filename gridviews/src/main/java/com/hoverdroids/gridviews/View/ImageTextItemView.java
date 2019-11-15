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

package com.hoverdroids.gridviews.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoverdroids.gridviews.Model.ImageViewItem;
import com.hoverdroids.gridviews.Model.TextViewItem;
import com.hoverdroids.gridviews.Model.ViewItem;
import com.hoverdroids.gridviews.Util.GenericViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use this as a wrapper in an xml layout that has an ImageView with ID=item_image and a TextView with ID=item_text.
 * The benefit of this approach is that you can use any view layout you wish and those two fields with get updated
 * with the model data used to create the GenericItem in the adapter, and you never need to write another adapter or
 * custom view for ListViews or GridViews.
 *
 * The added benefit is that this will work for both ListViews and GridViews since it's only purpose is to populate
 * the specified views with the specified model data.
 */
public class ImageTextItemView extends LinearLayout implements GenericViewHolder
{
    private Map<Integer, View> views = new HashMap<Integer, View>();

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

    private void init(){
        //TODO Use Dagger to inject singletons we need - e.g. EventBus
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViews();
    }

    private void initViews(){
        if (getId() != NO_ID){
            views.put(getId(), this);
        }

        for (int i = 0; i < getChildCount(); i++){
            final View view = getChildAt(i);
            final int id = view.getId();
            if (id != NO_ID) {
                views.put(view.getId(), view);
            }
        }
    }

    @Override
    public void updateViews(int position, boolean isFirst, boolean isLast, Object item)
    {
        if (item instanceof ViewItem){
            final ViewItem viewItem = (ViewItem) item;
            final List<Integer> ids = viewItem.getViewIds();
            for (Integer id : ids){
                updateView(views.get(id), id, viewItem);
            }
        }
    }

    private void updateView(View view, int id, ViewItem item){
        //It's always possible to map the generic view to the viewItem, so update without checking.
        final int bgColor = item.getBackgroundColor(id);
        if (bgColor != Integer.MIN_VALUE) {
            view.setBackgroundColor(bgColor);
        }

        //There is no guarantee that the an item was setup correctly and hence we must match the
        //view type and the item type
        if (view instanceof ImageView && item instanceof ImageViewItem) {
            updateView((ImageView)view, id, (ImageViewItem)item);
        }

        if (view instanceof TextView && item instanceof TextViewItem){
            updateView((TextView)view, id, (TextViewItem)item);
        }
    }

    private void updateView(TextView view, int id, TextViewItem item){
        final String text = item.getText(id);
        if (text != null) {
            view.setText(text);
        }
    }

    private void updateView(ImageView view, int id, ImageViewItem item){
        //TODO The following is technically possible on a view. We need to update for imageView attrs
        final int bgResId = item.getImageResourceId(id);
        if (bgResId != Integer.MIN_VALUE) {
            view.setBackgroundResource(bgResId);
        }
    }
}
