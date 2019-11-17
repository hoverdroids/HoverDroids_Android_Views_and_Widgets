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

/** Model for updating a View. */
public interface ViewModel {

    /**
     * Get the ID for the view that corresponds to this item.
     * @return The ID
     */
    int getViewId();

    /**
     * Set the ID for the view that corresponds to this item.
     * @param id The ID
     */
    void setViewId(int id);

    /**
     * Get the background color.
     * @return The color
     */
    default int getBackgroundColor(){
        return Integer.MIN_VALUE;
    }

    /**
     * Set the background color.
     * @param color The color
     */
    void setBackgroundColor(int color);
}
