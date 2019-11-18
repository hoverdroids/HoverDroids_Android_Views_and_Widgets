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

/** The base model for instantiating or inflating views in an AdapterView with the ViewModelAdapter. */
public interface AdapterModel
{
    /** An invalid resource ID. Use it when the value is not set. */
    int INVALID_RESOURCE_ID = 0;

    /** An invalid item ID. Use it when the value is not set. */
    int UNKNOWN_ITEM_ID = Integer.MIN_VALUE;

    /**
     *  The layout Id of the layout to inflate.
     * @return The layout resource id.
     */
    default int getLayoutResourceId() {
        return INVALID_RESOURCE_ID;
    }

    /**
     * The layout resourceId that is used to view the model.
     * @param resourceId The layout resource id.
     */
    void setLayoutResourceId(int resourceId);

    /**
     * Get the view class. This can be used to create a new view instead of layoutResourceId.
     * @return The view class
     */
    String getViewClass();

    /**
     * Set the view class. This can be used to create a new view instead of layoutResourceId.
     * @param viewClass The view class (e.g. com.android.view.TextView)
     */
    void setViewClass(String viewClass);

    /**
     * Unique IDs based on project-specific requirements.
     * @return The item id.
     */
    default int getItemId() {
        return UNKNOWN_ITEM_ID;
    }

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

    /**
     * Determine if model the is first position in the Adapter.
     * @return True if first. False otherwise
     */
    boolean isFirst();

    /**
     * Set if model is the first position in the Adapter.
     * @param isFirst True if first. False otherwise
     */
    void setIsFirst(final boolean isFirst);

    /**
     * Determine if model is the last position in the Adapter.
     * @return True if last. False otherwise.
     */
    boolean isLast();

    /**
     * Set if model is the last position in the Adapter.
     * @param isLast True if last. False otherwise.
     */
    void setIsLast(final boolean isLast);

    /**
     * Get the position of the model in the Adapter.
     * @return The position
     */
    int getPosition();

    /**
     * Set the position of the model in the Adapter.
     * @param position The position
     */
    void setPosition(final int position);
}
