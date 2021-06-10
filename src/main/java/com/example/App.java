package com.example;

import java.util.Random;

import com.example.model.Grid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();

        Grid grid = new Grid(10,10);
//
//        grid.toggleCellStatus(1,0);
//        grid.toggleCellStatus(0,1);
//        grid.toggleCellStatus(1,1);
//        System.out.println(grid.aliveNeighbours(0,0));
//
//
//        grid.toggleCellStatus(8,8);
//        grid.toggleCellStatus(8,9);
//        grid.toggleCellStatus(9,8);
//        System.out.println(grid.aliveNeighbours(9,9));
//
//        grid.toggleCellStatus(9,1);
//        grid.toggleCellStatus(8,0);
//        grid.toggleCellStatus(8,1);
//        System.out.println(grid.aliveNeighbours(9,0));
//
//
//        grid.toggleCellStatus(0,8);
//        grid.toggleCellStatus(1,8);
//        grid.toggleCellStatus(1,9);
//        System.out.println(grid.aliveNeighbours(0,9));

        Random random = new Random(1188);
//        grid.toggleCellStatus(4,3);
//        grid.toggleCellStatus(4,4);
//        grid.toggleCellStatus(4,5);
//        grid.toggleCellStatus(5,3);

        grid.randomGeneration(random);

        while(true){
            try {
                Thread.sleep(2000);
                grid.nextIteration();
                System.out.println(grid);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}