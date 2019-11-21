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

package com.hoverdroids.viewmodel.model;

import static android.view.View.NO_ID;

/** Implementation of the ViewModel. */
public class ViewModelImp extends AdapterModelImp implements ViewModel {

    /** The layout resource ID to inflate to visualize this model. */
    private int viewId = NO_ID;

    /** The background color. */
    private int backgroudColor = INVALID_COLOR;

    /** The background resource ID. */
    private int backgroundResourceId = INVALID_RESOURCE_ID;

    /**
     * Constructor. Use this when the viewModel's view is the top-level parent.
     * @param layoutResourceId The layout resource ID
     * @param viewId The viewID corresponding to this viewModel
     */
    public ViewModelImp(final int layoutResourceId, final int viewId) {
        super(layoutResourceId);
        this.viewId = viewId;
    }

    /**
     * Constructor. Use this when the viewModel's view is the top-level parent.
     * @param viewClass The view class
     * @param viewId The view ID corresponding to this viewModel
     */
    public ViewModelImp(final String viewClass, final int viewId) {
        super(viewClass);
        this.viewId = viewId;
    }

    /**
     * Constructor. Use this when the viewModel's view is a child since only the top-level parent's layoutResId is used.
     * @param id The view ID corresponding to this viewModel
     */
    public ViewModelImp(final int viewId) {
        super(-1);
        this.viewId = viewId;
    }

    /**
     * Get the view ID corresponding to this viewModel.
     * @return The ID
     */
    @Override
    public int getViewId() {
        return viewId;
    }

    /**
     * Set the view ID corresponding to this viewModel.
     * @param id The ID
     */
    @Override
    public void setViewId(final int id) {
        this.viewId = id;
    }

    /**
     * Get the background color.
     * @return The color
     */
    @Override
    public int getBackgroundColor() {
        return backgroudColor;
    }

    /**
     * Set the background color.
     * @param color The color
     */
    @Override
    public void setBackgroundColor(final int color) {
        this.backgroudColor = color;
    }

    @Override
    public int getBackgroundResourceId() {
        return backgroundResourceId;
    }

    @Override
    public void setBackgroundResourceId(int resourceId) {
        this.backgroundResourceId = resourceId;
    }
}