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

/** Implementation of AdapterViewModel.*/
public class AdapterViewModelImp extends ViewModelImp implements AdapterViewModel
{
    /** The list of items to display. */
    private List<AdapterModel> items;

    /** Handler for scroll events.*/
    private OnScrollListener onScrollListener;

    /** Indicates if the associated view should allow the use to fling contents.*/
    private boolean flingEnabled;

    /** Indicates if the associated view should be enabled/disabled. */
    private boolean enabled;

    /** The current scroll position in X. Essential for realigning the associated view in an adapter view. */
    private int currentScrollDistanceX;

    /** The current scroll position in Y. Essential for realigning the associated view in an adapter view. */
    private int currentScrollDistanceY;

    /** The first visible position. */
    private int firstPosition;

    /** The offset of the first visible position. */
    private int firstPositionOffset;

    /**
     * Constructor for the model.
     * @param items Items used to populate a TwoWayAdapterView.
     * @param layoutResourceId The layout.xml used for inflating the TwoWayAdapterView.
     * @param onScrollListener A scroll listener for the TwoWayAdapterView.
     */
    public AdapterViewModelImp(final List<AdapterModel> items, final int layoutResourceId,
                               final OnScrollListener onScrollListener)
    {
        this(items, layoutResourceId);
        this.onScrollListener = onScrollListener;
    }

    /**
     * Constructor for the model.
     * @param items Items used to populate a TwoWayAdapterView.
     * @param layoutResourceId The layout.xml used for inflating the TwoWayAdapterView.
     */
    public AdapterViewModelImp(final List<AdapterModel> items, final int layoutResourceId)
    {
        super(layoutResourceId);
        this.items = items;
    }

    /**
     * Get the items used by the TwoWayAdapterView and its adapter.
     * @return The items.
     */
    @Override
    public List<AdapterModel> getItems()
    {
        return items;
    }

    /**
     * Set the items used by the TwoWayAdapterView and its adapter.
     * @param items A list of items.
     */
    @Override
    public void setItems(final List<AdapterModel> items)
    {
        this.items = items;
    }

    /**
     * Get the scroll listener used by the TwoWayAdapterView
     * @return The scroll listener.
     */
    @Override
    public OnScrollListener getOnScrollListener()
    {
        return onScrollListener;
    }

    /**
     * Set the scroll listener used by the TwoWayAdapterView.
     * @param onScrollListener The scroll listener.
     */
    @Override
    public void setOnScrollListener(final OnScrollListener onScrollListener)
    {
        this.onScrollListener = onScrollListener;
    }

    /**
     * Determine if fling is enabled/disabled.
     * @return True if enabled. False otherwise.
     */
    @Override
    public boolean isFlingEnabled()
    {
        return flingEnabled;
    }

    /**
     * Enable/Disable fling.
     * @param enabled True if enabled. False otherwise.
     */
    @Override
    public void setFlingEnabled(final boolean enabled)
    {
        flingEnabled = enabled;
    }

    /**
     * Determine if TwoWayAdapterView is enabled/disabled.
     * @return True if it should be enbled. False otherwise.
     */
    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    /**
     * Enable/Disable the TwoWayAdapterView.
     * @param isEnabled True if it should be enbled. False otherwise.
     */
    @Override
    public void setEnabled(final boolean isEnabled)
    {
        this.enabled = isEnabled;
    }

    /**
     * Get the current scroll distance. This is meant for resetting the TwoWayGridView to a desired
     * position when it is displayed after going off screen. It is not guarenteed to be the the
     * current view's actual location. In fact, it most likely won't be.
     * @return The current scroll distance in X.
     */
    @Override
    public int getCurrentScrollDistanceX()
    {
        return currentScrollDistanceX;
    }

    /**
     * Set the current scroll distance. This is meant for resetting the TwoWayGridView to a desired
     * position when it is displayed after going off screen. It is not guarenteed to be the the
     * current view's actual location. In fact, it most likely won't be.
     * @param distance The current scroll distance in X.
     */
    @Override
    public void setCurrentScrollDistanceX(final int distance)
    {
        currentScrollDistanceX = distance;
    }

    /**
     * Get the current scroll distance. This is meant for resetting the TwoWayGridView to a desired
     * position when it is displayed after going off screen. It is not guarenteed to be the the
     * current view's actual location. In fact, it most likely won't be.
     * @return The current scroll distance in Y.
     */
    @Override
    public int getCurrentScrollDistanceY()
    {
        return currentScrollDistanceY;
    }

    /**
     * Set the current scroll distance. This is meant for resetting the TwoWayGridView to a desired
     * position when it is displayed after going off screen. It is not guarenteed to be the the
     * current view's actual location. In fact, it most likely won't be.
     * @param distance The current scroll distance in Y.
     */
    @Override
    public void setCurrentScrollDistanceY(final int distance)
    {
        currentScrollDistanceY = distance;
    }

    /**
     * Get the first visible position.
     * @return The position
     */
    @Override
    public int getFirstPosition() {
        return firstPosition;
    }

    /**
     * Set the first visible position.
     * @param position The position
     */
    @Override
    public void setFirstPosition(int position) {
        firstPosition = position;
    }

    /**
     * Get the first position offset.
     * @return The offset
     */
    @Override
    public int getFirstPositionOffset() {
        return firstPositionOffset;
    }

    /**
     * Set the first position offset.
     * @param offset The offset
     */
    @Override
    public void setFirstPositionOffset(int offset) {
        firstPositionOffset = offset;
    }
}
