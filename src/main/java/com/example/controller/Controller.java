package com.example.controller;

import java.io.File;
import java.util.Random;

import com.example.model.Grid;
import com.example.model.Options;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    @FXML private AnchorPane rootPane; // Overall program AnchorPane.
    @FXML private ToggleButton playPauseButton;
    @FXML private RadioButton slow; // Speed settings.
    @FXML private RadioButton medium;
    @FXML private RadioButton fast;
    @FXML private ComboBox<String> sizeOptions; // Integer cause size is being entered, which can only be a number.
    @FXML private TextField seedField; // Seed gets entered.
    @FXML private Button generateButton; // Generates seed from seedField.
    @FXML private Button clearButton; // Clears gridPane.
    @FXML private Button importButton; // Import or export via JSON file the current gridPane.
    @FXML private Button exportButton;
    @FXML private GridPane gridPane; // Actual gridPane where the game is displayed.

    @FXML
    private void initialize() { // At the start shows a blank canvas.
        restartGrid();
        sizeOptions.getItems().addAll("Small", "Medium", "Large");
    }

    private void restartGrid() {
        Grid grid = new Grid(Options.getSize(), Options.getSize());
        Random random = new Random(10); // Just an initialization value.
        grid.randomGeneration(random);
        Options.setGrid(grid);
        setCells(grid);
    }

    private void setCells(Grid grid) {
        boolean[][] cells = grid.getCells();
        for (int i = 0; i < grid.getColumns(); i++) {
            for (int j = 0; j < grid.getRows(); j++) {
                Pane pane = new Pane();
                pane.setPrefSize(10, 10); // Sets size of each cell. https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Pane.html
                gridPane.setFillHeight(pane, cells[i][j]);
                gridPane.setFillWidth(pane, cells[i][j]);
                gridPane.add(pane, i, j);
                if (cells[i][j] == true) {
                    pane.setStyle("-fx-background-color: #800080"); // Purple to see if it works.
                } else {
                    pane.setStyle("-fx-background-color: #000000");
                }
            }
        }
    }

    private void advanceGame() {
        gridPane.getChildren().clear();
        Grid grid = Options.getGrid(); // This was written at almost 4 AM, no judge pls.
        grid.nextIteration();
        setCells(grid);
        Options.setGrid(grid);
    }

    @FXML
    private void onPlayPauseButton() throws InterruptedException {
        setCells(Options.getGrid());
        Grid grid = Options.getGrid();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(Options.getSpeed()), e -> advanceGame())); // TODO: Speed probably needed in miliseconds instead, game feels too slow.
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        if (!playPauseButton.isSelected()) { // TODO: Make proper way to pause game, this does not work.
            timeline.stop();
        }
    }

    @FXML
    private void onSlow() {
        Options.setSpeed(4);
    }

    @FXML
    private void onMedium() {
        Options.setSpeed(2);
    }

    @FXML
    private void onFast() {
        Options.setSpeed(1);
    }

    @FXML
    private void onSizeOption() {
        playPauseButton.setSelected(false); // Makes sure that game is not running anymore by deselecting the play button.
        if (sizeOptions.getValue().equals("Small")) {
            Options.setSize(25);
            restartGrid(); // Stops the grid and generates a new one according to the size.
        } else if (sizeOptions.getValue().equals("Medium")) {
            Options.setSize(50);
            restartGrid();
        } else if (sizeOptions.getValue().equals("Large")) {
            Options.setSize(75);
            restartGrid();
        }
    }

    @FXML
    private void onGenerateButton() {
        Grid grid = Options.getGrid();
        Random random = new Random(69); // TODO: Remove this after testing.
        grid.randomGeneration(random);
        setCells(grid);
    }

    @FXML
    private void onClearButton() {
        // TODO: Make clear button also stop current game of life instance.
        playPauseButton.setSelected(false);
        restartGrid();
        for (int i = 0; i < Options.getSize(); i++) {
            for (int j = 0; j < Options.getSize(); j++) {
                Pane pane = new Pane();
                    pane.setPrefSize(10, 10); // Sets size of each cell. https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Pane.html
                    gridPane.setFillHeight(pane, true);
                    gridPane.setFillWidth(pane, true);
                    gridPane.add(pane, i, j);
                    pane.setStyle("-fx-background-color: #000000"); // Purple to see if it works.
            }
        }
    }

    @FXML
    private void onImportButton(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        System.out.println(file.getAbsolutePath());
        // TODO: Check if correct JSON format.
        // TODO: Actually figure out how to import properly cause this is not working out ;-;
        // Options.setGrid(Options.getGrid().loadJson(file.getAbsolutePath())); // Idk even.
    }
    
    @FXML
    private void onExportButton() { // TODO: Use file explorer here to save as JSON.
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save file");
        File file = chooser.showOpenDialog(new Stage());
        // System.out.println(file.getAbsolutePath());
        Options.getGrid().saveAsJson(file.getAbsolutePath());
    }

}
