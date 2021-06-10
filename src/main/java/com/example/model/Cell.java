package com.example.model;

public class Cell {
    private boolean alive;
    private long age; //will se if needed;

    public Cell(){
        alive = false;
        age = 0;
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public void toggleCellStatus(){
        if(alive) {
            alive = false;
            setAge(0);
        }
        else{
            alive = true;
            setAge(1);
        }
    }

    public void addAge(){
        age++;
    }

    public String toString(){
        return Integer.toString(alive ? 1 : 0);
    }
}
