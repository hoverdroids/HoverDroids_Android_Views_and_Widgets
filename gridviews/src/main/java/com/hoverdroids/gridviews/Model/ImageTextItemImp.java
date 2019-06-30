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

import com.hoverdroids.gridviews.Model.adapter.ImageTextItem;

public class ImageTextItemImp implements ImageTextItem
{
    private int layoutResourceId;
    private String text;
    private int imageResourceId;
    private int itemId;

    public ImageTextItemImp(int layoutResourceId, String text, int imageResourceId){
        this.layoutResourceId = layoutResourceId;
        this.text = text;
        this.imageResourceId = imageResourceId;
    }

    @Override
    public void setText(String text)
    {
        this.text = text;
    }

    @Override
    public String getText()
    {
        return text;
    }

    @Override
    public void setImageResourceId(int resourceId)
    {
        this.imageResourceId = resourceId;
    }

    @Override
    public int getImageResourceId()
    {
        return imageResourceId;
    }

    @Override
    public void setLayoutResourceId(int resourceId)
    {
        layoutResourceId = resourceId;
    }

    @Override
    public int getLayoutResourceId()
    {
        return layoutResourceId;
    }

    @Override
    public void setItemId(int id)
    {
        itemId = id;
    }

    @Override
    public int getItemId()
    {
        return itemId;
    }
}
