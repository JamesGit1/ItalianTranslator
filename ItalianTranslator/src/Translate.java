import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
	public Node root;

	public String[] translateText(String languageFrom, String searchText) {
		// String phrasesTranslated = translatePhrases(languageFrom, searchText);
		String wordArray[] = searchText.split(" ");
		ArrayList<String> wordsToAdd = new ArrayList<String>();

		ArrayList<String> translatedList = new ArrayList<String>();
		for (int i = 0; i < wordArray.length; i++) {
			translatedList.add(translateWord(languageFrom, wordArray[i]));
			if (translatedList.get(i).equals(wordArray[i])) {
				wordsToAdd.add(translatedList.get(i));
			}
		}

		// Convert ArrayList to object array
		Object[] objArr = translatedList.toArray();

		// convert Object array to String array
		wordArray = Arrays.copyOf(objArr, objArr.length, String[].class);

		System.out.println("Translates to...");
		for (int i = 0; i < wordArray.length; i++) {
			System.out.print(wordArray[i] + " ");
		}

		if (wordsToAdd != null) {
			System.out.println("Seems like some words couldn't be translated...");
			System.out.println("Looking up translation and adding them to dictionary");
			for (int i = 0; i < wordsToAdd.size(); i++) {
				System.out.println("Adding a translation for " + wordsToAdd.get(i));
				
					try {
						if (languageFrom.equals("english")) {
							String translatedText = translate("en","it",wordsToAdd.get(i));
							tree.addToTree(translatedText, wordsToAdd.get(i));
							tree.saveDictionary(tree.root);
							System.out.println("ENGLISH WORD " + wordsToAdd.get(i) + " TRANSLATED TO " + translatedText + "\n");
							
						} else {
							String translatedText = translate("it","en",wordsToAdd.get(i));
							tree.addToTree(wordsToAdd.get(i), translatedText);
							tree.saveDictionary(tree.root);
							System.out.println("ITALIAN WORD " + wordsToAdd.get(i) + " TRANSLATED TO " + translatedText + "\n");
						}	
					}
					catch(IOException e){
						System.out.print(e);
					}
					
				}

			}
		return wordArray;
	}

	public String translatePhrases(String languageFrom, String searchText) {
		String translatedPhrase = searchText;

		int numOfPhrases = 40;
		String[][] phrases = new String[numOfPhrases][2];

		for (int i = 0; i < numOfPhrases; i++) {
			if (languageFrom.equals("italian")) {
				if (searchText.contains(phrases[i][0])) {
					translatedPhrase = searchText.replace(phrases[i][0], phrases[i][1]);
				}
			} else {
				if (searchText.contains(phrases[i][1])) {
					translatedPhrase = searchText.replace(phrases[i][1], phrases[i][0]);
				}
			}
		}
		return translatedPhrase;
	}

	public String translateWord(String languageFrom, String searchWord) {
		String translatedWord = searchWord;
		if (languageFrom.equals("english")) {
			// Sets the translated word to the node related to the English phrase
			Node currentNode = tree.findNode(searchWord, languageFrom);
			if (currentNode == null) {
				System.out.println("There is no translation for " + searchWord);
				return searchWord;
			}
			translatedWord = currentNode.getItalianTranslation();
		} else if (languageFrom.equals("italian")) {
			// Sets the translated word to the node related to the Spanish phrase
			Node currentNode = tree.findNode(searchWord, languageFrom);
			if (currentNode == null) {
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
		root = tree.root;
	}

	public void saveDictionary(Node current) {
		tree.saveDictionary(current);
	}

	public void removeFromTree(String wordToDelete, String language) {
		tree.removeFromTree(wordToDelete, language);
	}

	private String translate(String langFrom, String langTo, String text) throws IOException {
		// INSERT YOU URL HERE
		String urlStr = "https://script.google.com/macros/s/AKfycbytFopLzFCyVVa6z90044PoMlD3xslHCxz3srWZryzo6SGvyYW4/exec"
				+ "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo + "&source=" + langFrom;
		URL url = new URL(urlStr);
		StringBuilder response = new StringBuilder();
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

}
