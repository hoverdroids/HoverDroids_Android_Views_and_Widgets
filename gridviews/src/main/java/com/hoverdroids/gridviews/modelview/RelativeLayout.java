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

package com.hoverdroids.gridviews.modelview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hoverdroids.gridviews.util.ViewUtils;
import com.hoverdroids.gridviews.viewmodel.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class RelativeLayout extends android.widget.RelativeLayout implements ModelView {

    private Map<Integer, View> viewIds = new HashMap<Integer, View>();

    private ViewModel viewModel;

    public RelativeLayout(final @NonNull Context context) {
        super(context);
        init();
        initViews();
    }

    public RelativeLayout(final @NonNull Context context, final @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RelativeLayout(final @NonNull Context context, final @Nullable AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RelativeLayout(final @NonNull Context context, final @Nullable AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
        //Map all views with IDs for quicker reference - especially in AdapterViews.
        viewIds = ViewUtils.getViewIdMapping(this);
    }

    /**
     * Set viewModel.
     * If the viewModel implements AdapterModel, and this view is being shown in an AdapterView, then this is a good place to modify
     * views based on the model position, is isFirst, and isLast. For example, alternate row views so every other row looks different,
     * display the first row as a header, and display last row as a footer.
     * @param viewModel The viewModel
     */
    @Override
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;

        //Update own attrs
        ViewUtils.setBackgroundColor(this, viewModel);

        //Update child attrs
        ViewUtils.setChildrenViewModels(viewIds, viewModel);
    }

    /**
     * This is called when the view is first detached from the window. If the view is used in an AdapterVeiw, this
     * is called when the row moves out of view when scrolling. It is a good time to save the state to the model.
     */
    @Override
    public void onStartTemporaryDetach(){
        //Save own state
        ViewUtils.saveViewStateToViewModel(this, viewModel);

        //Save children states
        ViewUtils.saveChildrenViewStatesToViewModels(viewIds, viewModel);

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
