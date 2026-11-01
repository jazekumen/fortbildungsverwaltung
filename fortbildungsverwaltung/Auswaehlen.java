package fortbildungsverwaltung;
public class Auswaehlen {

    public static String waehleAus(String[] alternativen) {
        if (alternativen == null || alternativen.length == 0)
            throw new IllegalArgumentException("Keine Auswahlmöglichkeiten vorhanden");
        // Prompt the user to enter the desired number
        System.out.println("Bitte geben Sie die gewünschte Nummer ein:");

        // Display the alternatives with their corresponding numbers
        for (int i = 0; i < alternativen.length; i++) {
            System.out.println((i + 1) + ". " + alternativen[i]);
        }

        // Get the user's selection
        while (true) {
            String userInput = Eingabe.eingeben("Hier eingeben: ");

            try {
                int index = Integer.parseInt(userInput) - 1;

                // Check if the index is valid
                if (index >= 0 && index < alternativen.length) {
                    return alternativen[index];
                } else {
                    System.out.println("Ungültigen Index eingegeben. Bitte eingabe wiederholen.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Kein index eingegeben. Bitte eingabe wiederholen.");
            }
        }
    }
}
