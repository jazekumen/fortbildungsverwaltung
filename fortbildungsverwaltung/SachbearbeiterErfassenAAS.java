package fortbildungsverwaltung;

public final class SachbearbeiterErfassenAAS {
    private static final SachbearbeiterErfassenAAS INSTANCE = new SachbearbeiterErfassenAAS();
    private final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    private SachbearbeiterErfassenAAS() { }
    public static SachbearbeiterErfassenAAS getInstance() { return INSTANCE; }
    public void oeffnen() {
        SachbearbeiterS s = SachbearbeiterS.getInstance();
        String name = s.name("Benutzername:"); String passwort = s.passwort("Passwort:");
        kontrolle.erfassen(name, passwort, s.rolle()); System.out.println("Sachbearbeiter erfasst.");
    }
}
