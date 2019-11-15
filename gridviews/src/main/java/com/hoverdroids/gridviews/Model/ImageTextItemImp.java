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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** A generic item that holds values for updating any number of TextViews and ImageViews. */
public class ImageTextItemImp extends GenericItemImpl implements ImageViewItem, TextViewItem
{
    /** A list of view IDs. */
    private List<Integer> viewIds = new ArrayList<>();

    /** A list of texts for the different textViews. */
    private Map<Integer, String> texts = new HashMap<Integer, String>();

    /** A list of imageResourceIds for the different imageViews. */
    private Map<Integer, Integer> imageResourceIds = new HashMap<Integer, Integer>();

    /** A list of background colors. */
    private Map<Integer, Integer> backgroundColors = new HashMap<Integer, Integer>();

    /**
     * Constructor with minimum arguments to create the view in the adapterView.
     * @param layoutResourceId The layoutResourceId
     */
    public ImageTextItemImp(int layoutResourceId){
        super(layoutResourceId);
    }

    /**
     * Constructor for quickly creating an item when only one ImageView and TextView are used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param textViewId The textViewId
     * @param text The text
     * @param imageViewId The imageViewId
     * @param imageResourceId The imageResourceId
     */
    public ImageTextItemImp(int layoutResourceId, int textViewId, String text,
                            int imageViewId, int imageResourceId){
        super(layoutResourceId);

        addViewId(textViewId);
        setText(textViewId, text);

        addViewId(imageViewId);
        setImageResourceId(imageViewId, imageResourceId);
    }

    /**
     * Constructor for quickly creating an item when only one TextView is used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param textViewId The textViewId
     * @param text The text
     */
    public ImageTextItemImp(int layoutResourceId, int textViewId, String text){
        super(layoutResourceId);
        addViewId(textViewId);
        setText(textViewId, text);
    }

    /**
     * Constructor for quickly creating an item when only one ImageView is used in the layout.
     * @param layoutResourceId The layoutResourceId
     * @param imageViewId The imageViewId
     * @param imageResourceId The imageResourceId
     */
    public ImageTextItemImp(int layoutResourceId, int imageViewId, int imageResourceId){
        super(layoutResourceId);
        addViewId(imageViewId);
        setImageResourceId(imageViewId, imageResourceId);
    }

    @Override
    public List<Integer> getViewIds() {
        return viewIds;
    }

    @Override
    public void setText(int viewId, String text)
    {
        texts.put(viewId, text);
    }

    @Override
    public String getText(int viewId)
    {
        return texts.get(viewId);
    }

    @Override
    public void setImageResourceId(int viewId, int resourceId)
    {
        imageResourceIds.put(viewId, resourceId);
    }

    @Override
    public int getImageResourceId(int viewId)
    {
        final Integer resId = imageResourceIds.get(viewId);
        return resId == null ? Integer.MIN_VALUE : resId;
    }

    @Override
    public int getBackgroundColor(int viewId) {
        final Integer color = backgroundColors.get(viewId);
        return color == null ? Integer.MIN_VALUE : color;
    }

    @Override
    public void setBackgroundColor(int viewId, int color) {
        backgroundColors.put(viewId, color);
    }
}
