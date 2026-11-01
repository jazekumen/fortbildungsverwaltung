package fortbildungsverwaltung;

/** Gemeinsame, Android-unabhängige Kontrollfassade mit Sitzungsrechten. */
public final class FortbildungsService {
    private Sachbearbeiter angemeldet;
    private boolean adminRolle;
    private static void pruefe(String fehler) {
        if (fehler != null) throw new IllegalArgumentException(fehler);
    }
    public void laden(java.io.File datei) {
        abmelden(); Sachbearbeiter.setzeSpeicherdatei(datei); Sachbearbeiter.einlesen();
    }
    public void speichern() { Sachbearbeiter.abspeichern(); }
    public void anmelden(String name, String passwort, boolean admin) {
        abmelden(); LoginK k = new LoginK();
        pruefe(k.passwortPasstZuBenutzername(name, passwort));
        pruefe(k.gewaehlteBerechtigungPasstZuSachbearbeiter(name, admin));
        angemeldet = Pruefung.sachbearbeiter(name); adminRolle = admin;
    }
    public void abmelden() { angemeldet = null; adminRolle = false; }
    public boolean istAngemeldet() {
        return angemeldet != null && Sachbearbeiter.gib(angemeldet.gibBenutzername()) == angemeldet
                && (!adminRolle || angemeldet.istAdmin());
    }
    public boolean istAdminRolle() { return istAngemeldet() && adminRolle; }
    private void pruefeSitzung() { if (!istAngemeldet()) throw new IllegalArgumentException("Bitte erneut anmelden."); }
    private void pruefeAdmin() { pruefeSitzung(); if (!adminRolle) throw new IllegalArgumentException("Administratorrolle erforderlich."); }
    public String[] namen() { pruefeSitzung(); return Sachbearbeiter.gibAlleNamen(); }
    public boolean berechtigung(String name) { pruefeSitzung(); return Pruefung.sachbearbeiter(name).istAdmin(); }
    public void erfassen(String name, String passwort, boolean admin) {
        pruefeAdmin(); pruefe(new SachbearbeiterErfassenK().erzeugeSachbearbeiter(name, passwort, admin));
    }
    public void bearbeiten(String alt, String neu, String passwort, boolean admin) {
        pruefeSitzung(); Sachbearbeiter s = Pruefung.sachbearbeiter(alt);
        if (!adminRolle && admin != s.istAdmin()) throw new IllegalArgumentException("Berechtigung darf in Normalrolle nicht geändert werden.");
        if (passwort == null) throw new IllegalArgumentException("Passwort fehlt.");
        pruefe(new SachbearbeiterBearbeitenK().schreibeSachbearbeiter(alt, neu, passwort.isEmpty() ? s.gibPasswort() : passwort, admin));
    }
    public void loeschen(String name) { pruefeAdmin(); pruefe(new SachbearbeiterLoeschenK().loescheSachbearbeiter(name)); }
    public String[] moeglicheFortbildungen(String name, boolean bestanden) {
        pruefeSitzung(); FortbildungAuswaehlenK k = new FortbildungAuswaehlenK();
        return bestanden ? k.gibZuBestehendeFortbildungenNamen(name) : k.gibZuBelegendeFortbildungenNamen(name);
    }
    public void zuordnen(String name, String titel, boolean bestanden) {
        pruefeSitzung(); FortbildungZuordnenK k = new FortbildungZuordnenK();
        if (bestanden) k.besteheFortbildung(name, titel); else k.belegeFortbildung(name, titel);
    }
    public String[] zuordnungen(String name, boolean bestanden) {
        pruefeSitzung(); FortbildungszuordnungenAnzeigenK k = new FortbildungszuordnungenAnzeigenK();
        return bestanden ? k.gibBestandeneFortbildungenNamen(name) : k.gibBelegteFortbildungenNamen(name);
    }
    public void zuordnungLoeschen(String name, String titel) {
        pruefeSitzung(); pruefe(new FortbildungszuordnungLoeschenK().loescheFortbildungszuordnung(name, titel));
    }
}
