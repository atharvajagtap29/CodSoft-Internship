package Internship_Tasks;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {
	
    public static void playGame() {
    	
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();  // random class object
        int score = 0;  // score variable to store user score
        int maxAttempts = 5;  // max tries

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            int target = random.nextInt(100) + 1;  // generating a random number > 0
            //System.out.println(target+" is the number");
            int attempts = 0;  // user attempts

            System.out.println("\nI have generated a number between 1 and 100. Can you guess it?\n");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                int guess = scanner.nextInt();  // user's guess

                if (guess == target) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score++;
                    break;
                } else if (guess < target) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                attempts++;
            }

            if (attempts == maxAttempts) {
                System.out.println("Oops! You've exhausted your total attempts.");
                System.out.println("The correct number was: " + target);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Game over. Your score: " + score);
        scanner.close();
    }
    
    public static void main(String[] args) {
        playGame();
    }
}
