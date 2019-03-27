import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Binary tree to store all the words in the dictionary using the modified node
 * class.
 * 
 * @author James, Josh, Jesse, Luke.
 *
 */

public class Tree {
	public Node root;
	public Node otherRoot;

	/**
	 * Constructor method.
	 */

	public Tree() {
		root = null;
	}

	/**
	 * Constructor method.
	 */

	public Tree(Node italianRoot, Node root) {
		this.root = root;
	}

	/**
	 * Displays the entire dictionary
	 *
	 * @param english  Gets English left and right Tree
	 * @param itatlian Gets Italian left and right Tree
	 */

	public String changeLanguage(String language) {
		if (language.equals("italian")) {
			return "english";
		} else {
			return "italian";
		}
	}

	/**
	 * Sets the root of the tree.
	 * 
	 * @param newRoot The new root of the tree.
	 */

	public void setRoot(Node newRoot) {
		this.root = newRoot;
	}

	/**
	 * Gets the root of the tree.
	 * 
	 * @param language The current language, and therefore what tree is being worked
	 *                 on.
	 * @return root The root of the tree
	 */

	public Node getRoot(String language) {
		return root;
	}

	/**
	 * Displays the entire English tree.
	 * 
	 * @param current The current node.
	 */

	public void displayTree(Node current, String language) {
		if (current != null) {
			displayTree(current.getLeft(language), language);
			System.out.println(current.getEnglishTranslation() + "\t" + current.getItalianTranslation());
			displayTree(current.getRight(language), language);
		}
	}

	/**
	 * Displays the tree a different kind of way.
	 * 
	 * @param current The current node.
	 */

	public void displayTreeAlternate(Node current) {
		if (current != null) {
			System.out.println(current.getEnglishTranslation() + "\t" + current.getItalianTranslation());
			displayTreeAlternate(current.getEnglishLeft());
			displayTreeAlternate(current.getEnglishRight());
		}
	}

	/**
	 * Loads the dictionary.
	 */

