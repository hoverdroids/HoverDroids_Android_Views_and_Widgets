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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoverdroids.gridviews.Model.adapter.GenericItem;
import com.hoverdroids.gridviews.Model.adapter.ImageTextItem;
import com.hoverdroids.gridviews.R;
import com.hoverdroids.gridviews.Util.GenericViewHolder;
import com.hoverdroids.gridviews.ViewGroup.HierarchyTreeChangeListener;

/**
 * Use this as a wrapper in an xml layout that has an ImageView with ID=item_image and a TextView with ID=item_text.
 * The benefit of this approach is that you can use any view layout you wish and those two fields with get updated
 * with the model data used to create the GenericItem in the adapter, and you never need to write another adapter or
 * custom view for ListViews or GridViews.
 *
 * The added benefit is that this will work for both ListViews and GridViews since it's only purpose is to populate
 * the specified views with the specified model data.
 */
public class ImageTextItemView extends LinearLayout implements GenericViewHolder, ViewGroup.OnHierarchyChangeListener
{
    private ImageView imageView;
    private TextView textView;

    public ImageTextItemView(Context context) {
        super(context);
        init();
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
        //The child views don't exist when this is created. So, listen for when the children are added instead.
        setOnHierarchyChangeListener(HierarchyTreeChangeListener.wrap(this));
    }

    //TODO just use butterknife in onAttachedToWindow-may not work since it's a module

    @Override
    public void updateViews(int position, boolean isFirst, boolean isLast, GenericItem item)
    {
        if (item instanceof ImageTextItem)
        {
            final ImageTextItem imgTxt = (ImageTextItem) item;
            imageView.setBackgroundResource(imgTxt.getImageResourceId());
            textView.setText(imgTxt.getText());
        }
    }

    @Override
    public void onChildViewAdded(View parent, View child)
    {
        //Could technically call findViewsById, but such a waste.
        if (child instanceof ImageView && child.getId() == R.id.item_image) {
            imageView = (ImageView) child;
        } else if (child instanceof TextView && child.getId() == R.id.item_text){
            textView = (TextView) child;
        }
    }

    @Override
    public void onChildViewRemoved(View parent, View child)
    {
        //Not necessary, but the method is here; might as well use it.
        if (child instanceof ImageView && child.getId() == R.id.item_image) {
            imageView = null;
        } else if (child instanceof TextView && child.getId() == R.id.item_text){
            textView = null;
        }
    }
}
