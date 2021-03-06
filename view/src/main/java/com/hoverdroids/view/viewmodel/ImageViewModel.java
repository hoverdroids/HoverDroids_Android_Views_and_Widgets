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

import com.hoverdroids.viewmodel.model.ViewModel;

/** Model for updating an ImageView. */
public interface ImageViewModel extends ViewModel
{
    /**
     * Get the image resource ID.
     * @return The resource ID
     */
    default int getImageResourceId(){
        return INVALID_RESOURCE_ID;
    }

    /**
     * Set the image resource ID.
     * @param resourceId
     */
    void setImageResourceId(int resourceId);
}
