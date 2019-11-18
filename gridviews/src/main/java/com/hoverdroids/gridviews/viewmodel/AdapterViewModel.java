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

import com.hoverdroids.gridviews.view.TwoWayAbsListView.OnScrollListener;

import java.util.List;

/** Model for updating an AdapterView - e.g. TWGV, ListView, GridView, etc. */
public interface AdapterViewModel extends AdapterModel
{
    /**
     * Gets items.
     * @return the items
     */
    List<AdapterModel> getItems();

    /**
     * Sets items.
     * @param items the items
     */
    void setItems(final List<AdapterModel> items);

    /**
     * Gets on scroll listener.
     * @return the on scroll listener
     */
    OnScrollListener getOnScrollListener();

    /**
     * Sets on scroll listener.
     * @param onScrollListener the on scroll listener
     */
    void setOnScrollListener(final OnScrollListener onScrollListener);

    /**
     * Is fling enabled boolean.
     * @return the boolean
     */
    boolean isFlingEnabled();

    /**
     * Sets fling enabled.
     * @param enabled the enabled
     */
    void setFlingEnabled(final boolean enabled);

    /**
     * Is enabled boolean.
     * @return the boolean
     */
    boolean isEnabled();

    /**
     * Sets enabled.
     * @param isEnabled the is enabled
     */
    void setEnabled(final boolean isEnabled);

    /**
     * Gets current scroll distance x.
     * @return the current scroll distance x
     */
    int getCurrentScrollDistanceX();//TODO necessary?

    /**
     * Sets current scroll distance x.
     * @param distance the distance
     */
    void setCurrentScrollDistanceX(final int distance);//TODO necessary?

    /**
     * Gets current scroll distance y.
     * @return the current scroll distance y
     */
    int getCurrentScrollDistanceY();//TODO necessary?

    /**
     * Sets current scroll distance y.
     * @param distance the distance
     */
    void setCurrentScrollDistanceY(final int distance);//TODO necessary?

    //TODO disableScroll(boolean)


    /**
     * Get the first visible position.
     * @return The position
     */
    int getFirstPosition();

    /**
     * Set the first visible position.
     * @param position The position
     */
    void setFirstPosition(final int position);

    /**
     * Get the first position offset.
     * @return The offset
     */
    int getFirstPositionOffset();

    /**
     * Set the first position offset.
     * @param offset The offset
     */
    void setFirstPositionOffset(final int offset);
}
