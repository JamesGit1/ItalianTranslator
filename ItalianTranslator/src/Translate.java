import java.util.ArrayList;

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
	
	public void translateText(String languageFrom, String searchText) {
		String wordArray[] = searchText.split(" ");
		String translated[] = wordArray;
		//ArrayList<String> translatedList=new ArrayList<String>();
		for(int i=0; i<wordArray.length; i++) {
			//translated[i] = translateWord(languageFrom,wordArray[i]);
		}
	}
	
	public String translatePhrase(String languageFrom, String searchPhrase) {
		String translatedPhrase = null;
		if(languageFrom.equals("english")) {
			//Sets the translated Phrase to the node related to the English phrase
			Node currentNode = tree.findNode(searchPhrase, languageFrom);
			translatedPhrase = currentNode.getItalianTranslation();
		}
		else {
			//Sets the translated Phrase to the node related to the Spanish phrase
			Node currentNode = tree.findNode(searchPhrase, languageFrom);
			translatedPhrase = currentNode.getItalianTranslation();
		}
		return translatedPhrase;
	}
	
	
	public String translateWord(String languageFrom, String searchWord) 
	{
		String translatedWord = null;
		if(languageFrom.equals("english")) {
			//Sets the translated word to the node related to the English phrase
			Node currentNode = tree.findNode(searchWord, languageFrom);
			translatedWord = currentNode.getItalianTranslation();
		}
		else {
			//Sets the translated word to the node related to the Spanish phrase
			Node currentNode = tree.findNode(searchWord, languageFrom);
			translatedWord = currentNode.getItalianTranslation();
		}
		return translatedWord;
	}
	
	
}
