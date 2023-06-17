package sorting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.*;


/**
 * JavaFX App
 */
public class Visualizer extends Application {
    private Label generationLabel = new Label();
    //private Button runPauseButton;
    private Button selAlgButton;
    private Button insAlgButton;
    private Button mergeAlgButton;
    private Button bogoAlgButton;
    private Button stepButton;
    private Button resetCountButton;
    private Button generateSeedButton;
    private Label stepTimeCaption;
    private Algorithms alg;
    private boolean running;
    private Canvas canvas;
    private SortingAlgorithm currentAlgorithm;


    private Timeline timeline;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();

        stepButton = new Button("Step");
        resetCountButton = new Button("Reset Gen Count");
        generateSeedButton = new Button("Generate Random Values");
        selAlgButton = createSortingButton("Selection Sort", SortingAlgorithm.SELECTION_SORT);
        insAlgButton = createSortingButton("Insertion Sort", SortingAlgorithm.INSERTION_SORT);
        mergeAlgButton = createSortingButton("Merge Sort", SortingAlgorithm.MERGE_SORT);
        bogoAlgButton = createSortingButton("Bogo Sort", SortingAlgorithm.BOGO_SORT);
        alg = new Algorithms();
        
        stepTimeCaption = new Label();        
        running = false;
    }

    @Override
    public void start(Stage stage) {
        BorderPane bPane = new BorderPane();
        canvas = new Canvas(500, 500);

        bPane.setTop(generationLabel);

        HBox userBox = new HBox(8);
        userBox.getChildren().addAll(generateSeedButton, stepButton, resetCountButton);
        HBox timeBox = new HBox(8);
        timeBox.getChildren().addAll(stepTimeCaption);
        HBox algBox = new HBox(8);
        algBox.getChildren().addAll(selAlgButton, insAlgButton, mergeAlgButton, bogoAlgButton);

        VBox bottomBox = new VBox(8);
        bottomBox.getChildren().addAll(userBox);
        bPane.setBottom(bottomBox); 
        VBox topBox = new VBox(8);
        topBox.getChildren().addAll(timeBox, algBox);
        bPane.setTop(topBox);

        bPane.setCenter(canvas);


        resetCountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                alg.resetGeneration();
                generationLabel.setText(currentAlgorithm + "; Generation: " + alg.getGeneration());
            }
        });



        // TODO: Get the time thing to work
        // stepTimeCaption.textProperty().bind(
        //     Bindings.format("%.3f sec", stepTimeSlider.valueProperty())
        // );
        
        // timeline = new Timeline(
        //     new KeyFrame(Duration.seconds(stepTimeSlider.getValue()), e -> {
        //         if(!running)
        //             return;
        //         try{
        //             life.passTime();
        //             updateBoard();
        //         } catch (Exception ex){
        //             running = false;
        //             throw ex;
        //         }
        //     }));
        // timeline.setAutoReverse(true);
        // timeline.setCycleCount(Timeline.INDEFINITE);

        /* whats good future me its past you. the whole stepTimeSlider.value()
         * stuff is pretty weird but maybe it is just the regular time value 
         * multiplied by whatever the slider is. try to experiment around with 
         * just putting in the number 0.5 or 1 or try to implement the slider **/

        stage.setTitle("Algorithms");
        stage.setScene(new Scene(bPane));
        stage.show();

        //timeline.play();

        
    }

    public void updateScreen() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double w = gc.getCanvas().getWidth();
        double h = gc.getCanvas().getHeight();
        double cw = w / Algorithms.MAX_COLUMNS;
        double ch = h / Algorithms.MAX_ROWS;

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, w, h);

        gc.setFill(Color.BLACK);

        for (int i = 0; i < Algorithms.MAX_ROWS; i++) {
            for (int j = 0; j < Algorithms.MAX_COLUMNS; j++) {
                if (alg.isFilled(i, j)) {   
                    double y = ch * i;
                    double x = ch * j;
                    gc.fillRect(x, y, cw, ch);
                }
            }
        }

    }

    private Button createSortingButton(String text, SortingAlgorithm algorithm) {
        Button button = new Button(text);
        button.setOnAction(event -> {
            currentAlgorithm = algorithm;
            sortArray();
        });
        return button;
    }

    private void sortArray() {
        if (currentAlgorithm == null || alg.getArray() == null) {
            return;
        }

        switch (currentAlgorithm) {
            // TODO: REPLACE EVERY alg.sort() with a method in this file that uses it like this:
            //void gridSelSort() {
            //     alg.SelectionSort();
            //     look at ethans update visualization stuff
            // }
            case SELECTION_SORT:
                while (true) {
                    alg.SelectionSort();
                    updateScreen();
                    break;
                }
            case INSERTION_SORT:
                alg.InsertionSort();
                break;
            case MERGE_SORT:
                //mergeSort(0, array.length - 1);
                break;
            case BOGO_SORT:
                alg.BogoSort();
                break;
        }
    }

    private enum SortingAlgorithm {
        SELECTION_SORT,
        INSERTION_SORT,
        MERGE_SORT,
        BOGO_SORT
    }

}
