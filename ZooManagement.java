import javafx.application.Application;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import java.util.*;

public class ZooManagement extends Application
{
    // Initialize our zoo
    Zoo zoo = new Zoo();
    private final ObservableList<Animal> observable = zoo.toObservableList();
    public void start (Stage mainStage)
    {
        // Create a new border pane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-font-size: 15;");
        
        // Set the scene
        Scene scene = new Scene(root, 450,500);
        mainStage.setTitle("Zoo Management App");
        mainStage.setScene(scene);
        
        // Set up the main layout
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        root.setCenter(mainLayout);
        
        // Create a TableView for all animals currently in the zoo
        TableView<Animal> table = new TableView();
        mainLayout.getChildren().add(table);
        table.setItems(observable);
        
        // Animal Name Column
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setMinWidth(80);
        table.getColumns().add(nameCol);
        
        // Animal is Fed? Column
        TableColumn feedCol = new TableColumn("Animal Fed?");
        feedCol.setCellValueFactory(new PropertyValueFactory("fed"));
        feedCol.setMinWidth(80);
        table.getColumns().add(feedCol);
        
        // remove extra columns/rows
        table.setMaxWidth(244);
        table.setMaxHeight(490);
        
        // Set the menu for functionality
        MenuBar topMenu = new MenuBar();
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        
        // Menu item - add a new animal to our zoo
        MenuItem addItem = new MenuItem("Add a New Animal");
        addItem.setOnAction((event->
        {
            String animalName = zoo.enterName();
            Animal added = zoo.addAnimal(animalName);
            if (added == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Animal");
                alert.setHeaderText("Invalid Animal");
                alert.setContentText("Animal is not valid to be put in the zoo.");
                alert.showAndWait();
            }
            else {
                if (!observable.contains(added)) {
                    observable.add(added);
                }
                table.refresh();
            }
        }));
        
        // Menu item - remove an animal from our zoo
        MenuItem removeItem = new MenuItem("Remove an Animal");
        removeItem.setOnAction((event->
        {
            String animalName = zoo.enterName();
            Animal removed = zoo.removeAnimal(animalName);
            if (removed == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Animal Not Found");
                alert.setHeaderText("Animal Not Found");
                alert.setContentText("There is no animal with the name " + animalName + " to be removed");
                alert.showAndWait();
            }
            else {
                observable.remove(removed);
                table.refresh();
            }
        }));
        
        // Menu Item - feed an animal
        MenuItem feedItem = new MenuItem("Feed an Animal");
        feedItem.setOnAction((event->
        {
            String animalName = zoo.enterName();
            Animal fed = zoo.feedAnimal(animalName);
            if (fed == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Animal Not Found");
                alert.setHeaderText("Animal Not Found");
                alert.setContentText("There is no animal with the name " + animalName + " to be fed");
                alert.showAndWait();
            }
            else {
                table.refresh();
            }
        }));
        
        // Menu item - Quit
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction((e->
        {
            mainStage.close();
        }));
        
        fileMenu.getItems().addAll(addItem, removeItem, feedItem, quitItem);
        // Show stage once everything is processed
        mainStage.show();
    }
}
