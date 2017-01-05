import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Game {
	private static final String[] Q_SETS_NAME = {"Computer Science", "Abstract", "Presidents", "Random"};
	private static final String[] Q_SETS_PATH = {"CS.txt", "Abstract.txt", "Presidents.txt", "*"};
	private static Player[] players = new Player[2];
	private static ArrayList<Question>[] questions = new ArrayList[2];
	
	public static void main(String[] args) throws IOException{
		int counter;
		initializeGame();
		for (int i = 0; i < players.length; i++){
			counter = 0;
			players[i].prompt("it's your turn");
			for (Question thisQuestion : questions[i])
				players[i].recordAnswer(thisQuestion.isCorrect(thisQuestion.askQuestion(counter++)));
		}
		showPoints();
		showWinner();
		System.exit(0);
	}

	/**
	 * Initializes the game with 2 players and 10 questions.
	 * @throws IOException
	 */
	private static void initializeGame() throws IOException {
		Random num = new Random();
		String selectedPath;
		players[0] = new Player();
		players[1] = new Player();
		for (int i = 0; i < questions.length; i++)
			questions[i] = new ArrayList<>();
		selectedPath = getPath();
		Question.setPath(selectedPath);
		for (ArrayList<Question> thisHalf : questions)
			for (int i = 0; i < 5; i++)
				thisHalf.add(new Question());
		if (selectedPath.equals("*"))
			for (ArrayList<Question> thisHalf : questions)
				for (Question thisQuestion : thisHalf) {
				Question.setPath(getPath(num.nextInt((Q_SETS_PATH.length - 1))));
				thisQuestion.resetQuestion();
				}

	}

	/**
	 * Displays the winner.
	 */
	private static void showWinner() {
		int winner;
		if (players[0].percentCorrect() == players[1].percentCorrect())
			JOptionPane.showMessageDialog(null, "It's a tie.",
					"Congratulations", JOptionPane.PLAIN_MESSAGE);
		else {
			if (players[0].percentCorrect() > players[1].percentCorrect())
				winner = 0;
			else
				winner = 1;
			JOptionPane.showMessageDialog(null, players[winner].getName() + " won!",
					"Congratulations", JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * Gets the path of the question file.
	 * @return the path of the question file.
	 */
	private static String getPath() {
		String selection = (String) JOptionPane.showInputDialog(null ,
				"Select a question set:", "Topic",JOptionPane.PLAIN_MESSAGE,null,Q_SETS_NAME,
				"Computer Science");
		for (int i = 0; i < Q_SETS_NAME.length; i++)
			if (Q_SETS_NAME[i].equals(selection))
				return Q_SETS_PATH[i];
		return "*"; //If no set is selected, then random questions are assigned.
	}

	/**
	 * Gets the path for a specific index
	 * @param i the index of the path.
	 * @return the path.
	 */
	private static String getPath(int i) {
		return Q_SETS_PATH[i];
	}
	/**
	 * Displays the points for each player
	 */
	private static void showPoints() {
		for (Player thisPlayer : players)
			JOptionPane.showMessageDialog(null,
					"You got " + thisPlayer.percentCorrect() + " percent correct.", thisPlayer.getName(),
					JOptionPane.PLAIN_MESSAGE);
	}
}
