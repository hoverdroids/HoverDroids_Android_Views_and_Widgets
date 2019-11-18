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

package com.hoverdroids.gridviews.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hoverdroids.gridviews.modelview.ModelView;
import com.hoverdroids.gridviews.view.TwoWayGridView;
import com.hoverdroids.gridviews.viewmodel.AdapterViewModel;
import com.hoverdroids.gridviews.viewmodel.ImageViewModel;
import com.hoverdroids.gridviews.viewmodel.TextViewModel;
import com.hoverdroids.gridviews.viewmodel.ViewGroupModel;
import com.hoverdroids.gridviews.viewmodel.ViewModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.view.View.NO_ID;

public class ViewUtils {

    /**
     * Instantiate a new view using the class name.
     * @param context The context
     * @param viewClass The view class name (e.g. com.android.view.TextView)
     * @return The view or null.
     */
    public static View instantiateView(final Context context, final String viewClass) {
        try {
            final Class<?> clazz = Class.forName(viewClass);
            final Constructor<?> constructor = clazz.getConstructor(Context.class);
            return (View) constructor.newInstance(context);

        } catch (final ClassNotFoundException e) {
            e.printStackTrace();

        } catch (final NoSuchMethodException e) {
            e.printStackTrace();

        } catch (final InstantiationException e) {
            e.printStackTrace();

        } catch (final IllegalAccessException e) {
            e.printStackTrace();

        } catch (final InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a mapping of child IDs to child views. If a child does not have an ID, it is not added to the mapping.
     * @param parent The parent to traverse
     * @return The mapping
     */
    public static Map<Integer, View> getViewIdMapping(final ViewGroup parent) {
        final Map<Integer, View> viewIds = new HashMap<>();

        for (int i = 0; i < parent.getChildCount(); i++) {
            final View view = parent.getChildAt(i);
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
    public static void setChildrenViewModels(final Map<Integer, View> childViewIds, final ViewModel parentViewModel) {
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

    /**
     * Set the view's background color if it was specified in the viewModel. Otherwise, don't override.
     * @param view The view
     * @param viewModel The viewModel
     */
    public static void setBackgroundColor(final View view, final ViewModel viewModel) {
        final int color = viewModel.getBackgroundColor();
        if (color != Integer.MIN_VALUE) {
            view.setBackgroundColor(color);
        }
    }

    /**
     * Set the TextView's text if it was specified in the textViewModel. Otherwise, don't override.
     * @param view The TextView
     * @param textViewModel The TextViewModel
     */
    public static void setText(final TextView view, final TextViewModel textViewModel) {
        final String text = textViewModel.getText();
        if (text != null) {
            view.setText(text);
        }
    }

    /**
     * Set the TextView's text if it was specified in the textViewModel. Otherwise, don't override.
     * @param view The TextView
     * @param textViewModel The TextViewModel
     */
    public static void setTextColor(final TextView view, final TextViewModel textViewModel) {
        final int color = textViewModel.getTextColor();
        if (color != Integer.MIN_VALUE) {
            view.setTextColor(color);
        }
    }

    /**
     * Set teh ImageView's image via a resource ID if it was specified in teh imageViewModel. Otherwise, don't override.
     * @param view The ImageView
     * @param imageViewModel The ImageViewModel
     */
    public static void setImageResourceId(final ImageView view, final ImageViewModel imageViewModel) {
        final int resId = imageViewModel.getImageResourceId();
        if (resId != Integer.MIN_VALUE) {
            view.setImageResource(resId);
        }
    }

    public static void saveChildrenViewStatesToViewModels(final Map<Integer, View> childViewIds, final ViewModel parentViewModel) {

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

            saveViewStateToViewModel(view, childViewModel);
        }
    }

    public static void saveViewStateToViewModel(final View view, final ViewModel viewModel) {

        if (view instanceof TwoWayGridView) {
            saveViewStateToViewModel((TwoWayGridView) view, viewModel);

        } else if (view instanceof FrameLayout) {
            saveViewStateToViewModel((FrameLayout) view, viewModel);

        } else if (view instanceof RelativeLayout) {
            saveViewStateToViewModel((RelativeLayout) view, viewModel);

        } else if (view instanceof LinearLayout) {
            saveViewStateToViewModel((LinearLayout) view, viewModel);

        } else if (view instanceof TextView) {
                saveViewStateToViewModel((TextView) view, viewModel);

        } else if (view instanceof ImageView) {
            saveViewStateToViewModel((ImageView) view, viewModel);

        }
        //TODO Add additional views that need their state saved to the model
    }

    public static void saveViewStateToViewModel(final TwoWayGridView twoWayGridView, final ViewModel viewModel) {
        //Stop fling and other scrolling to ensure the state is recorded correctly
        twoWayGridView.endFling();

        if (viewModel instanceof AdapterViewModel) {
            //Save the state so the gridView can be resurrected in the same position the next time it's displayed
            final AdapterViewModel adapterViewModel = (AdapterViewModel) viewModel;
            adapterViewModel.setFirstPosition(twoWayGridView.getFirstVisiblePosition());
            adapterViewModel.setFirstPositionOffset(twoWayGridView.getScrollByDistance());
        }
    }

    public static void saveViewStateToViewModel(final FrameLayout frameLayout, final ViewModel viewModel) {
        //Nothing to save at the moment
    }

    public static void saveViewStateToViewModel(final RelativeLayout linearLayout, final ViewModel viewModel) {
        //Nothing to save at the moment
    }

    public static void saveViewStateToViewModel(final LinearLayout linearLayout, final ViewModel viewModel) {
        //Nothing to save at the moment
    }

    public static void saveViewStateToViewModel(final ImageView imageView, final ViewModel viewModel) {
        //Nothing to save at the moment
    }

    public static void saveViewStateToViewModel(final TextView textView, final ViewModel viewModel) {
        //Nothing to save at the moment
    }
}