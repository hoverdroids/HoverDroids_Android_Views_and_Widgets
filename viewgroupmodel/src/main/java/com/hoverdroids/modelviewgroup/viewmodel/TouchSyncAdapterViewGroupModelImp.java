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

import com.hoverdroids.adapterview.viewmodel.TouchSyncAdapterViewModelImp;
import com.hoverdroids.touchsync.OnSourceTouchEventListener;
import com.hoverdroids.touchsync.TouchSyncModel;
import com.hoverdroids.viewmodel.model.AdapterModel;

import java.util.List;

public class TouchSyncAdapterViewGroupModelImp extends AdapterViewGroupModelImp implements TouchSyncModel
{
    /**
     * Constructor - with minimum arguments.
     * @param layoutResourceId The layout resource ID
     * @param viewId The view ID corresponding to this viewModel
     */
    public TouchSyncAdapterViewGroupModelImp(final int layoutResourceId, final int viewId) {
        super(layoutResourceId, viewId);
    }

    /**
     * Constructor for quickly creating a viewModel when only one TextView is used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param viewId The view ID corresponding to this viewModel
     * @param adapterViewId The adapterView ID
     * @param text The text
     */
    public TouchSyncAdapterViewGroupModelImp(final int layoutResourceId, final int viewId, final int adapterViewId, final List<AdapterModel> models){
        super(layoutResourceId, viewId);

        //Create the ViewModel - no need to set layoutResourceId since it's provided by viewGroupModel
        final TouchSyncAdapterViewModelImp adapterViewModel = new TouchSyncAdapterViewModelImp(INVALID_RESOURCE_ID, adapterViewId, models);

        //Add the AdapterViewModel to the ViewGroupModel - the ViewGroup will pass it to the AdapterView
        //This also replaces the AdapterViewModel created by Super
        setChildViewModel(adapterViewModel);
    }

    @Override
    public void setOnSourceTouchEventListener(OnSourceTouchEventListener listener) {
        ((TouchSyncAdapterViewModelImp) getChildViewModel(adapterViewId)).setOnSourceTouchEventListener(listener);
    }

    @Override
    public OnSourceTouchEventListener getOnSourceTouchEventListener() {
        return ((TouchSyncAdapterViewModelImp) getChildViewModel(adapterViewId)).getOnSourceTouchEventListener();
    }
}
