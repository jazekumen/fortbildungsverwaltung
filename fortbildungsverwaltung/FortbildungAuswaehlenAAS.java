package fortbildungsverwaltung;

public final class FortbildungAuswaehlenAAS {
    private static final FortbildungAuswaehlenAAS INSTANCE = new FortbildungAuswaehlenAAS();
    private final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    private FortbildungAuswaehlenAAS() { }
    public static FortbildungAuswaehlenAAS getInstance() { return INSTANCE; }
    public String oeffnen(String name, boolean bestanden) {
        String[] titel = kontrolle.moeglicheFortbildungen(name, bestanden);
        if (titel.length == 0) throw new IllegalArgumentException("Keine passende Fortbildung verfügbar.");
        return Auswaehlen.waehleAus(titel);
    }
}
