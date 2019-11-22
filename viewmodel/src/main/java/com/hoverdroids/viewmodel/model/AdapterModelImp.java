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

import static com.hoverdroids.viewmodel.model.ViewModel.INVALID_RESOURCE_ID;

/** Implementation of a AdapterModel. */
public class AdapterModelImp implements AdapterModel
{
    /** The model id used by adapters in getModelById (optional). */
    private int itemId = INVALID_RESOURCE_ID;

    /** The layout resource ID to inflate to visualize this model. */
    private int layoutResourceId = INVALID_RESOURCE_ID;

    /** The view class name can be used to instantiate a new view instead of layoutResourceId (e.g. com.android.view.TextView) */
    private String viewClass;

    /** The model name. Can be anything. Not used by the adapter. */
    private String name;

    /** True if the model is the first model in the Adapter. */
    private boolean isFirst;

    /** True if the model is the last model in the Adapter. */
    private boolean isLast;

    /** The position of the model in the Adapter. */
    private int position;

    /**
     * Constructor - use when inflating view from XML.
     * @param layoutResourceId the layout resource id
     */
    public AdapterModelImp(final int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    /**
     * Constructor - use when instantiating new View from class.
     * @param viewClass The view class
     */
    public AdapterModelImp(final String viewClass) {
        this.viewClass = viewClass;
    }

    /**
     * Get the item id.
     * @return The item id.
     */
    @Override
    public int getItemId()
    {
        return itemId;
    }

    /**
     * Set the item id.
     * @param id The unique Id.
     */
    @Override
    public void setItemId(final int itemId)
    {
        this.itemId = itemId;
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

    /**
     * Determine if model the is first position in the Adapter.
     * @return True if first. False otherwise
     */
    @Override
    public boolean isFirst() {
        return isFirst;
    }

    /**
     * Set if model is the first position in the Adapter.
     * @param isFirst True if first. False otherwise
     */
    @Override
    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    /**
     * Determine if model is the last position in the Adapter.
     * @return True if last. False otherwise.
     */
    @Override
    public boolean isLast() {
        return isLast;
    }

    /**
     * Set if model is the last position in the Adapter.
     * @param isLast True if last. False otherwise.
     */
    @Override
    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    /**
     * Get the position of the model in the Adapter.
     * @return The position
     */
    @Override
    public int getPosition() {
        return position;
    }

    /**
     * Set the position of the model in the Adapter.
     * @param position The position
     */
    @Override
    public void setPosition(int position) {
        this.position = position;
    }
}