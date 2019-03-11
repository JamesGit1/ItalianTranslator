
/** This is the Menu class, it will display a menu to the 
 * user and provide functionality to the given options. 
 * 
 * @author James, Josh, Jesse, Luke.
 */
public class Menu {

	public static void main(String[] args) {
       displayMenu()
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
		
		boolean stopMenu=false;
		
		do 
		{
			String option;
			
			displayMenu();
		
			Scanner s1 = new Scanner (System.in);
			option=s1.nextLine();
		
			if(option.equals("1")) 
			{
				
			}
		
			else if(option.equals("2")) 
			{
				
			}
				
			
			else if(option.equals("3")) 
			{
			
			}
			
			else if(option.equals("4")) 
			{
				
			}
			
			else if(option.equals("5")) 
			{
			
			}
			
			else if(option.equals("6")) 
			{
				
				stopMenu=true;
			}
			
		
			else
			{
				error();
			}
		
		}
		while(stopMenu != true);
	}

	/**
	 * If the user enters and invalid option this is run.
	 */
	public static void error()
	{
		System.out.println("No valid input was entered");
	}
	
	


}
