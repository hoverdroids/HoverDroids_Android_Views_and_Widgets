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
import android.text.method.Touch;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.hoverdroids.adapterview.modelview.ViewModelAdapter;
import com.hoverdroids.adapterview.view.TouchSyncTwoWayGridView;
import com.hoverdroids.adapterview.view.TwoWayGridView;
import com.hoverdroids.adapterview.viewmodel.TouchSyncAdapterViewModelImp;
import com.hoverdroids.modelviewgroup.viewmodel.AdapterViewGroupModelImp;
import com.hoverdroids.modelviewgroup.viewmodel.ImageTextViewGroupModelImp;
import com.hoverdroids.modelviewgroup.viewmodel.TouchSyncAdapterViewGroupModelImp;
import com.hoverdroids.touchsync.OnSourceTouchEventListener;
import com.hoverdroids.viewmodel.model.AdapterModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static android.view.View.NO_ID;

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

        Timber.plant(new Timber.DebugTree());
        Timber.d("CHRIS planteddd");

        listViewAdapter = new ViewModelAdapter(getApplicationContext(), getGridViewModels());
        listView.setAdapter(listViewAdapter);
    }

    @Override
    public void onSourceTouchEvent(View sourceView, MotionEvent ev) {
        Timber.d("CHRIS onSourceTouchEvent");
        if (sourceView instanceof TouchSyncTwoWayGridView) {
            final int position = ((TouchSyncTwoWayGridView)sourceView).getFirstVisiblePosition();
            final int offset = ((TouchSyncTwoWayGridView)sourceView).getScrollByDistance();
            for (int i = 0; i < listViewAdapter.getCount(); i++) {
                final TouchSyncAdapterViewGroupModelImp model = (TouchSyncAdapterViewGroupModelImp) listViewAdapter.getItem(i);
                model.setFirstPosition(position);
                model.setFirstPositionOffset(offset);
            }
        }

        for (int i = 0; i < listView.getChildCount(); i++) {
            //Get the GridView - and don' forget it's wrapped in a FrameLayout
            final TouchSyncTwoWayGridView gridView = listView.getChildAt(i).findViewById(R.id.gridview);
            if (gridView != null) {
                gridView.onTouchEvent(sourceView, ev);
            }
        }
    }

    private List<AdapterModel> getGridViewModels() {
        final List<AdapterModel> gridViewModels = new ArrayList<>();

        final Random rnd = new Random();

        for (int i = 0; i < 300; i++) {
            //Oddities with TWGV.onMeasure in an AdapterView require a ViewGroup container - and hence the ViewModel requires a ViewGroupModel
            final TouchSyncAdapterViewGroupModelImp viewGroupModel = new TouchSyncAdapterViewGroupModelImp(R.layout.gridview_item_view, NO_ID, R.id.gridview, getImageTextItems(i));
            viewGroupModel.setBackgroundColor(i%2 ==0 ? Color.BLACK : Color.WHITE);

            final int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            viewGroupModel.getChildViewModel(R.id.gridview).setBackgroundColor(color);

            viewGroupModel.setOnSourceTouchEventListener(this);

            gridViewModels.add(viewGroupModel);
        }

        return gridViewModels;
    }

    /**
     * Get an example list of items with ImageView and TextView data.
     * @return The items
     */
    private List<AdapterModel> getImageTextItems(int row) {
        //final int[] layouts = {R.layout.horizontal_image_text_item_view, R.layout.horizontal_text_image_item_view};

        final List<AdapterModel> items = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            final int layout = R.layout.horizontal_image_text_item_view;//layouts[i%2];
            final ImageTextViewGroupModelImp item
                    = new ImageTextViewGroupModelImp(layout, R.id.container,
                    R.id.text_view_1, "Row:" + row + " Col:" + i,
                    R.id.image_view_1, R.drawable.ic_launcher_background);

            items.add(item);
        }
        return items;
    }
}