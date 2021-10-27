package com.vivek.bms.model;

import java.util.ArrayList;
import java.util.List;

public class Theatre {

    private final String id;
    private final String name;
    private final List<Screen> screens;
    //Other theatre metadata.

    public Theatre(final String id, final String name) {
        this.id = id;
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public void addScreen(final  Screen screen) {
        screens.add(screen);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Screen> getScreens() {
        return screens;
    }
    
}
