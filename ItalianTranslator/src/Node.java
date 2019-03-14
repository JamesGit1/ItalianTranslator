
/**
 * This node class is slightly modified so that it can be part of two trees at
 * the same time, hence the fact it has two right and two left links. It will be
 * part of one tree that is sorted by the English word, and one tree sorted by
 * the Italian word.
 * 
 * @author James, Josh, Jesse, Luke.
 *
 */

public class Node {
	Node englishLeft;
	Node englishRight;
	Node italianRight;
	Node italianLeft;
	String englishTranslation;
	String italianTranslation;

	/**
	 * The black constructor, will be used if no parameters are supplied.
	 */

	public Node() {
		englishLeft = null;
		englishRight = null;
		italianLeft = null;
		italianRight = null;
		englishTranslation = "";
		italianTranslation = "";
	}

	/**
	 * The constructor that is used if there are parameters that are given.
	 * 
	 * @param englishRight       The link to the englishTree node to the right.
	 * @param englishLeft        The link to the englishTree node to the left.
	 * @param italianRight       The link to the italianTree node to the right.
	 * @param italianLeft        The link to the italianTree node to the left.
	 * @param englishTranslation The word stored in English.
	 * @param italianTranslation The word stored in Italian.
	 */

	public Node(Node englishRight, Node englishLeft, Node italianRight, Node italianLeft, String englishTranslation,
			String italianTranslation) {
		this.englishLeft = englishLeft;
		this.englishRight = englishRight;
		this.italianRight = italianRight;
		this.italianLeft = italianLeft;
		this.italianTranslation = italianTranslation;
		this.englishTranslation = englishTranslation;
	}

	public Node getEnglishLeft() {
		return englishLeft;
	}

	public void setEnglishLeft(Node englishLeft) {
		this.englishLeft = englishLeft;
	}

	public Node getEnglishRight() {
		return englishRight;
	}

	public void setEnglishRight(Node englishRight) {
		this.englishRight = englishRight;
	}

	public Node getItalianRight() {
		return italianRight;
	}

	public void setItalianRight(Node italianRight) {
		this.italianRight = italianRight;
	}

	public Node getItalianLeft() {
		return italianLeft;
	}

	public void setItalianLeft(Node italianLeft) {
		this.italianLeft = italianLeft;
	}

	public String getEnglishTranslation() {
		return englishTranslation;
	}

	public void setEnglishTranslation(String englishTranslation) {
		this.englishTranslation = englishTranslation;
	}

	public String getItalianTranslation() {
		return italianTranslation;
	}

	public void setItalianTranslation(String italianTranslation) {
		this.italianTranslation = italianTranslation;
	}

	/**
	 * This method returns both translations of the stored word.
	 * 
	 * @return summary A String containing both the Italian and English translation.
	 */

	public String showWordInfo() {
		String summary = englishTranslation + italianTranslation;
		return summary;
	}

	public Node getRight(String language) {
		if (language.equals("italian")) {
			return getItalianRight();
		} else {
			return getEnglishRight();
		}
	}

	public Node getLeft(String language) {
		if (language.equals("italian")) {
			return getItalianLeft();
		} else {
			return getEnglishLeft();
		}
	}

	public void setRight(Node newNode, String language) {
		if (language.equals("italian")) {
			setItalianRight(newNode);
		} else {
			setEnglishRight(newNode);
		}
	}

	public void setLeft(Node newNode, String language) {
		if (language.equals("italian")) {
			setItalianLeft(newNode);
		} else {
			setEnglishLeft(newNode);
		}
	}

	public String getWord(String language) {
		if (language.equals("italian")) {
			return getItalianTranslation();
		} else {
			return getEnglishTranslation();
		}
	}
}
