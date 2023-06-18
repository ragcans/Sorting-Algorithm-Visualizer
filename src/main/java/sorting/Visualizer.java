package sorting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.*;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class Visualizer extends Application {
    private Label generationLabel = new Label();
    private Label stepTimeCaption;
    private Button selAlgButton;
    private Button insAlgButton;
    private Button mergeAlgButton;
    private Button bogoAlgButton;
    private Button resetCountButton;
    private Button generateSeedButton;
    private Algorithms alg;
    private String currentAlgorithm;
    private Rectangle[] rectangles; 
    private int[] arr;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();

        resetCountButton = new Button("Reset Gen Count");
        generateSeedButton = new Button("Randomize");
        selAlgButton = sortButton("Selection Sort");
        insAlgButton = sortButton("Insertion Sort");
        mergeAlgButton = sortButton("Merge Sort");
        bogoAlgButton = sortButton("Bogo Sort");
        
        alg = new Algorithms();
        arr = alg.generateRandomArray(Algorithms.getArrSize(), Algorithms.getArrMin(), Algorithms.getArrMax());
        rectangles = createRectangles(arr);
        
        stepTimeCaption = new Label();        
    }

    @Override
    public void start(Stage stage) { 

        // button containers
        HBox userBox = new HBox(8);
        userBox.getChildren().addAll(generateSeedButton, resetCountButton, generationLabel);
        HBox timeBox = new HBox(8);
        timeBox.getChildren().addAll(stepTimeCaption);
        HBox algBox = new HBox(8);
        algBox.getChildren().addAll(selAlgButton, insAlgButton, mergeAlgButton, bogoAlgButton);

        // rectangles
        Pane visualizationPane = new Pane(rectangles);
        visualizationPane.setMinHeight(Algorithms.getArrMax());
        visualizationPane.setMaxHeight(Algorithms.getArrMax());
        visualizationPane.setMinWidth(Algorithms.getArrSize() * 10);
        visualizationPane.setMaxWidth(Algorithms.getArrSize() * 10);

        VBox root = new VBox(8);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(userBox, timeBox, visualizationPane, algBox);  

        resetCountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                alg.resetGeneration();
                generationLabel.setText(currentAlgorithm + "; Generation: " + alg.getGeneration());
            }
        });

        generateSeedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                arr = alg.generateRandomArray(Algorithms.getArrSize(), Algorithms.getArrMin(), Algorithms.getArrMax());
                updateScreen();
            }
        });

        stage.setTitle("Algorithms");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void updateScreen() {
        for (int i = 0; i < arr.length; i++) {
            rectangles[i].setHeight(arr[i]);
            rectangles[i].setY(Algorithms.getArrMax() - arr[i]);
        }  
        
    }

    private Button sortButton(String text) {
        Button button = new Button(text);
        button.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent e) { 
                while (!isSorted()) { 
                    currentAlgorithm = text;
                    sortArray();
                    updateScreen();
                } 
            }
        });
        return button;
    }

    private Rectangle[] createRectangles(int[] array) {
        Rectangle[] rectangles = new Rectangle[array.length];
        for (int i = 0; i < array.length; i++) {
            rectangles[i] = new Rectangle(10, array[i], Color.BLUE);
            rectangles[i].setX(i * 10);
            rectangles[i].setY(Algorithms.getArrMax() - array[i]);
        }
        return rectangles;
    }

    private void sortArray() { 
        switch (currentAlgorithm) {
            case "Selection Sort":
                alg.SelectionSort(arr);
                updateScreen();
                delay();
                break;
            case "Insertion Sort":
                alg.InsertionSort(arr);
                updateScreen();
                break;
            case "Merge Sort":
                //mergeSort(0, array.length - 1);
                break;
            case "Bogo Sort":
                alg.BogoSort(arr);
                updateScreen();
                break;
        }
    }

     private boolean isSorted() {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void delay() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
