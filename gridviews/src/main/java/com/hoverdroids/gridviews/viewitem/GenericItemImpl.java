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

package com.hoverdroids.gridviews.viewitem;

/** Implementation of a GenericItem. */
public class GenericItemImpl implements GenericItem
{
    /** The item id used by adapters in getItemById (optional). */
    private int id = INVALID_RESOURCE_ID;

    /** The layout resource ID to inflate to visualize this model. */
    private int layoutResourceId = INVALID_RESOURCE_ID;

    /** The view class name can be used to instantiate a new view instead of layoutResourceId (e.g. com.android.view.TextView) */
    private String viewClass;

    /** The item name. Can be anything. Not used by the adapter. */
    private String name;

    /**
     * Constructor - use when inflating view from XML.
     * @param layoutResourceId the layout resource id
     */
    public GenericItemImpl(final int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    /**
     * Constructor - use when instantiating new View from class.
     * @param viewClass The view class
     */
    public GenericItemImpl(final String viewClass) {
        this.viewClass = viewClass;
    }

    /**
     * Get the item id.
     * @return The item id.
     */
    @Override
    public int getItemId()
    {
        return id;
    }

    /**
     * Set the item id.
     * @param id The unique Id.
     */
    @Override
    public void setItemId(final int id)
    {
        this.id = id;
    }

    /**
     * The the layout resource id.
     * @return The layout resource id.
     */
    @Override
    public int getLayoutResourceId()
    {
        return layoutResourceId;
    }

    /**
     * Get the view class.
     * @return The view class
     */
    @Override
    public String getViewClass() {
        return viewClass;
    }

    /**
     * Set the view class.
     * @param viewClass The view class (e.g. com.android.view.TextView)
     */
    @Override
    public void setViewClass(String viewClass) {
        this.viewClass = viewClass;
    }

    /**
     * Set the layout resource id.
     * @param resourceId The layout resource id.
     */
    @Override
    public void setLayoutResourceId(final int resourceId)
    {
        layoutResourceId = resourceId;
    }

    /**
     * Set the item name.
     * @param name The name;
     */
    @Override
    public void setItemName(final String name)
    {
        this.name = name;
    }

    /**
     * Get the item name.
     * @return the item name.
     */
    @Override
    public String getItemName()
    {
        return name;
    }
}