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

import androidx.appcompat.widget.AppCompatTextView;

import com.hoverdroids.gridviews.util.ViewUtils;
import com.hoverdroids.gridviews.viewitem.TextViewItem;
import com.hoverdroids.gridviews.viewitem.ViewItem;

public class TextView extends AppCompatTextView implements ItemView{

    public TextView(final Context context) {
        super(context);
    }

    public TextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setViewItem(final ViewItem item) {
        //Set attrs specific to a View
        ViewUtils.setBackgroundColor(this, item);

        if (item instanceof TextViewItem) {
            //Set the attrs specific to ImageView
            final TextViewItem tvItem = (TextViewItem) item;
            ViewUtils.setText(this, tvItem);
        }
    }
}