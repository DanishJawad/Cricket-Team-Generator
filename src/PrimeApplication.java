package org.example.primeselectionsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PrimeApplication extends Application{

    @Override
    public void start(Stage stage) {

        stage.setTitle("Prime Selection System");
        Image icon = new Image("icon.jpg");
        ImageView imageView = new ImageView(icon);


        Scene loginPageScene = LoginPageScene.loginPageScene(stage);
        stage.setScene(loginPageScene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.getIcons().add(imageView.getImage());
        //stage.setResizable(true);
        stage.show();




    }

    public static void main(String[] args) {
        launch();
    }

}