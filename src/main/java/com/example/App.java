package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import com.example.model.Grid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/gameView.fxml"));
        Parent gameRoot = gameLoader.load();
        stage.setScene(new Scene(gameRoot));
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        // Grid grid = new Grid(20,20);
        // Random random = new Random(2000);

        // grid.randomGeneration(random);
        //grid.saveAsJson("C:\\testfolder\\yes.json");
        //grid.loadJson("C:\\testfolder\\yes.json");

        // while(true){
        //     try {
        //         Thread.sleep(2000);
        //         grid.nextIteration();
        //         System.out.println(grid);
        //     }catch (Exception e){
        //         System.out.println(e);
        //     }
        // }
    }
}