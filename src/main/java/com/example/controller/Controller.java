package com.example.controller;

import com.example.model.Grid;
import com.example.model.Options;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

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
    @FXML private Label iterationButton;

    Timeline timeline;

    private void restartGrid(int seed) {
        iterationButton.setText("Iteration: 0");
        Grid grid = new Grid(Options.getSize(), Options.getSize());
        Random random = new Random(seed);
        grid.randomGeneration(random);
        Options.setGrid(grid);
        setCells(grid);
    }

    private void setCells(Grid grid) {
        boolean[][] cells = grid.getCells();
        for (int i = 0; i < Options.getSize(); i++) {
            for (int j = 0; j < Options.getSize(); j++) {
                Pane pane = new Pane();
                pane.setPrefSize(100, 100); // Sets size of each cell. https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Pane.html
                int iValue = i; // This wouldn't work otherwise, no clue.
                int jValue = j;
                EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
                    @Override 
                    public void handle(MouseEvent e) { 
                       Options.getGrid().toggleCellStatus(iValue, jValue);
                       setCells(grid);
                    } 
                 };
                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

                gridPane.add(pane, i, j);
                if (cells[i][j] == true) {
                    pane.setStyle("-fx-background-color: #FFA500");
                } else {
                    pane.setStyle("-fx-background-color: #000000");
                }
            }
        }
    }

    private void advanceGame() {
        gridPane.getChildren().clear();
        Grid grid = Options.getGrid();
        grid.nextIteration();
        iterationButton.setText("Iteration: " + grid.getIteration());
        setCells(grid);
        Options.setGrid(grid);
    }

    private int getSeed() {
        if (seedField.getText().trim().isEmpty()) {
            return Options.getDefaultSeed();
        } else {
            int seed = Integer.parseInt(seedField.getText().trim());
            return seed;  
        }
    }

    @FXML
    private void initialize() { // At the start shows a random canvas layout.
        restartGrid(getSeed());
        sizeOptions.getItems().addAll("Small", "Medium", "Large");
        sizeOptions.setValue("Medium");
        timeline = new Timeline(new KeyFrame(Duration.millis(Options.getTickPeriod()), e -> advanceGame()));
    }

    @FXML
    private void onPlayPauseButton() {
        setCells(Options.getGrid());
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        if (!playPauseButton.isSelected()) {
            timeline.stop();
        }
    }

    @FXML
    private void onSlow() {
        timeline.setRate(1);
    }

    @FXML
    private void onMedium() {
        timeline.setRate(2);
    }

    @FXML
    private void onFast() { ;
        timeline.setRate(4);
    }

    @FXML
    private void onSizeOption() {
        playPauseButton.setSelected(false); // Makes sure that game is not running anymore by deselecting the play button.
        timeline.stop();
        gridPane.getChildren().clear();
        if (sizeOptions.getValue().equals("Small")) {
            Options.setSize(25);
            restartGrid(getSeed()); // Stops the grid and generates a new one according to the size.
        } else if (sizeOptions.getValue().equals("Medium")) {
            Options.setSize(50);
            restartGrid(getSeed());
        } else if (sizeOptions.getValue().equals("Large")) {
            Options.setSize(75);
            restartGrid(getSeed());
        }
    }

    @FXML
    private void onGenerateButton() {
        playPauseButton.setSelected(false); 
        timeline.stop();
        iterationButton.setText("Iteration: 0");
        Grid grid = new Grid(Options.getSize(), Options.getSize());
        Random random = new Random(getSeed());
        grid.randomGeneration(random);
        Options.setGrid(grid);
        setCells(grid);
    }

    @FXML
    private void onClearButton() {
        Options.setGrid(new Grid(Options.getSize(), Options.getSize()));
        playPauseButton.setSelected(false);
        timeline.stop();
        iterationButton.setText("Iteration: 0");
        setCells(Options.getGrid());
    }

    @FXML
    private void onImportButton(ActionEvent event) throws Exception {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load file");
        File file = chooser.showOpenDialog(new Stage());
        if (file == null) {
            return;
        }
        Options.getGrid().loadJson(file.getAbsolutePath());
        gridPane.getChildren().clear();
        Options.setSize(Options.getGrid().getColumns());
        setCells(Options.getGrid());
    }
    
    @FXML
    private void onExportButton() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save file");
        chooser.setInitialFileName("Amogus");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON File", "*.json"));
        File file = chooser.showSaveDialog(new Stage());
        if (file == null) {
            return;
        }
        Options.getGrid().saveAsJson(file.getAbsolutePath());
        System.out.println(file.getAbsolutePath());
    }

}
