package fortbildungsverwaltung;

public final class SachbearbeiterAuswaehlenAAS {
    private static final SachbearbeiterAuswaehlenAAS INSTANCE = new SachbearbeiterAuswaehlenAAS();
    private final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    private SachbearbeiterAuswaehlenAAS() { }
    public static SachbearbeiterAuswaehlenAAS getInstance() { return INSTANCE; }
    public String oeffnen() {
        String[] namen = kontrolle.namen();
        if (namen.length == 0) throw new IllegalArgumentException("Keine Sachbearbeiter vorhanden.");
        System.out.println("Sachbearbeiter auswählen:"); return Auswaehlen.waehleAus(namen);
    }
}
