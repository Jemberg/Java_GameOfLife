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

        Grid grid = new Grid(20,20);
        Random random = new Random(2000);

        grid.randomGeneration(random);
        //grid.saveAsJson("C:\\testfolder\\yes.json");
        //grid.loadJson("C:\\testfolder\\yes.json");

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