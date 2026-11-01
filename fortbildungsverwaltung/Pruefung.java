package fortbildungsverwaltung;

final class Pruefung {
    static Sachbearbeiter sachbearbeiter(String name) {
        Sachbearbeiter s = Sachbearbeiter.gib(name);
        if (s == null) throw new IllegalArgumentException("Sachbearbeiter nicht gefunden: " + name);
        return s;
    }
    static Fortbildung fortbildung(String name) {
        Fortbildung f = Fortbildung.gib(name);
        if (f == null) throw new IllegalArgumentException("Fortbildung nicht gefunden: " + name);
        return f;
    }
}
