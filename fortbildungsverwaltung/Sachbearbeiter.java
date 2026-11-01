package fortbildungsverwaltung;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Sachbearbeiter implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // Invariante: mindestens ein Administrator muss in dieSachbearbeiter vorhanden
    // sein
    static private Map<String, Sachbearbeiter> dieSachbearbeiter = new HashMap<>();

    static String[] gibAlleNamen() {
        return dieSachbearbeiter.keySet().stream().sorted().toArray(String[]::new);
    }

    static Sachbearbeiter gib(String benutzername) {
        return dieSachbearbeiter.get(benutzername);
    }

    // fuegeHinzu ist private wegen der
    // Invariante: jeder Sachbearbeiter, der erzeugt wird, wird auch
    // der Map dieSachbearbeiter hinzugefuegt
    // fuegeHinzu soll daher nur von erzeuge aufgerufen werden

    private static void fuegeHinzu(Sachbearbeiter sachbearbeiter) {
        dieSachbearbeiter.put(sachbearbeiter.gibBenutzername(), sachbearbeiter);
    }

    static boolean mindestensEinSachbearbeiterExistiert() {
        return !dieSachbearbeiter.isEmpty();
    }

    static boolean mindestensEinAdminExistiert() {

        for (Sachbearbeiter s : dieSachbearbeiter.values()) {
            if (s.istAdmin() == true) {
                return true;
            }
        }

        return false;
    }

    static String pruefeBenutzername(String benutzername) {
        if (benutzername == null) return "Benutzername fehlt";
        boolean istOk = !dieSachbearbeiter.containsKey(benutzername);

        if (!istOk) {
            return "Den Benutzer " + benutzername + " gibt es bereits";
        }

        String regAusdruck = "[a-zäöüßA-ZÄÖÜ_]+";
        istOk = benutzername.matches(regAusdruck);
        if (istOk) {
            return null;
        } else {
            return "Der Benutzername entspricht leider nicht den Vorgaben für Benutzernamen";
        }
    }

    static String pruefePasswort(String passwort) {
        if (passwort == null) return "Passwort fehlt";
        String regAusdruck = ".*[A-ZÄÖÜ].*[A-ZÄÖÜ].*";
        boolean istOk = passwort.matches(regAusdruck);
        regAusdruck = ".*[a-zäöüß].*[a-zäöüß].*";
        istOk &= passwort.matches(regAusdruck);
        regAusdruck = ".*\\d.*\\d.*";
        istOk &= passwort.matches(regAusdruck);
        // laut Wikipedia ist ein Sonderzeichen ein Zeichen, das weder Buchstabe noch
        // Ziffer ist
        String sonderzeichen = "[^a-zäöüßA-ZÄÖÜ0-9]";
        regAusdruck = ".*" + sonderzeichen + ".*" + sonderzeichen + ".*";
        istOk &= passwort.matches(regAusdruck);
        istOk &= passwort.length() >= 10;
        if (istOk) {
            return null;
        } else {
            return "Das Passwort entspricht leider nicht den Vorgaben";
        }
    }

    // Invariante: jeder Sachbearbeiter, der erzeugt wird, wird auch
    // der Map dieSachbearbeiter hinzugefuegt
    // wenn das erzeugen klappt, wird null zurueckgegeben
    // wenn es nicht klappt, wird eine Fehlermeldung zurueckgegeben
    static String erzeuge(String benutzername, String passwort, boolean istAdmin) {
        String fehlermeldung = pruefeBenutzername(benutzername);
        if (fehlermeldung == null) {
            fehlermeldung = pruefePasswort(passwort);
            if (fehlermeldung == null) {
                Sachbearbeiter s = new Sachbearbeiter(benutzername, passwort, istAdmin);
                fuegeHinzu(s);
                return null;
            }
        }
        return fehlermeldung;
    }

    private String benutzername;
    private String passwort;
    private boolean istAdmin = false;

    private Map<String, Fortbildung> bestandeneFortbildungen = new HashMap<>();
    private Map<String, Fortbildung> belegteFortbildungen = new HashMap<>();

    boolean istBestanden(Fortbildung fortbildung) {
        return bestandeneFortbildungen.containsKey(fortbildung.gibName());
    }

    boolean istBelegt(Fortbildung fortbildung) {
        return belegteFortbildungen.containsKey(fortbildung.gibName());
    }

    String gibBenutzername() {
        return benutzername;
    }

    String setzeBenutzername(String neuerBenutzername) {
        String fehlermeldung = null;
        if (!java.util.Objects.equals(neuerBenutzername, this.benutzername)) {
            fehlermeldung = pruefeBenutzername(neuerBenutzername);
        }

        if (fehlermeldung == null) {
            // der Schluessel der Map aendert sich
            dieSachbearbeiter.remove(this.benutzername);
            this.benutzername = neuerBenutzername;
            dieSachbearbeiter.put(neuerBenutzername, this);
        }
        return fehlermeldung;
    }

    String gibPasswort() {
        return passwort;
    }

    String setzePasswort(String passwort) {
        String fehlermeldung = pruefePasswort(passwort);

        if (fehlermeldung == null) {
            this.passwort = passwort;
        }
        return fehlermeldung;
    }

    boolean istAdmin() {
        return istAdmin;
    }

    String setzeIstAdmin(boolean istAdmin) {
        boolean alteBerechtigung = this.istAdmin;
        this.istAdmin = istAdmin;
        if (mindestensEinAdminExistiert()) {
            return null;
        } else {
            // rollback
            this.istAdmin = alteBerechtigung;
            return "Der Sachbearbeiter " + gibBenutzername() + " ist der letzte Administrator im System und kann "
                    + "daher keine geringere Berechtigung annehmen";
        }
    }

    private Sachbearbeiter(String benutzername, String passwort, boolean istAdmin) {
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.istAdmin = istAdmin;
    }

    boolean mindestensEineFortbildungszuordnungExistiert() {
        boolean existiert = !this.belegteFortbildungen.isEmpty();
        return existiert |= !this.bestandeneFortbildungen.isEmpty();
    }

    String loesche() {
        dieSachbearbeiter.remove(gibBenutzername());
        // mindestens ein Admin muss in dieFortbildungen existieren
        if (mindestensEinAdminExistiert()) {
            return null;
        } else {
            dieSachbearbeiter.put(gibBenutzername(), this);
            return "Der Sachbearbeiter " + gibBenutzername() + " ist der letzte Administrator im System und kann "
                    + "daher nicht gelöscht werden";
        }
    }

    void belege(Fortbildung fortbildung) {
        if (fortbildung == null) throw new IllegalArgumentException("Fortbildung fehlt");
        if (istBelegt(fortbildung) || istBestanden(fortbildung))
            throw new IllegalArgumentException("Fortbildung ist bereits zugeordnet");
        for (Fortbildung voraussetzung : fortbildung.gibVoraussetzungen()) {
            if (!istBestanden(voraussetzung))
                throw new IllegalArgumentException("Voraussetzung nicht bestanden: " + voraussetzung.gibName());
        }
        belegteFortbildungen.put(fortbildung.gibName(), fortbildung);
    }

    void bestehe(Fortbildung fortbildung) {
        if (fortbildung == null || !istBelegt(fortbildung))
            throw new IllegalArgumentException("Fortbildung muss zuerst belegt werden");
        belegteFortbildungen.remove(fortbildung.gibName());
        bestandeneFortbildungen.put(fortbildung.gibName(), fortbildung);
    }

    void fortbildungsbelegungLoeschen(Fortbildung fortbildung) {
        belegteFortbildungen.remove(fortbildung.gibName());
    }

    void fortbildungsbestehenLoeschen(Fortbildung fortbildung) {
        for (Fortbildung f : belegteFortbildungen.values()) {
            if (fortbildung.istVoraussetzungVon(f))
                throw new IllegalArgumentException("Fortbildung ist Voraussetzung von " + f.gibName());
        }
        for (Fortbildung f : bestandeneFortbildungen.values()) {
            if (fortbildung.istVoraussetzungVon(f))
                throw new IllegalArgumentException("Fortbildung ist Voraussetzung von " + f.gibName());
        }
        bestandeneFortbildungen.remove(fortbildung.gibName());
    }

    public static void main(String[] args) {
        String name = "admin";

        System.out.println(erzeuge(name, "gGG+2&256g", true));
        gib("admin").loesche();
        System.out.println(gib(name).loesche());
        alleNamenAusgeben();
    }

    static void alleNamenAusgeben() {
        String[] alleNamen = Sachbearbeiter.gibAlleNamen();
        for (String name : alleNamen) {
            System.out.println(name);
        }

    }

    Collection<Fortbildung> gibBestandeneFortbildungen() {
        return bestandeneFortbildungen.values();
    }

    Collection<Fortbildung> gibBelegteFortbildungen() {
        return belegteFortbildungen.values();
    }

    static private String dateiname = "./Entitaeten.ser";
    static void setzeSpeicherdatei(java.io.File datei) { dateiname = datei.getAbsolutePath(); }


    static void abspeichern() {
        java.nio.file.Path ziel = java.nio.file.Paths.get(dateiname).toAbsolutePath();
        java.nio.file.Path temporaer = null;
        try {
            temporaer = java.nio.file.Files.createTempFile(ziel.getParent(), "Entitaeten-", ".tmp");
            try (ObjectOutputStream aus = new ObjectOutputStream(
                    java.nio.file.Files.newOutputStream(temporaer))) {
                aus.writeObject(dieSachbearbeiter);
                aus.writeObject(Fortbildung.gibDieFortbildungen());
            }
            try {
                java.nio.file.Files.move(temporaer, ziel,
                        java.nio.file.StandardCopyOption.ATOMIC_MOVE,
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            } catch (java.nio.file.AtomicMoveNotSupportedException ex) {
                java.nio.file.Files.move(temporaer, ziel,
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Speichern fehlgeschlagen", ex);
        } finally {
            if (temporaer != null) {
                try { java.nio.file.Files.deleteIfExists(temporaer); }
                catch (IOException ignored) { /* Originalfehler nicht verdecken. */ }
            }
        }
    }

    @SuppressWarnings("unchecked")
    static void einlesen() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dateiname))) {
            Map<String, Sachbearbeiter> geladeneSachbearbeiter = (Map<String, Sachbearbeiter>) in.readObject();
            Map<String, Fortbildung> dieFortbildungen = (Map<String, Fortbildung>) in.readObject();
            if (geladeneSachbearbeiter == null || dieFortbildungen == null
                    || geladeneSachbearbeiter.values().stream().noneMatch(Sachbearbeiter::istAdmin))
                throw new IOException("Speicherdatei enthält keinen Administrator");
            dieSachbearbeiter = geladeneSachbearbeiter;
            Fortbildung.setzeDieFortbildungen(dieFortbildungen);
        } catch (FileNotFoundException ex) {
            System.out.println("Speicherdatei (noch) nicht vorhanden!");

            // zum initialen Login und fuer die Invariante
            erzeuge("admin", "aaAA11&&aa", true);

        } catch (Exception ex) {
            throw new IllegalStateException("Laden fehlgeschlagen; Speicherdatei bleibt unverändert", ex);
        }
    }

}
