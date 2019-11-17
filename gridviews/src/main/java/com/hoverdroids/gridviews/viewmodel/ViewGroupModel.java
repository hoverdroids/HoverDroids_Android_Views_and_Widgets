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

package com.hoverdroids.gridviews.viewmodel;

import java.util.Map;

/**
 * Model for updating a ViewGroup.
 * View and ViewGroup are analogous to ViewModel and ViewGroupModel.
 * This is more straight forward since the group simply maps child IDs to the child view's ViewItem.
 */
public interface ViewGroupModel extends ViewModel {

    /**
     * Get the child viewItem by its view ID.
     * @param id The view's ID
     * @return The view's viewItem
     */
    default ViewModel getChildViewItem(int id) {
        return getChildViewItems() == null ? null : getChildViewItems().get(id);
    }

    /**
     * Set the child viewItem by its view ID. The viewId is not required as it's already included in the viewItem.
     * @param item The view's viewItem
     */
    default void setChildViewItem(ViewModel item) {
        getChildViewItems().put(item.getViewId(), item);
    }

    /**
     * Get all of the child view items.
     * @return The child view items.
     */
    Map<Integer, ViewModel> getChildViewItems();

    /**
     * Set all of teh child view items.
     * @param items The child view items.
     */
    void setChildViewItems(Map<Integer, ViewModel> items);
}
