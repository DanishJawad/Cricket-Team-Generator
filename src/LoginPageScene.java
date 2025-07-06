package org.example.primeselectionsystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * LoginPageScene handles the login and account creation interface for the app.
 * Provides form fields for team name and password, and buttons to:
 * - Log in to an existing team account
 * - Create a new team account
 * - Exit the application
 *
 * On successful login, redirects to the main scene.
 */

public class LoginPageScene {

    /**
     * Builds the login page UI with team name and password fields.
     * Includes:
     * - Login button that validates credentials and loads the main scene
     * - Create account button that switches to an account creation view
     * - Exit button to close the application
     *
     * Account creation includes input validation and success/failure messages.
     *
     * @param stage The current JavaFX stage
     * @return A Scene object containing the login interface
     */

    public static Scene loginPageScene(Stage stage) {


        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setHgap(10);
        layout.setVgap(10);

        Text welcomeText = new Text("Prime Selection System");
        welcomeText.setFont(new Font("verdana", 30));
        welcomeText.setFill(Color.BLANCHEDALMOND);

        Image backgroundImage_1 = new Image("stadium2.jpg");
        BackgroundImage backgroundImageView_1 = new BackgroundImage(backgroundImage_1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, true, true, true, true));

        Background bg = new Background(backgroundImageView_1);
        Image image = new Image("background5.png");
        ImageView imageView1 = new ImageView(image);

        imageView1.setFitHeight(300);
        imageView1.setFitWidth(300);

        layout.setBackground(bg);

        Label userNameLabel = new Label("Team Name");
        userNameLabel.setFont(new Font(15));
        userNameLabel.setTextFill(Color.BLANCHEDALMOND);
        TextField userNameField = new TextField();
        layout.add(userNameLabel, 0, 1);
        layout.add(userNameField, 1, 1);

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(new Font(15));
        passwordLabel.setTextFill(Color.BLANCHEDALMOND);

        PasswordField passwordField = new PasswordField();
        layout.add(passwordLabel, 0, 2);
        layout.add(passwordField, 1, 2);

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setStyle("-fx-color: darkblue;");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-color: darkgreen;");

        Button cancelButton = new Button("Exit APP");
        cancelButton.setStyle("-fx-color: red;");
        cancelButton.setTextFill(Color.WHITE);

        Label accountCreatedLabel = new Label("");
        accountCreatedLabel.setTextFill(Color.BLANCHEDALMOND);
        accountCreatedLabel.setFont(new Font("verdana",15));
        layout.add(accountCreatedLabel, 0, 4, 3, 1);

        HBox hBox2 = new HBox();
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.TOP_CENTER);
        hBox2.getChildren().addAll(imageView1);
        layout.add(hBox2, 0, 0, 3, 1);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        hBox.getChildren().addAll(cancelButton, createAccountButton,  loginButton);
        layout.add(hBox, 1, 3);

        layout.setAlignment(Pos.TOP_CENTER);

        cancelButton.setOnAction(actionEvent -> stage.hide());

        createAccountButton.setOnAction(actionEvent -> {
            userNameLabel.setText("Set Team Name");
            passwordLabel.setText("Set Password");
            accountCreatedLabel.setText("");

            hBox.getChildren().removeAll(createAccountButton, cancelButton, loginButton);

            Button addAccount = new Button("Add New Account");
            Button cancel = new Button("Cancel");
            cancel.setTextFill(Color.WHITE);
            cancel.setStyle("-fx-color: red;");
            hBox.getChildren().addAll(cancel, addAccount);

            addAccount.setOnAction(actionEvent1 -> {
                if (userNameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                    accountCreatedLabel.setTextFill(Color.WHITE);
                    accountCreatedLabel.setFont(new Font(15));
                    accountCreatedLabel.setText("Invalid Username or Password");
                } else {
                    TeamProfile.addAccount(userNameField.getText(),passwordField.getText());
                    userNameField.setText("");
                    passwordField.setText("");
                    hBox.getChildren().remove(addAccount);
                    hBox.getChildren().addAll(createAccountButton, loginButton);
                    userNameLabel.setText("User Name");
                    passwordLabel.setText("Password");
                    accountCreatedLabel.setTextFill(Color.WHITE);
                    accountCreatedLabel.setFont(new Font(15));
                    accountCreatedLabel.setText("Account Created Successfully! Please Login");
                }
            });

            cancel.setOnAction(actionEvent12 -> {

                stage.setFullScreen(false);
                stage.setScene(loginPageScene(stage));
                stage.setFullScreen(true);
            });


        });

        loginButton.setOnAction(actionEvent -> {

            Boolean bool = TeamProfile.loginAccount(userNameField.getText(), passwordField.getText());

            if (bool) {


                stage.setFullScreen(false);

                Scene mainScene = MainScene.mainScene(stage, new TeamProfile(userNameField.getText(), passwordField.getText()));

                    stage.setScene(mainScene);

                    stage.setFullScreen(true);




            } else {
                userNameField.setText("");
                passwordField.setText("");
                accountCreatedLabel.setText("Login Failed! Please Try Again");
            }


        });




        return new Scene(layout, 500, 400);
    }






}


