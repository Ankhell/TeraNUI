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
package org.terasology.nui.canvas;

import org.terasology.input.MouseInput;
import org.joml.Vector2i;
import org.terasology.nui.Canvas;

/**
 */
public interface CanvasControl extends Canvas {

    void preRender();

    void postRender();

    void processMousePosition(Vector2i position);

    boolean processMouseClick(MouseInput button, Vector2i pos);

    boolean processMouseRelease(MouseInput button, Vector2i pos);

    boolean processMouseWheel(int wheelTurns, Vector2i pos);

    /**
     * Returns the scale factor to scale the UI by, relative to the screen size.
     * < 1 is smaller, > 1 is larger and = 1 is the same as the screen size.
     * @return the current UI scale
     */
    float getUiScale();

    /**
     * Sets the UI scale factor, relative to the screen size
     * < 1 is smaller, > 1 is larger and = 1 is the same as the screen size.
     * @param uiScale the scale factor to use
     */
    void setUiScale(float uiScale);
}
