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
	public void displayTree(Node english, Node italian) {
		displayTree(english.getEnglishLeft(), italian.getItalianLeft());
		System.out.println(english + "\t" + italian);
		displayTree(english.getEnglishRight(), italian.getItalianRight());
	}

	public Node getRoot(String language) {
		if (language.equals("italian")) {
			return italianRoot;
		} else {
			return englishRoot;
		}
	}

	public void loadDictionary() {
		System.out.println("Test");
	}

	public void saveDictionary(Node current) {
		try {
			if (current == null) {
				return;
				// yy
			}
		} catch (Exception e) {
			System.out.println("File not found");
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
