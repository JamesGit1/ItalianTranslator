import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    //Create variable
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        //Create two buttons
        TextField translateInput = new TextField();
        translateInput.setPromptText("Please enter text you want to translate");
        Button yes = new Button("Yes");
        Button no = new Button("No");

        //Clicking will set answer and close window
        yes.setOnAction(e -> {
            answer = true;
            window.close();
        });
        no.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(label, yes, no);
        layout.setAlignment(Pos.CENTER);
    
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("app.css");
        window.setScene(scene);
        window.showAndWait();

     
        return answer;
    }

}