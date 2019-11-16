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

package com.hoverdroids.gridviews.viewgroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hoverdroids.gridviews.itemview.AdapterItemView;
import com.hoverdroids.gridviews.itemview.ItemView;
import com.hoverdroids.gridviews.util.ViewUtils;
import com.hoverdroids.gridviews.viewitem.GenericItem;
import com.hoverdroids.gridviews.viewitem.ViewItem;

import java.util.ArrayList;
import java.util.List;

import static com.hoverdroids.gridviews.viewitem.GenericItem.INVALID_RESOURCE_ID;

public class GenericAdapter extends BaseAdapter
{
    private Context context;
    private List<GenericItem> items;
    private List<Integer> layouts = new ArrayList<Integer>();
    private LayoutInflater inflater;

    public GenericAdapter(Context context, List<GenericItem> items){
        super();
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);

        resetLayouts();
    }

    @Override
    public int getViewTypeCount() {
        return layouts.size();
    }

    @Override
    public int getCount() {
        return items == null ? -1 : items.size();
    }

    @Override
    public GenericItem getItem(int position) {
        return items == null ? null : items.get(position);
    }

    /** Get an item based on a UniqueId that is applied to each item and is project-specific.*/
    @Override
    public long getItemId(int position) {
        return getItem(position) == null ? 0 : getItem(position).getItemId();
    }

    /**
     * Get the view type used by the current item.
     * @param position The position of the item.
     * @return The view type index, which MUST be greater or equal to 1.
     */
    @Override
    public int getItemViewType(int position) {
        if (getItem(position) == null)
        {
            return 0;
        }

        final int layoutResId = getItem(position).getLayoutResourceId();
        for(int i = 0; i < layouts.size(); i++){
            if(layoutResId == layouts.get(i)){
                return i;
            }
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final GenericItem item = getItem(position);

        if (convertView == null && item.getLayoutResourceId() != INVALID_RESOURCE_ID) {
            convertView = inflater.inflate(item.getLayoutResourceId(), parent, false);

        } else if (convertView == null && item.getViewClass() != null) {
            convertView = ViewUtils.instantiateView(context, item.getViewClass());
        }

        final boolean isLast = position == items.size() - 1;
        final boolean isFirst = position == 0;

        if (convertView instanceof AdapterItemView && item instanceof ViewItem) {
            ((AdapterItemView)convertView).updateViews(position, isFirst, isLast, (ViewItem)item);

        } else if (convertView instanceof ItemView && item instanceof ViewItem) {
            ((ItemView)convertView).setViewItem((ViewItem)item);
        }

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        //The data has already been updated but not the adapterView.So, update
        //the layoutTypes before updating the adapterView
        resetLayouts();

        //Update the adapterView
        super.notifyDataSetChanged();
    }

    public void setItems(List<GenericItem> items){
        this.items = items;
        notifyDataSetChanged();
    }

    private void resetLayouts(){
        if (items == null)
        {
            return;
        }

        layouts.clear();//TODO CHRIS-make sure this doesn't break things

        //Add unique layoutIds
        for (GenericItem item : items){
            final int layoutId = item.getLayoutResourceId();
            if (!layouts.contains(layoutId)){
                layouts.add(layoutId);
            }
        }
    }
}
/*package com.sncorp.atrax.atakplugin.view.adapter;

        import android.content.Context;
        import android.support.annotation.LayoutRes;
        import android.support.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;

        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.List;

*//**
 * This is a broadly usable adapter that expands the functionality of ArrayAdapter while simultaneously generalizing
 * getView so that developers can focus less on creating adapters and more on the models and views.
 *
 * To take advantage of the full functionality of this adapter, use items that implement GenericItem. Items can be used
 * that do not implement GenericItem; this limits the adapter to a single view type which is instantiated using the
 * resource layout ID passed into the constructor or via setLayoutResourceID.
 *
 * One caveat: Tags should not be set on the outer-most container view of each item. Setting tags for any
 * child View or ViewGroup within the outer-most container view is fine.
 *
 * @param <T> the type parameter
 *//*
@SuppressWarnings("PMD")
public class GenericAdapter<T> extends BaseAdapter
{
    *//** Indicator for unknown item id - because item is not a GenericItem. *//*
    public static final int UNKNOWN_ITEM_ID = 0;

    *//** The context. *//*
    private Context context;

    *//** The list of items to display. *//*
    private List<T> items;

    *//**
     * An item for showing "List Is Empty". Using a separate item instead of adding an item to items
     * so that all other methods return the expected values.
     *
     * emptyItem.getResourceId() != 0 indicates the developer wants to show "List Is Empty" in place of empty list.
     *//*
    private GenericItem emptyItem = new GenericItemImp(0);

    *//** The layout resource ids used by all of the views in the adapter view. *//*
    private List<Integer> layouts = new ArrayList<>();

    *//** The layout inflater. *//*
    private LayoutInflater inflater;

    *//**
     * Indicates whether or not {@link #notifyDataSetChanged()} must be called whenever
     * {@link #items} is modified.
     *//*
    private boolean notifyOnChange;

    *//**
     * Lock used to modify the content of {@link #mObjects}. Any write operation
     * performed on the array should be synchronized on this lock. This lock is also
     * used by the filter (see {@link #getFilter()} to make a synchronized copy of
     * the original array of data.
     *//*
    @SuppressWarnings("JavadocReference")
    private final Object lock = new Object();

    *//**
     * Constructor for a new GenericAdapter.
     *
     * @param context The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     *//*
    public GenericAdapter(@NonNull Context context, @LayoutRes int resource) {
        this(context, new ArrayList<>(), resource, 0);
    }

    public GenericAdapter(@NonNull Context context, @LayoutRes int resource, int emptyLayoutResourceId) {
        this(context, new ArrayList<>(), resource, emptyLayoutResourceId);
    }

    *//**
     * Constructor for a new GenericAdapter.
     *
     * @param context The context.
     * @param layoutResourceId The default layoutResourceId used to inflate child views.
     * @param items   The items.
     *//*
    public GenericAdapter(@NonNull final Context context, @NonNull  final List<T> items, final int layoutResourceId,
                          final int emptyLayoutResourceId)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);

        setLayoutResourceId(layoutResourceId);
        setEmptyLayoutResourceId(emptyLayoutResourceId);

        setItems(items);

        resetLayouts();

        //Call NotifyDataSetChanged from here on out, unless otherwise specified.
        notifyOnChange = true;
    }

    *//**
     * Get the number of view types used. If there are no items and the emptyItem has a valid layout resource ID, then
     * this returns 1 to indicate a single "List Is Empty" view will be shown.
     * @return The number of view types.
     *//*
    @Override
    public int getViewTypeCount()
    {
        return items.isEmpty() && hasValidLayoutResourceId(emptyItem) ? 1 : layouts.size();
    }

    *//**
     * Get the number of items in the adapter. If there are no items and the emptyItem has a valid layout resource
     * ID, then this returns 1 to indicate a single "List Is Empty" view will be shown.
     * @return The number of items.
     *//*
    @Override
    public int getCount()
    {
        return items.isEmpty() && hasValidLayoutResourceId(emptyItem) ? 1 : items.size();
    }

    *//**
     * Get the item at the given position. This will not return the emptyItem even if it is displayed. For that, call
     * getGenericItem instead.
     * @return The item.
     *//*
    @Override
    public T getItem(final int position)
    {
        return items.isEmpty() ? null : items.get(position);
    }

    *//**
     * Get the GenericItem at the given position.
     * @param position The position of the item.
     * @return The item cast as GenericItem or null if not a GenericItem.
     *//*
    public GenericItem getGenericItem(final int position)
    {
        final T item = getItem(position);
        final GenericItem genItem = item instanceof GenericItem ? (GenericItem) item : null ;
        return items.isEmpty() && hasValidLayoutResourceId(emptyItem) ? emptyItem : genItem;
    }

    *//**
     * Get an item based on a UniqueId that is applied to each item and is project-specific.
     * @param position The position.
     * @return The item id.
     *//*
    @Override
    public long getItemId(final int position)
    {
        final GenericItem item = getGenericItem(position);
        return item == null ? UNKNOWN_ITEM_ID : item.getItemId();
    }

    *//**
     * Get the view type used by the current item.
     *
     * @param position The position of the item.
     * @return The view type index, which MUST be greater or equal to 1.
     *//*
    @Override
    public int getItemViewType(final int position)
    {
        //Get the generic item. This accounts for the null item and empty item. It also accounts for the item not being
        //a GenericItem - since in those cases the defaultLayoutResourceId is returned.
        final GenericItem item = getGenericItem(position);
        final boolean useDefault = !hasValidLayoutResourceId(item);
        return useDefault ? 0 : layouts.indexOf(item.getLayoutResourceId());
    }

    *//**
     * Get the layout resource ID used to inflate the view for the given position.
     * @param position The position in the adapter.
     * @return The layout resource ID.
     *//*
    public int getItemResourceId(final int position)
    {
        return layouts.get(getItemViewType(position));
    }

    *//**
     * Get the view for the given position.
     * @param position The position of the view.
     * @param convertView The recycled view.
     * @param parent The parent holding the views.
     * @return The view.
     *//*
    //CSOFF: FinalParametersCheck
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent)
    {

        //final GenericItem item = getItem(position);//TODO CHRIS - if not a genericItem, use resId; if genItem and the

        // resId is not specified but the resId is globally specified, use the global
        //One of the problems here is that the item resId count will be wrong if we don't account for it
        final AdapterItemView viewHolder;

        if (convertView == null)
        {
            convertView = inflater.inflate(getItemResourceId(position), parent, false);

            viewHolder = getViewHolder(convertView, position);
            if (viewHolder != null)
            {
                convertView.setTag(viewHolder);
            }
        }
        else
        {
            viewHolder = convertView.getTag() != null ? (AdapterItemView) convertView.getTag() : null;
        }

        final boolean isLast = position == items.size() - 1;
        final boolean isFirst = position == 0;

        if (viewHolder != null)
        {
            viewHolder.updateViews(position, isFirst, isLast, getItem(position));
        }

        return convertView;
    }

    *//**
     * Override this to implement your own ViewHolder pattern. Otherwise, let's assume you're using one of the
     * generic items from the library, all of which implement the AdapterItemView themselves.
     *
     * @param convertView The view.
     * @param position The position of the view.
     * @return The viewholder for the specified view.
     *//*
    protected AdapterItemView getViewHolder(final View convertView, final int position)
    {
        return convertView instanceof AdapterItemView ? (AdapterItemView) convertView : null;
    }

    *//**
     * Call when the dataset has changed.
     *//*
    @Override
    public void notifyDataSetChanged()
    {
        //TODO CHRIS should this check for nullAllowed?

        //The data has already been updated but not the adapterView.So, update
        //the layoutTypes before updating the adapterView
        resetLayouts();

        //Update the adapterView
        super.notifyDataSetChanged();
    }

    *//** Call notifyDataSetChanged when locally desired. *//*
    private void localNotifyDataSetChanged()
    {
        if (notifyOnChange)
        {
            notifyDataSetChanged();
        }
    }

    *//**
     * Set items.
     *
     * @param items the items
     *//*
    public void setItems(final List<T> items)
    {
        //Don't allow null data sets. Empty is fine.
        this.items = items == null ? new ArrayList<>() : items;

        //TODO CHRIS - need to update resource ids

        localNotifyDataSetChanged();
    }

    *//**
     * Recheck for layout ids used by the items.
     *//*
    private void resetLayouts()
    {
        //Wait until all layouts resource IDs are added before updating the AdapterView.
        final boolean actualNotifyOnChange = notifyOnChange;
        notifyOnChange = false;

        //Clear old layouts while ensuring the default layout is always first.
        final int defaultLayoutResourceId = layouts.get(0);
        layouts.clear();
        addLayoutResourceId(defaultLayoutResourceId);

        //Add unique layout IDs that are specified by the items.
        for (final T item : items)
        {
            if (item instanceof GenericItem)
            {
                addLayoutResourceId(((GenericItem) item).getLayoutResourceId());
            }
        }

        //Now update the AdapterView if desired.
        notifyOnChange = actualNotifyOnChange;
        localNotifyDataSetChanged();
    }

    *//**
     * Set the default layout resource ID used to inflate new views.
     * @param layoutResourceId The layout resource ID.
     *//*
    public void setLayoutResourceId(int layoutResourceId)
    {
        addLayoutResourceId(layoutResourceId);
    }

    *//**
     * Set the layout resource ID to use when the list is empty. Set to 0 to indicate the "List Is Empty" view
     * should not be shown and instead, the empty list should be shown.
     * @param layoutResourceId The layout resource ID used to display the "List Is Empty" view.
     *//*
    public void setEmptyLayoutResourceId(int layoutResourceId)
    {
        final boolean changed = emptyItem.getLayoutResourceId() != layoutResourceId;

        if (changed)
        {
            //Update the "list is empty" item
            emptyItem.setLayoutResourceId(layoutResourceId);

            //Update the the AdapterView to use the new layout resource ID.
            localNotifyDataSetChanged();
        }
    }

    private void addLayoutResourceId(int layoutResourceId)
    {
        //Only add unique and valid layout resource IDs (i.e. ID < 0 && not already included)
        if (layoutResourceId != 0 && !layouts.contains(layoutResourceId))
        {
            layouts.add(layoutResourceId);

            //Trigger a view update if a new id is used because the current visualization assumed a different layout.
            localNotifyDataSetChanged();
        }
    }

    *//**
     * Remove all elements from the list.
     *
     * @throws UnsupportedOperationException if the underlying data collection is immutable
     *//*
    @SuppressWarnings("JavadocReference")
    public void clear()
    {
        synchronized (lock)
        {
            items.clear();
        }
    }

    *//**
     * Add all.
     *
     * @param collection the collection
     *//*
    public void addAll(@NonNull final Collection<? extends T> collection)
    {
        synchronized (lock)
        {
            items.addAll(collection);
        }
    }

    private boolean hasValidLayoutResourceId(final GenericItem item)
    {
        return item != null && item.getLayoutResourceId() < 0;
    }
}*/
