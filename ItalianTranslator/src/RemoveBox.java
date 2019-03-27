import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
/**
 * pop up window which asks the user what word they would to to delete
 */
public class RemoveBox {

    public static void display(String title, Translate translater) {

        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setResizable(false);
        ChoiceBox<String> language = new ChoiceBox<>();

        language.getItems().add("english");
        language.getItems().add("italian");
        language.setValue("english");
        Label label = new Label();
        label.setText("What word would you like to remove");
        
        TextField input = new TextField();
      input.setPromptText("Word to delete");
        
        Button closeButton = new Button("Delete");
        closeButton.setOnAction(e ->
        translater.removeFromTree(input.getText(), language.getValue()));
       

        
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,input ,closeButton,language);
   
        
        layout.setAlignment(Pos.CENTER);
        
 
        
        

        
  
        
 
        
        
        
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

	

}