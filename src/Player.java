import javax.swing.JOptionPane;

public class Player {
	private String playerName;
	private int counterQuestions;
	private final int PLAYER_NUM;
	private static int counterPlayers;
	private boolean questions[] = new boolean[5];

	/**
	 * Constructor.
	 * @param name the player's name;
	 */
	public Player(String name) {
		PLAYER_NUM = counterPlayers++;
		setName(name);
	}

	/**
	 * Constructor.
	 */
	public Player() {
		PLAYER_NUM = counterPlayers++;
		setName();
	}

	/**
	 * Records the answer in a for each question in a parallel boolean array.
	 * @param answer a boolean answer.
	 */
	public void recordAnswer(boolean answer) {
		questions[counterQuestions++] = answer;
	}

	/**
	 * Calculates the players score.
	 * @return the players score.
	 */
	public double percentCorrect() {
		int total = 0;
		for (int i = 0; i < questions.length; i++)
			if (questions[i] == true)
				total++;
		return 100 * ((double) total / questions.length);
	}

	/**
	 * Sets the player's name.
	 */
	public void setName() {
		playerName = JOptionPane.showInputDialog(null, "What is player your name?",
				"Player " + (PLAYER_NUM + 1), JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Sets the player's name to the param.
	 * @param name the player's name.
	 */
	public void setName(String name) {
		playerName = name;
	}

	/**
	 * Prompts the player.
	 * @param msg the message to prompt the player with.
	 */
	public void prompt(String msg) {
		JOptionPane.showMessageDialog(null,
				playerName + ", " + msg + ".");
	}

	/**
	 * Gets the player's name.
	 * @return the player's name.
	 */
	public String getName() {
		return playerName;
	}

	/**
	 * Tests if the user answered a particular question correctly.
	 * @param i the index of the question to test.
	 * @return if the question was answered correctly.
	 */
	public boolean wasCorrect(int i) {
		return questions[i];
	}
}
