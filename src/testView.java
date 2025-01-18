import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class testView {

    public void start(Stage primaryStage) {
        // Create the title bar
        Label titleLabel = new Label("CAMP NOW");
        titleLabel.setStyle("-fx-font-weight: bold");
        HBox titleBar = new HBox(titleLabel);
        titleBar.setAlignment(Pos.CENTER);
        titleBar.setPadding(new Insets(10));
        titleBar.setStyle("-fx-background-color: lightgrey;");

        // Create the log out section
        Button logoutButton = new Button("Logout");
        logoutButton.setPrefSize(70, 30);
        logoutButton.setAlignment(Pos.CENTER);


        // Create the user section
        Button userButton = new Button("User");
        logoutButton.setPrefSize(70, 30);
        userButton.setAlignment(Pos.CENTER);
// Create a spacer to push buttons to the sides
        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Create the navigation bar with logout on the left and user on the right
        HBox navButtonSection = new HBox(logoutButton, spacer, userButton);
        navButtonSection.setAlignment(Pos.CENTER_LEFT);
        navButtonSection.setPadding(new Insets(10));
        navButtonSection.setSpacing(10);

        // Create the home section
        Label homeLabel = new Label("HOME");
        homeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Create the buttons
        Button cercaCampiButton = new Button("Cerca Campi");
        cercaCampiButton.setPrefWidth(200);
        cercaCampiButton.setPrefHeight(40);
        Button prenotazioniButton = new Button("Le mie prenotazioni");
        prenotazioniButton.setPrefWidth(200);
        prenotazioniButton.setPrefHeight(40);


        VBox navLayout  = new VBox(titleBar, navButtonSection, homeLabel);
        navLayout.setAlignment(Pos.CENTER);
        navLayout.setSpacing(5);

        // Create the main layout
        VBox mainLayout = new VBox(cercaCampiButton, prenotazioniButton );
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(20);
        //mainLayout.setPadding(new Insets(20));

        // Set up the scene
        BorderPane root = new BorderPane();
        root.setTop(navLayout);  // Position titleBar at the top
        root.setCenter(mainLayout);  // Main content in the center

        Scene scene = new Scene(root, 800, 500);

        // Configure the stage
        primaryStage.setTitle("CampNow");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
