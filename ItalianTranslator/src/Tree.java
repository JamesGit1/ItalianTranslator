
public class Tree {

	
	
	
	
	
	
	// Pass in the Main Binary tree into here 
	
	
	/**
	 * 
	 *Displays the entire dictionary
	 *
	 * @param english - Gets english left and right Tree
	 * @param itatlian - Gets itatlian left and right Tree 
	 * TesT TesT
	 */
	public void  display(Node english, Node italian){
		
		display(english.getEnglishLeft(),italian.getItalianLeft());
		System.out.println(english+ "\t" + italian);
		display(english.getEnglishRight(),italian.getItalianRight());
		
	}
	
	
	
	
	
}
