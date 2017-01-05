import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Question {

	private String question, ans[] = new String[4];
	private int correctAns;
	private final int QUESTION_NUM;
	private static String path;
	private static int counter;

	/**
	 * Constructor.
	 * @throws IOException
	 */
	public Question() throws IOException {
		QUESTION_NUM = counter++;
		setQuestion();
	}

	/**
	 * Asks the question.
	 * @param relativeQuestionNum the number that this question is relative to the current player.
	 * @return the index of the selected answer.
	 */
	public int askQuestion(int relativeQuestionNum) {
		return JOptionPane.showOptionDialog(null, question,
				"Question " + (relativeQuestionNum + 1), JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, ans, null);
	}

	/**
	 * Tests if the question was answered correctly.
	 * @param ans the index of the answer chosen.
	 * @return if the question was answered correctly.
	 */
	public boolean isCorrect(int ans) {
		if (ans == correctAns || correctAns == -1)
			return true;
		else {
			JOptionPane.showMessageDialog(null, getCorrectAns(), "Uh-oh",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	/**
	 * Sets the path for all question objects.
	 * @param string the path.
	 */
	public static void setPath(String string) {
		path = string;
	}

	/**
	 * Resets the question.
	 * @throws IOException
	 */
	public void resetQuestion() throws IOException {
		setQuestion();
	}

	/**
	 * Sets the question.
	 * @throws IOException
	 */
	private void setQuestion() throws IOException {
		if (!path.equals("*")) {
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

	/**
	 * Gets the text of the correct answer.
	 * @return a string of the question and the correct answer.
	 */
	public String getCorrectAns() {
		return "The correct answer was: " + ans[correctAns];
	}
	
}
