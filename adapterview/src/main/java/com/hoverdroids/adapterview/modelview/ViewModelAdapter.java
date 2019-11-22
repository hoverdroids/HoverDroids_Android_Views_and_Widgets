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

package com.hoverdroids.adapterview.modelview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hoverdroids.viewmodel.model.AdapterModel;
import com.hoverdroids.viewmodel.model.ViewModel;
import com.hoverdroids.viewmodel.view.ModelView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hoverdroids.viewmodel.model.ViewModel.INVALID_RESOURCE_ID;

public class ViewModelAdapter<T> extends BaseAdapter
{
    private static int DEFAULT_VIEW_TYPE = 0;

    private Context context;
    private List<T> items;
    private List<Integer> layouts = new ArrayList<Integer>();
    private LayoutInflater inflater;

    /** Used to lock during synchronized. */
    private final Object lock = new Object();

    /** Indicates whether or not notifyDataSetChanged() must be called whenever items is modified. */
    private boolean notifyOnChange = true;

    /** Reset list of layout IDs or just keep adding to the current list. */
    private boolean resetLayouts;

    public ViewModelAdapter(final Context context) {
        this(context, null);
    }

    public ViewModelAdapter(final Context context, final List<T> items) {
        super();
        this.context = context;
        inflater = LayoutInflater.from(context);

        resetLayouts = true;
        setItems(items);
        resetLayouts = false;
    }

    /**
     * Set the items.
     * @param items The items
     */
    public void setItems(final List<T> items){

        synchronized (lock) {
            //Don't allow null data sets. Empty is fine.
            this.items = items == null ? new ArrayList<>() : items;
        }

        notifyDataSetChanged();
    }

    /** Remove all items from list. */
    public void clear() {
        clear(true);
    }

    /**
     * Remove all items from list.
     * @param notifyOnChange True if call notifyDataSetChanged(). False otherwise.
     */
    public void clear (final boolean notifyOnChange) {
        synchronized (lock) {
            items.clear();
        }

        notifyDataSetChanged(notifyOnChange);
    }

    /**
     * Add an item to the adapter and call notifyDataSetChanged.
     * @param item The item
     */
    public void add(final T item) {
        add(item, true);
    }

    /**
     * Add an item to the adapter and only call notifyDataSetChanged if desired.
     * @param item The item
     * @param notifyOnChange True if notifyDataSetChanged should be called. False otherwise
     */
    public void add(final T item, final boolean notifyOnChange) {
        add(-1, item, notifyOnChange);
    }

    /**
     * Add an item to the adapter at the given position and call notifyDataSetChanged.
     * @param position The position
     * @param item The item
     */
    public void add(final int position, final T item) {
        add(position, item, true);
    }

    /**
     * Add an item to the adapter at the given position and only call notifyDataSetChanged if desired.
     * @param position The position
     * @param item The item
     * @param notifyOnChange True if notifyDataSetChanged should be called. False otherwise.
     */
    public void add(final int position, final T item, final boolean notifyOnChange) {
        synchronized (lock) {
            if (position >= 0) {
                items.add(position, item);
            } else {
                items.add(item);
            }
        }

        notifyDataSetChanged(notifyOnChange);
    }

    /**
     * Add all items to the adapter and call notifyDataSetChanged.
     * @param collection The collection of items to add
     */
    public void addAll(final Collection<? extends T> collection) {
        addAll(collection, true);
    }

    /**
     * Add all items to the adapter and call notifyDataSetChanged if desired.
     * @param collection The collection of items to add
     * @param notifyOnChange True if call notifyDataSetChanged. False otherwise
     */
    public void addAll(final Collection<? extends T> collection, final boolean notifyOnChange) {
        addAll(-1, collection, notifyOnChange);
    }

    /**
     * Add all items to the adapter at the given position and call notifyDataSetChanged if desired.
     * @param position The position to add the items
     * @param collection The collection of items to add
     * @param notifyOnChange True if call notifyDataSetChanged. False otherwise
     */
    public void addAll(final int position, final Collection<? extends T> collection, final boolean notifyOnChange) {
        synchronized (lock) {
            if (position >= 0) {
                items.addAll(position, collection);
            } else {
                items.addAll(collection);
            }
        }
        notifyDataSetChanged(notifyOnChange);
    }

