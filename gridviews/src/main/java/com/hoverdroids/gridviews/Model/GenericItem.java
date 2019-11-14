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

package com.hoverdroids.gridviews.Model;

public interface GenericItem
{
    /**
     *  The layout Id of the layout to inflate.
     * @return The layout resource id.
     */
    int getLayoutResourceId();

    /**
     * The layout resourceId that is used to view the model.
     * @param resourceId The layout resource id.
     */
    void setLayoutResourceId(int resourceId);

    /**
     * Unique IDs based on project-specific requirements.
     * @return The item id.
     */
    int getItemId();

    /**
     * Set a unique id that is based on project-specific requirements.
     * @param id The unique Id.
     */
    void setItemId(int id);

    /**
     * Set the item name.
     * @param name The name;
     */
    void setItemName(final String name);

    /**
     * Get the item name.
     * @return The item name.
     */
    String getItemName();
}
