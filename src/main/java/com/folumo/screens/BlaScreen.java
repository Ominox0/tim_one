package com.folumo.screens;

import com.folumo.render.Screen;
import com.folumo.render.Window;
import com.folumo.render.elements.Circle;
import com.folumo.render.elements.Rect;
import com.folumo.render.elements.Line;

public class BlaScreen extends Screen {
    @Override
    public String getName() {
        return "bla";
    }

    @Override
    public void init(Window window) {
        //this.elements.add(
        //        new Rect(0, 0, window.options.w, window.options.h, (byte) 20, (byte) 20, (byte) 20, (byte) 255, true)
        //);

        //this.elements.add(
        //        new Rect(100,150, 200,50, (byte) 0, (byte) 255, (byte)255, (byte) 255 , true )
        //);
//
        //this.elements.add(
        //        new Circle(100,150, 40, (byte) 0, (byte) 255, (byte)255, (byte) 255, true)
        //);
        //this.elements.add(
        //        new Line(150,200,200,250,(byte) 50, (byte) 50, (byte)100, (byte) 255)
        //);


        //for (int i = 0; i < 204 + 51; i++) {
        //    int xPos = i * 5;
        //    for (int j = 0; j < 204 + 51; j++) {
        //        int yPos = j * 5;
        //        this.elements.add(
        //                new Rect(xPos, yPos, 5, 5, (byte) ((xPos) / 5), (byte) 0, (byte) ((yPos) / 5), (byte) 255, true)
        //        );
        //    }
        //}
    }

    // 0 - 255
    // 0 - 1
    // #000000 - #ffffff
}
