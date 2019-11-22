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

/** Model for updating a View. */
public interface ViewModel {

    /** An invalid color. Use it when the color is not set. */
    int INVALID_COLOR = Integer.MIN_VALUE;

    /** An invalid resource ID. Use it when the value is not set. */
    int INVALID_RESOURCE_ID = 0;

    /**
     * Get the ID for the view that corresponds to this viewModel.
     * @return The ID
     */
    default int getViewId() {
        return NO_ID;
    }

    /**
     * Set the ID for the view that corresponds to this viewModel.
     * @param viewId The ID
     */
    void setViewId(int viewId);

    /**
     * Get the background color.
     * @return The color
     */
    default int getBackgroundColor(){
        return INVALID_COLOR;
    }

    /**
     * Set the background color.
     * @param color The color
     */
    void setBackgroundColor(int color);

    /**
     * Get the background resource ID.
     * @return The resource ID
     */
    default int getBackgroundResourceId() {
        return INVALID_RESOURCE_ID;
    }

    /**
     * Set the background resource ID.
     * @param resourceId The resource ID
     */
    void setBackgroundResourceId(int resourceId);
}
