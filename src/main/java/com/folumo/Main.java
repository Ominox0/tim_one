package com.folumo;
import com.folumo.render.Screen;
import com.folumo.render.Window;
import com.folumo.render.elements.Element;
import com.folumo.screens.BlaScreen;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Window.WindowOptions  windowoptions= new Window.WindowOptions();
        windowoptions.title = "bla screen";
        windowoptions.h = 1000;
        windowoptions.w = 1000;

        List<Screen<?>> screenlist = new ArrayList<>();
        String defoultscreen = "bla";

        screenlist.add(new BlaScreen());

        Window<?> window = new Window<>(windowoptions, screenlist, defoultscreen);

        window.run();
    }
}
