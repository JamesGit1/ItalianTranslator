import java.util.Scanner;

/**
 * This is the Menu class, it will display a menu to the user and provide
 * functionality to the given options.
 * 
 * @author James, Josh, Jesse, Luke.
 */
public class Menu {

	public static void main(String[] args) {

		Menu menu = new Menu();
		menu.processUserChoices();
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
		System.out.println("3. Remove a word from the dictionary");
		System.out.println("4. Display the dictionary");
		System.out.println("5. Automated tests");
		System.out.println("6. Display tree alternatively");
		System.out.println("0. Exit ");
	}

	/**
	 * This method gives functionality to the menu.
	 */
	public void processUserChoices() {

		boolean stopMenu = false;
		Translate translate = new Translate();
		translate.loadDictionary();

		do {
			String option;
			displayMenu();
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
				System.out.println("Option 3 selected. What is the word you would like to delete? ");
				Scanner s4 = new Scanner(System.in);
				String wordToDelete = s4.nextLine();
				System.out.println("What language is this in? English or Italian? ");
				Scanner s5 = new Scanner(System.in);
				String languageToDelete = s5.nextLine();
				translate.removeFromTree(wordToDelete, languageToDelete);
			}

			else if (option.equals("4")) {
				System.out.println("Option 4 selected. Displaying dictionary...");
				translate.displayTree(translate.tree.root);
			}

			else if (option.equals("5")) {
				System.out.println("Option 5 selected. Running automated tests...");
				automatedTest();
			}

			else if (option.equals("6")) {
				System.out.println("Option 6 selected. Displaying the tree alternatively.");
				translate.tree.displayTreeAlternate(translate.tree.root);
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

	/**
	 * Performs automated tests on the data provided.
	 */

	public void automatedTest() {
		Tree tree = new Tree();
		System.out.println("First, we will add a few example words with their translations...");
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
		System.out.println(tree.root.getEnglishTranslation());
		System.out.println("Displaying tree...");
		tree.displayTree(tree.root);

		String wordToFind = "i";

		System.out.println("Deleting word..." + wordToFind);
		tree.removeFromTree(wordToFind, "english");
		tree.displayTreeAlternate(tree.getRoot("english"));
	}

	/**
	 * If the user enters and invalid option this is run.
	 */
	public static void error() {
		System.out.println("No valid input was entered, please try again.");
	}

}
