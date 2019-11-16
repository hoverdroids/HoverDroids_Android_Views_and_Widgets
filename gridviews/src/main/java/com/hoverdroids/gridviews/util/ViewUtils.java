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

package com.hoverdroids.gridviews.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoverdroids.gridviews.itemview.ItemView;
import com.hoverdroids.gridviews.viewitem.ImageViewItem;
import com.hoverdroids.gridviews.viewitem.TextViewItem;
import com.hoverdroids.gridviews.viewitem.ViewGroupItem;
import com.hoverdroids.gridviews.viewitem.ViewItem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.view.View.NO_ID;

public class ViewUtils {

    /**
     * Get a mapping of child IDs to child views. If a child does not have an ID, it is not added to the mapping.
     * @param parent The parent to traverse
     * @return The mapping
     */
    public static Map<Integer, View> getViewIdMapping(final ViewGroup parent) {
        final Map<Integer, View> viewIds = new HashMap<>();

        for (int i = 0; i < parent.getChildCount(); i++) {
            final View view = parent.getChildAt(i);
            final int id = view.getId();
            if (id != NO_ID) {
                viewIds.put(view.getId(), view);
            }
        }

        return viewIds;
    }

    /**
     * Set the viewItems for the children.
     * @param viewIds The mapping of children IDs to the children views
     * @param item The item containing the mapping of children IDs to the children ViewItems
     */
    public static void setChildrenViewItems(final Map<Integer, View> viewIds, final ViewItem item) {
        //Nothing to do since no child attr info is available
        if (!(item instanceof ViewGroupItem)) {
            return;
        }

        final ViewGroupItem vgItem = (ViewGroupItem) item;
        final Iterator<Integer> it = viewIds.keySet().iterator();
        while (it.hasNext()) {
            final Integer id = it.next();
            final View view = viewIds.get(id);

            final ViewItem childViewItem = vgItem.getChildViewItem(id);

            //There is no requirement for each child with an ID to have a corresponding viewItem. Indeed, it's more unlikely than likely.
            if (view instanceof ItemView) {
                ((ItemView)view).setViewItem(childViewItem);
            }
        }
    }

    /**
     * Set the view's background color if it was specified in the viewItem. Otherwise, don't override.
     * @param view The view
     * @param item The viewItem
     */
    public static void setBackgroundColor(final View view, final ViewItem item) {
        final int color = item.getBackgroundColor();
        if (color != Integer.MIN_VALUE) {
            view.setBackgroundColor(color);
        }
    }

    /**
     * Set the TextView's text if it was specified in the tvItem. Otherwise, don't override.
     * @param view The TextView
     * @param item The TextViewItem
     */
    public static void setText(final TextView view, final TextViewItem item) {
        final String text = item.getText();
        if (text != null) {
            view.setText(text);
        }
    }

    /**
     * Set the TextView's text if it was specified in the tvItem. Otherwise, don't override.
     * @param view The TextView
     * @param item The TextViewItem
     */
    public static void setTextColor(final TextView view, final TextViewItem item) {
        final int color = item.getTextColor();
        if (color != Integer.MIN_VALUE) {
            view.setTextColor(color);
        }
    }

    /**
     * Set teh ImageView's image via a resource ID if it was specified in teh ivItem. Otherwise, don't override.
     * @param view The ImageView
     * @param item The ImageViewItem
     */
    public static void setImageResourceId(final ImageView view, final ImageViewItem item) {
        final int resId = item.getImageResourceId();
        if (resId != Integer.MIN_VALUE) {
            view.setImageResource(resId);
        }
    }
}
