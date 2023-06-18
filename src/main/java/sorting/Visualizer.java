package sorting;
 
import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage; 


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
    private Button selStepButton;
    private Button insStepButton;
    private Button mergeStepButton;
    private Button bogoStepButton; 
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
 
        generateSeedButton = new Button("Randomize");
        selAlgButton = sortButton("Selection Sort");
        insAlgButton = sortButton("Insertion Sort");
        mergeAlgButton = sortButton("Merge Sort");
        bogoAlgButton = sortButton("Bogo Sort");
        selStepButton = stepButton("Selection Step");
        insStepButton = stepButton("Insertion Step");
        mergeStepButton = stepButton("Merge Step");
        bogoStepButton = stepButton("Bogo Step");

        generateSeedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                alg.generation = 0;
                generationLabel.setText("Generation: " + alg.generation);
                arr = alg.generateRandomArray(Algorithms.getArrSize(), Algorithms.getArrMin(), Algorithms.getArrMax());
                updateScreen();
            }
        });

        
        alg = new Algorithms();
        arr = alg.generateRandomArray(Algorithms.getArrSize(), Algorithms.getArrMin(), Algorithms.getArrMax());
        rectangles = createRectangles(arr);
        
        stepTimeCaption = new Label();
    }

    @Override
    public void start(Stage stage) { 

        // button containers
        HBox userBox = new HBox(8);
        userBox.getChildren().addAll(generateSeedButton, generationLabel);
        HBox timeBox = new HBox(8);
        timeBox.getChildren().addAll(stepTimeCaption);
        HBox algBox = new HBox(8);
        algBox.getChildren().addAll(selAlgButton, insAlgButton, mergeAlgButton, bogoAlgButton);
        HBox stepBox = new HBox(8);
        stepBox.getChildren().addAll(selStepButton, insStepButton, mergeStepButton, bogoStepButton);

        // rectangles
        Pane visualizationPane = new Pane(rectangles);
        visualizationPane.setMinHeight(Algorithms.getArrMax());
        visualizationPane.setMaxHeight(Algorithms.getArrMax());
        visualizationPane.setMinWidth(Algorithms.getArrSize() * 10);
        visualizationPane.setMaxWidth(Algorithms.getArrSize() * 10);

        VBox root = new VBox(8);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(userBox, timeBox, visualizationPane, algBox, stepBox);  

        stage.setTitle("Algorithms");
        stage.setScene(new Scene(root));
        stage.show();
        generationLabel.setText("Generation: " + alg.generation);
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
                alg.generation = 0;
                while (!isSorted()) { 
                    currentAlgorithm = text;
                    generationLabel.setText(currentAlgorithm + "; Generation: " + alg.generation);
                    sortArray();
                    updateScreen();
                } 
            }
        });
        return button;
    }
    
    private Button stepButton(String text) {
        Button button = new Button(text);
        button.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent e) { 
                if (!isSorted()) {

                    currentAlgorithm = text;
                    sortArray();
                    generationLabel.setText(currentAlgorithm + "; Generation: " + alg.generation);
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
                break;
            case "Insertion Sort":
                alg.InsertionSort(arr);
                updateScreen();
                break;
            case "Merge Sort":
                arr = alg.MergeSort(arr);
                updateScreen();
                break;
            case "Bogo Sort":
                alg.BogoSort(arr);
                updateScreen();
                break;
            case "Selection Step":
                alg.SelectionSort(arr);
                updateScreen(); 
                break;
            case "Insertion Step":
                alg.InsertionSort(arr);
                updateScreen();
                break;
            case "Merge Step":
                arr = alg.MergeSort(arr);
                updateScreen();
                break;
            case "Bogo Step":
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
 

}