package sorting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import java.io.*;
import java.util.*;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;
import javafx.geometry.Pos;

import java.util.Random;


/**
 * JavaFX App
 */
public class App extends Application {
    private ObservableList<String> algorithmList;
    private ComboBox<String> algListBox;
    private Label generationLabel = new Label();
    private Button runPauseButton;
    private Button selAlgButton;
    private Button insAlgButton;
    private Button mergeAlgButton;
    private Button bogoAlgButton;
    private Button stepButton;
    private Button resetCountButton;
    private Label stepTimeCaption;
    private Algorithms alg;
    private boolean running;
    private Canvas canvas;

    private Timeline timeline;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
        algorithmList.addAll("Selection Sort", "Insertion Sort", "Merge Sort", "Bogo Sort");
        algListBox = new ComboBox<>(algorithmList);
        algListBox.setValue(algorithmList.get(0));

        runPauseButton = new Button("Run");
        stepButton = new Button("Step");
        resetCountButton = new Button("Reset Gen Count");        
        selAlgButton = new Button("Selection Sort");
        insAlgButton = new Button("Insertion Sort");
        mergeAlgButton = new Button("Merge Sort");
        bogoAlgButton = new Button("Bogo Sort");
        
        stepTimeCaption = new Label();        
        running = false;
    }

    @Override
    public void start(Stage stage) {
        BorderPane bPane = new BorderPane();
        canvas = new Canvas(500, 500);

        bPane.setTop(generationLabel);

        HBox userBox = new HBox(8);
        userBox.getChildren().addAll(algListBox, runPauseButton, stepButton, resetCountButton);
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



        runPauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                running = !running;
                runPauseButton.setText(running ? "Pause" : "Run");
            }
        });

        resetCountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                alg.resetGeneration();
                generationLabel.setText(algListBox.getValue() + "; Generation: " + alg.getGeneration());
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
        double cw = w / alg.MAX_COLUMNS;
        double ch = h / alg.MAX_ROWS;

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, w, h);

        gc.setFill(Color.BLACK);

        for (int i = 0; i < alg.MAX_ROWS; i++) {
            for (int j = 0; j < alg.MAX_COLUMNS; j++) {
                if (alg.isFilled(i, j)) {
                    double y = ch * i;
                    double x = ch * j;
                    gc.fillRect(x, y, cw, ch);
                }
            }
        }

        // TODO: Update a 2D array in every single alg method
    }

}
