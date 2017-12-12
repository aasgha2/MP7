/***
 * Hangman
 * Ali Asghar - aasgha2
 * Andy Medina - amedin44
 */
import java.io.*;
import java.util.*;
public class Hangman {
	static String[] wordBank = {"recursion", "array", 
		"algorithm", "class", "abstraction", "binary", "iteration", "initialize", "declare", "mergesort"}; 
	
	static final int MAX_MISSES_ALLOWED = 6;
	
	public String wordAnswer;
	
	public int missed;
	
	public int totalGuesses;
	
	public boolean isWon;
	
	public boolean isLost;
	
	ArrayList<String> characterBank;
	
	public String hiddenWord;
	
	
	public Hangman() {
		this.wordAnswer = wordBank[(int) (Math.random() * wordBank.length)];
		hiddenWord = createHiddenWord(wordAnswer.length());
		this.missed = 0;
		this.isWon = false;
		this.isLost = false;
		characterBank = new ArrayList<String>();
		process();
	}
	
	public String createHiddenWord(int length) {
		String returnValue = "";
		for (int i = 0; i < length; i++) {
			returnValue += "_";
		}
		return returnValue;
	}
	
	public void process() {
		Scanner scan = new Scanner(System.in);
		System.out.println("---Guess a letter!---");
		String input = scan.nextLine().toLowerCase();
		while (input.length() == 0 || input.length() > 1) {
			System.out.println ("Make sure your guess is one letter. Try again.");
			input = scan.nextLine().toLowerCase();
		}
		while(isInLetterBank(input)) {
			System.out.println("You have entered this letter before");
			input = scan.nextLine().toLowerCase();
		}
		totalGuesses++;
		if(wordAnswer.contains(input)) {
			System.out.println("\nNice!\n");
			for(int i = 0; i < wordAnswer.length(); i++) {
				if (wordAnswer.substring(i, i + 1).equals(input)) {
					hiddenWord = hiddenWord.substring(0,i) + input + hiddenWord.substring(i + 1);
				}
			}
		} else {
			System.out.println("\nMiss!\n");
			missed++;
		}
		characterBank.add(input);
		System.out.println("Current progress: " + hiddenWord + "\nNumber of chances left: " + (MAX_MISSES_ALLOWED - missed) + "\n");
		System.out.println("Word Bank:\n" + characterBank + "\n");
		checkStatus();
	}
	
	public void checkStatus() {
		if (missed > MAX_MISSES_ALLOWED) {
			isLost = true;
			System.out.println("The word was " + wordAnswer + ". Sorry! Better luck next time.");
		}
		else if (hiddenWord.equals(wordAnswer)) {
			isWon = true;
			System.out.print("YOU GOT IT! Good job!\nYou guessed: " + totalGuesses + " times");
		} else {
			process();
		}
	}
	
	public boolean isInLetterBank(String pInput) {
		for(int i = 0; i < characterBank.size(); i++) {
			if(pInput.equals(characterBank.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public static void main (String[] args) {
		Hangman game = new Hangman();
	}
	}