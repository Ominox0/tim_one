package com.folumo;
import com.folumo.render.Screen;
import com.folumo.render.Window;
import com.folumo.render.elements.Element;
import com.folumo.screens.BlaScreen;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Window.WindowOptions  varijablab = new Window.WindowOptions();
        varijablab.title = "bla screen";
        varijablab.h = 1000;
        varijablab.w = 1000;

        List<Screen<?>> varijablac = new ArrayList<>();
        String varijablad = "bla";

        varijablac.add(new BlaScreen());

        Window<?> window = new Window<>(varijablab, varijablac, varijablad);

        window.run();
    }
}
