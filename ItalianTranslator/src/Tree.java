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

	public String changeLanguage(String language) {
		if (language.equals("italian")) {
			return "english";
		} else {
			return "italian";
		}
	}

	public void setRoot(Node newRoot, String language) {
		if (language.equals("italian")) {
			italianRoot = newRoot;
		} else {
			englishRoot = newRoot;
		}
	}

	public Node getRoot(String language) {
		if (language.equals("italian")) {
			return italianRoot;
		} else {
			return englishRoot;
		}
	}

	public void displayTree(Node current) {
		if (current != null) {
			displayTree(current.getEnglishLeft());
			System.out.println(current.getEnglishTranslation() + "\t" + current.getItalianTranslation());
			displayTree(current.getEnglishRight());
		}
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
				addToTree(words[0], words[1]);
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
			pw.println(current.getEnglishTranslation() + "," + current.getItalianTranslation());
			save(current.getEnglishRight(), pw);
		}
	}

	public void addToTree(String italianWord, String englishWord) {
		Node newNode = new Node(italianWord, englishWord);
		Node previous = null;
		if (findNode(newNode.getEnglishTranslation(), "english") != null
				|| findNode(newNode.getItalianTranslation(), "italian") != null) {
			System.out.println("This word (English or Italian) already exists, it will not be added");
		}
		if (englishRoot == null) {
			englishRoot = newNode;
		} else if (italianRoot == null) {
			italianRoot = newNode;
		} else {
			String language = "english";
			for (int i = 0; i < 2; i++) {
				Node current = getRoot(language);
				while (current != null) {
					previous = current;
					if (newNode.getTranslation(language).compareTo(current.getTranslation(language)) < 0) {
						current = current.getLeft(language);
						if (current == null) {
							previous.setLeft(newNode, language);
						}
					} else if (newNode.getTranslation(language).compareTo(current.getTranslation(language)) > 0) {
						current = current.getRight(language);
						if (current == null) {
							previous.setRight(newNode, language);
						}
					}
				}
				language = changeLanguage(language);
			}
		}
	}

	public Node findNode(String searchWord, String language) {
		Node current = getRoot(language);
		while (current != null) {
			if (current.getTranslation(language).equals(searchWord)) {
				return current;
			} else if (current.getTranslation(language).compareTo(searchWord) < 0) {
				current = current.getRight(language);
			} else {
				current = current.getLeft(language);
			}
		}
		if (getRoot(changeLanguage(language)).equals(searchWord)) {
			return getRoot(changeLanguage(language));
		}
		System.out.println("Word not found.");
		return null;
	}

	public Node findParentNode(String wordToFind, String language) {
		Node current = getRoot(language);
		Node previous = null;
		while (current != null) {
			if (current.getTranslation(language).equals(wordToFind)) {
				return previous;
			} else if (wordToFind.compareTo(current.getTranslation(language)) < 0) {
				previous = current;
				current = current.getRight(language);
			} else {
				previous = current;
				current = current.getLeft(language);
			}
		}
		if (getRoot(changeLanguage(language)).equals(wordToFind)) {
			return getRoot(changeLanguage(language));
		}
		System.out.println("Parent node not found.");
		return null;
	}

	public void removeFromTree(String wordToDelete, String language) {
		for (int i = 0; i < 2; i++) {
			Node nodeToDelete = findNode(wordToDelete, language);
			Node parentNode = findParentNode(wordToDelete, language);
			if (nodeToDelete.getRight(language) == null && nodeToDelete.getLeft(language) == null) {
				deleteLeaf(nodeToDelete, parentNode, language);
				// If the node has one child node on the right.
			} else if (nodeToDelete.getRight(language) != null && nodeToDelete.getLeft(language) == null
					|| nodeToDelete.getRight(language) == null && nodeToDelete.getLeft(language) != null) {
				deleteNodeWithOneChild(nodeToDelete, parentNode, language);
				// If the node has two child nodes, one on either side.
			} else if (nodeToDelete.getRight(language) != null && nodeToDelete.getLeft(language) != null) {
				deleteNodeWithTwoChildren(nodeToDelete, parentNode, language);
			}
			language = changeLanguage(language);
		}
	}

	public void deleteLeaf(Node nodeToDelete, Node parentNode, String language) {
		if (parentNode.getTranslation(language).compareTo(nodeToDelete.getTranslation(language)) < 0) {
			parentNode.setRight(null, language);
		} else {
			parentNode.setLeft(null, language);
		}
	}

	public void deleteNodeWithOneChild(Node nodeToDelete, Node parentNode, String language) {
		if (nodeToDelete.getRight(language) != null && nodeToDelete.getLeft(language) == null) {
			if (parentNode.getRight(language) == nodeToDelete) {
				parentNode.setRight(nodeToDelete.getRight(language), language);
			} else {
				parentNode.setLeft(nodeToDelete.getRight(language), language);
			}
		} else if (nodeToDelete.getRight(language) == null && nodeToDelete.getLeft(language) != null) {
			if (parentNode.getLeft(language) == nodeToDelete) {
				parentNode.setLeft(nodeToDelete.getLeft(language), language);
			} else {
				parentNode.setRight(nodeToDelete.getLeft(language), language);
			}
		}
	}

	public void deleteNodeWithTwoChildren(Node nodeToDelete, Node parentNode, String language) {
		Node replacementNode = nodeToDelete.getLeft(language);
		Node previous = parentNode;
		while (replacementNode.getRight(language) != null) {
			previous = replacementNode;
			replacementNode = replacementNode.getRight(language);
		}
		if (replacementNode.getLeft(language) == null) {
			deleteLeaf(replacementNode, previous, language);
		} else {
			deleteNodeWithOneChild(replacementNode, previous, language);
		}
		replacementNode.setRight(nodeToDelete.getRight(language), language);
		if (replacementNode != nodeToDelete.getLeft(language)) {
			replacementNode.setLeft(nodeToDelete.getLeft(language), language);
		}
		if (parentNode != null) {
			// If the node was on the right of the parent, set the parent right
			// pointer to point to the replacement node.
			if (parentNode.getRight(language) == nodeToDelete) {
				parentNode.setRight(replacementNode, language);
				// Otherwise, set the parent node left pointer to point to the replacement node.
			} else {
				parentNode.setLeft(replacementNode, language);
			}
		} else {
			setRoot(replacementNode, language);
		}
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
