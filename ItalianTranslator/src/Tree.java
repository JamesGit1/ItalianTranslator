import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Tree {

	
	
	
	
	
	
	// Pass in the Main Binary tree into here 
	
	
	
	/**
	 *Displays the entire dictionary
	 *
	 * @param english - Gets english left and right Tree
	 * @param itatlian - Gets italian left and right Tree 
	 */
	public void  display(Node english, Node italian){
		
		display(english.getEnglishLeft(),italian.getItalianLeft());
		System.out.println(english+ "\t" + italian);
		display(english.getEnglishRight(),italian.getItalianRight());
		
	}
	
public void loadDictionary() {
	System.out.println("Test");
}
	
public void saveDictionary(Node current) {
	

try {
		
	if(current == null) {
		return;
}
	
	
		
		
		
		
}catch(Exception e) {
	
	System.out.println("File not found");
	
}



	
	
}
	
	
}
