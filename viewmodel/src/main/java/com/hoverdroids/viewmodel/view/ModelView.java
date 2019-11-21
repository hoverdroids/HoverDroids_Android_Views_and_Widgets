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

package com.hoverdroids.viewmodel.view;

import android.view.View;

import com.hoverdroids.viewmodel.model.ViewModel;

import static com.hoverdroids.viewmodel.model.ViewModel.INVALID_COLOR;
import static com.hoverdroids.viewmodel.model.ViewModel.INVALID_RESOURCE_ID;

/** Only implement this in classes that extend android.view.View. */
public interface ModelView {

    /** Get the ModelView's ViewModel. */
    ViewModel getViewModel();

    /** Set the ModelView's ViewModel. */
    <E extends ViewModel> void setViewModel(final E model);

    /**
     * Set the View's background color if a valid color is set in the viewModel.
     * @param model The model
     */
    default void setBackgroundColor(final ViewModel model){
        final int color = model == null ? INVALID_COLOR : model.getBackgroundColor();
        if (color != INVALID_COLOR) {
            ((View)this).setBackgroundColor(color);
        }
    }

    /**
     * Set the background resource by ID if a valid resourceID is set in the viewModel.
     * @param model The model
     */
    default void setBackgroundResource(final ViewModel model) {
        final int resourceId = model == null ? INVALID_RESOURCE_ID : model.getBackgroundResourceId();
        if (resourceId != INVALID_RESOURCE_ID) {
            ((View)this).setBackgroundResource(resourceId);
        }
    }

    /** Each class needs to indicate how the ViewState gets saved to the ViewModel. */
    void saveViewStateToViewModel();
}
