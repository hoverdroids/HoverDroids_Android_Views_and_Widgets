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

/**
 * A generic item that holds attrs for one TextView and one ImageView. This is useful given the number of situations where it is used
 * in almost every app - e.g. spinners, dropdowns, profiles, etc.
 */
public class ImageTextItemImp extends ViewGroupItemImp
{
    /**
     * Constructor - with minimum arguments.
     * @param layoutResourceId The layout resource ID
     * @param id The view ID corresponding to this item
     */
    public ImageTextItemImp(final int layoutResourceId, final int id) {
        super(layoutResourceId, id);
    }

    /**
     * Constructor for quickly creating an item when only one ImageView and TextView are used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param id The view ID corresponding to this item
     * @param textViewId The textViewId
     * @param text The text
     * @param imageViewId The imageViewId
     * @param imageResourceId The imageResourceId
     */
    public ImageTextItemImp(final int layoutResourceId, final int id, final int textViewId, final String text,
                            final int imageViewId, final int imageResourceId){
        super(layoutResourceId, id);
        setChildViewItem(new TextViewItemImp(textViewId, text));
        setChildViewItem(new ImageViewItemImp(imageViewId, imageResourceId));
    }

    /**
     * Constructor for quickly creating an item when only one TextView is used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param id The view ID corresponding to this item
     * @param textViewId The textViewId
     * @param text The text
     */
    public ImageTextItemImp(final int layoutResourceId, final int id, final int textViewId, final String text){
        super(layoutResourceId, id);
        setChildViewItem(new TextViewItemImp(textViewId, text));
    }

    /**
     * Constructor for quickly creating an item when only one ImageView is used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param id The view ID corresponding to this item
     * @param imageViewId The imageViewId
     * @param imageResourceId The imageResourceId
     */
    public ImageTextItemImp(final int layoutResourceId, final int id, final int imageViewId, final int imageResourceId){
        super(layoutResourceId, id);
        setChildViewItem(new ImageViewItemImp(imageViewId, imageResourceId));
    }
}
