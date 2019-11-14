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

package com.hoverdroids.gridviews.Util;

/** View holder that handles view updates for the child view in the adapter.*/
public interface GenericViewHolder
{
    /**
     * Regardless of whether or not convertView was just inflated, its views need to be updated
     * with the current data. So, update the views with the provided data
     * @param position The position being updated.
     * @param isFirst Is it the first position.
     * @param isLast Is it the last position.
     * @param item The item for the given position.
     */
    void updateViews(int position, boolean isFirst, boolean isLast, Object item);
}
