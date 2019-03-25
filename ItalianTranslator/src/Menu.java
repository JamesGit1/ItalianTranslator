import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * This is the Menu class, it will display a menu to the user and provide
 * functionality to the given options.
 * 
 * @author James, Josh, Jesse, Luke.
 */
public class Menu extends Application {
	Button s1ButtonTranslate,s1ButtonDisplay,s1ButtonRemove,back, exit ,button , yn,translateButton;
	Stage window;
	Scene main, translate,load;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		window = primaryStage;
		String title = "Italiano Transaltor";
		Label label1 = new Label("Italiano Translate");
		
		//Buttons That all go to different Scenes
 
        Button s1ButtonTranslate = new Button("Translator");
        s1ButtonTranslate.setOnAction(e -> window.setScene(translate));
        
        Button s1ButtonDisplay = new Button("Display Dictionary");
        s1ButtonDisplay.setOnAction(e -> window.setScene(translate));
        
        Button s1ButtonRemove = new Button("Remove word from dictionary");
       // CAll edit remove to display pop up window with text remove word s1ButtonRemove.setOnAction(e -> window.setScene(translate));
        
        Button exit = new Button("Exit");
        exit.setOnAction(e -> window.close());

        //Goes back to main menu
        Button back = new Button("Back");
        back.setOnAction(e -> window.setScene(main));
        
        
        
        
        // button test Use this for every error
              
        Button button = new Button("Alarm");
        button.setOnAction(e-> AlertBox.display(title, "Alarm"));
        
        //button1 test
        Button button1 = new Button("Confirm");
        button1.setOnAction(e-> ConfirmBox.display(title, "Confirm"));
        
        
        
        
        Text t = new Text();
        t.setFont( new Font(30));
        t.setText("gggg");
        t.setFont( new Font(30));
   
      
        
        t.setFont(Font.font("panpizza.ttf"));

        
        


       // Translate page 
        
        TextField translateInput = new TextField();
        translateInput.setPromptText("Please enter text you want to translate");

        translateButton = new Button("Click me");
     //   translateButton.setOnAction( e -> Method to translate(translateInput.getText()) );
        

       
        // back button
        
        // prompt user to yes or no don't need button 
        
        Button yn = new Button("Confirm");
        yn.setOnAction(e-> ConfirmBox.display(title, "Confirm"));

        ChoiceBox<String> language = new ChoiceBox<>();

        //Just pass language in when asked what to pass
        language.getItems().add("English");
        language.getItems().add("Italian");
        language.setValue("English");
      
      
        TextArea text = new TextArea();
        text.setPrefRowCount(10);
        text.setPrefColumnCount(20);
        text.setWrapText(true);

        // Button to print the TextArea node
        Button printTextBtn = new Button("Print Text");
        printTextBtn.setOnAction(e -> print(text,language));
        
        // Remove word will pop up window asking what to remove
        
        
        
        
        
        
        
        
        HBox mainText = new HBox();
        
        mainText.getChildren().add(label1);
       
        
       
        HBox buttons = new HBox();
        buttons.getChildren().addAll(s1ButtonTranslate,s1ButtonDisplay,s1ButtonRemove,back, exit ,button , button1, language,yn,text,printTextBtn,translateButton,translateInput);
        
        BorderPane borderpane = new BorderPane();
        borderpane.setTop(mainText);
        borderpane.setCenter(buttons);
        BorderPane.setAlignment(mainText, Pos.CENTER);
        BorderPane.setAlignment(buttons, Pos.CENTER);
        
    

 
        //Layout 1 - children laid out in vertical column
        main = new Scene(borderpane, 1280, 720);
        
        
        
        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(back);
        translate = new Scene(layout2, 1280, 720);
        
