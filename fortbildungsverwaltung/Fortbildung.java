package fortbildungsverwaltung;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class Fortbildung implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Map<String, Fortbildung> dieFortbildungen =
            new HashMap<>();

    static Map<String, Fortbildung> gibDieFortbildungen() {
        return dieFortbildungen;
    }

    static void setzeDieFortbildungen(Map<String, Fortbildung> fortbildungen) {
        dieFortbildungen = fortbildungen;
    }

    static {
        Fortbildung mathematik1 = new Fortbildung("Mathematik 1");
        Fortbildung allgemeineBwl = new Fortbildung("Allgemeine BWL");
        Fortbildung mathematik2 = new Fortbildung("Mathematik 2", mathematik1);
        new Fortbildung("Kostenrechnung", mathematik2, allgemeineBwl);
    }

    static String[] gibAlleNamen() {
        return dieFortbildungen.keySet().stream().sorted().toArray(String[]::new);
    }

    static Fortbildung gib(String name) {
        return dieFortbildungen.get(name);
    }

    private String name;
    private Set<Fortbildung> voraussetzungen = new LinkedHashSet<>();

    Fortbildung(String name, Fortbildung... vorausgesetzteFortbildungen) {
        this.name = name;

        for (Fortbildung f : vorausgesetzteFortbildungen) {
            voraussetzungen.add(f);
        }

        dieFortbildungen.put(this.gibName(), this);
    }

    String gibName() {
        return this.name;
    }

    boolean istVoraussetzungVon(Fortbildung fortbildung) {
        return fortbildung.voraussetzungen.contains(this);
    }

    Set<Fortbildung> gibVoraussetzungen() {
        return voraussetzungen;
    }


}