    /**
     * Remove the item from the adadpter.
     * @param item The item
     */
    public void remove(final T item) {
        remove(item, true);
    }

    /**
     * Remove the item from the adapter.
     * @param item The item
     * @param notifyOnChange True if call notifyDataSetChanged. False otherwise.
     */
    public void remove(final T item, final boolean notifyOnChange) {
        synchronized (lock) {
            items.remove(item);
        }
        notifyDataSetChanged(notifyOnChange);
    }

    /**
     * Remove the item from the adapter using its case-insensitive name and then call notifyDataSetChanged.
     * @param name The item name
     */
    public void removeByName(final String name) {
        removeByName(name, true, true);
    }

    /**
     * Remove the item from the adapter using desired case sensitivity and then call notifyDataSetChanged.
     * @param name The item name
     * @param ignoreCase True if the name comparison should be case-insensitive. False otherwise.
     */
    public void removeByName(final String name, final boolean ignoreCase) {
        removeByName(name, ignoreCase, true);
    }

    /**
     * Remove the item from the adapter using desired case sensitivity and then call notifyDataSetChanged if desired.
     * @param name The item name
     * @param ignoreCase True if the name comparison should be case-insensitive. False otherwise.
     * @param notifyOnChange True if call notifyDataSetChanged. False otherwise.
     */
    public void removeByName(final String name, final boolean ignoreCase, final boolean notifyOnChange) {
        remove(getItem(name, ignoreCase), notifyOnChange);
    }

    /**
     * Notifies the attached observers that the underlying data has been changed
     * and any View reflecting the data set should refresh itself.
     * This also results in the layout resource IDs getting updated.
     * @param notifyOnChange True if call notifyDataSetChanged. False otherwise.
     */
    public void notifyDataSetChanged(final boolean notifyOnChange) {
        this.notifyOnChange = notifyOnChange;
        notifyDataSetChanged();
    }

    /**
     * Notifies the attached observers that the underlying data has been changed
     * and any View reflecting the data set should refresh itself.
     * This also results in the layout resource IDs getting updated.
     */
    @Override
    public void notifyDataSetChanged() {
        //The data has already been updated but not the adapterView.So, update
        //the layoutTypes before updating the adapterView
        updateLayouts();

        if (notifyOnChange) {
            //Update the adapterView
            super.notifyDataSetChanged();
        }
        notifyOnChange = true;
    }

    /** Re-check for layout ids used by the items. */
    private void updateLayouts(){
        if (items == null)
        {
            return;
        }

        if (resetLayouts) {
            layouts.clear();
        }

        //Add unique layoutIds
        for (final T item : items){
            final int layoutId = item instanceof AdapterModel ? ((AdapterModel)item).getLayoutResourceId() : INVALID_RESOURCE_ID;
            if (layoutId != INVALID_RESOURCE_ID  && !layouts.contains(layoutId)){
                layouts.add(layoutId);
            }
        }
    }

    /**
     * Get the number of view types used.
     * @return The number of view types must be >= 1
     */
    @Override
    public int getViewTypeCount() {
        return layouts.isEmpty() ? 1 : layouts.size();
    }

    /**
     * Get the number of items.
     * @return The count
     */
    @Override
    public int getCount() {
        return items == null ? -1 : items.size();
    }

    /**
     * Get the item at the given position.
     * @param position The position
     * @return The item
     */
    @Override
    public T getItem(final int position) {
        return items == null || items.isEmpty() || position >= getCount() ? null : items.get(position);
    }

    /**
     * Get the ViewModel at the given position.
     * @param position The position of the item
     * @return The item cast as ViewModel or null if not a ViewModel
     */
    public AdapterModel getAdapterModel(final int position) {
        return items == null || items.isEmpty() || position >= getCount() || !(items.get(position) instanceof ViewModel)
                ? null : (AdapterModel) items.get(position);
    }

