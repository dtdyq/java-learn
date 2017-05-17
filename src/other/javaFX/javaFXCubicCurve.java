package other.javaFX;/**
 * Created by dtdyq on 2017/3/10.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class javaFXCubicCurve extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("deprecation")
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("object color");
        Group root=new Group();
        Scene sc=new Scene(root,300,300, Color.WHITE);

        Ellipse ell=new Ellipse(100,50+70/2,50,70/2);
        RadialGradient rg= RadialGradientBuilder.create()
                .focusAngle(0).focusDistance(0.1).centerX(80)
                .centerY(45).radius(120).proportional(false).cycleMethod(CycleMethod.NO_CYCLE)
                .stops(new Stop(0,Color.RED),new Stop(1,Color.BLACK))
                .build();

        ell.setFill(rg);
        root.getChildren().add(ell);

        Rectangle rec= RectangleBuilder.create().x(150).y(150)
                .width(100).height(70).build();
        rec.setFill(Color.RED);
        root.getChildren().add(rec);

        primaryStage.setScene(sc);
        primaryStage.show();
    }
}
