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

import com.hoverdroids.gridviews.util.OnSyncTouchEventListener;
import com.hoverdroids.gridviews.viewgroup.GenericAdapter;
import com.hoverdroids.gridviews.viewgroup.SyncListView;
import com.hoverdroids.gridviews.viewitem.GenericItem;
import com.hoverdroids.gridviews.viewitem.ImageTextItemImp;
import com.hoverdroids.gridviews.viewitem.ViewItemImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TwoWayGridViewsDemo extends AppCompatActivity implements OnSyncTouchEventListener
{
    @BindView(R.id.leftListView)
    SyncListView leftListView;

    @BindView(R.id.centerListView)
    SyncListView centerListView;

    @BindView(R.id.rightListView)
    SyncListView rightListView;

    private GenericAdapter leftAdapter;
    private GenericAdapter centerAdapter;
    private GenericAdapter rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twowaygridview_demo_main);
        
        ButterKnife.bind(this);
        
        leftAdapter = new GenericAdapter(getApplicationContext(), getImageTextItems());
        leftListView.setAdapter(leftAdapter);
        leftListView.setOnSyncTouchEventListener(this);

        centerAdapter = new GenericAdapter(getApplicationContext(), getImageTextItems());
        centerListView.setAdapter(centerAdapter);
        centerListView.setOnSyncTouchEventListener(this);

        rightAdapter = new GenericAdapter(getApplicationContext(), getImageTextItems());
        rightListView.setAdapter(rightAdapter);
        rightListView.setOnSyncTouchEventListener(this);
        

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onSyncTouchEvent(View sourceView, MotionEvent ev) {
        leftListView.onTouchEvent(sourceView, ev);
        centerListView.onTouchEvent(sourceView, ev);
        rightListView.onTouchEvent(sourceView, ev);
    }

    /**
     * Get an example list of items with ImageView and TextView data.
     * @return The items
     */
    private List<GenericItem> getImageTextItems() {
        final int[] layouts = {R.layout.image_text_item_view, R.layout.text_image_item_view};

        final List<GenericItem> items = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            final int layout = layouts[i%2];
            final ImageTextItemImp item
                    = new ImageTextItemImp(layout, R.id.container,
                    R.id.text_view_1, "My name is " + i,
                    R.id.image_view_1, R.drawable.ic_launcher_background);

            items.add(item);
        }
        return items;
    }

    private List<GenericItem> getColorItems() {
        final Random rnd = new Random();

        final List<GenericItem> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            final int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            //ID does not have to be set for the top-most parent since it's assumed
            final ViewItemImp item = new ViewItemImp("com.hoverdroids.gridviews.itemview.ImageView", -1);
            item.setBackgroundColor(color);
            items.add(item);
        }

        return items;
    }
}
