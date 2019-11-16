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

package com.hoverdroids.gridviews.viewitem;

import java.util.HashMap;
import java.util.Map;

public class ViewGroupItemImpl extends ViewItemImpl implements ViewGroupItem {

    private Map<Integer, ViewItem> viewItems = new HashMap<>();

    /**
     * Constructor.
     * @param layoutResourceId The layout resource ID
     * @param id The view ID corresponding to this item
     */
    public ViewGroupItemImpl(int layoutResourceId, int id) {
        super(layoutResourceId, id);
    }

    /**
     * Get the viewItem for the view with the given ID.
     * @param id The view's ID
     * @return
     */
    @Override
    public ViewItem getChildViewItem(int id) {
        return viewItems.get(id);
    }

    /**
     * Get all of the viewItems.
     * @return The child viewItems
     */
    @Override
    public Map<Integer, ViewItem> getChildViewItems() {
        return viewItems;
    }

    /**
     * Set all of teh viewItems
     * @param items The child view items.
     */
    @Override
    public void setChildViewItems(Map<Integer, ViewItem> items) {
        viewItems = items;
    }
}
