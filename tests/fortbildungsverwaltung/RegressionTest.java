package fortbildungsverwaltung;

import java.nio.file.*;
import java.util.*;

public class RegressionTest {
    private static int checks;
    private static void check(boolean ok, String message) {
        checks++;
        if (!ok) throw new AssertionError(message);
    }
    private static void rejects(Runnable action) {
        boolean rejected = false;
        try { action.run(); } catch (IllegalArgumentException ex) { rejected = true; }
        check(rejected, "Invalid action was accepted");
    }
    public static void main(String[] args) throws Exception {
        check(!Files.exists(Path.of("Entitaeten.ser")), "Run tests in a fresh temporary directory");
        Sachbearbeiter.einlesen();
        check(new LoginK().passwortPasstZuBenutzername("admin", "aaAA11&&aa") == null, "Initial login");
        check(Sachbearbeiter.erzeuge("Anna", "aaAA11&&aa", false) == null, "Create user");
        check(Sachbearbeiter.erzeuge("Anna", "aaAA11&&aa", false) != null, "Duplicate user");
        check(Sachbearbeiter.erzeuge(null, "aaAA11&&aa", false) != null, "Null name");
        check(Sachbearbeiter.pruefePasswort(null) != null, "Null password");
        check(Sachbearbeiter.gib("admin").loesche() != null, "Last admin delete");
        SachbearbeiterBearbeitenK edit = new SachbearbeiterBearbeitenK();
        check(edit.schreibeSachbearbeiter("admin", "Chef", "aaAA11&&aa", false) != null, "Last admin demote");
        check(Sachbearbeiter.gib("admin") != null && Sachbearbeiter.gib("Chef") == null, "Rename rollback");
        check(edit.schreibeSachbearbeiter("Anna", "Anne", "weak", false) != null, "Invalid password");
        check(Sachbearbeiter.gib("Anna") != null && Sachbearbeiter.gib("Anne") == null, "Password rollback");
        check(edit.schreibeSachbearbeiter("Anna", "Anne", "bbBB22!!bb", false) == null, "Rename");
        check(new LoginK().passwortPasstZuBenutzername("Anne", "bbBB22!!bb") == null, "Renamed login");
        FortbildungZuordnenK assign = new FortbildungZuordnenK();
        FortbildungszuordnungLoeschenK delete = new FortbildungszuordnungLoeschenK();
        rejects(() -> assign.belegeFortbildung("Anne", "Mathematik 2"));
        rejects(() -> assign.besteheFortbildung("Anne", "Mathematik 1"));
        rejects(() -> assign.belegeFortbildung("Nobody", "Mathematik 1"));
        rejects(() -> assign.belegeFortbildung("Anne", "Unknown"));
        assign.belegeFortbildung("Anne", "Mathematik 1");
        rejects(() -> assign.belegeFortbildung("Anne", "Mathematik 1"));
        assign.besteheFortbildung("Anne", "Mathematik 1");
        Sachbearbeiter s = Sachbearbeiter.gib("Anne");
        check(s.istBestanden(Fortbildung.gib("Mathematik 1")) && !s.istBelegt(Fortbildung.gib("Mathematik 1")), "Exclusive status");
        assign.belegeFortbildung("Anne", "Mathematik 2");
        check(delete.loescheFortbildungszuordnung("Anne", "Mathematik 1") != null, "Enrolled prerequisite protected");
        assign.besteheFortbildung("Anne", "Mathematik 2");
        check(delete.loescheFortbildungszuordnung("Anne", "Mathematik 1") != null, "Passed prerequisite protected");
        check(delete.loescheFortbildungszuordnung("Anne", "Allgemeine BWL") != null, "Missing assignment");
        Sachbearbeiter.abspeichern();
        Sachbearbeiter.einlesen();
        check(Sachbearbeiter.gib("Anne").istBestanden(Fortbildung.gib("Mathematik 2")), "Persistence roundtrip");
        check(delete.loescheFortbildungszuordnung("Anne", "Mathematik 2") == null, "Delete dependent");
        check(delete.loescheFortbildungszuordnung("Anne", "Mathematik 1") == null, "Delete prerequisite");
        Files.writeString(Path.of("Entitaeten.ser"), "broken");
        boolean failed = false;
        try { Sachbearbeiter.einlesen(); } catch (IllegalStateException ex) { failed = true; }
        check(failed && Sachbearbeiter.gib("Anne") != null, "Corrupt load preserves live state");
        check(Files.readString(Path.of("Entitaeten.ser")).equals("broken"), "Corrupt file preserved");
        System.out.println(checks + " regression checks passed.");
    }
}
