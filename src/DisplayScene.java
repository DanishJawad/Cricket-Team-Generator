package org.example.primeselectionsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * DisplayScene contains all UI components related to displaying player data.
 * Includes scenes for:
 * - Displaying the final selected XI team
 * - Viewing all saved players in the system
 * - Choosing the number of players for each category to generate the team
 */

public class DisplayScene {

    /**
     * Displays the final generated Prime XI team in a table format.
     * Includes columns for player rank, name, category, and points.
     * Allows user to return to the main menu.
     *
     * @param t The team profile being viewed
     * @param stage The current JavaFX stage
     * @param primeTeam The list of 11 selected players
     * @return A Scene showing the Prime XI
     */
    public static Scene teamDisplayScene(TeamProfile t, Stage stage, ArrayList<Player> primeTeam) {

        //stage.setFullScreen(true);
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        Image backgroundImage_1 = new Image("stadium3.jpg");
        BackgroundImage backgroundImageView_1 = new BackgroundImage(backgroundImage_1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));

        Background background = new Background(backgroundImageView_1);
        borderPane.setBackground(background);

        TableView<Player> playerTableView = new TableView<>();
        ObservableList<Player> playerObservableList = FXCollections.observableArrayList(primeTeam);
        playerTableView.setFixedCellSize(35);
        playerTableView.setPrefHeight(35 * 11 + 30);

        TableColumn<Player, Integer> positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

        TableColumn<Player, String> nameColumn = new TableColumn<>("Player Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Player, Double> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        positionColumn.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        nameColumn.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        categoryColumn.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        pointsColumn.setMaxWidth(1f * Integer.MAX_VALUE * 25);

        playerTableView.setItems(playerObservableList);

        playerTableView.getColumns().addAll(positionColumn, nameColumn, categoryColumn, pointsColumn);

        GridPane gridPane = new GridPane(1000, 1000);
        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.ALWAYS);
        col.setHalignment(HPos.CENTER);
        gridPane.getColumnConstraints().add(col);

        VBox vBox = new VBox(playerTableView);
        VBox.setVgrow(playerTableView, Priority.NEVER);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.setMaxWidth(800);
        vBox.setMaxHeight(500);

        vBox.setStyle("-fx-alignment: center;");

        playerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        playerTableView.setMaxWidth(Double.MAX_VALUE);

        gridPane.add(vBox, 0, 0);
        GridPane.setHgrow(vBox, Priority.ALWAYS);

        gridPane.setAlignment(Pos.CENTER);

        borderPane.setCenter(gridPane);


