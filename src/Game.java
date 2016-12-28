import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Game {
	static int counter;
	static final String[] Q_SETS_NAME = {"Computer Science", "Abstract", "Presidents"};
	static final String[] Q_SETS_PATH = {"CS.txt", "Abstract.txt", "Presidents.txt"};
	static Player[] players = new Player[2];
	static Question[] questions = new Question[10];
	static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException{
		initializeGame();
		for (int i = 0; i < players.length; i++){
			players[i].prompt("go");
			for (int i1 = 0; i1 < questions.length; i1++)
				players[i].recordAnswer(nextQuestion());
		}
		showWinner();
		System.exit(0);
	}

	public static void initializeGame() throws IOException {
		players[0] = new Player();
		players[1] = new Player();
		Question.setPath(getPath());
		for (int i = 0; i < questions.length; i++) {
			questions[i] = new Question();
		}
	}
	
	public static boolean nextQuestion() {
		boolean temp = questions[counter].isCorrect(questions[counter].askQuestion());
		if (++counter == 10)
			counter = 0;
		return temp;
	}
	
	public static void showWinner() {
		int winner = 0;
		for (int i = 1; i < players.length; i++)
		 if (players[i].percentCorrect() > players[winner].percentCorrect())
			 winner = i;
		JOptionPane.showMessageDialog(null, players[winner].getName(), "Congratulations", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static String getPath() {
		String selection = (String) JOptionPane.showInputDialog(null,"Select a question set:","Topic",JOptionPane.PLAIN_MESSAGE,null,Q_SETS_NAME,"Computer Science");
		for (int i = 0; i < Q_SETS_NAME.length; i++)
			if (Q_SETS_NAME[i].equals(selection))
				return Q_SETS_PATH[i];
		return null;
	}
	
}
