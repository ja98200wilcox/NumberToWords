package com.jwilcox.translator;

import java.text.MessageFormat;
import java.util.Scanner;

/**
 * A command prompt based Application
 * 
 * @author Jim Wilcox
 *
 */
public class Console implements Application {

	private final Convert convert;

	private static final String MESSAGE_QUIT = "q";
	private static final String MESSAGE_COMMAND = "Enter a number or {0} for quit";
	private static final String MESSAGE_EXIT = "Goodbye";
	private static final String MESSAGE_PROCESSING = "Processing...";
	private static final String MESSAGE_INVALID = "Invalid Value Entered";
	private static final String MESSAGE_WELCOME = "Welcome to Translator\nI am here to help you in your number to word translations";
	private static final String MESSAGE_INSTRUCTIONS = "Please enter a number ranging between {0,number,#} and {1,number,#}\nThe number can only contain digits\nValid 5673\nNot valid 5673.89";

	public Console(Convert convert) {
		this.convert = convert;
	}

	/**
	 * Launch the console application. It writes and reads from the command prompt
	 */
	@Override
	public void start() {
		showWelcomeMessage();
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.println(MessageFormat.format(MESSAGE_COMMAND, MESSAGE_QUIT));
				String numberToBeConverted = scanner.next();
				if (numberToBeConverted.equalsIgnoreCase(MESSAGE_QUIT)) {
					System.out.println(MESSAGE_EXIT);
					break;
				} else if (!Validator.isValid(numberToBeConverted)) {
					showErrorMessage();
					System.out.println("\n");
				} else {
					System.out.println(MESSAGE_PROCESSING);
					System.out.println(convert.convert(Long.valueOf(numberToBeConverted)));
					System.out.println("\n");
				}
			}
		}
	}

	/**
	 * Display an Error Message
	 */
	private void showErrorMessage() {
		System.out.println(MESSAGE_INVALID);
		showInstructions();
	}

	/**
	 * Display the Welcome Message
	 */
	private void showWelcomeMessage() {
		System.out.println(MESSAGE_WELCOME);
		showInstructions();
		System.out.println("\n");
	}

	/**
	 * Display the instructions on how to use the Application
	 */
	private void showInstructions() {
		System.out.println(MessageFormat.format(MESSAGE_INSTRUCTIONS, Validator.NUMBER_MIN, Validator.NUMBER_MAX));
	}
}
