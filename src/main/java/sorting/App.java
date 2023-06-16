package sorting;

import javafx.stage.Stage;
import javafx.application.Application;
import java.util.Random;

/* 
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
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
import javafx.util.Duration;
import javafx.geometry.Pos;
import java.io.*;
import java.util.*;
*/

/**
 * JavaFX App
 */
public class App extends Application {
    // instance variables

    public static void main(String[] args) {
        launch();
    }

    // public void init() ?

    @Override
    public void start(Stage stage) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = arr.length;
        randomize (arr, n);
    }

    // public void updatescreen() ?

    

    /* Fisher-Yates Shuffle provided by geeksforgeeks.org
     * You can find this algorithm here: 
     * https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
    */
    private static void randomize(int arr[], int n)
    {
        Random r = new Random();

        for (int i = n-1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = r.nextInt(i+1);
             
            // Swap arr[i] with the element at random index
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    } 
}
