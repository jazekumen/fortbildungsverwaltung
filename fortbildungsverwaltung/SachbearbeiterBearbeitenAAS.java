package fortbildungsverwaltung;

public class SachbearbeiterBearbeitenAAS {
    protected final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    protected SachbearbeiterBearbeitenAAS() { }
    protected void modifiziereSachbearbeiter(boolean rolleAenderbar) {
        String alt = SachbearbeiterAuswaehlenAAS.getInstance().oeffnen();
        SachbearbeiterS sicht = SachbearbeiterS.getInstance(); sicht.praesentiereSachbearbeiter(alt);
        String neu = sicht.name("Neuer Benutzername:");
        String passwort = sicht.passwort("Neues Passwort (leer: beibehalten):");
        boolean admin = rolleAenderbar ? sicht.rolle() : kontrolle.berechtigung(alt);
        kontrolle.bearbeiten(alt, neu, passwort, admin); System.out.println("Sachbearbeiter geändert: " + neu);
    }
}
