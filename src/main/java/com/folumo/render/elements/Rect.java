package com.folumo.render.elements;

import com.folumo.render.Utility;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.rect.SDL_Rect;
import io.github.libsdl4j.api.render.SDL_Renderer;

import java.awt.*;

import static io.github.libsdl4j.api.render.SdlRender.*;

public class Rect extends Element {
    public int x, y, w, h;
    public Color color;
    public boolean filled;

    public Rect(int x, int y, int w, int h, Color color, boolean filled) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.filled = filled;
    }

    @Override
    public void render(SDL_Renderer renderer) {
        Utility.setRenderColor(renderer, color);

        SDL_Rect rect = new SDL_Rect();
        rect.x = x;
        rect.y = y;
        rect.w = w;
        rect.h = h;

        if (filled) SDL_RenderFillRect(renderer, rect);
        else SDL_RenderDrawRect(renderer, rect);
    }

    @Override
    public boolean doesAcceptEvents() {
        return false;
    }

    @Override
    public void event(SDL_Event event) {}
}