package com.folumo.render.elements;

import com.folumo.render.Utility;
import com.sun.jna.ptr.IntByReference;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.rect.SDL_Rect;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.render.SDL_Texture;

import java.awt.*;

import static io.github.libsdl4j.api.render.SdlRender.*;

public class TextElement extends Element {

    private String text;
    private int x, y;
    private Color color;
    private Font font;

    private SDL_Texture texture;

    public TextElement(SDL_Renderer renderer, Font font, String text,
                       int x, int y, Color color) {

        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;

        updateTexture(renderer);
    }

    @Override
    public void render(SDL_Renderer renderer) {
        if (texture == null)
            return;

        SDL_Rect dst = new SDL_Rect();
        dst.x = x;
        dst.y = y;

        IntByReference w = new IntByReference();
        IntByReference h = new IntByReference();

        SDL_QueryTexture(texture, null, null, w, h);

        dst.w = w.getValue();
        dst.h = h.getValue();

        SDL_RenderCopy(renderer, texture, null, dst);
    }

    @Override
    public boolean doesAcceptEvents() {
        return false;
    }

    @Override
    public void event(SDL_Event event) {
    }

    public void destroy() {
        if (texture != null) {
            SDL_DestroyTexture(texture);
            texture = null;
        }
    }

    public void updateTexture(SDL_Renderer renderer){
        texture = Utility.bufferedImageToTexture(
                Utility.createTextImage(text, color, font),
                renderer);
    }
}
