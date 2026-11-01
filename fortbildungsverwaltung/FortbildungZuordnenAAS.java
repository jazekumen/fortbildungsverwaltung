package fortbildungsverwaltung;

public final class FortbildungZuordnenAAS {
    private static final FortbildungZuordnenAAS INSTANCE = new FortbildungZuordnenAAS();
    private final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    private FortbildungZuordnenAAS() { }
    public static FortbildungZuordnenAAS getInstance() { return INSTANCE; }
    public void oeffnen() {
        String name = SachbearbeiterAuswaehlenAAS.getInstance().oeffnen();
        boolean bestanden = Auswaehlen.waehleAus(new String[]{"belegt", "bestanden"}).equals("bestanden");
        String titel = FortbildungAuswaehlenAAS.getInstance().oeffnen(name, bestanden);
        kontrolle.zuordnen(name, titel, bestanden); System.out.println("Fortbildung erfolgreich zugeordnet.");
    }
}
