package other.javaFX;/**
 * Created by dtdyq on 2017/3/9.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class javaFXCss extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
      //  Scene scene = new Scene();
       // scene.getStylesheets().add("file/other/Style.css");
        ToggleButton tb3 = new ToggleButton("I don't know");
        tb3.setStyle("-fx-base: blue");

        ToggleButton tb4 = new ToggleButton("I don't know");
        tb4.setStyle("-fx-base: red");

        StackPane root = new StackPane();
        root.getChildren().add(tb3);
        StackPane rootr = new StackPane();
        rootr.getChildren().add(tb4);
        root.getChildren().add(rootr);
        Group g = new Group();
        for (int i = 0; i < 5; i++) {
            Rectangle r = new Rectangle();
            r.setY(i * 20);
            r.setWidth(100);
            r.setHeight(10);
            r.setFill(Color.RED);
            g.getChildren().add(r);
        }
        g.getChildren().add(tb3);

        primaryStage.setScene(new Scene(g, 300, 250));
        primaryStage.show();


    }
}
