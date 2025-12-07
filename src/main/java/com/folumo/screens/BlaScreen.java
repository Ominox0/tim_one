package com.folumo.screens;

import com.folumo.render.FontRenderer;
import com.folumo.render.Screen;
import com.folumo.render.Window;
import com.folumo.render.elements.*;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.event.SDL_EventType;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.scancode.SDL_Scancode;


public class BlaScreen extends Screen {
    private Rect player;
    private Rect floor;
    private int gravity = 10;
    @Override
    public String getName() {
        return "bla";
    }

    @Override
    public void init(Window window) {
        this.elements.add(
                new Rect(0, 0, window.options.w, window.options.h, (byte) 20, (byte) 20, (byte) 20, (byte) 255, true)
        );

        FontRenderer font = new FontRenderer(null, 36);

        this.elements.add(
                new TextElement(window.renderer, font, "example", 0, 0, (byte) 20, (byte) 20, (byte) 20, (byte) 255)
        );

        player = new Rect(50,50, 50,100, (byte) 0, (byte) 255, (byte)255, (byte) 255 , true );

        this.elements.add(
                player
        );

        floor = new Rect(0, window.options.h, window.options.w,1,(byte)0, (byte)0,(byte)255,(byte)255,true);
        this.elements.add(
                floor
        );
    }


    // 0 - 255
    // 0 - 1
    // #000000 - #ffffff

    private void tick(){


    }

    @Override
    public void event(SDL_Event evt) {
        super.event(evt);

        if (evt.type == SDL_EventType.SDL_KEYUP) {
            switch (evt.key.keysym.scancode){
                case SDL_Scancode.SDL_SCANCODE_W -> player.y -= 10;
                case SDL_Scancode.SDL_SCANCODE_D -> player.x += 10;
                case SDL_Scancode.SDL_SCANCODE_S -> player.y += 10;
                case SDL_Scancode.SDL_SCANCODE_A -> player.x -= 10;
            }

        }
    }

    @Override
    public void render(SDL_Renderer renderer) {
        super.render(renderer);

        this.tick();
    }

    public boolean checkCollision(int x1, int x2, int y1, int y2, int w1, int w2, int h1, int h2){
        return (x1 < x2 + w2 &&
        x1 + w1 > x2 &&
        y1 < y2 + h2 &&
        y1 + h1 > y2);
    }
}
