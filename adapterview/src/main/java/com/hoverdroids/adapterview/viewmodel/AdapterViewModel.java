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

package com.hoverdroids.adapterview.viewmodel;

import com.hoverdroids.adapterview.view.TwoWayAbsListView.OnScrollListener;
import com.hoverdroids.adapterview.view.TwoWayAdapterView.OnItemClickListener;
import com.hoverdroids.viewmodel.model.AdapterModel;

import java.util.List;

import static com.hoverdroids.adapterview.view.TwoWayAbsListView.TRANSCRIPT_MODE_RELATIVE;

/** Model for updating an AdapterView - e.g. TWGV, ListView, GridView, etc. */
public interface AdapterViewModel extends AdapterModel
{
    /** Invalid transcript mode. */
    int INVALID_TRANSCRIPT_MODE = -1;

    /**
     * Gets items.
     * @return the items
     */
    List<AdapterModel> getItems();

    /**
     * Sets items.
     * @param items the items
     */
    void setItems(final List<AdapterModel> items);

    /**
     * Get the item at the given position.
     * @param position The position
     * @return The item
     */
    default AdapterModel getItem(final int position) {
        return getItems() == null ? null : getItems().get(position);
    }

    /**
     * Get the item by it's item name.
     * @param name The item name
     * @return The item
     */
    default AdapterModel getItem(final String name) {
        if (getItems() != null) {
            for (final AdapterModel model: getItems()) {
                if (name.equals(model.getItemName())) {
                    return model;
                }
            }
        }
        return null;
    }

    /**
     * Get the position of the given item.
     * @param viewModel The viewModel
     * @return The position
     */
    default int getItemPosition(final AdapterModel adapterModel) {
        return getItems() == null ? -1 : getItems().indexOf(adapterModel);
    }

    /**
     * Add the item at the given position in the list.
     * @param position The position
     * @param adapterModel The adapterModel to add
     */
    default void addItem(final int position, final AdapterModel adapterModel) {
        if (getItems() != null) {
            getItems().add(position, adapterModel);
        }
    }

    /**
     * Add the item at the given position in the list.
     * @param adapterModel The adapterModel to add
     */
    default void addItem(final AdapterModel adapterModel) {
        if (getItems() != null) {
            getItems().add(adapterModel);
        }
    }

    /**
     * Remove the given item from the list.
     * @param adapterModel The adapterModel to remove
     */
    default void removeItem(final AdapterModel adapterModel) {
        if (getItems() != null) {
            getItems().remove(adapterModel);
        }
    }

    /**
     * Remove the item at the given position from the list.
     * @param position The position
     */
    default void removeItem(final int position) {
        if (getItems() != null) {
            getItems().remove(position);
        }
    }

    /**
     * Gets on scroll listener.
     * @return the on scroll listener
     */
    OnScrollListener getOnScrollListener();

    /**
     * Sets on scroll listener.
     * @param onScrollListener the on scroll listener
     */
    void setOnScrollListener(final OnScrollListener onScrollListener);

    /**
     * Get the onItemClickListener.
     * @return The onItemClickListener or null if one has not been set
     */
    OnItemClickListener getOnItemClickListener();

    /**
     * Set the onItemClickListener.
     * @param onItemClickListener The onItemClickListener
     */
    void setOnItemClickListener(final OnItemClickListener onItemClickListener);

    /**
     * Is fling enabled boolean.
     * @return the boolean
     */
    boolean isFlingEnabled();

    /**
     * Sets fling enabled.
     * @param enabled the enabled
     */
    void setFlingEnabled(final boolean enabled);

    /**
     * Get the first visible position.
     * @return The position
     */
    int getFirstPosition();

    /**
     * Set the first visible position.
     * @param position The position
     */
    void setFirstPosition(final int position);

    /**
     * Get the first position offset.
     * @return The offset
     */
    int getFirstPositionOffset();

    /**
     * Set the first position offset.
     * @param offset The offset
     */
    void setFirstPositionOffset(final int offset);

    /**
     * Set the TranscriptMode - see TwoWayAbsListView for all modes.
     * @param mode The mode
     */
    void setTranscriptMode(final int mode);

    /**
     * Get the transcript mode. Needs to be using TRANSCRIPT_MODE_RELATIVE (default) for AdapterViews to resurrect
     * correctly when using an AdapterViewModel.
     * @return The TranscriptMode
     */
    default int getTranscriptMode() {
        return TRANSCRIPT_MODE_RELATIVE;
    }
}
