import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Tree {
	public Node root;

	public Tree() {
		root = null;
	}

	public Tree(Node italianRoot, Node root) {
		this.root = root;
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

	public void setRoot(Node newRoot) {
		this.root = newRoot;
	}

	public Node getRoot(String language) {
		return root;
	}

	public void displayTree(Node current) {
		if (current != null) {
			displayTree(current.getEnglishLeft());
			System.out.println(current.getEnglishTranslation() + "\t" + current.getItalianTranslation());
			displayTree(current.getEnglishRight());
		}
	}

	public void displayTreeAlternate(Node current) {
		if (current != null) {
			System.out.println(current.getEnglishTranslation() + "\t" + current.getItalianTranslation());
			displayTree(current.getEnglishLeft());
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
		// Create the new node.
		Node newNode = new Node(italianWord, englishWord);
		Node previous = null;
		// Start with the English tree.
		String language = "english";
		// If the new node is found in the tree (search for the Italian and English).
		if (findNode(newNode.getEnglishTranslation(), "english") != null
				|| findNode(newNode.getItalianTranslation(), "italian") != null) {
			System.out.println("This word (English or Italian) already exists, it will not be added");
			// Otherwise, if the English root is null, make the node the root.
		} else if (root == null) {
			System.out.println("Adding root...");
			root = newNode;
			/*
			 * Otherwise, if the node is not already in the tree, and both the roots are
			 * already taken...
			 */
		} else {
			// Do this twice for each tree...
			for (int i = 0; i < 2; i++) {
				// The current node is the root.
				Node current = root;
				while (current != null) {
					previous = current;
					// If the newNode word is before the current node...
					if (newNode.getTranslation(language).compareTo(current.getTranslation(language)) < 0) {
						// Make the current the node on the left.
						current = current.getLeft(language);
						// If that is null...
						if (current == null) {
							// Set the previous node.s left pointer to the newNode.
							previous.setLeft(newNode, language);
						}
						// Else if the newNode is after the current node...
					} else if (newNode.getTranslation(language).compareTo(current.getTranslation(language)) > 0) {
						// Make the current the node on the right.
						current = current.getRight(language);
						// If this node is null...
						if (current == null) {
							// Set the previous node.s right pointer to the newNode.
							previous.setRight(newNode, language);
						}
					}
				}
				// Then change the language and repeat the process.
				language = changeLanguage(language);
			}
		}
	}

	public Node findNode(String searchWord, String language) {
		Node current = root;
		// While the current node is not null...
		while (current != null) {
			// If the word in current is the searchWord...
			if (current.getTranslation(language).equals(searchWord)) {
				// return current.
				return current;
				// Else if the current node's word is before the searchWord...
			} else if (searchWord.compareTo(current.getTranslation(language)) > 0) {
				// Make the current the node on the right of current.
				current = current.getRight(language);
				// Else go to the left.
			} else if (searchWord.compareTo(current.getTranslation(language)) < 0) {
				current = current.getLeft(language);
			}
		}
		// If the root of the other language is not null and is the searchWord...
//		if (getRoot(changeLanguage(language)) != null
//				&& getRoot(changeLanguage(language)).getTranslation(language).equals(searchWord)) {
//			// Return the root of the other language (This should not have to happen).
//			return getRoot(changeLanguage(language));
//		}
		System.out.println("NNF");
		return null;
	}

	public Node findParentNode(String wordToFind, String language) {
		Node current = root;
		Node previous = null;
		// While the current is not null...
		while (current != null) {
			// If the current node's word is the wordToFind...
			if (current.getTranslation(language).equals(wordToFind)) {
				if (current.getTranslation(language).equals(root.getTranslation(language))) {
					return null;
				}
				// Return the previous node (the parent of the current node.
				return previous;
				// Else if the wordToFind is before the current...
			} else if (wordToFind.compareTo(current.getTranslation(language)) > 0) {
				/*
				 * Make the previous node the current node and make the current node the one to
				 * the right.
				 */
				previous = current;
				current = current.getRight(language);
			} else if (wordToFind.compareTo(current.getTranslation(language)) < 0) {
				// Else (if the wordToFind is after the current)...
				previous = current;
				// Go to the left.
				current = current.getLeft(language);
			}
		}
		System.out.println("Parent node not found.");
		return previous;
	}

	public void removeFromTree(String wordToDelete, String language) {
		Node nodeToDelete = null;
		for (int i = 0; i < 2; i++) {
			nodeToDelete = findNode(wordToDelete, language);
			Node parentNode = findParentNode(wordToDelete, language);
			if (nodeToDelete.getRight(language) == null && nodeToDelete.getLeft(language) == null) {
				deleteLeaf(nodeToDelete, parentNode, language);
				// If the node has one child node on the right.
			} else if (nodeToDelete.getRight(language) != null && nodeToDelete.getLeft(language) == null
					|| nodeToDelete.getRight(language) == null && nodeToDelete.getLeft(language) != null) {
				deleteNodeWithOneChild(nodeToDelete, parentNode, language);
				// If the node has two child nodes, one on either side.
			} else if (nodeToDelete.getRight(language) != null && nodeToDelete.getLeft(language) != null) {
				deleteNodeWithTwoChildren(nodeToDelete, parentNode, i, language);
			}
			// Change the language before repeating the process.
			language = changeLanguage(language);
			wordToDelete = nodeToDelete.getTranslation(language);
		}
		if (findParentNode(nodeToDelete.getTranslation(language), language) == null) {

		}
	}

	public void deleteLeaf(Node nodeToDelete, Node parentNode, String language) {
		if (parentNode.getRight(language) != null && parentNode.getRight(language).equals(nodeToDelete)) {
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

	public Node findReplacementNode(Node nodeToDelete, Node parentNode, String language) {
		Node replacementNode = nodeToDelete.getLeft(language);
		while (replacementNode.getRight(language) != null) {
			replacementNode = replacementNode.getRight(language);
		}
		return replacementNode;
	}

	public void deleteNodeWithTwoChildren(Node nodeToDelete, Node parentNode, int i, String language) {
		Node replacementNode = findReplacementNode(nodeToDelete, parentNode, language);
		if (replacementNode.getLeft(language) == null) {
			deleteLeaf(replacementNode, findParentNode(replacementNode.getTranslation(language), language), language);
		} else {
			deleteNodeWithOneChild(replacementNode, findParentNode(replacementNode.getTranslation(language), language),
					language);
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
			/*
			 * If there has already been a swap and the node has no parent node, set the
			 * root as the replacement node.
			 */
		} else if (i == 1) {
			setRoot(replacementNode);
		}
	}

	/**
	 * This method checks if the tree is empty.
	 * 
	 * @return true or false
	 */
	public boolean isTreeEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}
}
