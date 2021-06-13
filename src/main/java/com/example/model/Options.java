package com.example.model;

public class Options {
    static int speed = 1; // Default speed set to medium.
    static int size = 30; // Default size set to medium.
    static Grid grid;

    public static int getSpeed() {
        return speed;
    }

    public static void setSpeed(int i) {
        Options.speed = i;
    }

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
