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
}
