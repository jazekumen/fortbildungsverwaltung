package fortbildungsverwaltung;

final class SachbearbeiterS {
    private static final SachbearbeiterS INSTANCE = new SachbearbeiterS();
    private final SachbearbeiterK kontrolle = new SachbearbeiterK();
    private SachbearbeiterS() { }
    static SachbearbeiterS getInstance() { return INSTANCE; }
    String name(String text) { return Eingabe.eingeben(text); }
    String passwort(String text) { return Eingabe.eingeben(text); }
    boolean rolle() { return Auswaehlen.waehleAus(new String[]{"Administrator", "Normalsachbearbeiter"}).equals("Administrator"); }
    void praesentiereSachbearbeiter(String name) {
        System.out.println("Benutzername: " + name);
        System.out.println("Berechtigung: " + (kontrolle.gibBerechtigung(name) ? "Administrator" : "Normalsachbearbeiter"));
    }
}
