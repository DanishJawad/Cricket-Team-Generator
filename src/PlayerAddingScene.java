package org.example.primeselectionsystem;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Spinner;
import java.io.IOException;

/**
 * PlayerAddingScene provides UI forms to input details for new players.
 * Supports both:
 * - Batsman (Top-order and Middle-order)
 * - Bowler (Pacers and Spinners)
 *
 * Fields include stats like matches, runs, averages, economy, wickets, etc.
 * On successful input, players are added to the team profile and saved to file.
 */

public class PlayerAddingScene {

    /**
     * Creates a form to add a top/middle-order batsman to the team.
     */
    public static Scene batsmanAddingScene(Stage stage, String category, TeamProfile t) {


        BorderPane borderPane = new BorderPane();

        borderPane.setPadding(new Insets(20, 20, 20, 20));

        VBox vBox = new VBox();
        Text text = new Text(category);
        text.setFont(Font.font("verdana", FontWeight.BOLD , 40));
        text.setFill(Color.LIGHTGREEN);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(text);

        borderPane.setTop(vBox);

        Image image = new Image("darkgreen.jpeg");

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
        Background background = new Background(backgroundImage);
        borderPane.setBackground(background);

        ImageView imageView1 = new ImageView(new Image("Batsman.png"));
        imageView1.setFitHeight(300);
        imageView1.setFitWidth(300);
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(imageView1);
        hBox1.setAlignment(Pos.CENTER);


        ImageView imageView2 = new ImageView(new Image("Batsman.png"));
        imageView2.setFitHeight(300);
        imageView2.setFitWidth(300);

        HBox hBox2 = new HBox();
        hBox2.getChildren().add(imageView2);
        hBox2.setAlignment(Pos.CENTER);


        borderPane.setLeft(hBox1);
        borderPane.setRight(hBox2);

        GridPane batsmanDetailsBox = new GridPane();

        Text id = new Text("Player ID (INT)");
        TextField idField = new TextField();
        id.setFill(Color.LIGHTBLUE);
        id.setFont(new Font(30));

        Text name = new Text("Name");
        TextField nameField = new TextField();
        name.setFill(Color.LIGHTBLUE);
        name.setFont(new Font(30));
        nameField.setMinSize(5, 1);


        Text noOfMatches = new Text("No Of Matches");
        Spinner<Integer> matchesField = new Spinner<>(1, 3000, 20);
        matchesField.setPrefWidth(70);
        noOfMatches.setFill(Color.LIGHTBLUE);
        noOfMatches.setFont(new Font(30));

        Text runs = new Text("Total Runs");
        TextField runsField = new TextField();
        runs.setFill(Color.LIGHTBLUE);
        runs.setFont(new Font(30));

        Text average = new Text("Average");
        TextField averageField = new TextField();
        average.setFill(Color.LIGHTBLUE);
        average.setFont(new Font(30));

        Text strikeRate = new Text("Strike Rate");
        TextField strikeRateField = new TextField();
        strikeRate.setFill(Color.LIGHTBLUE);
        strikeRate.setFont(new Font(30));

        Text noOfFifties = new Text("No Of Fifties");
        Spinner<Integer> fiftiesField = new Spinner<>(0, 200, 0);
        fiftiesField.setPrefWidth(70);
        noOfFifties.setFont(new Font(30));
        noOfFifties.setFill(Color.LIGHTBLUE);

        Text noOfCenturies = new Text("No Of Centuries");
        Spinner<Integer> centuriesField = new Spinner<>(0, 100, 0);
        centuriesField.setPrefWidth(70);
        noOfCenturies.setFont(new Font("verdana", 30));
        noOfCenturies.setFill(Color.LIGHTBLUE);


        batsmanDetailsBox.setAlignment(Pos.CENTER);

        batsmanDetailsBox.add(id, 0, 0);
        batsmanDetailsBox.add(idField, 1, 0);

        batsmanDetailsBox.add(name, 0, 1);
        batsmanDetailsBox.add(nameField, 1, 1);

        batsmanDetailsBox.add(noOfMatches, 0, 2);
        batsmanDetailsBox.add(matchesField, 1, 2);
        GridPane.setHalignment(matchesField, HPos.CENTER);

        batsmanDetailsBox.add(runs, 0, 3);
        batsmanDetailsBox.add(runsField, 1, 3);
        GridPane.setHalignment(runsField, HPos.CENTER);

        batsmanDetailsBox.add(average, 0, 4);
        batsmanDetailsBox.add(averageField, 1, 4);

        batsmanDetailsBox.add(strikeRate, 0, 5);
        batsmanDetailsBox.add(strikeRateField, 1, 5);

        batsmanDetailsBox.add(noOfFifties, 0, 6);
        batsmanDetailsBox.add(fiftiesField, 1, 6);
        GridPane.setHalignment(fiftiesField, HPos.CENTER);

        batsmanDetailsBox.add(noOfCenturies, 0, 7);
        batsmanDetailsBox.add(centuriesField, 1, 7);
        GridPane.setHalignment(centuriesField, HPos.CENTER);

        Button enterDetails = new Button("Enter");
        enterDetails.setStyle("-fx-background-color: darkgreen");
        enterDetails.setFont(new Font("verdana",20));
        enterDetails.setTextFill(Color.WHITE);
        Button cancelbutton = new Button("Cancel");
        cancelbutton.setStyle("-fx-background-color: red");
        cancelbutton.setFont(new Font("verdana",20));
        cancelbutton.setTextFill(Color.WHITE);

        Text incompleteDetails = new Text("");
        incompleteDetails.setFont(new Font("verdana", 15));
        incompleteDetails.setFill(Color.RED);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(cancelbutton, enterDetails);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        batsmanDetailsBox.add(hBox, 1, 8);
        batsmanDetailsBox.add(incompleteDetails, 1, 9, 2, 1);

        cancelbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setFullScreen(false);
                stage.setScene(MainScene.mainScene(stage, t));
                stage.setFullScreen(true);
            }
        });

        enterDetails.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (nameField.getText().isEmpty() && averageField.getText().isEmpty() && strikeRateField.getText().isEmpty()) {
                    incompleteDetails.setText("Please Fill All Details");
                } else {
                    try {
                        incompleteDetails.setText("");
                        int id = Integer.parseInt(idField.getText());
                        String name = nameField.getText();
                        int matches = matchesField.getValue();
                        int runs = Integer.parseInt(runsField.getText());
                        double average = Double.parseDouble(averageField.getText());
                        double strikeRate = Double.parseDouble(strikeRateField.getText());
                        int fifties = fiftiesField.getValue();
                        int centuries = centuriesField.getValue();

                        if (category.equals("TOP ORDER BATSMAN")) {
                            try {
                                TeamProfile.addBatsman(t, new TopOrderBatsman(id, name, new BattingStats(matches, runs, average, strikeRate, fifties, centuries)));
                                TeamProfile.rankPlayers(t, t.getTeamName() + ".TopOrder.txt");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (category.equals("MIDDLE ORDER BATSMAN")) {
                            try {
                                TeamProfile.addBatsman(t, new MiddleOrderBatsman(id, name, new BattingStats(matches, runs, average, strikeRate, fifties, centuries)));
                                TeamProfile.rankPlayers(t, t.getTeamName() + ".MiddleOrder.txt");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        stage.setFullScreen(false);
                        stage.setScene(MainScene.mainScene(stage, t));
                        stage.setFullScreen(true);
                    } catch (Exception e) {
                        incompleteDetails.setText("Invalid Details");
                    }
                }
            }
        });


        batsmanDetailsBox.setHgap(10);
        batsmanDetailsBox.setVgap(10);

        batsmanDetailsBox.setAlignment(Pos.CENTER);

        borderPane.setCenter(batsmanDetailsBox);


        return new Scene(borderPane, Color.BLACK);


    }

    /**
     * Creates a form to add a pacer or spinner to the team.
     */
    public static Scene bowlerAddingScene(Stage stage, String category, TeamProfile t) {

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        VBox vBox = new VBox();
        Text text = new Text(category);
        text.setFont(Font.font("verdana", FontWeight.BOLD , 40));
        text.setFill(Color.LIGHTGREEN);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(text);

        borderPane.setTop(vBox);

        Image image = new Image("darkgreen.jpeg");
        ImageView imageView = new ImageView(image);

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
        Background background = new Background(backgroundImage);
        borderPane.setBackground(background);

        ImageView imageView1 = new ImageView(new Image("Bowler.png"));
        imageView1.setFitHeight(300);
        imageView1.setFitWidth(300);
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(imageView1);
        hBox1.setAlignment(Pos.CENTER);


        ImageView imageView2 = new ImageView(new Image("Bowler.png"));
        imageView2.setFitHeight(300);
        imageView2.setFitWidth(300);

        HBox hBox2 = new HBox();
        hBox2.getChildren().add(imageView2);
        hBox2.setAlignment(Pos.CENTER);


        borderPane.setLeft(hBox1);
        borderPane.setRight(hBox2);

        GridPane bowlerDetailsbox = new GridPane();

        Text id = new Text("Player ID (INT)");
        TextField idField = new TextField();
        id.setFill(Color.LIGHTBLUE);
        id.setFont(new Font(30));


        Text name = new Text("Name");
        TextField nameField = new TextField();
        name.setFill(Color.LIGHTBLUE);
        name.setFont(new Font(30));
        nameField.setMinSize(5, 1);


        Text noOfMatches = new Text("No Of Matches");
        Spinner<Integer> matchesField = new Spinner<>(1, 3000, 20);
        matchesField.setPrefWidth(70);
        noOfMatches.setFill(Color.LIGHTBLUE);
        noOfMatches.setFont(new Font(30));

        Text economy = new Text("Economy");
        TextField economyField = new TextField();
        economy.setFill(Color.LIGHTBLUE);
        economy.setFont(new Font(30));

        Text noOfWickets = new Text("No Of Wickets");
        Spinner<Integer> wicketsField = new Spinner<>(1, 1500, 50);
        wicketsField.setPrefWidth(70);
        noOfWickets.setFill(Color.LIGHTBLUE);
        noOfWickets.setFont(new Font(30));

        Text noOfOvers = new Text("Overs Bowled");
        TextField oversField = new TextField();
        noOfOvers.setFill(Color.LIGHTBLUE);
        noOfOvers.setFont(new Font(30));

        bowlerDetailsbox.setAlignment(Pos.CENTER);

        bowlerDetailsbox.add(id, 0, 0);
        bowlerDetailsbox.add(idField, 1, 0);

        bowlerDetailsbox.add(name, 0, 1);
        bowlerDetailsbox.add(nameField, 1, 1);

        bowlerDetailsbox.add(noOfMatches, 0, 2);
        bowlerDetailsbox.add(matchesField, 1, 2);
        GridPane.setHalignment(matchesField, HPos.CENTER);

        bowlerDetailsbox.add(economy, 0, 3);
        bowlerDetailsbox.add(economyField, 1, 3);

        bowlerDetailsbox.add(noOfWickets, 0, 4);
        bowlerDetailsbox.add(wicketsField, 1, 4);
        GridPane.setHalignment(wicketsField, HPos.CENTER);

        bowlerDetailsbox.add(noOfOvers, 0, 5);
        bowlerDetailsbox.add(oversField, 1, 5);

        Button enterDetails = new Button("Enter");
        enterDetails.setStyle("-fx-background-color: darkgreen");
        enterDetails.setFont(new Font("verdana",20));
        enterDetails.setTextFill(Color.WHITE);
        Button cancelbutton = new Button("Cancel");
        cancelbutton.setStyle("-fx-background-color: red");
        cancelbutton.setFont(new Font("verdana",20));
        cancelbutton.setTextFill(Color.WHITE);

        Text incompleteDetails = new Text("");
        incompleteDetails.setFont(new Font("verdana", 15));
        incompleteDetails.setFill(Color.RED);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(cancelbutton, enterDetails);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        bowlerDetailsbox.add(hBox, 1, 6);
        bowlerDetailsbox.add(incompleteDetails, 1, 7, 2, 1);


        cancelbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setFullScreen(false);
                stage.setScene(MainScene.mainScene(stage, t));
                stage.setFullScreen(true);
            }
        });

        enterDetails.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (nameField.getText().isEmpty() && economyField.getText().isEmpty() && oversField.getText().isEmpty()) {
                    incompleteDetails.setText("Please Fill All Details");
                } else {
                    try {
                        incompleteDetails.setText("");
                        int id = Integer.parseInt(idField.getText());
                        String name = nameField.getText();
                        int matches = matchesField.getValue();
                        double economy = Double.parseDouble(economyField.getText());
                        int wickets = wicketsField.getValue();
                        double overs = Double.parseDouble(oversField.getText());
                        if (category.equals("FAST BOWLER")) {
                            try {
                                TeamProfile.addBowler(t, new Pacer(id, name, new BowlerStats(matches, economy, wickets, overs)));
                                TeamProfile.rankPlayers(t, t.getTeamName() + ".Pacers.txt");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (category.equals("SPIN BOWLER")) {
                            try {
                                TeamProfile.addBowler(t, new SpinBowler(id, name, new BowlerStats(matches, economy, wickets, overs)));
                                TeamProfile.rankPlayers(t, t.getTeamName() + ".Spinners.txt");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        stage.setFullScreen(false);
                        stage.setScene(MainScene.mainScene(stage, t));
                        stage.setFullScreen(true);
                    } catch (Exception e) {
                        incompleteDetails.setText("Invalid Details");
                    }
                }
            }
        });


        bowlerDetailsbox.setHgap(10);
        bowlerDetailsbox.setVgap(10);

        bowlerDetailsbox.setAlignment(Pos.CENTER);

        borderPane.setCenter(bowlerDetailsbox);

        return new Scene(borderPane, Color.BLACK);


    }


}
