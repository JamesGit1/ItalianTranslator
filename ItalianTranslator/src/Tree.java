
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
	public void display(Node english, Node italian) {

		display(english.getEnglishLeft(), italian.getItalianLeft());
		System.out.println(english + "\t" + italian);
		display(english.getEnglishRight(), italian.getItalianRight());

	}

	public void loadDictionary() {
		System.out.println("Test");
	}

}
