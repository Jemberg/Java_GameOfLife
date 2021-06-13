package com.example.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Options {
    static int tickPeriod = 200; // Default tick period (period between ticks/frames)
    static int size = 50; // Default size set to medium.
    static int defaultSeed = 1188;
    
    public static int getDefaultSeed() {
        return defaultSeed;
    }

    public static void setDefaultSeed(int defaultSeed) {
        Options.defaultSeed = defaultSeed;
    }

    static Grid grid;

    public static int getTickPeriod() {
        return tickPeriod;
    }

    public static void setTickPeriod(int i) {
        Options.tickPeriod = i;
    }   // not used in this version

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        Options.size = size;
    }

    public static Grid getGrid() {
        return grid;
    }

    public static void setGrid(Grid grid) {
        Options.grid = grid;
    }
}