        HBox hBox = new HBox();
        Label label = new Label("Here's The Prime XI for Team " + t.getTeamName());
        label.setFont(Font.font("verdana", FontWeight.BOLD ,30));
        label.setTextFill(Color.BLACK);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.getChildren().add(label);
        hBox.setPadding(new Insets(40, 0, 0, 0));

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: red");
        backButton.setFont(new Font("verdana",20));
        backButton.setTextFill(Color.WHITE);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setFullScreen(false);
                stage.setScene(MainScene.mainScene(stage , t));
                stage.setFullScreen(true);
            }
        });

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.BOTTOM_CENTER);
        hBox1.getChildren().add(backButton);

        borderPane.setBottom(hBox1);
        borderPane.setTop(hBox);

        Scene scene = new Scene(borderPane, 1000, 600);
        scene.getStylesheets().add(
                DisplayScene.class.getResource("/table-style.css").toExternalForm()
        );
        return scene;


    }

    /**
     * Displays all players stored in the current team profile.
     * Retrieves top-order batsmen, middle-order batsmen, pacers, and spinners from file.
     * Displays them in a unified table view with ID, name, category, and points.
     *
     * @param t The team profile to load players from
     * @param stage The current JavaFX stage
     * @return A Scene showing all players added to the team
     */

    public static Scene allPlayersDisplayScene(TeamProfile t, Stage stage) {

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        Image backgroundImage_1 = new Image("stadium3.jpg");
        BackgroundImage backgroundImageView_1 = new BackgroundImage(backgroundImage_1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));

        Background background = new Background(backgroundImageView_1);
        borderPane.setBackground(background);

        ArrayList<Player> allPlayers = new ArrayList<>();

        try {
            ArrayList<Batsman> topOrder = TeamProfile.getAllBatsmen(t, ".TopOrder.txt");
            ArrayList<Batsman> middleOrder = TeamProfile.getAllBatsmen(t, ".MiddleOrder.txt");
            ArrayList<Bowler> pacers = TeamProfile.getAllBowlers(t, ".Pacers.txt");
            ArrayList<Bowler> spinners = TeamProfile.getAllBowlers(t, ".Spinners.txt");

            for (int i = 0; i < topOrder.size(); i++) {
                allPlayers.add(topOrder.get(i));
            }

            for (int i = 0; i < middleOrder.size(); i++) {
                allPlayers.add(middleOrder.get(i));
            }

            for (int i = 0; i < pacers.size(); i++) {
                allPlayers.add(pacers.get(i));
            }

            for (int i = 0; i < spinners.size(); i++) {
                allPlayers.add(spinners.get(i));
            }

//            for (int i = 0; i < allPlayers.size(); i++) {
//                allPlayers.get(i).setRank(i + 1);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        TableView<Player> playerTableView = new TableView<>();
        ObservableList<Player> playerObservableList = FXCollections.observableArrayList(allPlayers);
        playerTableView.setPrefHeight(400);

        TableColumn<Player, Integer> positionColumn = new TableColumn<>("Player ID");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Player, String> nameColumn = new TableColumn<>("Player Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Player, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Player, Double> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        positionColumn.setMaxWidth(1f * Integer.MAX_VALUE * 10);
        nameColumn.setMaxWidth(1f * Integer.MAX_VALUE * 40);
        categoryColumn.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        pointsColumn.setMaxWidth(1f * Integer.MAX_VALUE * 25);

        playerTableView.setItems(playerObservableList);

        playerTableView.getColumns().addAll(positionColumn, nameColumn, categoryColumn, pointsColumn);
        playerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        playerTableView.setMaxWidth(Double.MAX_VALUE);

        GridPane gridPane = new GridPane();
        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.ALWAYS);
        col.setHalignment(HPos.CENTER);
        gridPane.getColumnConstraints().add(col);
        gridPane.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: red");
        backButton.setFont(new Font("verdana",30));
        backButton.setTextFill(Color.WHITE);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setFullScreen(false);
                stage.setScene(MainScene.mainScene(stage , t));
                stage.setFullScreen(true);
            }
        });

        VBox vBox = new VBox(playerTableView);
        VBox.setVgrow(playerTableView, Priority.NEVER);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.setMaxWidth(800);
        vBox.setMaxHeight(500);
        vBox.setStyle("-fx-alignment: center;");

        playerTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        playerTableView.setMaxWidth(Double.MAX_VALUE);

        gridPane.add(vBox, 0, 0);
        GridPane.setHgrow(vBox, Priority.ALWAYS);

        gridPane.setAlignment(Pos.CENTER);

        borderPane.setCenter(gridPane);


        HBox hBox = new HBox();
        Label label = new Label("Displaying All Players of Team " + t.getTeamName());
        label.setFont(Font.font("verdana", FontWeight.BOLD ,30));
        label.setTextFill(Color.BLACK);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.getChildren().add(label);
        hBox.setPadding(new Insets(40, 0, 0, 0));

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.BOTTOM_CENTER);
        hBox1.getChildren().add(backButton);

        borderPane.setBottom(hBox1);

        borderPane.setTop(hBox);

        Scene scene = new Scene(borderPane, 1000, 600);
        scene.getStylesheets().add(
                DisplayScene.class.getResource("/table-style.css").toExternalForm()
        );
        return scene;



    }

    /**
     * Provides spinners for selecting how many players to include from each category.
     * - Top-order batsmen
     * - Middle-order batsmen
     * - Pacers
     * - Spinners
     *
     * Ensures total equals 11 before generating the team.
     * If valid, redirects to Prime XI display; otherwise shows error.
     *
     * @param stage The JavaFX stage
     * @param t The team profile to generate team from
     * @return A GridPane layout with category selectors and confirm/cancel buttons
     */

    public static GridPane generatePlayerLayout(Stage stage, TeamProfile t) {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        Text topOrder = new Text("Top Order Batsmen");
        topOrder.setFont(Font.font("verdana", FontWeight.BOLD, 25));
        topOrder.setFill(Color.YELLOW);
        Spinner<Integer> topOrderSpinner = new Spinner<>(0, 5, 2);
        topOrderSpinner.setPrefWidth(60);

        Text middleOrder = new Text("Middle Order Batsmen");
        middleOrder.setFont(Font.font("verdana", FontWeight.BOLD, 25));
        middleOrder.setFill(Color.YELLOW);
        Spinner<Integer> middleOrderSpinner = new Spinner<>(0, 5, 2);
        middleOrderSpinner.setPrefWidth(60);

        Text pacers = new Text("Pacers");
        pacers.setFont(Font.font("verdana", FontWeight.BOLD, 25));
        pacers.setFill(Color.YELLOW);
        Spinner<Integer> pacerSpinner = new Spinner<>(0, 5, 2);
        pacerSpinner.setPrefWidth(60);

        Text spinners = new Text("Spinners");
        spinners.setFont(Font.font("verdana", FontWeight.BOLD, 25));
        spinners.setFill(Color.YELLOW);
        Spinner<Integer> spinnerSpinner = new Spinner<>(0, 5, 2);
        spinnerSpinner.setPrefWidth(60);

        Text invalidInputs = new Text("");
        invalidInputs.setFont(new Font("verdana", 30));
        invalidInputs.setFill(Color.RED);

        Button confirm = new Button("Confirm");
        confirm.setFont(new Font("verdana", 20));
        confirm.setTextFill(Color.DARKBLUE);

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<Player> primeTeam = new ArrayList<>();
                int noOfTopOrder = topOrderSpinner.getValue();
                int noOfMiddleOrder = middleOrderSpinner.getValue();
                int noOfPacers = pacerSpinner.getValue();
                int noOfSpinners = spinnerSpinner.getValue();

                int noOfPlayers = noOfTopOrder + noOfMiddleOrder + noOfPacers + noOfSpinners;

                if (noOfPlayers == 11) {
                    primeTeam = TeamProfile.getPrimeXI(t, noOfTopOrder, noOfMiddleOrder, noOfPacers, noOfSpinners);

                    stage.setFullScreen(false);
                    stage.setScene(teamDisplayScene(t, stage, primeTeam));
                    stage.setFullScreen(true);

                } else {
                    invalidInputs.setText("Please Select 11 Players");
                }
            }
        });


        Button cancel = new Button("Cancel");
        cancel.setFont(new Font("verdana", 20));
        cancel.setTextFill(Color.RED);

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setFullScreen(false);
                stage.setScene(MainScene.mainScene(stage, t));
                stage.setFullScreen(true);
            }
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(cancel, confirm);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        gridPane.add(topOrder, 0, 4);
        gridPane.add(topOrderSpinner, 1, 4);
        GridPane.setHalignment(topOrderSpinner, HPos.CENTER);
        gridPane.add(middleOrder, 0, 5);
        gridPane.add(middleOrderSpinner, 1, 5);
        GridPane.setHalignment(middleOrderSpinner, HPos.CENTER);
        gridPane.add(pacers, 0, 6);
        gridPane.add(pacerSpinner, 1, 6);
        GridPane.setHalignment(pacerSpinner, HPos.CENTER);
        gridPane.add(spinners, 0, 7);
        gridPane.add(spinnerSpinner, 1, 7);
        GridPane.setHalignment(spinnerSpinner, HPos.CENTER);
        gridPane.add(hBox, 0, 8 ,2 ,1);
        gridPane.add(invalidInputs, 0, 11, 3, 1);


        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(40);

        return gridPane;
    }

    /**
     * Searches a saved player file for a player by name.
     *
     * @param path The file path to search
     * @param playerName The name of the player to find
     * @return Index of the player in file (line-wise), or -1 if not found
     */

    public static int findPlayer(String path, String playerName) {
        try {
            File file = new File(path);
            String allInfo = TeamProfile.readFromFile(file);
            String[] players = allInfo.split("\n");

            for (int i = 0; i < players.length; i++) {

                String name = players[i].split(",")[1];
                if (playerName.equals(name)) {
                    return i;
                }


            }
        } catch (Exception e) {

        }

        return -1;
    }




}
