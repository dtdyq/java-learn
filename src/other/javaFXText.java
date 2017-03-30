package other;/**
 * Created by dtdyq on 2017/3/10.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class javaFXText extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("hello world");
        Group root=new Group();
        Scene sc=new Scene(root,300,250);
        sc.setFill(Color.BISQUE);

        Text text=new Text(100,100,"javaFX");
        text.setFont(new Font("serif",25));
        //设置阴影效果：
        DropShadow dr=new DropShadow();
        dr.setOffsetX(0.3);
        dr.setOffsetY(0.4);
        dr.setColor(Color.rgb(122,76,78,0.789));
        text.setEffect(dr);
        text.setFill(Color.RED);
        text.setOnMouseClicked(mouseEvent -> {
            System.out.println("mouse click");
        });
        root.getChildren().add(text);

        Text t2=new Text(100,140,"javaCode");
        //设置映射效果：
        Reflection ref=new Reflection();
        ref.setFraction(0.45);
        t2.setEffect(ref);
        t2.setFont(new Font("ComingCode",30));
        root.getChildren().add(t2);

        primaryStage.setScene(sc);
        primaryStage.show();

    }
}
