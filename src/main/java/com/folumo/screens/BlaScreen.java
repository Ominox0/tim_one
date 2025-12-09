package com.folumo.screens;

import com.folumo.render.Screen;
import com.folumo.render.Window;
import com.folumo.render.elements.*;
import io.github.libsdl4j.api.event.SDL_Event;
import io.github.libsdl4j.api.event.SDL_EventType;
import io.github.libsdl4j.api.scancode.SDL_Scancode;

import java.awt.*;


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
                new Rect(0, 0, window.options.w, window.options.h, new Color(20, 20, 20), true)
        );

        this.elements.add(
                new TextElement(window.renderer, new Font("Arial", Font.BOLD, 48), "example", 0, 0, Color.BLUE)
        );

        player = new Rect(50,50, 50,100, new Color(0, 255, 255) , true );

        this.elements.add(
                player
        );

        floor = new Rect(0, window.options.h, window.options.w,1, new Color(0, 0, 255),true);
        this.elements.add(
                floor
        );
    }

    @Override
    public void tick(double dt){}

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
}
