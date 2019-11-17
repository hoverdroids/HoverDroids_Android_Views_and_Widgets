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

package com.hoverdroids.gridviews.viewmodel;

import java.util.Map;

/**
 * Model for updating a ViewGroup.
 * View and ViewGroup are analogous to ViewModel and ViewGroupModel.
 * This is more straight forward since the group simply maps child IDs to the child view's ViewModel.
 */
public interface ViewGroupModel extends ViewModel {

    /**
     * Get the child viewModel by its view ID.
     * @param id The view's ID
     * @return The view's viewModel
     */
    default ViewModel getChildViewModel(final int id) {
        return getChildViewModels() == null ? null : getChildViewModels().get(id);
    }

    /**
     * Set the child viewModel by its view ID. The viewId is not required as it's already included in the viewModel.
     * @param viewModel The view's viewModel
     */
    default void setChildViewModel(final ViewModel viewModel) {
        getChildViewModels().put(viewModel.getViewId(), viewModel);
    }

    /**
     * Get all of the child view models.
     * @return The child view models.
     */
    Map<Integer, ViewModel> getChildViewModels();

    /**
     * Set all of teh child view models.
     * @param viewModels The child view models.
     */
    void setChildViewModels(final Map<Integer, ViewModel> viewModels);
}
