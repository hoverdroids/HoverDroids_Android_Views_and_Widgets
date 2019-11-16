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

import com.hoverdroids.gridviews.viewgroup.TwoWayAbsListView.OnScrollListener;

import java.util.List;

/** Generic adapter item containing essential data for displaying an adapter view in an adapter view. */
public interface GenericAdapterItem extends GenericItem
{
    /**
     * Gets items.
     *
     * @return the items
     */
    List<GenericItem> getItems();

    /**
     * Sets items.
     *
     * @param items the items
     */
    void setItems(List<GenericItem> items);

    /**
     * Gets on scroll listener.
     *
     * @return the on scroll listener
     */
    OnScrollListener getOnScrollListener();

    /**
     * Sets on scroll listener.
     *
     * @param onScrollListener the on scroll listener
     */
    void setOnScrollListener(OnScrollListener onScrollListener);

    /**
     * Is fling enabled boolean.
     *
     * @return the boolean
     */
    boolean isFlingEnabled();

    /**
     * Sets fling enabled.
     *
     * @param enabled the enabled
     */
    void setFlingEnabled(boolean enabled);

    /**
     * Is enabled boolean.
     *
     * @return the boolean
     */
    boolean isEnabled();

    /**
     * Sets enabled.
     *
     * @param isEnabled the is enabled
     */
    void setEnabled(boolean isEnabled);

    /**
     * Gets current scroll distance x.
     *
     * @return the current scroll distance x
     */
    int getCurrentScrollDistanceX();

    /**
     * Sets current scroll distance x.
     *
     * @param distance the distance
     */
    void setCurrentScrollDistanceX(int distance);

    /**
     * Gets current scroll distance y.
     *
     * @return the current scroll distance y
     */
    int getCurrentScrollDistanceY();

    /**
     * Sets current scroll distance y.
     *
     * @param distance the distance
     */
    void setCurrentScrollDistanceY(int distance);

    //TODO disableScroll(boolean)
}
