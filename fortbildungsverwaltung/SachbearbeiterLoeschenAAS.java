package fortbildungsverwaltung;

public final class SachbearbeiterLoeschenAAS {
    private static final SachbearbeiterLoeschenAAS INSTANCE = new SachbearbeiterLoeschenAAS();
    private final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    private SachbearbeiterLoeschenAAS() { }
    public static SachbearbeiterLoeschenAAS getInstance() { return INSTANCE; }
    public void oeffnen() {
        String name = SachbearbeiterAuswaehlenAAS.getInstance().oeffnen();
        System.out.println("Soll Sachbearbeiter " + name + " wirklich gelöscht werden?");
        if (Auswaehlen.waehleAus(new String[]{"Nein", "Ja"}).equals("Nein")) return;
        kontrolle.loeschen(name); System.out.println("Sachbearbeiter gelöscht.");
    }
}
