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

import com.hoverdroids.view.viewmodel.ImageViewModelImp;
import com.hoverdroids.view.viewmodel.TextViewModelImp;
import com.hoverdroids.viewmodel.model.ViewGroupModelImp;

/**
 * Model for updating a ViewGroup containing one TextView and one ImageView.
 * This is useful given the number of instances used in most apps.
 * e.g. spinners, dropdowns, profiles, etc.
 */
public class ImageTextViewGroupModelImp extends ViewGroupModelImp
{
    /**
     * Constructor - with minimum arguments.
     * @param layoutResourceId The layout resource ID
     * @param viewId The view ID corresponding to this viewModel
     */
    public ImageTextViewGroupModelImp(final int layoutResourceId, final int viewId) {
        super(layoutResourceId, viewId);
    }

    /**
     * Constructor for quickly creating a viewModel when only one ImageView and TextView are used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param viewId The view ID corresponding to this viewModel
     * @param textViewId The textViewId
     * @param text The text
     * @param imageViewId The imageViewId
     * @param imageResourceId The imageResourceId
     */
    public ImageTextViewGroupModelImp(final int layoutResourceId, final int viewId, final int textViewId, final String text,
                                      final int imageViewId, final int imageResourceId){
        super(layoutResourceId, viewId);
        setChildViewModel(new TextViewModelImp(textViewId, text));
        setChildViewModel(new ImageViewModelImp(imageViewId, imageResourceId));
    }

    /**
     * Constructor for quickly creating a viewModel when only one TextView is used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param viewId The view ID corresponding to this viewModel
     * @param textViewId The textViewId
     * @param text The text
     */
    public ImageTextViewGroupModelImp(final int layoutResourceId, final int viewId, final int textViewId, final String text){
        super(layoutResourceId, viewId);
        setChildViewModel(new TextViewModelImp(textViewId, text));
    }

    /**
     * Constructor for quickly creating an viewModel when only one ImageView is used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param viewId The view ID corresponding to this viewModel
     * @param imageViewId The imageViewId
     * @param imageResourceId The imageResourceId
     */
    public ImageTextViewGroupModelImp(final int layoutResourceId, final int viewId, final int imageViewId, final int imageResourceId){
        super(layoutResourceId, viewId);
        setChildViewModel(new ImageViewModelImp(imageViewId, imageResourceId));
    }
}
