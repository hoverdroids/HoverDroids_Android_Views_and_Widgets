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

package com.hoverdroids.modelviewgroup.viewmodel;

import com.hoverdroids.adapterview.view.TwoWayAbsListView.OnScrollListener;
import com.hoverdroids.adapterview.viewmodel.AdapterViewModelImp;
import com.hoverdroids.viewmodel.model.AdapterModel;
import com.hoverdroids.viewmodel.model.ViewGroupModelImp;

import java.util.List;

/**
 * This should look exactly like interactions with the TwgvModel from com.hoverdroids.viewgroup.modelview.AdapterViewGroupModelImp.
 * The only reason this model is required is because the AdapterView needs to be held inside
 * a ViewGroup when being displayed in another AdapterView or else onMeasure does not work correctly.
 */
public class AdapterViewGroupModelImp extends ViewGroupModelImp
{
    protected int adapterViewId;

    /**
     * Constructor - with minimum arguments.
     * @param layoutResourceId The layout resource ID
     * @param viewId The view ID corresponding to this viewModel
     */
    public AdapterViewGroupModelImp(final int layoutResourceId, final int viewId) {
        super(layoutResourceId, viewId);
    }

    /**
     * Constructor for quickly creating a viewModel when only one TextView is used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param viewId The view ID corresponding to this viewModel
     * @param adapterViewId The adapterView ID
     * @param text The text
     */
    public AdapterViewGroupModelImp(final int layoutResourceId, final int viewId, final int adapterViewId, final List<AdapterModel> models){
        super(layoutResourceId, viewId);

        //Create the ViewModel - no need to set layoutResourceId since it's provided by viewGroupModel
        final AdapterViewModelImp adapterViewModel = new AdapterViewModelImp(INVALID_RESOURCE_ID, adapterViewId, models);

        //Add the AdapterViewModel to the ViewGroupModel - the ViewGroup will pass it to the AdapterView
        setChildViewModel(adapterViewModel);

        this.adapterViewId = adapterViewId;
    }

    public void setFirstPosition(final int position) {
        ((AdapterViewModelImp) getChildViewModel(adapterViewId)).setFirstPosition(position);
    }

    public int getFirstPosition() {
        return ((AdapterViewModelImp) getChildViewModel(adapterViewId)).getFirstPosition();
    }

    public void setFirstPositionOffset(final int offset) {
        ((AdapterViewModelImp) getChildViewModel(adapterViewId)).setFirstPositionOffset(offset);
    }

    public int getFirstPositionOffset() {
        return ((AdapterViewModelImp) getChildViewModel(adapterViewId)).getFirstPositionOffset();
    }

    public void setItems(final List<AdapterModel> items) {
        ((AdapterViewModelImp) getChildViewModel(adapterViewId)).setItems(items);
    }

    public List<AdapterModel> getItems() {
        return ((AdapterViewModelImp) getChildViewModel(adapterViewId)).getItems();
    }

    public void addItem(final AdapterModel model) {
        ((AdapterViewModelImp) getChildViewModel(adapterViewId)).addItem(model);
    }

    public void addItem(final int position, final AdapterModel model) {
        ((AdapterViewModelImp) getChildViewModel(adapterViewId)).addItem(position, model);
    }

    public AdapterModel getItem(final int position) {
        return ((AdapterViewModelImp) getChildViewModel(adapterViewId)).getItem(position);
    }

    public AdapterModel getItem(final String name) {
        return ((AdapterViewModelImp) getChildViewModel(adapterViewId)).getItem(name);
    }

    public void setTranscriptMode(final int mode) {
        ((AdapterViewModelImp) getChildViewModel(adapterViewId)).setTranscriptMode(mode);
    }

    public int getTranscriptMode() {
        return ((AdapterViewModelImp) getChildViewModel(adapterViewId)).getTranscriptMode();
    }

    public void setOnScrollListener(final OnScrollListener listener) {
        ((AdapterViewModelImp) getChildViewModel(adapterViewId)).setOnScrollListener(listener);
    }

    public OnScrollListener getOnScrollListener() {
        return ((AdapterViewModelImp) getChildViewModel(adapterViewId)).getOnScrollListener();
    }
}

