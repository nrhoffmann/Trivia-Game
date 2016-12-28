import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Question {

	private String question, ans[] = new String[4];
	private int correctAns;
	private final int QUESTION_NUM;
	private static String path;
	private boolean flagPathSet;
	private static int counter;
	
	public Question() throws IOException {
		QUESTION_NUM = counter++;
		setQuestion();
	}
	
	public int askQuestion() {
		return JOptionPane.showOptionDialog(null, question,  "Question " + (QUESTION_NUM + 1), JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, ans, null);
	}
	
	public boolean isCorrect(int ans) {
		if (ans == correctAns || correctAns == -1)
			return true;
		else
			return false;
	}
	
	public static void setPath(String string) {
		path = string;
	}
	
	public void resetQuestion() throws IOException {
		setQuestion();
	}
	
	private void setQuestion() throws IOException {
		int line = 0;
		File qFile = new File(path);
		Scanner inputFile = new Scanner(qFile);
		inputFile.useDelimiter(";");
		while (line < QUESTION_NUM) {
			inputFile.nextLine();
			line++;
		}
		question = inputFile.next();
		for (int i = 0; i < ans.length; i++)
			ans[i] = inputFile.next();
		correctAns = Integer.parseInt(inputFile.next());
		inputFile.close();
	}
	
}
