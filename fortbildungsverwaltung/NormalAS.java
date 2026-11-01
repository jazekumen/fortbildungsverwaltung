package fortbildungsverwaltung;

public final class NormalAS {
    private static final NormalAS INSTANCE = new NormalAS();
    private final NormalK kontrolle = new NormalK();
    private NormalAS() { }
    public static NormalAS getInstance() { return INSTANCE; }
    void oeffnen() {
        while (FortbildungsverwaltungHK.SERVICE.istAngemeldet()) {
            String aktion = Auswaehlen.waehleAus(kontrolle.menue());
            try { switch (aktion) {
                case "Sachbearbeiter erfassen": SachbearbeiterErfassenAAS.getInstance().oeffnen(); break;
                case "Sachbearbeiter löschen": SachbearbeiterLoeschenAAS.getInstance().oeffnen(); break;
                case "Sachbearbeiter bearbeiten": NormalSachbearbeiterBearbeitenAAS.getInstance().oeffnen(); break;
                case "Fortbildung zuordnen": FortbildungZuordnenAAS.getInstance().oeffnen(); break;
                case "Zuordnung löschen": FortbildungsZuordnungLoeschenAAS.getInstance().oeffnen(); break;
                case "Fortbildungen anzeigen": FortbildungsZuordnungenAnzeigenAAS.getInstance().oeffnen(); break;
                case "Abmelden": return;
            } } catch (IllegalArgumentException ex) { System.out.println(ex.getMessage()); }
        }
    }
}
