package other.javaFX;
/**
 * Created by dtdyq on 2017/3/9.
 */

import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class javaFXButton extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("hello world");
        Group root=new Group();
        Scene sc=new Scene(root,300,250);
        Button bt= new Button();
        bt.setText("hello");
        bt.setLayoutX(100);
        bt.setLayoutY(100);
        //Ìí¼ÓÊÂ¼þ£º
        bt.setOnAction(actionEvent -> {
            System.out.println("hello,world!");
        });
        bt.setBackground(Background.EMPTY);
        root.getChildren().add(bt);

        primaryStage.setScene(sc);

        primaryStage.show();
    }
}

