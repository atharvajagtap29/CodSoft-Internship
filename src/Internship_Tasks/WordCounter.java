package Internship_Tasks;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCounter {

	// word count function
	static int countWord(String text) {
		String arr[] = text.split(" ");
		return arr.length;
	}

	// frequency of word function
	static int wordFrequency(String text, String word) {
		String[] arr = text.split("\\b");
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equalsIgnoreCase(word)) {
				cnt++;
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		System.out.println("Word & Frequency Count Program");
		System.out.println("---------------------------------------------");

		// Prompting for input type
		System.out.println("Enter 'T' to input text or 'F' to input a file:");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		if (choice.equalsIgnoreCase("t")) {

			// Prompting user for text input
			System.out.print("Enter the text: ");
			String text = scanner.nextLine();
			int wordCount = countWord(text);
			System.out.println("Total word count: " + wordCount);

			System.out.println("Do you want to check the frequency of a word in your text? Y/N");
			String freqChoice = scanner.nextLine();

			if (freqChoice.equalsIgnoreCase("Y")) {
				System.out.println("Enter the word, whose frequency you want to check.");
				String word = scanner.nextLine();
				int frequency = wordFrequency(text, word);
				System.out.println("The frequency of " + word + " is " + frequency);
			} else if (freqChoice.equalsIgnoreCase("N")) {
				System.out.println("Sure, Thankyou!");
			} else {
				System.out.println("Enter a valid input");
			}

		} else if (choice.equalsIgnoreCase("F")) {

			// Prompting user for file input
			System.out.print("Enter the file name: ");
			String filename = scanner.nextLine();
			try {
				File file = new File(
						"C:\\Users\\Atharva JAgtap\\Java Eclipse Workspace\\CodSoft\\src\\OtherFiles\\" + filename);
				Scanner fileScanner = new Scanner(file);
				StringBuilder sb = new StringBuilder();
				while (fileScanner.hasNextLine()) {
					sb.append(fileScanner.nextLine());
					sb.append(" ");
				}
				fileScanner.close();

				String fileContent = sb.toString();
				int wordCount = countWord(fileContent);
				System.out.println("Total word count: " + wordCount);

				// prompting user for frequency check
				System.out.println("Do you want to check the frequency of a word in your file? Y/N");
				String freqChoice = scanner.nextLine();

				if (freqChoice.equalsIgnoreCase("Y")) {
					System.out.println("Enter the word, whose frequency you want to check");
					String word = scanner.nextLine();
					int frequency = wordFrequency(fileContent, word);
					System.out.println("The frequency of " + word + " is " + frequency);
				} else if (freqChoice.equalsIgnoreCase("N")) {
					System.out.println("Sure, Thankyou!");
				} else {
					System.out.println("Enter a valid input");
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}

		} else {
			System.out.println("Invalid Choice.");
		}

		scanner.close();
	}
}
