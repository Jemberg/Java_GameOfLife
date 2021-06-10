package com.example.model;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private int iteration;
    private int rows;
    private int columns;
    private Cell[][] cells;

    public Grid(int rows, int columns){
        if(rows<=0 || columns<=0){
            throw new RuntimeException("neither rows nor columns can be negative numbers");
        }
        iteration = 0;
        this.rows = rows;
        this.columns = columns;
        cells = generateCells();
    }

    private Cell[][] generateCells(){
        Cell[][] cells = new Cell[rows][columns];       //i dont check if rows or columns are correct because the only function that is gonna call this is the constructor which already has a check
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<rows; j++){
                cells[i][j] = new Cell();
            }
        }
        return cells;
    }

    public int getIteration(){
        return iteration;
    }

    public Cell getCell(int row, int column){
        return cells[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void toggleCellStatus(int row, int column){
        if(rows<=0 || columns<=0){
            throw new RuntimeException("neither rows nor columns can be negative numbers");
        }
        cells[row][column].toggleCellStatus();
    }

    public void randomGeneration(Random random) {           ////idk if this works like that
        if(random == null){
            throw new RuntimeException("random can not be null");
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < getColumns(); j++) {
                getCell(i, j).setAlive(random.nextBoolean());
            }
        }
    }

    public void setAllDead(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < getColumns(); j++) {
                getCell(i, j).setAlive(false);
            }
        }
    }

    public ArrayList<Cell> getNeighbourCells(int row, int column){
        if(column>columns-1||column<0||row>rows-1||row<0){
            throw new RuntimeException("index out of range");
        }
        ArrayList<Cell> neighbours = new ArrayList<>();
        if(row+1<=rows-1){
            neighbours.add(cells[row+1][column]);
            if(column+1<=columns-1) neighbours.add(cells[row+1][column+1]);
            if(column-1>=0) neighbours.add(cells[row+1][column-1]);
        }
        if(row-1>=0){
            neighbours.add(cells[row-1][column]);
            if(column+1<=columns-1) neighbours.add(cells[row-1][column+1]);
            if(column-1>=0) neighbours.add(cells[row-1][column-1]);
        }
        if(column-1>=0){
            neighbours.add(cells[row][column-1]);
        }
        if(column+1<=columns-1){
            neighbours.add(cells[row][column+1]);
        }

        return neighbours;
    }

    public int aliveNeighbours(int row, int column){
        int aliveCounter = 0;
        ArrayList<Cell> neighbours = getNeighbourCells(row,column);
        for(Cell cell: neighbours){
            if(cell.isAlive()){
                aliveCounter++;
            }
        }
        return aliveCounter;
    }


    public String toString(){
        String returnStr = "";
        for(int i = 0; i<=rows-1; i++){
            for(Cell cell: cells[i]){
                returnStr+=cell + " ";
            }
            returnStr+="\n";
        }
        return returnStr;
    }

    public void nextIteration(){
        //creates 2d array of booleans which denote what state each cell will be in the next iteration
        boolean[][] newCellStates = new boolean[rows][columns];     //all false by default
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < getColumns(); j++) {
                int aliveNeighbours = aliveNeighbours(i,j);
                if(cells[i][j].isAlive() && aliveNeighbours == 2 || aliveNeighbours == 3) newCellStates[i][j] = true;
            }
        }
        //replaces current cell values with the ones we generated in the first step
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < getColumns(); j++) {
                cells[i][j].setAlive(newCellStates[i][j]);
            }
        }
        iteration++;
    }
}
