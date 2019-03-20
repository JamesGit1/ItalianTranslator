import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * This is the Menu class, it will display a menu to the user and provide
 * functionality to the given options.
 * 
 * @author James, Josh, Jesse, Luke.
 */
public class Menu extends Application implements EventHandler<ActionEvent> {
Button button;
Stage window;
Scene scene1,scene2;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Italiano Transaltor");
		button = new Button();
		button.setText("Test");
		button.setOnAction(this);
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
	
	 scene1 = new Scene(layout, 1280,720);
		primaryStage.setScene(scene1);
		primaryStage.show();
		
	}
	
	public void handle(ActionEvent event) {
		
		if(event.getSource() == button) {
			
			System.out.println("testest");
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
		Node n = tree.findParentNode(wordToFind, "english");
		System.out.println(n.getTranslation("english"));

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