        VBox a = new VBox(40);
        load = new Scene(a,1280, 720);
        

        
        //Display Main Menu first
        main.getStylesheets().add("app.css");
        window.setScene(main);
        window.setTitle(title);
        window.show();
		
		
		
	
	
	
	
	}

	
	private void print(TextArea text, ChoiceBox<String> language) {

			text.setPromptText(language.getValue());
			System.out.println(language.getValue());
	}
	
	private boolean isInt(TextField input, String message){
        try{
            int age = Integer.parseInt(input.getText());
            System.out.println("User is: " + age);
            return true;
        }catch(NumberFormatException e){
            System.out.println("Error: " + message + " is not a number");
            return false;
        }
    }

	
	
	/**
	 * This method displays the menu.
	 */
	public static void displayMenu() {

		System.out.println("WELCOME TO ITALIANO TRANSLATE");
		System.out.println("Please select one of the following options");
		System.out.println("-----------------------------------------------");
		System.out.println("1. Translate from English to Italian");
		System.out.println("2. Translate from Italian to English");
		System.out.println("3. Load a dictionary");
		System.out.println("4. Remove a word from the dictionary");
		System.out.println("5. Display the dictionary");
		System.out.println("6. Automated tests");
		System.out.println("0. Exit ");
	}

	/**
	 * This method gives functionality to the menu.
	 */
	public void processUserChoices() {

		boolean stopMenu = false;
		Translate translate = new Translate();

		do {
			String option;

			Scanner s1 = new Scanner(System.in);
			option = s1.nextLine();

			if (option.equals("1")) {
				System.out.println(
						"Option 1 selected. Please enter English text that you would like to be translated into Italian:");
				Scanner s2 = new Scanner(System.in);
				String searchText = s2.nextLine();
				translate.translateText("english", searchText);
			}

			else if (option.equals("2")) {
				System.out.println(
						"Option 2 selected. Please enter Italian text that you would like to be translated into English:");
				Scanner s3 = new Scanner(System.in);
				String searchText = s3.nextLine();
				translate.translateText("italian", searchText);
			}

			else if (option.equals("3")) {
				System.out.println("Option 3 selected. Loading dictionary...");
				// translate.loadDictionary();
			}

			else if (option.equals("4")) {
				System.out.println(
						"Option 4 selected. Please enter an English or Italian word to delete from the dictionary:");
				Scanner s4 = new Scanner(System.in);
				String wordToDelete = s4.nextLine();
				// translate.removeFromTree();
			}

			else if (option.equals("5")) {
				System.out.println("Option 5 selected. Displaying dictionary...");
				// translate.displayDictionary();
			}

			else if (option.equals("6")) {
				System.out.println("Option 6 selected. Running automated tests...");
				automatedTest();
			}

			else if (option.equals("0")) {
				System.out.println("Addio!");
				stopMenu = true;
			}

			else {
				error();
			}

		} while (stopMenu != true);
	}

	public void automatedTest() {
		Tree tree = new Tree();
//		System.out.println("was".compareTo("i"));
//		System.out.println("z".compareTo("I"));
		tree.addToTree("io", "i");
		tree.addToTree("il suo", "his");
		tree.addToTree("era", "was");
		tree.addToTree("per", "for");
		tree.addToTree("sono", "are");
		tree.addToTree("con", "with");
		tree.addToTree("lattina", "can");
		tree.addToTree("fuori", "out");
		tree.addToTree("fuori", "out");
		System.out.println("Displaying root...");
		System.out.println("Displaying tree...");

		String wordToFind = "was";

		System.out.println("Finding parent of node of..." + wordToFind);
		// Node n = tree.findParentNode(wordToFind, "english");
		// System.out.println(n.getTranslation("english"));

		System.out.println("Deleting word..." + wordToFind);
		tree.removeFromTree(wordToFind, "english");
		tree.displayTree(tree.getRoot("english"));

//		String word1 = "was";
//		String word2 = "his";
//		System.out.println(word1.compareTo(word2));

		// System.out.print(tree.findNode("fuori",language);
		// "italian").getTranslation(tree.changeLanguage(language)));
	}

	/**
	 * If the user enters and invalid option this is run.
	 */
	public static void error() {
		System.out.println("No valid input was entered, please try again.");
	}

}
