package fortbildungsverwaltung;

public final class AdminAS {
    private static final AdminAS INSTANCE = new AdminAS();
    private final AdminK kontrolle = new AdminK();
    private AdminAS() { }
    public static AdminAS getInstance() { return INSTANCE; }
    void oeffnen() {
        while (FortbildungsverwaltungHK.SERVICE.istAngemeldet()) {
            String aktion = Auswaehlen.waehleAus(kontrolle.menue());
            try { switch (aktion) {
                case "Sachbearbeiter erfassen": SachbearbeiterErfassenAAS.getInstance().oeffnen(); break;
                case "Sachbearbeiter löschen": SachbearbeiterLoeschenAAS.getInstance().oeffnen(); break;
                case "Sachbearbeiter bearbeiten": AdminSachbearbeiterBearbeitenAAS.getInstance().oeffnen(); break;
                case "Fortbildung zuordnen": FortbildungZuordnenAAS.getInstance().oeffnen(); break;
                case "Zuordnung löschen": FortbildungsZuordnungLoeschenAAS.getInstance().oeffnen(); break;
                case "Fortbildungen anzeigen": FortbildungsZuordnungenAnzeigenAAS.getInstance().oeffnen(); break;
                case "Abmelden": return;
            } } catch (IllegalArgumentException ex) { System.out.println(ex.getMessage()); }
        }
    }
}
