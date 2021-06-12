package com.example.model;



import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private int iteration;
    private int rows;
    private int columns;
    private boolean[][] cells;

    public Grid(int rows, int columns){
        if(rows<=0 || columns<=0){
            throw new RuntimeException("neither rows nor columns can be negative numbers");
        }
        iteration = 0;
        this.rows = rows;
        this.columns = columns;
        cells = new boolean[rows][columns];
    }


    public int getIteration(){
        return iteration;
    }

    public Boolean getCellVal(int row, int column){
        return cells[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean[][] getCells() {
        return cells;
    }

    public void toggleCellStatus(int row, int column){
        if(row<=0 || column<=0){
            throw new RuntimeException("neither rows nor columns can be negative numbers");
        } else if(row>=rows || column>=columns){
            throw new RuntimeException("index out of range");
        } else cells[row][column]= !cells[row][column];
    }

    public void randomGeneration(Random random) {           ////idk if this works like that
        if(random == null){
            throw new RuntimeException("random can not be null");
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < getColumns(); j++) {
                cells[i][j] = random.nextBoolean();
            }
        }
    }

    public void setAllDead(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < getColumns(); j++) {
                cells[i][j] = false;
            }
        }
    }

    public ArrayList<Boolean> getNeighbourCells(int row, int column){
        if(column>columns-1||column<0||row>rows-1||row<0){
            throw new RuntimeException("index out of range");
        }
        ArrayList<Boolean> neighbours = new ArrayList<>();
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
        ArrayList<Boolean> neighbours = getNeighbourCells(row,column);
        for(Boolean cell: neighbours){
            if(cell){
                aliveCounter++;
            }
        }
        return aliveCounter;
    }


    public String toString(){
        String returnStr = "";
        for(int i = 0; i<=rows-1; i++){
            for(boolean cell: cells[i]){
                if(cell){
                    returnStr+=1 + " ";
                } else{
                    returnStr+=0 + " ";
                }
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
                if(cells[i][j] && aliveNeighbours == 2 || aliveNeighbours == 3) newCellStates[i][j] = true;
            }
        }
        //replaces current cell values with the ones we generated in the first step
        for (int i = 0; i < rows; i++) {
            if (getColumns() >= 0) System.arraycopy(newCellStates[i], 0, cells[i], 0, getColumns()); //TODO check how this works
        }
        iteration++;


    }

//    public void saveAsJson(String filePath){
//        JSONObject json = new JSONObject();
//        json.put("grid_rows", rows);
//        json.put("grid_columns", columns);
//        json.put("iteration", iteration);
//        json.put()
//    }




}
