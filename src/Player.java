import javax.swing.JOptionPane;

public class Player {
	private  String playerName;
	private int counterQuestions;
	private final int PLAYER_NUM;
	private static int counterPlayers;
	private boolean questions[] = new boolean[10];
	

	public Player(String name) {
		PLAYER_NUM = counterPlayers++;
		setName(name);
	}
	
	public Player() {
		PLAYER_NUM = counterPlayers++;
		setName();
	}
	
	public void recordAnswer(boolean answer) {
		questions[counterQuestions++] = answer;
	}
	
	public double percentCorrect() {
		int total = 0;
		for (int i = 0; i < questions.length; i++)
			if (questions[i] == true)
				total++;
		return 100 * ((double) total / questions.length);
	}
	
	public void setName() {
		playerName = JOptionPane.showInputDialog(null, "What is player your name?", "Player " + (PLAYER_NUM + 1), JOptionPane.PLAIN_MESSAGE);
	}
	
	public void setName(String name) {
		playerName = name;
	}
	
	public void prompt(String msg) {
		JOptionPane.showMessageDialog(null, playerName + ", " + msg + ".");
	}
	
	public String getName() {
		return playerName;
	}
}
