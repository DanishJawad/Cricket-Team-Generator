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

import java.io.File;
import java.util.ArrayList;

import static org.example.primeselectionsystem.LoginPageScene.*;

/**
 * MainScene.java
 *
 * This class builds the main dashboard interface of the Prime Selection System.
 * It provides buttons to navigate to different functionalities like adding, updating,
 * removing players, generating a team, and logging out.
 */

public class MainScene {

    /**
     * Builds the main menu scene with all core feature buttons.
     * Each button links to a specific functionality within the app, such as:
     * - Adding new players by type
     * - Generating a team from saved players
     * - Updating or removing player records
     * - Viewing all player entries
     * - Logging out of the session
     *
     * The scene uses a BorderPane layout with dynamic content loading.
     *
     * @param stage The primary JavaFX stage for the app
     * @param t The current team profile being modified
     * @return A fully constructed JavaFX Scene object for the main dashboard
     */

    public static Scene mainScene(Stage stage, TeamProfile t) {

        // Title text at the top
        Text optionText = new Text("Choose Desired Option");
        optionText.setFont(Font.font("verdana", FontWeight.BOLD , 30));
        optionText.setFill(Color.YELLOW);

        BorderPane gridPane = new BorderPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        // Background image
        Image backgroundImage_1 = new Image("grassbg.jpg");
        BackgroundImage backgroundImageView_1 = new BackgroundImage(backgroundImage_1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));

        Background background = new Background(backgroundImageView_1);
        gridPane.setBackground(background);

        // Create and style menu buttons
        Button addPlayerButton = new Button("Add New Player");
        addPlayerButton.setStyle("-fx-background-color: white");
        addPlayerButton.setTextFill(Color.BLACK);
        addPlayerButton.setFont(new Font("verdana", 18));


        Button generateTeam = new Button("Generate Prime XI");
        generateTeam.setStyle("-fx-background-color: white");
        generateTeam.setTextFill(Color.BLACK);
        generateTeam.setFont(new Font("verdana", 18));

        Button logout = new Button("Logout");
        logout.setStyle("-fx-background-color: white");
        logout.setTextFill(Color.BLACK);
        logout.setFont(new Font("verdana", 18));

        Button updatePlayer = new Button("Update Player");
        updatePlayer.setStyle("-fx-background-color: white");
        updatePlayer.setTextFill(Color.BLACK);
        updatePlayer.setFont(new Font("verdana", 18));

        Button removePlayer = new Button("Remove a Player");
        removePlayer.setStyle("-fx-background-color: white");
        removePlayer.setTextFill(Color.BLACK);
        removePlayer.setFont(new Font("verdana", 18));

        Button viewAllPlayer = new Button("View All Players");
        viewAllPlayer.setStyle("-fx-background-color: white");
        viewAllPlayer.setTextFill(Color.BLACK);
        viewAllPlayer.setFont(new Font("verdana", 18));


        GridPane gridPane1 = new GridPane();

        // Button Actions
        addPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                gridPane.setLeft(new ImageView(new Image("bg2.png")));
                gridPane.setRight(new ImageView(new Image("bg2.png")));

                Text selectCategory = new Text("PLEASE SELECT CATEGORY");
                selectCategory.setFont(Font.font("verdana", FontWeight.BOLD , 40));
                selectCategory.setFill(Color.LIGHTGREEN);
                HBox hBox = new HBox();
                hBox.getChildren().add(selectCategory);
                hBox.setPrefHeight(50);
                hBox.setAlignment(Pos.CENTER);

                gridPane.setTop(hBox);

