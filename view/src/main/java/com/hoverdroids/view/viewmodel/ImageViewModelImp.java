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

package com.hoverdroids.view.viewmodel;

import com.hoverdroids.viewmodel.model.ViewModelImp;

/** Implementation of ImageViewModel. */
public class ImageViewModelImp extends ViewModelImp implements ImageViewModel {

    private int imageResourceId = INVALID_RESOURCE_ID;

    /**
     * Constructor. Use this when the viewModel's view is the top-level parent.
     * @param layoutResourceId The layout resource ID
     * @param id The view ID corresponding to this viewModel
     * @param imageResourceId The image resource ID
     */
    public ImageViewModelImp(final int layoutResourceId, final int id, final int imageResourceId) {
        super(layoutResourceId, id);
        this.imageResourceId = imageResourceId;
    }

    /**
     * Constructor. Use this when the viewModel's view is a child since only the top-level parent's layoutResId is used.
     * @param id The view ID corresponding to this viewModel
     * @param imageResourceId The image resource ID
     */
    public ImageViewModelImp(final int id, final int imageResourceId) {
        super(id);
        this.imageResourceId = imageResourceId;
    }

    /**
     * Get the image resource ID.
     * @return The ID
     */
    @Override
    public int getImageResourceId() {
        return imageResourceId;
    }

    /**
     * Set the image resource ID.
     * @param resourceId The ID
     */
    @Override
    public void setImageResourceId(final int resourceId) {
        this.imageResourceId = resourceId;
    }
}
