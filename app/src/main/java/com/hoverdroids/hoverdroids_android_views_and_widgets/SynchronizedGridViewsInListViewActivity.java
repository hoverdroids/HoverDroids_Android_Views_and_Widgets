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

package com.hoverdroids.hoverdroids_android_views_and_widgets;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hoverdroids.gridviews.util.OnSourceTouchEventListener;
import com.hoverdroids.gridviews.view.TwoWayGridView;
import com.hoverdroids.gridviews.view.ViewModelAdapter;
import com.hoverdroids.gridviews.viewmodel.AdapterModel;
import com.hoverdroids.gridviews.viewmodel.AdapterViewModelImp;
import com.hoverdroids.gridviews.viewmodel.ImageTextModelImp;
import com.hoverdroids.gridviews.viewmodel.ViewModelImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SynchronizedGridViewsInListViewActivity extends AppCompatActivity implements OnSourceTouchEventListener
{
    @BindView(R.id.listView)
    TwoWayGridView listView;

    private ViewModelAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synchronized_gridviews_in_listview);

        ButterKnife.bind(this);

        listViewAdapter = new ViewModelAdapter(getApplicationContext(), getGridViewModels());
        listView.setAdapter(listViewAdapter);
    }

    @Override
    public void onSourceTouchEvent(View sourceView, MotionEvent ev) {
        //TODO
    }

    private List<AdapterModel> getGridViewModels() {
        final List<AdapterModel> gridViewModels = new ArrayList<>();

        final Random rnd = new Random();

        for (int i = 0; i < 300; i++) {
            final List<AdapterModel> gridViewChildModels = getImageTextItems();
            final AdapterViewModelImp model = new AdapterViewModelImp(R.layout.gridview_item_view, R.id.gridview, gridViewChildModels);
            model.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            gridViewModels.add(model);
        }

        return gridViewModels;
    }

    /**
     * Get an example list of items with ImageView and TextView data.
     * @return The items
     */
    private List<AdapterModel> getImageTextItems() {
        final int[] layouts = {R.layout.image_text_item_view, R.layout.text_image_item_view};

        final List<AdapterModel> items = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            final int layout = layouts[i%2];
            final ImageTextModelImp item
                    = new ImageTextModelImp(layout, R.id.container,
                    R.id.text_view_1, "My name is " + i,
                    R.id.image_view_1, R.drawable.ic_launcher_background);

            items.add(item);
        }
        return items;
    }

    private List<AdapterModel> getColorItems() {
        final Random rnd = new Random();

        final List<AdapterModel> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            final int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            //ID does not have to be set for the top-most parent since it's assumed
            final ViewModelImp item = new ViewModelImp("com.hoverdroids.gridviews.itemview.ImageView", -1);
            item.setBackgroundColor(color);
            items.add(item);
        }

        return items;
    }
}