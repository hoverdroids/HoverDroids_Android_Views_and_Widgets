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
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hoverdroids.adapterview.view.TwoWayAbsListView;
import com.hoverdroids.adapterview.viewmodel.AdapterViewModel;
import com.hoverdroids.viewmodel.model.ViewModel;
import com.hoverdroids.viewmodel.view.ModelView;


/**
 * TwoWayGridView that can handle a ViewModel.
 * ViewGroups that are not AdapterViews, and that want to handle ViewModels, should implement ModelViewGroup. AdpterViews implement ModelView instead
 * because their child should only be updated via an Adapter like the ViewModelAdapter.
 */
public class TwoWayGridView extends com.hoverdroids.adapterview.view.TwoWayGridView implements ModelView {

    private ViewModel viewModel;

    private ViewModelAdapter adapter;

    public TwoWayGridView(final @NonNull Context context) {
        super(context);
        init();
        initViews();
    }

    public TwoWayGridView(final @NonNull Context context, final @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TwoWayGridView(final @NonNull Context context, final @Nullable AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /** Wait until inflation is finished or else the children are not ready and don't get included. */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initViews();
    }

    protected void init(){
        //TODO Use Dagger to inject singletons we need - e.g. EventBus
    }

    protected void initViews() {
        //Only set adapter once. Null items list will generate an empty ArrayList
        adapter = new ViewModelAdapter(getContext(), null);
        setAdapter(adapter);
    }

    /**
     * Set viewModel.
     * If the viewModel implements AdapterModel, and this view is being shown in an AdapterView, then this is a good place to modify
     * views based on the model position, is isFirst, and isLast. For example, alternate row views so every other row looks different,
     * display the first row as a header, and display last row as a footer.
     * @param viewModel The viewModel
     */
    @Override
    public void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;

        //Update own View attrs
        setBackgroundColor(viewModel);
        setBackgroundResource(viewModel);

        //Update own AdapterView attrs
        if (viewModel instanceof AdapterViewModel) {
            final AdapterViewModel avModel = (AdapterViewModel) viewModel;

            //Set the gv's position BEFORE updating the adapter - need to be using TRANSCRIPT_MODE_RELATIVE (default)
            setTranscriptMode(TwoWayAbsListView.TRANSCRIPT_MODE_RELATIVE);
            setRelativePosition(avModel.getFirstPosition(), avModel.getFirstPositionOffset());

            adapter.setItems(avModel.getItems());
            setRelativePosition(avModel.getFirstPosition(), avModel.getFirstPositionOffset());
        }

        //NOTE: ViewGroups that are not AdapterViews would call setViewModel on child views. AdapterViews must do this through an Adapter like ViewModelAdapter.
    }

    @Override
    public ViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void saveViewStateToViewModel() {
        //Stop fling and other scrolling to ensure the state is recorded correctly
        endFling();

        if (viewModel instanceof AdapterViewModel) {
            //Save the state so the gridView can be resurrected in the same position the next time it's displayed
            final AdapterViewModel adapterViewModel = (AdapterViewModel) viewModel;
            adapterViewModel.setFirstPosition(getFirstVisiblePosition());
            adapterViewModel.setFirstPositionOffset(getScrollByDistance());
        }
    }

    /**
     * This is called when the view is first detached from the window. If the view is used in an AdapterVeiw, this
     * is called when the row moves out of view when scrolling. It is a good time to save the state to the model.
     */
    @Override
    public void onStartTemporaryDetach(){
        //Save own state
        saveViewStateToViewModel();

        //NOTE: Other ViewGroups that handle a ViewModel would call saveChildrenViewStatesToViewModels here. AdapterViews must do this through the ViewGroup
        //of each child - e.g. the ViewGroup for an entire row.

        super.onStartTemporaryDetach();
    }

    /**
     * This is called when the view is first attached to the window. If the view is used in an AdapterView, this
     * is called when the row first moves into view when scrolling.
     */
    @Override
    public void onFinishTemporaryDetach(){
        //TODO Something useful when view is attached to the window.

        //Let the superclass do whatever it does
        super.onFinishTemporaryDetach();
    }
}
