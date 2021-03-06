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

package com.hoverdroids.adapterview.viewmodel;

import com.hoverdroids.adapterview.view.TwoWayAbsListView.OnScrollListener;
import com.hoverdroids.adapterview.view.TwoWayAdapterView.OnItemClickListener;
import com.hoverdroids.viewmodel.model.AdapterModel;
import com.hoverdroids.viewmodel.model.ViewModelImp;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.NO_ID;
import static com.hoverdroids.adapterview.view.TwoWayAbsListView.TRANSCRIPT_MODE_RELATIVE;

/** Implementation of AdapterViewModel.*/
public class AdapterViewModelImp extends ViewModelImp implements AdapterViewModel
{
    /** The list of items to display. */
    private List<AdapterModel> items;

    /** Handler for scroll events.*/
    private OnScrollListener onScrollListener;

    /** Handler for clicks on child items. */
    private OnItemClickListener onItemClickListener;

    /** Transcript mode. Needs to be using TRANSCRIPT_MODE_RELATIVE (default) for AdapterViews to resurrect
     * correctly when using an AdapterViewModel. */
    private int transcriptMode = TRANSCRIPT_MODE_RELATIVE;

    /** Indicates if the associated view should allow the use to fling contents.*/
    private boolean flingEnabled;

    /** The first visible position. */
    private int firstPosition;

    /** The offset of the first visible position. */
    private int firstPositionOffset;

    /**
     * Constructor. Use this when the viewModel's view is the top-level parent.
     * @param layoutResourceId The layout resource ID
     * @param viewId The viewID corresponding to this viewModel
     * @param items Items used in an ViewModelAdapter to populate a AdapterView.
     */
    public AdapterViewModelImp(final int layoutResourceId) {
        this(layoutResourceId, NO_ID);
    }

    /**
     * Constructor. Use this when the viewModel's view is the top-level parent.
     * @param layoutResourceId The layout resource ID
     * @param viewId The viewID corresponding to this viewModel
     */
    public AdapterViewModelImp(final int layoutResourceId, final int viewId) {
        this(layoutResourceId, viewId, new ArrayList<>());
    }

    /**
     * Constructor. Use this when the viewModel's view is the top-level parent.
     * @param layoutResourceId The layout resource ID
     * @param viewId The viewID corresponding to this viewModel
     * @param items Items used in an ViewModelAdapter to populate a AdapterView.
     */
    public AdapterViewModelImp(final int layoutResourceId, final int viewId, final List<AdapterModel> items) {
        super(layoutResourceId, viewId);
        this.items = items;
    }

    /**
     * Constructor. Use this when the viewModel's view is the top-level parent.
     * @param viewClass The view class
     * @param id The view ID corresponding to this viewModel
     * @param items Items used in an ViewModelAdapter to populate a AdapterView.
     */
    public AdapterViewModelImp(final String viewClass, final int viewId, final List<AdapterModel> items) {
        super(viewClass, viewId);
        this.items = items;
    }

    /**
     * Constructor. Use this when the viewModel's view is a child since only the top-level parent's layoutResId is used.
     * @param id The view ID corresponding to this viewModel
     * @param items Items used in an ViewModelAdapter to populate a AdapterView.
     */
    public AdapterViewModelImp(final int viewId, final List<AdapterModel> items) {
        super(viewId);
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

    @Override
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    @Override
    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void setTranscriptMode(int mode) {
        transcriptMode = mode;
    }

    @Override
    public int getTranscriptMode() {
        return transcriptMode;
    }
}
