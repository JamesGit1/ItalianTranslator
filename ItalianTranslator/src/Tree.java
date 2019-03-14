import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Tree {
	public Node italianRoot;
	public Node englishRoot;

	public Tree() {
		englishRoot = null;
		italianRoot = null;
	}

	public Tree(Node italianRoot, Node englishRoot) {
		this.englishRoot = englishRoot;
		this.italianRoot = italianRoot;
	}

	// Pass in the Main Binary tree into here

	/**
	 * Displays the entire dictionary
	 *
	 * @param english  - Gets English left and right Tree
	 * @param itatlian - Gets Italian left and right Tree
	 */
	public Node getRoot(String language) {
		if (language.equals("italian")) {
			return italianRoot;
		} else {
			return englishRoot;
		}
	}

	public void displayTree(Node current) {

		displayTree(current.getEnglishLeft());
		System.out.println(current.getEnglishTranslation() + "\t" + current.getItalianTranslation());
		displayTree(current.getEnglishRight());

	}

	public void loadDictionary() {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("A.txt");
			br = new BufferedReader(fr);

			String nextLine = br.readLine();

			while (nextLine != null) {
				String[] words = nextLine.split(",");

				// Add to tree pass word 0 and 1
				nextLine = br.readLine();
			}

			br.close();

		} catch (Exception e) {

			System.out.println("heehsheshes");

		}
	}

	public void saveDictionary(Node current) {

		FileOutputStream os = null;
		PrintWriter pw = null;

		try {
			os = new FileOutputStream("Dictionary.txt");
			pw = new PrintWriter(os);

			save(current, pw);
			pw.flush();

		} catch (IOException e) {
			System.out.println("File not found");

		}
	}

	public void save(Node current, PrintWriter pw) {

		if (current != null) {
			save(current.getEnglishLeft(), pw);
			pw.print(current.getEnglishTranslation() + "," + current.getItalianTranslation());
			save(current.getEnglishRight(), pw);

		}

	}

	public Node findNode(String searchWord, String language) {
		Node current;
		current = getRoot(language);
		while (current != null) {
			if (current.getWord(language).equals(searchWord)) {
				return current;
			} else if (current.getWord(language).compareTo(searchWord) < 0) {
				current = current.getLeft(language);
			} else {
				current = current.getRight(language);
			}
		}
		return null;
	}

	public Node findParentNode(String wordToFind, String language) {
		Node current = getRoot(language);
		Node previous = null;
		while (current != null) {
			if (current.getWord(language).equals(wordToFind)) {
				return previous;
			} else if (wordToFind.compareTo(current.getWord(language)) < 0) {
				previous = current;
				current = current.getRight(language);
			} else {
				previous = current;
				current = current.getLeft(language);
			}
		}
		return null;
	}

	public void removeFromTree(String wordToDelete, String language) {
		Node nodeToDelete = findNode(wordToDelete, language);
		Node parentNode;
	}

	/**
	 * This method checks if the tree is empty.
	 * 
	 * @return true or false
	 */
	public boolean isTreeEmpty() {
		if (englishRoot == null) {
			return true;
		}
		return false;

	}
}
