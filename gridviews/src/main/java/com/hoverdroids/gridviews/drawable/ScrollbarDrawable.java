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
package com.hoverdroids.gridviews.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class ScrollbarDrawable extends Drawable {
    
    private Drawable verticalTrack;
    private Drawable horizontalTrack;
    private Drawable verticalThumb;
    private Drawable horizontalThumb;
    private int range;
    private int offset;
    private int extent;
    private boolean vertical;
    private boolean changed;
    private boolean rangeChanged;
    private final Rect tempBounds = new Rect();
    private boolean alwaysDrawHorizontalTrack;
    private boolean alwaysDrawVerticalTrack;

    public ScrollbarDrawable() {
    }

    /**
     * Indicate whether the horizontal scrollbar track should always be drawn regardless of the
     * extent. Defaults to false.
     *
     * @param alwaysDrawTrack Set to true if the track should always be drawn
     */
    public void setAlwaysDrawHorizontalTrack(boolean alwaysDrawTrack) {
        alwaysDrawHorizontalTrack = alwaysDrawTrack;
    }

    /**
     * Indicate whether the vertical scrollbar track should always be drawn regardless of the
     * extent. Defaults to false.
     *
     * @param alwaysDrawTrack Set to true if the track should always be drawn
     */
    public void setAlwaysDrawVerticalTrack(boolean alwaysDrawTrack) {
        alwaysDrawVerticalTrack = alwaysDrawTrack;
    }

    /**
     * Indicates whether the vertical scrollbar track should always be drawn regardless of the
     * extent.
     */
    public boolean getAlwaysDrawVerticalTrack() {
        return alwaysDrawVerticalTrack;
    }

    /**
     * Indicates whether the horizontal scrollbar track should always be drawn regardless of the
     * extent.
     */
    public boolean getAlwaysDrawHorizontalTrack() {
        return alwaysDrawHorizontalTrack;
    }

    public void setParameters(int range, int offset, int extent, boolean vertical) {
        if (this.vertical != vertical) {
            changed = true;
        }

        if (this.range != range || this.offset != offset || this.extent != extent) {
            rangeChanged = true;
        }

        this.range = range;
        this.offset = offset;
        this.extent = extent;
        this.vertical = vertical;
    }

    @Override
    public void draw(Canvas canvas) {
        final boolean vertical = this.vertical;
        final int extent = this.extent;
        final int range = this.range;

        boolean drawTrack = true;
        boolean drawThumb = true;
        if (extent <= 0 || range <= extent) {
            drawTrack = vertical ? alwaysDrawVerticalTrack : alwaysDrawHorizontalTrack;
            drawThumb = false;
        }

        Rect r = getBounds();
        if (canvas.quickReject(r.left, r.top, r.right, r.bottom,
                Canvas.EdgeType.AA)) {
            return;
        }
        if (drawTrack) {
            drawTrack(canvas, r, vertical);
        }

        if (drawThumb) {
            int size = vertical ? r.height() : r.width();
            int thickness = vertical ? r.width() : r.height();
            int length = Math.round((float) size * extent / range);
            int offset = Math.round((float) (size - length) * this.offset / (range - extent));

            // avoid the tiny thumb
            int minLength = thickness * 2;
            if (length < minLength) {
                length = minLength;
            }
            // avoid the too-big thumb
            if (offset + length > size) {
                offset = size - length;
            }

            drawThumb(canvas, r, offset, length, vertical);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        changed = true;
    }

    private void drawTrack(Canvas canvas, Rect bounds, boolean vertical) {
        Drawable track;
        if (vertical) {
            track = verticalTrack;
        } else {
            track = horizontalTrack;
        }
        if (track != null) {
            if (changed) {
                track.setBounds(bounds);
            }
            track.draw(canvas);
        }
    }

    private void drawThumb(Canvas canvas, Rect bounds, int offset, int length, boolean vertical) {
        final Rect thumbRect = tempBounds;
        final boolean changed = rangeChanged || this.changed;
        if (changed) {
            if (vertical) {
                thumbRect.set(bounds.left,  bounds.top + offset,
                        bounds.right, bounds.top + offset + length);
            } else {
                thumbRect.set(bounds.left + offset, bounds.top,
                        bounds.left + offset + length, bounds.bottom);
            }
        }

        if (vertical) {
            final Drawable thumb = verticalThumb;
            if (changed) thumb.setBounds(thumbRect);
            thumb.draw(canvas);
        } else {
            final Drawable thumb = horizontalThumb;
            if (changed) thumb.setBounds(thumbRect);
            thumb.draw(canvas);
        }
    }

    public void setVerticalThumbDrawable(Drawable thumb) {
        if (thumb != null) {
            verticalThumb = thumb;
        }
    }

    public void setVerticalTrackDrawable(Drawable track) {
        verticalTrack = track;
    }

    public void setHorizontalThumbDrawable(Drawable thumb) {
        if (thumb != null) {
            horizontalThumb = thumb;
        }
    }

    public void setHorizontalTrackDrawable(Drawable track) {
        horizontalTrack = track;
    }

    public int getSize(boolean vertical) {
        if (vertical) {
            return (verticalTrack != null ?
                    verticalTrack : verticalThumb).getIntrinsicWidth();
        } else {
            return (horizontalTrack != null ?
                    horizontalTrack : horizontalThumb).getIntrinsicHeight();
        }
    }

    @Override
    public void setAlpha(int alpha) {
        if (verticalTrack != null) {
            verticalTrack.setAlpha(alpha);
        }
        verticalThumb.setAlpha(alpha);
        if (horizontalTrack != null) {
            horizontalTrack.setAlpha(alpha);
        }
        horizontalThumb.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (verticalTrack != null) {
            verticalTrack.setColorFilter(cf);
        }
        verticalThumb.setColorFilter(cf);
        if (horizontalTrack != null) {
            horizontalTrack.setColorFilter(cf);
        }
        horizontalThumb.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public String toString() {
        return "ScrollbarDrawable: range=" + range + " offset=" + offset +
                " extent=" + extent + (vertical ? " V" : " H");
    }
}
