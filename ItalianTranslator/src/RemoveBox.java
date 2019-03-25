import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class RemoveBox {

    public static void display(String title, String message) {
    	
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        TextField removeDictionary = new TextField();
        removeDictionary.setPromptText("Please enter text you want to translate");
        
        
        removeDictionary.getText();
        
        Button remove = new Button("Remove");
        Button back = new Button("Exit");
        
        ChoiceBox<String> language = new ChoiceBox<>();

        language.getItems().add("English");
        language.getItems().add("Italian");
        language.setValue("English");
        
        remove.setOnAction(e -> {
  
        	
            window.close();
        });
        back.setOnAction(e -> {
            window.close();
        });
        
        
        
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

	

}