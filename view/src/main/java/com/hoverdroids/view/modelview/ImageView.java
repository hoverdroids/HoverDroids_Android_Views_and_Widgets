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

package com.hoverdroids.view.modelview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.hoverdroids.view.viewmodel.ImageViewModel;
import com.hoverdroids.viewmodel.model.ViewModel;
import com.hoverdroids.viewmodel.view.ModelView;

import static com.hoverdroids.viewmodel.model.ViewModel.INVALID_RESOURCE_ID;

public class ImageView extends AppCompatImageView implements ModelView {

    protected ViewModel viewModel;

    public ImageView(final Context context) {
        super(context);
    }

    public ImageView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

        //Set attrs specific to a View
        setBackgroundColor(viewModel);
        setBackgroundResource(viewModel);

        //Set the attrs specific to ImageView
        if (viewModel instanceof ImageViewModel) {
            setImageResource((ImageViewModel) viewModel);
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

    @Override
    public ViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void saveViewStateToViewModel() {
        //Nothing to do yet.
    }

    /**
     * Set teh ImageView's image via a resource ID if it was specified in teh imageViewModel. Otherwise, don't override.
     * @param imageViewModel The ImageViewModel
     */
    public void setImageResource(final ImageViewModel model) {
        final int resId = model == null ? INVALID_RESOURCE_ID :  model.getImageResourceId();
        if (resId != INVALID_RESOURCE_ID) {
           setImageResource(resId);
        }
    }
}