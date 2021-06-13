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
import javafx.scene.image.Image;
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
        stage.setTitle("Game of Life");
        stage.show();
        Image applicationIcon = new Image(getClass().getResourceAsStream("/logo.png"));
        stage.getIcons().add(applicationIcon);
    }

    public static void main(String[] args) {
        launch();
    }
}