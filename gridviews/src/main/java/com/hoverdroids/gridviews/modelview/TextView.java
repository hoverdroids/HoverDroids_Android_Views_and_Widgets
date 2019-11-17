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

package com.hoverdroids.gridviews.modelview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.hoverdroids.gridviews.util.ViewUtils;
import com.hoverdroids.gridviews.viewmodel.TextViewModel;
import com.hoverdroids.gridviews.viewmodel.ViewModel;

public class TextView extends AppCompatTextView implements AdapterModelView, ModelView {

    public TextView(final Context context) {
        super(context);
    }

    public TextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Set the view item from an AdapterView - e.g. when used in a ListView.
     * @param position The items's position in the adapter.
     * @param isFirst Is it the first position in the adapter.
     * @param isLast Is it the last position in the adapter.
     * @param item The item for the given position in the adapter.
     */
    @Override
    public void updateViews(final int position, final boolean isFirst, final boolean isLast, final ViewModel item) {
        //TODO handle case where isFirst, isLast. Typically, this allows the item to display as a header or footer.
        setViewItem(item);
    }

    @Override
    public void setViewItem(final ViewModel item) {
        //Set attrs specific to a View
        ViewUtils.setBackgroundColor(this, item);

        if (item instanceof TextViewModel) {
            //Set the attrs specific to ImageView
            final TextViewModel tvItem = (TextViewModel) item;
            ViewUtils.setText(this, tvItem);
        }
    }
}