    /**
     * Get the item by its name.
     * @param name The item name
     * @param ignoreCase True if ignore case. False otherwise.
     * @return The item
     */
    public T getItem(final String name, final boolean ignoreCase) {
        if (items == null ) {
            return null;
        }

        for (final T item : items) {
            if (item instanceof AdapterModel) {
                final String itemName = ((AdapterModel)item).getItemName();
                if (ignoreCase && name.equalsIgnoreCase(itemName)) {
                    return item;
                } else if (!ignoreCase && name.equals(itemName)) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * Get the item by its item ID.
     * @param itemId The item ID
     * @return The item
     */
    public T getItemById(final int itemId) {
        if (items == null ) {
            return null;
        }

        for (final T item : items) {
            if (item instanceof AdapterModel && itemId == ((AdapterModel)item).getItemId()) {
                return item;
            }
        }
        return null;
    }

    /**
     * Get an item based on a UniqueId that is applied to each item and is project-specific.
     * @param position The position
     * @return The item ID
     */
    @Override
    public long getItemId(int position) {
        final AdapterModel adapterModel = getAdapterModel(position);
        return adapterModel == null ? AdapterModel.UNKNOWN_ITEM_ID : adapterModel.getItemId();
    }

    /**
     * Get the view type used by the current item.
     * @param position The position of the item.
     * @return The view type index, which MUST be greater or equal to 1.
     */
    @Override
    public int getItemViewType(final int position) {
        if (getItem(position) == null)
        {
            return DEFAULT_VIEW_TYPE;
        }

        //Get the type using the AdapterModel. This accounts for the null item and empty item. It also accounts for the item
        //not being an AdapterModel since, in that case, the defaultLayoutResourceId is returned.
        final boolean useDefault = !hasValidLayoutResourceId(getItem(position));
        return useDefault ? DEFAULT_VIEW_TYPE : layouts.indexOf(getAdapterModel(position).getLayoutResourceId());
    }

    /**
     * Get the position of the given item.
     * @param item The item
     * @return The position
     */
    public int getItemPosition(final T item) {
        return items == null || items.isEmpty() ? -1 : items.indexOf(item);
    }

    /**
     * Set whether or not the list of layout resource IDs is reset each time notifyDataSetChanged is called.
     * This should only be set to true when the new data set has entirely different layout resource IDS than the
     * current list. If there is overlap, it's best to leave the list along and add new ones. Thsi should be reset to false
     * after each call to notifyDataSetChanged.
     * @param reset True if reset the list of layout resource IDs. False otherwise.
     */
    public void setResetLayouts(final boolean reset) {
        resetLayouts = reset;
    }

    /**
     * Determine if teh item has a valid layout resource ID.
     * @param item The item
     * @return True if layout resource ID is valid. False otherwise.
     */
    public boolean hasValidLayoutResourceId(final T item) {
        return item instanceof AdapterModel && ((AdapterModel)item).getLayoutResourceId() != INVALID_RESOURCE_ID;
    }

    /** {@inheritDoc} */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final AdapterModel item = getAdapterModel(position);
        if (item == null) {
            //Item isn't instance of AdapterModel and so we don't know how to handle view inflation/instantiation
            return convertView;
        }

        final boolean isLast = position == items.size() - 1;
        final boolean isFirst = position == 0;

        item.setPosition(position);
        item.setIsFirst(isFirst);
        item.setIsLast(isLast);

        if (convertView == null && item.getLayoutResourceId() != INVALID_RESOURCE_ID) {
            convertView = inflater.inflate(item.getLayoutResourceId(), parent, false);

        } else if (convertView == null && item.getViewClass() != null) {
            //TODO not sure how useful this is given that view recylcing depends on the number of view types, which in turn
            //relies on the number of xml layouts
            convertView = instantiateView(context, item.getViewClass());
        }

        if (convertView instanceof ModelView && item instanceof ViewModel) {
            ((ModelView)convertView).setViewModel((ViewModel)item);
        }

        return convertView;
    }

    /**
     * Instantiate a new view using the class name.
     * @param context The context
     * @param viewClass The view class name (e.g. com.android.view.TextView)
     * @return The view or null.
     */
    private View instantiateView(final Context context, final String viewClass) {
        try {
            final Class<?> clazz = Class.forName(viewClass);
            final Constructor<?> constructor = clazz.getConstructor(Context.class);
            return (View) constructor.newInstance(context);

        } catch (final ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}