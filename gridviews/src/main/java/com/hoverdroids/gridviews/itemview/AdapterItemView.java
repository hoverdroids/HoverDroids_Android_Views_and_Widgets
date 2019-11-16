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

package com.hoverdroids.gridviews.itemview;

import com.hoverdroids.gridviews.viewitem.ViewItem;

/** View holder that handles view updates for the child view in the adapter.*/
public interface AdapterItemView
{
    /**
     * Set the view item from an AdapterView - e.g. when used in a ListView.
     * @param position The items's position in the adapter.
     * @param isFirst Is it the first position in the adapter.
     * @param isLast Is it the last position in the adapter.
     * @param item The item for the given position in the adapter.
     */
    void updateViews(int position, boolean isFirst, boolean isLast, ViewItem item);
}
