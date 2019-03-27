import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
	ArrayList<Integer> wordsToBeTranslated = new ArrayList<Integer>();

	public String[] translateText(String languageFrom, String searchText) {
		wordsToBeTranslated.add(-1);

		long wordCount = 0;
		long startTime = new Date().getTime();

		ArrayList<String> translatedList = new ArrayList<String>();
		ArrayList<String> workingList = new ArrayList<String>();
		ArrayList<String> wordsToAdd = new ArrayList<String>();

		searchText = searchText.toLowerCase();
		String[] sentences = searchText.split("(?<=[a-z])\\.\\s+");

		for (int i = 0; i < sentences.length; i++) {
			translatedList = translatePhrases(languageFrom, sentences[i]);
			String[] wordArray = sentences[i].split(" ");
			wordCount += wordArray.length;

			if (translatedList == null) { // i.e no phrases found in sentence
				for (int j = 0; j < wordArray.length; j++) {
					String strToAdd = "";
					if (wordArray[j].contains("!")) { // if contains exclamation mark removes and adds back after
						strToAdd = "!";
						wordArray[j] = wordArray[j].replace("!", "");
					}
					if (wordArray[j].contains("?")) { // if contains question mark removes and adds back after
						strToAdd = "?";
						wordArray[j] = wordArray[j].replace("?", "");
					}

					String tanslatedWord = translateWord(languageFrom, wordArray[j]);
					workingList.add(tanslatedWord + strToAdd);
					if (tree.findNode(wordArray[j], languageFrom) == null) {
						wordsToAdd.add(wordArray[j]);
					}
					translatedList = workingList;
				}
				// Check if there should be a full stop added by checking if there is a question
				// mark or exclamation mark at the end of the last word
				String addFullStop = workingList.get(workingList.size() - 1);
				addFullStop = addFullStop.substring(addFullStop.length() - 1);
				if ("!".equals(addFullStop) || "?".equals(addFullStop)) {
				} else {
					workingList.add(".");
				}

			} else {
				for (int j = 0; j < translatedList.size(); j++) {
					if (wordsToBeTranslated.contains(j)) {
						translatedList.set(j, translateWord(languageFrom, wordArray[j]));
					}
				}
				translatedList.add(".");
			}
		}

		/*
		 * // Convert ArrayList to object array Object[] objArr =
		 * translatedList.toArray();
		 * 
		 * // convert Object array to String array wordArray = Arrays.copyOf(objArr,
		 * objArr.length, String[].class); for (int i = 0; i < wordArray.length; i++) {
		 * System.out.print(wordArray[i] + " "); }
		 */

		System.out.println("Translates to...");
		for (int i = 0; i < translatedList.size(); i++) {
			// capitalise the first letter of the first word
			translatedList.set(0,
					translatedList.get(0).substring(0, 1).toUpperCase() + translatedList.get(0).substring(1));
			String space = " ";
			try {
				if (translatedList.get(i + 1).equals(".")) {
					space = "";
				}
			} catch (java.lang.IndexOutOfBoundsException e) {
				// end of string
			}
			System.out.print(translatedList.get(i) + space);
		}
		System.out.println("");

		long endTime = new Date().getTime();
		if (startTime == endTime) {
			System.out.println("Too fast to record");
		} else {
			long totalTime = endTime - startTime;
			double wps = wordCount / totalTime;
			System.out.println("WORDS TRANSLATED PER MILLISECOND: " + wps + "words/ms");
		}

		// Convert ArrayList to object array
		Object[] objArr = translatedList.toArray();

		// convert Object array to String array
		String[] translatedArray = Arrays.copyOf(objArr, objArr.length, String[].class);

		/* This is also code to handle interactions with the Google API
		 * Refer to bottom to find the rest of the Google API
		 * 
		 * if(wordsToAdd.size()!=0) {
		 * System.out.println("Seems like some words couldn't be translated...");
		 * System.out.println("Looking up translation and adding them to dictionary"); }
		 * for (int i = 0; i < wordsToAdd.size(); i++) {
		 * 
		 * System.out.println("Adding a translation for " + wordsToAdd.get(i));
		 * 
		 * try { if (languageFrom.equals("english")) { String translatedText =
		 * translate("en", "it", wordsToAdd.get(i)); tree.addToTree(translatedText,
		 * wordsToAdd.get(i)); tree.saveDictionary(tree.root); System.out.println(
		 * "ENGLISH WORD " + wordsToAdd.get(i) + " TRANSLATED TO " + translatedText +
		 * "\n");
		 * 
		 * } else { String translatedText = translate("it", "en", wordsToAdd.get(i));
		 * tree.addToTree(wordsToAdd.get(i), translatedText);
		 * tree.saveDictionary(tree.root); System.out.println( "ITALIAN WORD " +
		 * wordsToAdd.get(i) + " TRANSLATED TO " + translatedText + "\n"); } } catch
		 * (IOException e) { System.out.print(e); }
		 * 
		 * }
		 */
		if (wordsToAdd.size() != 0) {
			System.out.println("Seems like some words couldn't be translated...");
			for (int i = 0; i < wordsToAdd.size(); i++) {
				System.out.println("Add a translation for " + wordsToAdd.get(i) + " [y/n]?");

				Scanner in = new Scanner(System.in);
				String answer = in.nextLine();
				answer = answer.toLowerCase();

				if (answer.equals("y") || answer.equals("yes")) {
					System.out.println("Please enter the translation of the word- " + wordsToAdd.get(i));
					answer = in.nextLine();
					answer = answer.toLowerCase();
					if (languageFrom.equals("english")) {
						tree.addToTree(answer, wordsToAdd.get(i));
						tree.saveDictionary(tree.root);
						System.out.println("Word added and saved to dictionary!");
					} else {
						tree.addToTree(answer, wordsToAdd.get(i));
						tree.saveDictionary(tree.root);
						System.out.println("Word added and saved to dictionary!");
					}
				}
			}
		}

		return translatedArray;
	}

	/**
	 * 
	 * @param languageFrom
	 * @param searchText
	 * @return translatedPhrases ArrayList- contains succesfully translated phrases
	 * 
	 * Method to take in language and sentence of text and output a list of words containing the translated phrases
	 * Returns untranslated phrases as spaces which can be filled later in position wordsToBeTranslated
	 */
	public ArrayList<String> translatePhrases(String languageFrom, String searchText) {
		ArrayList<String> translatedList = new ArrayList<String>();
		String[] wordArray = searchText.split(" ");

		int numOfPhrases = 1;
		String[][] phrases = { { "that is epic", " that is v epic" },
				{ "THIS WILL BE ITALIAN PHRASE", "THIS WILL ALSO BE ITALIAN PHRASE" } };

		for (int i = 0; i < numOfPhrases; i++) {
			if (languageFrom.equals("english")) {
				if (searchText.contains(phrases[i][0])) { // IF PHRASE IS IN TEXT
					String[] phraseSplit = phrases[0][i].split(" "); // SPLIT PHRASE INTO INDIVIDUAL WORDS
					String[] translationPhraseSplit = phrases[1][i].split(" "); // SPLIT TRANSLATED PHRASE INTO
																				// INDIVIDUAL WORDS
					int counter = 0;

					while (counter < wordArray.length) { // IF WORD ARRAY = PHRASE ADD INTO CORRECT POSITION
						if (wordArray[counter].equals(phraseSplit[0])) {
							for (int j = 0; j < translationPhraseSplit.length; j++) {
								translatedList.add(translationPhraseSplit[j]);
							}
							return translatedList;
						} else {
							translatedList.add(""); // OTHERWISE ADD BLANK SPACE TO BE TRANSLATED LATER AND ADD POSITION
							wordsToBeTranslated.add(counter);
						}
						counter++;
					}
				}
			} else {
				if (searchText.contains(phrases[i][1])) { // IF PHRASE IS IN TEXT
					String[] phraseSplit = phrases[1][i].split(" "); // SPLIT PHRASE INTO INDIVIDUAL WORDS
					String[] translationPhraseSplit = phrases[1][i].split(" "); // SPLIT TRANSLATED PHRASE INTO
																				// INDIVIDUAL WORDS
					int counter = 0;

					while (counter < wordArray.length) { // IF WORD ARRAY = PHRASE ADD INTO CORRECT POSITION
						if (wordArray[counter].equals(phraseSplit[0])) {
							for (int j = 0; j < translationPhraseSplit.length; j++) {
								translatedList.add(translationPhraseSplit[j]);
							}
							return translatedList;
						} else {
							translatedList.add(""); // OTHERWISE ADD BLANK SPACE TO BE TRANSLATED LATER
							wordsToBeTranslated.add(counter);
						}
						counter++;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param languageFrom
	 * @param searchWord
	 * @return 
	 * 
	 * Translates word input into Italian word from dictionary
	 */
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
		tree.displayTreeAlternate(current);
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

	
	/*
	 * This was code to interact with the Google API and allow lots of words to be added to the dictionary very quickly. It also meant if that translation was
	 * not in the dictionary it could automatically be searched and added. This is good for constructing a dictionary but does not meet the final specifications
	 * for the program so is not used in it.
	 * This is where the majority of our dictionary data was collected from.
	 * 
	private String translate(String langFrom, String langTo, String text) throws IOException {
		String urlStr = "https://script.google.com/macros/s/AKfycbxoCIB_d4HBT7znrlRY1F6DQBFbZNMZRVhH2VIGhiDSUutfG99t/exec"
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
		String out = response.toString();
		out = checkEncoding(out);
		out = out.toLowerCase();
		return out;
	}
	
	//Some characters don't get encoded properly so were changed so nonsense characters weren't added to the dictionary
	private String checkEncoding(String str) {
		str = str.replace("Ã¡", "á");
		str = str.replace("Ã¢", "â");
		str = str.replace("Ã£²", "ã");
		str = str.replace("Ã¤", "ä");
		str = str.replace("Ã¥", "å");
		str = str.replace("Ã¦", "æ");
		str = str.replace("Ã§", "ç");
		str = str.replace("Ã¨", "è");
		str = str.replace("Ã©", "é");
		str = str.replace("Ãª", "ê");
		str = str.replace("Ã«", "ë");
		str = str.replace("Ã¬", "ì");
		str = str.replace("Ã­", "í");
		str = str.replace("Ã®", "î");
		str = str.replace("Ã¯", "ï");
		str = str.replace("Ã°", "ð");
		str = str.replace("Ã±", "ñ");
		str = str.replace("Ã²", "ò");
		str = str.replace("Ã³", "ó");
		str = str.replace("Ã´", "ô");
		str = str.replace("Ãµ", "õ");
		str = str.replace("Ã¶", "ö");
		str = str.replace("Ã·", "÷");
		str = str.replace("Ã¸", "ø");
		str = str.replace("Ã¹", "ù");
		str = str.replace("Ãº", "ú");
		str = str.replace("Ã»", "û");
		str = str.replace("Ã¼", "ü");
		str = str.replace("Ã½", "ý");
		str = str.replace("Ã¾", "þ");
		str = str.replace("Ã¿", "ÿ");
		str = str.replace("Ã", "à");
		return str;
	}
*/
}
