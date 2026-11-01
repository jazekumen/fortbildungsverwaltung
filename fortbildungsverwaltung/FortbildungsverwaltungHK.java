package fortbildungsverwaltung;

final class FortbildungsverwaltungHK {
    static final FortbildungsService SERVICE = new FortbildungsService();
    void laden() { SERVICE.laden(new java.io.File("Entitaeten.ser")); }
    void speichern() { SERVICE.speichern(); }
}