                GridPane gridPane3 = addingPLayerLayout(stage, t);
                gridPane.setCenter(gridPane3);


            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                stage.setFullScreen(false);
                stage.setScene(loginPageScene(stage));
                stage.setFullScreen(true);

            }
        });

        generateTeam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gridPane.setLeft(new ImageView(new Image("bg2.png")));
                gridPane.setRight(new ImageView(new Image("bg2.png")));

                Text selectCategory = new Text("SELECT PLAYERS FROM EACH CATEGORY");
                selectCategory.setFont(Font.font("verdana", FontWeight.BOLD , 40));
                selectCategory.setFill(Color.LIGHTGREEN);
                HBox hBox = new HBox();
                hBox.getChildren().add(selectCategory);
                hBox.setPrefHeight(50);
                hBox.setAlignment(Pos.CENTER);

                gridPane.setTop(hBox);

                GridPane gridPane3 = DisplayScene.generatePlayerLayout(stage, t);
                gridPane.setCenter(gridPane3);
            }
        });

        updatePlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                TextField updatePlayerField = new TextField();

                Button findPlayerButton = new Button("Update Player");
                findPlayerButton.setTextFill(Color.DARKBLUE);
                findPlayerButton.setFont(new Font("verdana", 15));

                HBox hBox = new HBox();
                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                hBox.getChildren().add(findPlayerButton);

                Text playerName = new Text("Enter Player Name");
                playerName.setFont(new Font("verdana", 20));
                playerName.setFill(Color.LIGHTBLUE);

                gridPane1.add(playerName, 0, 7, 3, 1);
                gridPane1.add(updatePlayerField, 0, 8, 3, 1);
                updatePlayerField.setPrefWidth(20);
                GridPane.setHalignment(updatePlayerField, HPos.CENTER);
                gridPane1.add(hBox, 0, 9, 3, 1);
                GridPane.setHalignment(hBox, HPos.CENTER);

                findPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String player = updatePlayerField.getText();
                        try {
                            int topOrderIndex = DisplayScene.findPlayer(t.getTeamName() + ".TopOrder.txt", player);
                            if (topOrderIndex != -1) {

                                ArrayList<Batsman> topOrder = TeamProfile.getAllBatsmen(t, ".TopOrder.txt");
                                File file = new File(t.getTeamName() + ".TopOrder.txt");
                                TeamProfile.clearFile(file);
                                TopOrderBatsman temp;
                                temp = (TopOrderBatsman) topOrder.get(topOrderIndex);
                                topOrder.remove(topOrderIndex);

                                for (Batsman tob : topOrder) {
                                    TeamProfile.addBatsman(t, tob);
                                }

                                stage.setFullScreen(false);
                                stage.setScene(PlayerUpdatingScene.batsmanUpdatingScene(stage, t, temp));
                                stage.setFullScreen(true);
                            }

                        } catch (Exception e) {
                        }

                        try {
                            int middleOrderIndex = DisplayScene.findPlayer(t.getTeamName() + ".MiddleOrder.txt", player);
                            if (middleOrderIndex != -1) {

                                ArrayList<Batsman> middleOrder = TeamProfile.getAllBatsmen(t, ".MiddleOrder.txt");
                                File file = new File(t.getTeamName() + ".MiddleOrder.txt");
                                TeamProfile.clearFile(file);
                                MiddleOrderBatsman temp;
                                temp = (MiddleOrderBatsman) middleOrder.get(middleOrderIndex);

                                middleOrder.remove(middleOrderIndex);

                                for (Batsman mob : middleOrder) {
                                    TeamProfile.addBatsman(t, mob);
                                }
                                stage.setFullScreen(false);
                                stage.setScene(PlayerUpdatingScene.batsmanUpdatingScene(stage, t, temp));
                                stage.setFullScreen(true);

                            }

                        } catch (Exception e) {
                        }

                        try {
                            int pacerIndex = DisplayScene.findPlayer(t.getTeamName() + ".Pacers.txt", player);
                            if (pacerIndex != -1) {

                                ArrayList<Bowler> pacers = TeamProfile.getAllBowlers(t, ".Pacers.txt");
                                File file = new File(t.getTeamName() + ".MiddleOrder.txt");
                                TeamProfile.clearFile(file);
                                Pacer temp;
                                temp = (Pacer) pacers.get(pacerIndex);

                                pacers.remove(pacerIndex);

                                for (Bowler b : pacers) {
                                    TeamProfile.addBowler(t, b);
                                }
                                stage.setFullScreen(false);
                                stage.setScene(PlayerUpdatingScene.bowlerUpdatingScene(stage, t, temp));
                                stage.setFullScreen(true);

                            }

                        } catch (Exception e) {
                        }

                        try {
                            int spinnerIndex = DisplayScene.findPlayer(t.getTeamName() + ".Spinners.txt", player);
                            if (spinnerIndex != -1) {

                                ArrayList<Bowler> spinners = TeamProfile.getAllBowlers(t, ".Spinners.txt");
                                File file = new File(t.getTeamName() + ".Spinners.txt");
                                TeamProfile.clearFile(file);
                                SpinBowler temp;
                                temp = (SpinBowler) spinners.get(spinnerIndex);

                                spinners.remove(spinnerIndex);

                                for (Bowler b : spinners) {
                                    TeamProfile.addBowler(t, b);
                                }
                                stage.setFullScreen(false);
                                stage.setScene(PlayerUpdatingScene.bowlerUpdatingScene(stage, t, temp));
                                stage.setFullScreen(true);

                            }

                        } catch (Exception e) {
                        }


                    }

                });


            }
        });

        removePlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                TextField removePlayerField = new TextField();
                Button removePlayerButton = new Button("Remove Player");
                removePlayerButton.setStyle("-fx-background-color: red");
                removePlayerButton.setTextFill(Color.WHITE);
                removePlayerButton.setFont(new Font("verdana", 15));

                HBox hBox = new HBox();
                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                hBox.getChildren().add(removePlayerButton);

                Text playerName = new Text("Enter Player Name");
                playerName.setFont(new Font("verdana", 20));
                playerName.setFill(Color.LIGHTBLUE);

                Text playerRemoved = new Text("");
                playerRemoved.setText("");
                playerRemoved.setFont(new Font("verdana", 20));
                playerRemoved.setFill(Color.RED);

                gridPane1.add(playerName, 0, 7, 3, 1);
                gridPane1.add(removePlayerField, 0, 8, 3, 1);
                removePlayerField.setPrefWidth(20);
                GridPane.setHalignment(removePlayerButton, HPos.CENTER);
                gridPane1.add(hBox, 0, 9, 3, 1);
                GridPane.setHalignment(hBox, HPos.CENTER);
                gridPane1.add(playerRemoved, 0, 10, 3, 1);
                GridPane.setHalignment(hBox, HPos.CENTER);


                removePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String player = removePlayerField.getText();
                        try {

                            int topOrderIndex = DisplayScene.findPlayer(t.getTeamName() + ".TopOrder.txt", player);
                            if (topOrderIndex != -1) {

                                ArrayList<Batsman> topOrder = TeamProfile.getAllBatsmen(t, ".TopOrder.txt");
                                File file = new File(t.getTeamName() + ".TopOrder.txt");
                                TeamProfile.clearFile(file);
                                TopOrderBatsman temp;
                                temp = (TopOrderBatsman) topOrder.get(topOrderIndex);
                                topOrder.remove(topOrderIndex);

                                for (Batsman tob : topOrder) {
                                    TeamProfile.addBatsman(t, tob);
                                }

                                playerRemoved.setText(temp.getName() + " is Removed From Team");


                            }

                        } catch (Exception e) {
                        }

                        try {
                            int middleOrderIndex = DisplayScene.findPlayer(t.getTeamName() + ".MiddleOrder.txt", player);
                            if (middleOrderIndex != -1) {

                                ArrayList<Batsman> middleOrder = TeamProfile.getAllBatsmen(t, ".MiddleOrder.txt");
                                File file = new File(t.getTeamName() + ".MiddleOrder.txt");
                                TeamProfile.clearFile(file);
                                MiddleOrderBatsman temp;
                                temp = (MiddleOrderBatsman) middleOrder.get(middleOrderIndex);

                                middleOrder.remove(middleOrderIndex);

                                for (Batsman mob : middleOrder) {
                                    TeamProfile.addBatsman(t, mob);
                                }

                                playerRemoved.setText(temp.getName() + " is Removed From Team");

                            }

                        } catch (Exception e) {
                        }

                        try {
                            int pacerIndex = DisplayScene.findPlayer(t.getTeamName() + ".Pacers.txt", player);
                            if (pacerIndex != -1) {

                                ArrayList<Bowler> pacers = TeamProfile.getAllBowlers(t, ".Pacers.txt");
                                File file = new File(t.getTeamName() + ".MiddleOrder.txt");
                                TeamProfile.clearFile(file);
                                Pacer temp;
                                temp = (Pacer) pacers.get(pacerIndex);

                                pacers.remove(pacerIndex);

                                for (Bowler b : pacers) {
                                    TeamProfile.addBowler(t, b);
                                }

                                playerRemoved.setText(temp.getName() + " is Removed From Team");


                            }

                        } catch (Exception e) {
                        }

                        try {
                            int spinnerIndex = DisplayScene.findPlayer(t.getTeamName() + ".Spinners.txt", player);
                            if (spinnerIndex != -1) {

                                ArrayList<Bowler> spinners = TeamProfile.getAllBowlers(t, ".Spinners.txt");
                                File file = new File(t.getTeamName() + ".Spinners.txt");
                                TeamProfile.clearFile(file);
                                SpinBowler temp;
                                temp = (SpinBowler) spinners.get(spinnerIndex);

                                spinners.remove(spinnerIndex);

                                for (Bowler b : spinners) {
                                    TeamProfile.addBowler(t, b);
                                }

                                playerRemoved.setText(temp.getName() + " is Removed From Team");


                            }

                        } catch (Exception e) {
                        }


                    }


                });


            }

        });

        viewAllPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setFullScreen(false);
                stage.setScene(DisplayScene.allPlayersDisplayScene(t , stage));
                stage.setFullScreen(true);
            }
        });

        gridPane1.setVgap(20);

        gridPane1.add(optionText, 0, 0, 3, 1);
        GridPane.setHalignment(optionText, HPos.CENTER);
        gridPane1.add(addPlayerButton, 0, 1, 3, 1);
        GridPane.setHalignment(addPlayerButton, HPos.CENTER);
        gridPane1.add(generateTeam, 0, 2, 3, 1);
        GridPane.setHalignment(generateTeam, HPos.CENTER);
        gridPane1.add(updatePlayer, 0, 3, 3, 1);
        GridPane.setHalignment(updatePlayer, HPos.CENTER);
        gridPane1.add(viewAllPlayer, 0, 4, 3, 1);
        GridPane.setHalignment(viewAllPlayer, HPos.CENTER);
        gridPane1.add(removePlayer, 0, 5, 3, 1);
        GridPane.setHalignment(removePlayer , HPos.CENTER);
        gridPane1.add(logout, 0, 6, 3, 1);
        GridPane.setHalignment(logout, HPos.CENTER);

        gridPane1.setAlignment(Pos.CENTER);

        gridPane.setCenter(gridPane1);

        return new Scene(gridPane, Color.BLACK);


    }

    /**
     * Creates the player-type selection screen.
     * Allows the user to choose which type of player to add:
     * - Top-order or middle-order batsman
     * - Pacer or spinner bowler
     *
     * Each option redirects to the relevant data input scene.
     * Includes a cancel button to return to the main menu.
     *
     * @param stage The JavaFX stage being displayed
     * @param t The current team profile instance
     * @return A GridPane layout containing all player-type buttons
     */

    public static GridPane addingPLayerLayout(Stage stage, TeamProfile t) {

        GridPane gridPane = new GridPane();

        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Image image = new Image("bg7.png");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(140);
        imageView.setFitHeight(140);

        HBox hBox = new HBox();
        hBox.getChildren().add(imageView);
        hBox.setAlignment(Pos.CENTER);
        Button addTopOrder = new Button("ADD TOP ORDER BATSMAN");
        addTopOrder.setTextFill(Color.DARKBLUE);
        addTopOrder.setFont(new Font("verdana", 15));

        Button addMiddleOrder = new Button("ADD MIDDLE ORDER BATSMAN");
        addMiddleOrder.setTextFill(Color.DARKBLUE);
        addMiddleOrder.setFont(new Font("verdana", 15));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(addTopOrder, addMiddleOrder);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        gridPane.add(hBox, 0, 0, 2, 1);
        gridPane.add(vBox, 0, 1, 2, 1);

        addTopOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Scene topOrderScene = PlayerAddingScene.batsmanAddingScene(stage, "TOP ORDER BATSMAN", t);
                stage.setFullScreen(false);
                stage.setScene(topOrderScene);
                stage.setFullScreen(true);
            }
        });

        addMiddleOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Scene middleOrderScene = PlayerAddingScene.batsmanAddingScene(stage, "MIDDLE ORDER BATSMAN", t);
                stage.setFullScreen(false);
                stage.setScene(middleOrderScene);
                stage.setFullScreen(true);
            }
        });

        Image bowlerImage = new Image("bg8.png");
        ImageView bowlerImageView = new ImageView(bowlerImage);
        bowlerImageView.setFitWidth(140);
        bowlerImageView.setFitHeight(140);

        HBox hBox1 = new HBox();
        hBox1.getChildren().add(bowlerImageView);
        hBox1.setAlignment(Pos.CENTER);
        gridPane.add(hBox1, 0, 2, 2, 1);

        Button addPacer = new Button("ADD PACER");
        addPacer.setTextFill(Color.DARKBLUE);
        addPacer.setFont(new Font("verdana", 15));

        Button addSpinner = new Button("ADD SPINNER");
        addSpinner.setTextFill(Color.DARKBLUE);
        addSpinner.setFont(new Font("verdana", 15));

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: red");
        cancelButton.setTextFill(Color.WHITE);
        cancelButton.setFont(new Font("verdana", 20));

        addPacer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Scene pacerAddingScene = PlayerAddingScene.bowlerAddingScene(stage, "FAST BOWLER", t);
                stage.setFullScreen(false);
                stage.setScene(pacerAddingScene);
                stage.setFullScreen(true);
            }
        });

        addSpinner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Scene spinnerAddingScene = PlayerAddingScene.bowlerAddingScene(stage, "SPIN BOWLER", t);
                stage.setFullScreen(false);
                stage.setScene(spinnerAddingScene);
                stage.setFullScreen(true);

            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                stage.setFullScreen(false);
                stage.setScene(MainScene.mainScene(stage, t));
                stage.setFullScreen(true);

            }
        });

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(addPacer, addSpinner, cancelButton);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(10);

        gridPane.add(vBox1, 0, 3, 2, 1);


        gridPane.setAlignment(Pos.TOP_CENTER);

        return gridPane;

    }


}
