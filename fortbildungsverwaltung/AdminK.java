package fortbildungsverwaltung;

class AdminK extends NormalK {
    @Override String[] menue() { return new String[]{"Sachbearbeiter erfassen", "Sachbearbeiter löschen", "Sachbearbeiter bearbeiten", "Fortbildung zuordnen", "Zuordnung löschen", "Fortbildungen anzeigen", "Abmelden"}; }
}
