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

/** Implementation of the ViewModel. */
public class ViewModelImp extends AdapterModelImp implements ViewModel {

    private int id;
    private int color = Integer.MIN_VALUE;

    /**
     * Constructor. Use this when the viewModel's view is the top-level parent.
     * @param layoutResourceId The layout resource ID
     * @param id The view ID corresponding to this viewModel
     */
    public ViewModelImp(final int layoutResourceId, final int id) {
        super(layoutResourceId);
        this.id = id;
    }

    /**
     * Constructor. Use this when the viewModel's view is the top-level parent.
     * @param viewClass The view class
     * @param id The view ID corresponding to this viewModel
     */
    public ViewModelImp(final String viewClass, final int id) {
        super(viewClass);
        this.id = id;
    }

    /**
     * Constructor. Use this when the viewModel's view is a child since only the top-level parent's layoutResId is used.
     * @param id The view ID corresponding to this viewModel
     */
    public ViewModelImp(final int id) {
        super(-1);
        this.id = id;
    }

    /**
     * Get the view ID corresponding to this viewModel.
     * @return The ID
     */
    @Override
    public int getViewId() {
        return id;
    }

    /**
     * Set the view ID corresponding to this viewModel.
     * @param id The ID
     */
    @Override
    public void setViewId(final int id) {
        this.id = id;
    }

    /**
     * Get the background color.
     * @return The color
     */
    @Override
    public int getBackgroundColor() {
        return color;
    }

    /**
     * Set the background color.
     * @param color The color
     */
    @Override
    public void setBackgroundColor(final int color) {
        this.color = color;
    }
}
