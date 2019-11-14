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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoverdroids.gridviews.Model.ImageViewItem;
import com.hoverdroids.gridviews.Model.TextViewItem;
import com.hoverdroids.gridviews.R;
import com.hoverdroids.gridviews.Util.GenericViewHolder;

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
    private ImageView imageView;
    private TextView textView;

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
        imageView = findViewById(R.id.image_view_1);
        textView = findViewById(R.id.text_view_1);
    }

    @Override
    public void updateViews(int position, boolean isFirst, boolean isLast, Object item)
    {
        if (item instanceof ImageViewItem){
            final ImageViewItem img = (ImageViewItem) item;
            imageView.setBackgroundResource(img.getImageResourceId());
        }
        if (item instanceof TextViewItem){
            final TextViewItem txt = (TextViewItem) item;
            textView.setText(txt.getText());
            textView.setBackgroundColor(txt.getBackgroundColor());
        }
    }
}
