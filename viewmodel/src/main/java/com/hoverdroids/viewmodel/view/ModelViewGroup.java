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

package com.hoverdroids.viewmodel.view;

import android.view.View;
import android.view.ViewGroup;

import com.hoverdroids.viewmodel.model.ViewGroupModel;
import com.hoverdroids.viewmodel.model.ViewModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.view.View.NO_ID;

public interface ModelViewGroup extends ModelView {
    /**
     * Get a mapping of child IDs to child views. If a child does not have an ID, it is not added to the mapping.
     * @param parent The parent to traverse
     * @return The mapping
     */
    default Map<Integer, View> getViewIdMapping() {

        if (!(this instanceof ViewGroup)) {
            return null;
        }

        final Map<Integer, View> viewIds = new HashMap<>();

        for (int i = 0; i < ((ViewGroup)this).getChildCount(); i++) {
            final View view = ((ViewGroup)this).getChildAt(i);
            final int id = view.getId();
            if (id != NO_ID) {
                viewIds.put(view.getId(), view);
            }
        }

        return viewIds;
    }

    /**
     * Set the children view models by extracting them from the parentViewModel using the child's viewId.
     * @param childViewIds The mapping of children IDs to the children views
     * @param parentViewModel The viewModel containing the mapping of children IDs to the children ViewModels
     */
    default void setChildrenViewModels(final Map<Integer, View> childViewIds, final ViewModel parentViewModel) {
        //Nothing to do since no child attr info is available
        if (!(parentViewModel instanceof ViewGroupModel)) {
            return;
        }

        final ViewGroupModel viewGroupModel = (ViewGroupModel) parentViewModel;
        final Iterator<Integer> it = childViewIds.keySet().iterator();
        while (it.hasNext()) {
            final Integer id = it.next();
            final View view = childViewIds.get(id);

            final ViewModel childViewModel = viewGroupModel.getChildViewModel(id);

            //There is no requirement for each child with an ID to have a corresponding viewModel. Indeed, it's more unlikely than likely.
            if (view instanceof ModelView) {
                ((ModelView)view).setViewModel(childViewModel);
            }
        }
    }

    /** Get the mapping of children views to thier view IDs. */
    Map<Integer, View> getChildrenViewIds();

    /** Save the chilren view statis to the viewl models. */
    default void saveChildrenViewStatesToViewModels() {

        final ViewModel model = getViewModel();

        //Nothing to do since no child attr info is available
        if (!(model instanceof ViewGroupModel)) {
            return;
        }

        final Map<Integer, View> childViewIds = getChildrenViewIds();

        final Iterator<Integer> it = childViewIds.keySet().iterator();
        while (it.hasNext()) {
            final Integer id = it.next();
            final View childView = childViewIds.get(id);

            if (childView instanceof ModelView) {
                ((ModelView)childView).saveViewStateToViewModel();
            }
        }
    }
}
