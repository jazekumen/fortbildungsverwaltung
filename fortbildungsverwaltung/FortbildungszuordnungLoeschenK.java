package fortbildungsverwaltung;

class FortbildungszuordnungLoeschenK {
    String loescheFortbildungszuordnung(String name, String titel) {
        try {
            Sachbearbeiter s = Pruefung.sachbearbeiter(name);
            Fortbildung f = Pruefung.fortbildung(titel);
            if (s.istBelegt(f)) s.fortbildungsbelegungLoeschen(f);
            else if (s.istBestanden(f)) s.fortbildungsbestehenLoeschen(f);
            else return "Fortbildung ist nicht zugeordnet";
            return null;
        } catch (IllegalArgumentException ex) { return ex.getMessage(); }
    }
    String[] gibBelegteFortbildungen(String name) {
        return new FortbildungszuordnungenAnzeigenK().gibBelegteFortbildungenNamen(name);
    }
    String[] gibBestandeneFortbildungen(String name) {
        return new FortbildungszuordnungenAnzeigenK().gibBestandeneFortbildungenNamen(name);
    }
}
