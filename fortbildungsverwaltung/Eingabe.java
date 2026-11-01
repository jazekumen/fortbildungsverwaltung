package fortbildungsverwaltung;
import java.util.Scanner;

public class Eingabe {

    // Create a Scanner object for user input
    private static Scanner scanner = new Scanner(System.in);

    public static String eingeben(String eingabeAufforderung) {
        // Print the input prompt
        System.out.println(eingabeAufforderung);

        // Get the user's input
        String userInput = scanner.nextLine();

        // Return the user's input
        return userInput;
    }
}