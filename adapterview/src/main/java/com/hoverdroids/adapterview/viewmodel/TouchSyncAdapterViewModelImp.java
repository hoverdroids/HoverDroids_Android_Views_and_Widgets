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

import com.hoverdroids.touchsync.OnSourceTouchEventListener;
import com.hoverdroids.touchsync.TouchSyncModel;
import com.hoverdroids.viewmodel.model.AdapterModel;

import java.util.List;

public class TouchSyncAdapterViewModelImp extends AdapterViewModelImp implements TouchSyncModel {

    private OnSourceTouchEventListener onSourceTouchEventListener;

    public TouchSyncAdapterViewModelImp(int layoutResourceId) {
        super(layoutResourceId);
    }

    public TouchSyncAdapterViewModelImp(int layoutResourceId, int viewId) {
        super(layoutResourceId, viewId);
    }

    public TouchSyncAdapterViewModelImp(int layoutResourceId, int viewId, List<AdapterModel> items) {
        super(layoutResourceId, viewId, items);
    }

    public TouchSyncAdapterViewModelImp(String viewClass, int viewId, List<AdapterModel> items) {
        super(viewClass, viewId, items);
    }

    public TouchSyncAdapterViewModelImp(int viewId, List<AdapterModel> items) {
        super(viewId, items);
    }

    @Override
    public void setOnSourceTouchEventListener(OnSourceTouchEventListener listener) {
        onSourceTouchEventListener = listener;
    }

    @Override
    public OnSourceTouchEventListener getOnSourceTouchEventListener() {
        return onSourceTouchEventListener;
    }
}
