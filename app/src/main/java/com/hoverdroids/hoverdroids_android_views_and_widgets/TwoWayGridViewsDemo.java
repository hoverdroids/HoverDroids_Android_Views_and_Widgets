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

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hoverdroids.gridviews.Model.adapter.GenericItem;
import com.hoverdroids.gridviews.Model.ImageTextItemImp;
import com.hoverdroids.gridviews.ViewGroup.GenericAdapter;
import com.hoverdroids.gridviews.ViewGroup.TwoWayGridView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TwoWayGridViewsDemo extends AppCompatActivity
{

    @BindView(R.id.gridView)
    TwoWayGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twowaygridview_demo_main);
        ButterKnife.bind(this);

        final int[] layouts = {R.layout.image_text_item_view, R.layout.text_image_item_view};

        ArrayList<GenericItem> items = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            final int layout = layouts[i%2];
            items.add((GenericItem)new ImageTextItemImp(layout, "My name is " + i,
                    R.drawable.ic_launcher_background));
        }

        GenericAdapter adapter = new GenericAdapter(getApplicationContext(), items);
        gridView.setAdapter(adapter);

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
}