	public void loadDictionary() {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader("DICTEST.csv");
			br = new BufferedReader(fr);
			String nextLine = br.readLine();

			while (nextLine != null) {
				String[] words = nextLine.split(",");
				// Add to tree pass word 0 and 1
				addToTree(words[0], words[1]);
				nextLine = br.readLine();
			}

			br.close();
			isTreeBalanced(root, "english");

		} catch (Exception e) {
			// TODO Write error handling.
			System.out.println("An error happened");
		}
	}

	/**
	 * Saves the dictionary.
	 * 
	 * @param current The current node.
	 */

	public void saveDictionary(Node current) {
		FileOutputStream os = null;
		PrintWriter pw = null;
		try {
			os = new FileOutputStream("DICTEST.csv");
			pw = new PrintWriter(os);
			save(current, pw);
			pw.flush();
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

	/**
	 * Goes through all the elements in the tree and writes each one to a file.
	 * 
	 * @param current The current node.
	 * @param pw      The print writer.
	 */

	public void save(Node current, PrintWriter pw) {
		if (current != null) {
			pw.println(current.getItalianTranslation() + "," + current.getEnglishTranslation());
			save(current.getEnglishLeft(), pw);
			save(current.getEnglishRight(), pw);
		}
	}

	public void addAgain(Node current, String language) {
		if (current != null) {
			try {
				current.setRight(null, changeLanguage(language));
				current.setLeft(null, changeLanguage(language));
				addToCertainTree(current, changeLanguage(language));
				addAgain(current.getLeft(language), language);
				addAgain(current.getRight(language), language);
			} catch (NullPointerException e) {
				System.out.println("An error occurred, the node could not be accessed." + e);
			}
		}
	}

	/**
	 * Adds a node to a certain tree instead of both.
	 * 
	 * @param newNode  The node that you want to add.
	 * @param language The language that the node is being added to.
	 */

	public void addToCertainTree(Node newNode, String language) {
		// Start are the root.
		Node current = root;
		Node previous = null;
		// If the node is found in the other tree...
		if (findNode(root, newNode.getTranslation(language), language) != null) {
			// End the loop
			return;
			// If there is no root, make the new node the root...
		} else if (root == null) {
			root = newNode;
		} else {
			// This is very similar to the addNode method, but modified to only add to one
			// tree instead of both.
			while (current != null) {
				if (newNode != root) {
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
			}
		}
	}

	/**
	 * Adds an element to the tree.
	 * 
	 * @param italianWord The Italian word.
	 * @param englishWord The English word.
	 */

	public void addToTree(String italianWord, String englishWord) {
		// Create the new node.
		Node newNode = new Node(italianWord, englishWord);
		Node previous = null;
		// Start with the English tree.
		String language = "english";
		// If the new node is found in the tree (search for the Italian and English).
		if (findNode(root, newNode.getEnglishTranslation(), "english") != null
				|| findNode(root, newNode.getItalianTranslation(), "italian") != null) {
			System.out.println("This word (English or Italian) already exists, it will not be added");
			// Otherwise, if the English root is null, make the node the root.
		} else if (root == null) {
			root = newNode;
			/*
			 * Otherwise, if the node is not already in the tree, and both the roots are
			 * already taken...
			 */
		} else {
			// Do this twice for each tree...
			for (int i = 0; i < 2; i++) {
				// The current node is the root.
				System.out.println(newNode.getTranslation(language));
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
			balanceTree(newNode, changeLanguage(language));
		}
	}

	/**
	 * Finds a node in the binary tree.
	 * 
	 * @param searchWord The word that is being searched for.
	 * @param language   The language of the word that is being searched for.
	 * @return current The node once it is found.
	 */

	public Node findNode(Node root, String searchWord, String language) {
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
		return null;
	}

	/**
	 * Finds the parent node of a node.
	 * 
	 * @param wordToFind The word that the user wants to find the parent of.
	 * @param language   The current language, and therefore what tree is being
	 *                   worked on.
	 * @return previous The node that was current before the node was found.
	 */

	public Node findParentNode(Node requestedNode, String language) {
		Node current = root;
		Node previous = null;
		// While the current is not null...
		while (current != null) {
			// If the current node's word is the wordToFind...
			if (current == requestedNode) {
				if (current == root) {
					return null;
				}
				// Return the previous node (the parent of the current node.
				return previous;
				// Else if the wordToFind is before the current...
			} else if (requestedNode.getTranslation(language).compareTo(current.getTranslation(language)) > 0) {
				/*
				 * Make the previous node the current node and make the current node the one to
				 * the right.
				 */
				previous = current;
				current = current.getRight(language);
			} else if (requestedNode.getTranslation(language).compareTo(current.getTranslation(language)) < 0) {
				// Else (if the wordToFind is after the current)...
				previous = current;
				// Go to the left.
				current = current.getLeft(language);
			}
		}
		return null;
	}

	/**
	 * Removes a node from both trees.
	 * 
	 * @param wordToDelete The word that the user wants to be deleted.
	 * @param language     The current language, and therefore what tree is being
	 *                     worked on.
	 */

	public void removeFromTree(String wordToDelete, String language) {
		Node nodeToDelete = null;
		nodeToDelete = findNode(root, wordToDelete, language);
		if (nodeToDelete == root) {
			deleteNodeWithTwoChildren(root, null, "english");
			addAgain(root, "english");
		} else {
			try {
				// Do this twice because there are two trees...
				for (int i = 0; i < 2; i++) {
					Node parentNode = findParentNode(nodeToDelete, language);
					// If nodeToDelete is a leaf...
					if (nodeToDelete.getRight(language) == null && nodeToDelete.getLeft(language) == null) {
						// Run the deleteLeaf method.
						deleteLeaf(nodeToDelete, parentNode, language);
						// If the node has one child node on the right or left...
					} else if (nodeToDelete.getRight(language) != null && nodeToDelete.getLeft(language) == null
							|| nodeToDelete.getRight(language) == null && nodeToDelete.getLeft(language) != null) {
						deleteNodeWithOneChild(nodeToDelete, parentNode, language);
						// If the node has two child nodes, one on either side.
					} else if (nodeToDelete.getRight(language) != null && nodeToDelete.getLeft(language) != null) {
						deleteNodeWithTwoChildren(nodeToDelete, parentNode, language);
					}
					// Change the language before repeating the process.
					language = changeLanguage(language);
					wordToDelete = nodeToDelete.getTranslation(language);
				}
			} catch (Exception e) {
				System.out.println("The word was not found and so cannot be deleted." + e);
			}
		}
	}

	/**
	 * Deletes a leaf.
	 * 
	 * @param nodeToDelete The node that is going to be deleted.
	 * @param parentNode   The parent node of nodeToDelete.
	 * @param language     The current language, and therefore what tree is being
	 *                     worked on.
	 */

	public void deleteLeaf(Node nodeToDelete, Node parentNode, String language) {
		// If nodeToDelete is on the right of its parent node...
		if (parentNode.getRight(language) != null && parentNode.getRight(language).equals(nodeToDelete)) {
			parentNode.setRight(null, language);
			// Otherwise (nodeToDelete is on the left of its parent node)...
		} else {
			parentNode.setLeft(null, language);
		}
	}

	/**
	 * Deletes a node with one child node.
	 * 
	 * @param nodeToDelete The node that is going to be deleted.
	 * @param parentNode   The parent node of nodeToDelete.
	 * @param language     The current language, and therefore what tree is being
	 *                     worked on.
	 */

	public void deleteNodeWithOneChild(Node nodeToDelete, Node parentNode, String language) {
		// (Finding what side of nodeToDelete the child is on).
		// If the child node is on the right of nodeToDelete...
		if (nodeToDelete.getRight(language) != null && nodeToDelete.getLeft(language) == null) {
			// If nodeToDelete is on the right of its parent node...
			if (parentNode.getRight(language) == nodeToDelete) {
				// Set what is on the right of the parent node to what is on the right of
				// nodeToDelete.
				parentNode.setRight(nodeToDelete.getRight(language), language);
				// Otherwise (nodeToDelete is on the left of its parent node)...
			} else {
				// Set what is on the left of the parent node to what is on the right of
				// nodeToDelete.
				parentNode.setLeft(nodeToDelete.getRight(language), language);
			}
			// Otherwise (the child node is on the left of nodeToDelete)...
		} else if (nodeToDelete.getRight(language) == null && nodeToDelete.getLeft(language) != null) {
			// If nodeToDelete is on the left of its parent node...
			if (parentNode.getLeft(language) == nodeToDelete) {
				// Set what is on the left of the parent node to what is on the left of
				// nodeToDelete.
				parentNode.setLeft(nodeToDelete.getLeft(language), language);
				// Otherwise (nodeToDelete is on the right of its parent node)...
			} else {
				// Set what is on the right of the parent node to what is on the left of
				// nodeToDelete.
				parentNode.setRight(nodeToDelete.getLeft(language), language);
			}
		}
	}

	/**
	 * Finds the replacement node if a node with two children is being deleted.
	 * 
	 * @param nodeToDelete The node that is going to be deleted.
	 * @param parentNode   The parent node of nodeToDelete.
	 * @param language     The current language, and therefore what tree is being
	 *                     worked on.
	 * @return replacementNode The node that can replace nodeToDelete.
	 */

	public Node findReplacementNode(Node nodeToDelete, Node parentNode, String language) {
		Node replacementNode = nodeToDelete.getLeft(language);
		// While the node on the right of replacementNode is not null...
		while (replacementNode.getRight(language) != null) {
			// Set replacementNode to what is currently on the right of replacementNode.
			replacementNode = replacementNode.getRight(language);
		}
		return replacementNode;
	}

	public Node findReplacementNodeRight(Node nodeToDelete, Node parentNode, String language) {
		Node replacementNode = nodeToDelete.getRight(language);
		// While the node on the right of replacementNode is not null...
		while (replacementNode.getLeft(language) != null) {
			// Set replacementNode to what is currently on the right of replacementNode.
			replacementNode = replacementNode.getLeft(language);
		}
		return replacementNode;
	}

	/**
	 * Deletes a node with two active connections (for the selected language).
	 * 
	 * @param nodeToDelete The node that is going to be deleted.
	 * @param parentNode   The parent node of nodeToDelete.
	 * @param i            The counter from removeNode, this is the number of times
	 *                     the language has changed.
	 * @param language     The current language, and therefore what tree is being
	 *                     worked on.
	 */

	public void deleteNodeWithTwoChildren(Node nodeToDelete, Node parentNode, String language) {
		Node replacementNode = findReplacementNode(nodeToDelete, parentNode, language);

		// If what is on the left of replacementNode is null...
		if (replacementNode.getLeft(language) == null) {
			// It must be a leaf and therefore deleteLeaf is called.
			deleteLeaf(replacementNode, findParentNode(replacementNode, language), language);
		} else {
			// Otherwise (as nothing can be on the right of replacementNode) it must have
			// one child (on the left).
			deleteNodeWithOneChild(replacementNode, findParentNode(replacementNode, language), language);
		}

		replacementNode.setRight(nodeToDelete.getRight(language), language);

		// If the replacement node is not directly on the left of the nodeToDelete...
		if (replacementNode != nodeToDelete.getLeft(language)) {
			// Set the replacementNode's left to be what's on the left of nodeToDelete.
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
			 * root as the replacement node. (note, this is not very good for tree
			 * balancing).
			 */
		} else {
			setRoot(replacementNode);
		}
	}

	/**
	 * Checks if the tree is empty.
	 * 
	 * @return true or false
	 */

	public boolean isTreeEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	/**
	 * Finds the height of the tree.
	 * 
	 * @param root     The root of the tree.
	 * @param language The language of the tree that the height that is being
	 *                 checked.
	 * @return The height of the tree.
	 */

	public int getHeight(Node node, String language) {
		if (node == null)
			return 0;
		return (1
				+ Math.max(getHeight(node.getLeft(language), language), getHeight(node.getRight(language), language)));
	}

	/**
	 * Balances the tree.
	 * 
	 * @param node     The node that is being parsed into the function.
	 * @param language The language of the tree being worked in.
	 */

	public void balanceTree(Node newNode, String language) {
		Node current = newNode;
		Node replacementNode = null;
		while (current != null) {
			boolean treeBalancing = isTreeBalanced(current, language);
			// If we have a line of 3 unbalanced nodes where the new node is...
			if (treeBalancing == false) {
				System.out.println("Tree unbalanced when adding " + newNode.getTranslation(language));
				if (findNode(current.getLeft(language), newNode.getTranslation(language), language) != null) {
					replacementNode = findReplacementNode(current, findParentNode(current, language), language);
					if (current == root) {
						setRoot(replacementNode);
						addAgain(root, language);
					}
					replacementNode.setRight(current, language);
					if (current.getLeft(language) != replacementNode) {
						replacementNode.setLeft(current.getLeft(language), language);
					} else {
						current.setLeft(null, language);
					}
					removeFromTree(replacementNode.getTranslation(language), language);
					current = newNode;
				} else if (findNode(current.getRight(language), newNode.getTranslation(language), language) != null) {
					replacementNode = findReplacementNodeRight(current, findParentNode(current, language), language);
					if (current == root) {
						setRoot(replacementNode);
						addAgain(root, language);
					}
					replacementNode.setLeft(current, language);
					if (current.getRight(language) != replacementNode) {
						replacementNode.setRight(current.getLeft(language), language);
					} else {
						current.setRight(null, language);
					}
					removeFromTree(replacementNode.getTranslation(language), language);
					current = newNode;
				}
			} else {
				current = findParentNode(current, language);
			}
		}
	}

	/**
	 * Checks if the tree is balanced.
	 * 
	 * @param root     The root of the tree.
	 * @param language The language of the tree that the height that is being
	 *                 checked.
	 * @return Whether the tree is balanced or not.
	 */

	public boolean isTreeBalanced(Node newNode, String language) {
		if (newNode == null)
			return true;
		int heightdifference = getHeight(newNode.getLeft(language), language)
				- getHeight(newNode.getRight(language), language);
		if (Math.abs(heightdifference) > 1) {
			return false;
		} else {
			return isTreeBalanced(newNode.getLeft(language), language)
					&& isTreeBalanced(newNode.getRight(language), language);
		}
	}
}
