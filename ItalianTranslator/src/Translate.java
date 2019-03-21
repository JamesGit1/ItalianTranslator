import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 */

/**
 * Class which creates translating functionality using tree class 
 * 
 * @author James, Josh, Jesse, Luke.
 *
 */
public class Translate {
	Tree tree = new Tree();
	
	public String[] translateText(String languageFrom, String searchText) {
		//String phrasesTranslated = translatePhrases(languageFrom, searchText);
		String wordArray[] = searchText.split(" ");
		ArrayList<String> wordsToAdd=new ArrayList<String>();

		ArrayList<String> translatedList=new ArrayList<String>();
		for(int i=0; i<wordArray.length; i++) {
			translatedList.add(translateWord(languageFrom,wordArray[i]));
			if(translatedList.get(i).equals(wordArray[i])) {
				wordsToAdd.add(translatedList.get(i));
			}
		}
		
        // Convert ArrayList to object array 
        Object[] objArr = translatedList.toArray(); 
  
        // convert Object array to String array 
        wordArray = Arrays.copyOf(objArr, objArr.length, String[].class);
        
        System.out.println("Translates to...");
        for(int i=0; i<wordArray.length; i++) {
        	System.out.print(wordArray[i] + " ");
		}
        
        if(wordsToAdd!=null) {
        	System.out.println("Seems like some words couldn't be translated...");
        	for(int i=0; i<wordsToAdd.size(); i++) {
        		System.out.println("Add a translation for " + wordsToAdd.get(i) + " [y/n]?");
        		
        		Scanner in = new Scanner(System.in);
    			String answer = in.nextLine();
    			answer = answer.toLowerCase();
    			
    			if(answer.equals("y") || answer.equals("yes")) {
    				System.out.println("Please enter the translation of the word- " + wordsToAdd.get(i));
    				answer = in.nextLine();
        			answer = answer.toLowerCase();
        			if(languageFrom.equals("english")) {
        				tree.addToTree(answer,wordsToAdd.get(i));
        				tree.saveDictionary(tree.root);
        				System.out.println("Word added and saved to dictionary!");
        			}
        			else {
        				tree.addToTree(answer,wordsToAdd.get(i));
        				tree.saveDictionary(tree.root);
        				System.out.println("Word added and saved to dictionary!");
        			}
    			}
    			
        	}
        }
        
        return wordArray;
	}
	
	public String translatePhrases(String languageFrom, String searchText) {
		String translatedPhrase=searchText;
		
		int numOfPhrases = 40;
		String[][] phrases = new String[numOfPhrases][2];
		
		for(int i=0;i<numOfPhrases;i++) {
			if(languageFrom.equals("italian")) {
				if(searchText.contains(phrases[i][0])) {
					translatedPhrase = searchText.replace(phrases[i][0], phrases[i][1]);
				}
			}
			else {
				if(searchText.contains(phrases[i][1])) {
					translatedPhrase = searchText.replace(phrases[i][1], phrases[i][0]);
				}
			}
		}
		return translatedPhrase;
	}
	
	
	public String translateWord(String languageFrom, String searchWord) 
	{
		String translatedWord = searchWord;
		if(languageFrom.equals("english")) {
			//Sets the translated word to the node related to the English phrase
			Node currentNode = tree.findNode(searchWord, languageFrom);
			if(currentNode==null) {
				System.out.println("There is no translation for " + searchWord);
				return searchWord; 
			}
			translatedWord = currentNode.getItalianTranslation();
		}
		else if(languageFrom.equals("italian")) {
			//Sets the translated word to the node related to the Spanish phrase
			Node currentNode = tree.findNode(searchWord, languageFrom);
			if(currentNode==null) {
				System.out.println("There is no translation for " + searchWord);
				return searchWord; 
			}
			translatedWord = currentNode.getEnglishTranslation();
		}
		return translatedWord;
	}
	
	public void displayTree(Node current) {
		tree.displayTree(current);
	}
	
	public void loadDictionary() {
		tree.loadDictionary();
	}
	
	public void saveDictionary(Node current) {
		tree.saveDictionary(current);
	}
	
	public void removeFromTree(String wordToDelete, String language) {
		tree.removeFromTree(wordToDelete, language);
	}
	
}
