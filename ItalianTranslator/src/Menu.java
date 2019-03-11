import java.util.Scanner;

/**
 * This is the Menu class, it will display a menu to the user and provide
 * functionality to the given options.
 * 
 * @author James, Josh, Jesse, Luke.
 */
public class Menu 
{

	public static void main(String[] args) 
	{
		displayMenu();
	}

	/**
	 * This method displays the menu.
	 */
	public static void displayMenu() 
	{
		System.out.println("WELCOME TO ITALIANO TRANSLATE");
		System.out.println("Please select one of the following options");
		System.out.println("-----------------------------------------------");
		System.out.println("1. Translate from English to Italian");
		System.out.println("2. Translate from Italian to English");
		System.out.println("3. Load a dictionary");
		System.out.println("4. Remove a word from the dictionary");
		System.out.println("5. Display the dictionary");
		System.out.println("0. Exit ");
	}

	/**
	 * This method gives functionality to the menu.
	 */
	public void processUserChoices() 
	{

		boolean stopMenu = false;

		do 
		{
			String option;

			displayMenu();

			Scanner s1 = new Scanner(System.in);
			option = s1.nextLine();

			if (option.equals("1")) 
			{
				System.out.println("Option 1 selected. Please enter English text that you would like to be translated into Italian:");
			}

			else if (option.equals("2")) 
			{
				System.out.println("Option 2 selected. Please enter Italian text that you would like to be translated into English:");
			}

			else if (option.equals("3")) 
			{
				System.out.println("Option 3 selected. Loading dictionary...");
			}

			else if (option.equals("4")) 
			{
				System.out.println("Option 4 selected. Please enter an English or Italian word to delete from the dictionary:");
				
			}

			else if (option.equals("5")) 
			{
				System.out.println("Option 5 selected. Displaying dictionary...");
			}

			else if (option.equals("0")) 
			{
				System.out.println("Addio!");
				stopMenu = true;
			}

			else {
				error();
			}

		} while (stopMenu != true);
	}

	/**
	 * If the user enters and invalid option this is run.
	 */
	public static void error() 
	{
		System.out.println("No valid input was entered");
	}

}
