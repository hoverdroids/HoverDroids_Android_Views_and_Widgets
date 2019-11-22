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

package com.hoverdroids.adapterview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hoverdroids.adapterview.R;
import com.hoverdroids.touchsync.OnSourceTouchEventListener;
import com.hoverdroids.touchsync.SourceMode;
import com.hoverdroids.touchsync.SyncMode;
import com.hoverdroids.touchsync.TouchSyncView;

import static com.hoverdroids.touchsync.SourceMode.NOT_SOURCE;

public class TouchSyncTwoWayGridView extends TwoWayGridView implements TouchSyncView {

    private SourceMode sourceMode = SourceMode.XY;
    private SyncMode syncMode = SyncMode.XY;

    private OnSourceTouchEventListener onSourceTouchEventListener;

    private boolean isSyncTouchEvent;

    public TouchSyncTwoWayGridView(final Context context) {
        super(context);
    }

    public TouchSyncTwoWayGridView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        readCustomAttrs(attrs,0, 0);
    }

    public TouchSyncTwoWayGridView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readCustomAttrs(attrs, defStyleAttr, 0);
    }

    private void readCustomAttrs(final AttributeSet attrs, final int defStyleAttr, final int defStyleRes){
        if (attrs == null){
            return;
        }

        final Context context = getContext();
        final TypedArray ary = context.obtainStyledAttributes(attrs, R.styleable.TouchSyncTwoWayGridView, defStyleAttr, defStyleRes);

        int mode = ary.getInt(R.styleable.TouchSyncTwoWayGridView_sourceMode, SourceMode.XY.ordinal());
        sourceMode = SourceMode.values()[mode];

        mode = ary.getInt(R.styleable.TouchSyncTwoWayGridView_syncMode, SyncMode.XY.ordinal());
        syncMode = SyncMode.values()[mode];

        ary.recycle();
    }

    @Override
    public boolean onTouchEvent(final MotionEvent ev) {
        //Only relay touch events that are actually on this view. Avoid sending touch events
        //coming from a source because that will create an infinite loop of MotionEvents
        if (onSourceTouchEventListener != null && !sourceMode.equals(NOT_SOURCE) && !isSyncTouchEvent){

            //Send touch event data in X, Y, or both. This allows the output to easily be filtered
            //in a single direction - ie avoids the need to determine how much movement in an axis
            //we don't care to sync.
            final MotionEvent clonedEvent = MotionEvent.obtain(ev);

            if (ev.getHistorySize() > 1) {
                final float origX = ev.getHistoricalX(0, 0);
                final float origY = ev.getHistoricalY(0, 0);

                float x = SourceMode.X.equals(sourceMode) || SourceMode.XY.equals(sourceMode) ? ev.getX() : origX;
                float y = SourceMode.Y.equals(sourceMode) || SourceMode.XY.equals(sourceMode) ? ev.getY() : origY;

                clonedEvent.setLocation(x, y);
            }

            onSourceTouchEventListener.onSourceTouchEvent(this, ev);
            clonedEvent.recycle();
        }

        //Handle the touch event as though the user made it on this view
        final boolean consumed = super.onTouchEvent(ev);

        //If sync touch event then always return false and allow the initial view to handle
        //everything as usual.
        return !isSyncTouchEvent && consumed;
    }

    @Override
    public void onTouchEvent(final View sourceView, final MotionEvent ev) {
        if (sourceView.equals(this) || SyncMode.NOT_SYNC.equals(syncMode))
        {
            return;
        }

        //Accept touch event data in X, Y, or both. This allows the input to easily be filtered
        //in a single direction - ie avoids the need to determine how much movement in an axis
        //we don't care to sync.
        final MotionEvent clonedEvent = MotionEvent.obtain(ev);

        if (ev.getHistorySize() > 1) {
            final float origX = ev.getHistoricalX(0, 0);
            final float origY = ev.getHistoricalX(0, 0);

            float x = SyncMode.X.equals(syncMode) || SyncMode.XY.equals(syncMode) ? ev.getX() : origX;
            float y = SyncMode.Y.equals(syncMode) || SyncMode.XY.equals(syncMode) ? ev.getY() : origY;

            clonedEvent.setLocation(x, y);
        }

        isSyncTouchEvent = true;
        onTouchEvent(clonedEvent);
        clonedEvent.recycle();
        isSyncTouchEvent = false;
    }

    /**
     * Gets onSourceTouchEventListener.
     * @return The onSourceTouchEventListener.
     */
    public OnSourceTouchEventListener getOnSourceTouchEventListener() {
        return onSourceTouchEventListener;
    }

    /**
     * Sets onSourceTouchEventListener.
     * @param onSourceTouchEventListener The onSourceTouchEventListener.
     */
    public void setOnSourceTouchEventListener(final OnSourceTouchEventListener onSourceTouchEventListener) {
        this.onSourceTouchEventListener = onSourceTouchEventListener;
    }

    public SourceMode getSourceMode() {
        return sourceMode;
    }

    public void setSourceMode(final SourceMode sourceMode) {
        this.sourceMode = sourceMode;
    }

    public SyncMode getSyncMode() {
        return syncMode;
    }

    public void setSyncMode(final SyncMode syncMode) {
        this.syncMode = syncMode;
    }
}

