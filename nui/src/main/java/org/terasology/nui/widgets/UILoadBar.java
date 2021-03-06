/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.nui.widgets;

import org.terasology.nui.util.NUIMathUtil;
import org.joml.Vector2i;
import org.terasology.nui.UITextureRegion;
import org.terasology.nui.Canvas;
import org.terasology.nui.CoreWidget;
import org.terasology.nui.LayoutConfig;
import org.terasology.nui.ScaleMode;
import org.terasology.nui.databinding.Binding;
import org.terasology.nui.databinding.DefaultBinding;
import org.terasology.nui.util.RectUtility;

/**
 * An animated loading bar
 */
public class UILoadBar extends CoreWidget {

    @LayoutConfig
    private UITextureRegion fillTexture;

    @LayoutConfig
    private boolean animate = true;

    @LayoutConfig
    private Binding<Float> value = new DefaultBinding<>(0f);

    private float timeInMilliseconds = 0.0f;

    @Override
    public void onDraw(Canvas canvas) {
        if (fillTexture != null) {
            int size = NUIMathUtil.floorToInt(canvas.size().x * getValue());
            int barWidth = fillTexture.getWidth();
            int offset = 0;
            if ( animate) {
                offset = (int) ((timeInMilliseconds / 10) % barWidth);
            }
            int drawnWidth = 0;
            // Draw Offset
            if (offset != 0) {
                int drawWidth = Math.min(size, offset);
                canvas.drawTextureRaw(fillTexture, RectUtility.createFromMinAndSize(0, 0, drawWidth, canvas.size().y),
                        ScaleMode.STRETCH, barWidth - offset, 0, drawWidth, canvas.size().y);
                drawnWidth += drawWidth;
            }
            // Draw Remainder
            while (drawnWidth < size) {
                int drawWidth = Math.min(size - drawnWidth, barWidth);
                canvas.drawTextureRaw(fillTexture, RectUtility.createFromMinAndSize(drawnWidth, 0, drawWidth, canvas.size().y),
                        ScaleMode.STRETCH, 0, 0, drawWidth, canvas.size().y);
                drawnWidth += drawWidth;
            }
        }
    }

    @Override
    public Vector2i getPreferredContentSize(Canvas canvas, Vector2i sizeHint) {
        return new Vector2i();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    /**
     * @return The texture used in the bar.
     */
    public UITextureRegion getFillTexture() {
        return fillTexture;
    }

    /**
     * @param fillTexture The new texture to use.
     */
    public void setFillTexture(UITextureRegion fillTexture) {
        this.fillTexture = fillTexture;
    }

    /**
     * @return A Boolean indicating whether. the bar is animated.
     */
    public boolean isAnimate() {
        return animate;
    }

    /**
     * @param animate A Boolean indicating if the bar should be animated.
     */
    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public void bindValue(Binding<Float> binding) {
        value = binding;
    }

    /**
     * @return A Float between 0 and 1 indicating the percentage of the bar.
     */
    public float getValue() {
        return value.get();
    }

    /**
     * @param val A float from 0 to 1 indicating the percentage loaded.
     */
    public void setValue(float val) {
        value.set(val);
    }

    public void setTimeInMilliseconds(float timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }
}
