package com.folumo.render.elements;

import com.folumo.render.Utility;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.render.SDL_Renderer;

import java.awt.*;

import static io.github.libsdl4j.api.render.SdlRender.*;

public class Circle extends Element {
    private int cx, cy, radius;
    private Color color;
    private boolean filled;

    public Circle(int cx, int cy, int radius, Color color, boolean filled) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
        this.filled = filled;
    }

    @Override
    public void render(SDL_Renderer renderer) {
        Utility.setRenderColor(renderer, color);

        int x = radius;
        int y = 0;
        int err = 1 - x;

        while (x >= y) {
            if (filled) {
                SDL_RenderDrawLine(renderer, cx - x, cy + y, cx + x, cy + y);
                SDL_RenderDrawLine(renderer, cx - y, cy + x, cx + y, cy + x);
                SDL_RenderDrawLine(renderer, cx - x, cy - y, cx + x, cy - y);
                SDL_RenderDrawLine(renderer, cx - y, cy - x, cx + y, cy - x);
            } else {
                SDL_RenderDrawPoint(renderer, cx + x, cy + y);
                SDL_RenderDrawPoint(renderer, cx + y, cy + x);
                SDL_RenderDrawPoint(renderer, cx - y, cy + x);
                SDL_RenderDrawPoint(renderer, cx - x, cy + y);
                SDL_RenderDrawPoint(renderer, cx - x, cy - y);
                SDL_RenderDrawPoint(renderer, cx - y, cy - x);
                SDL_RenderDrawPoint(renderer, cx + y, cy - x);
                SDL_RenderDrawPoint(renderer, cx + x, cy - y);
            }

            y++;
            if (err < 0) {
                err += 2 * y + 1;
            } else {
                x--;
                err += 2 * (y - x + 1);
            }
        }
    }

    @Override
    public boolean doesAcceptEvents() {
        return false;
    }

    @Override
    public void event(SDL_Event event) {}
}