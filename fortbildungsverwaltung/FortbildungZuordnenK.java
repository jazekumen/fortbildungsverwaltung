package fortbildungsverwaltung;


class FortbildungZuordnenK {

	void belegeFortbildung(String sachbearbeiterName, String fortbildungName) {
		Fortbildung fortbildung = Pruefung.fortbildung(fortbildungName);
		Sachbearbeiter sachbearbeiter = Pruefung.sachbearbeiter(sachbearbeiterName);
		sachbearbeiter.belege(fortbildung);
	}

	void besteheFortbildung(String sachbearbeiterName, String fortbildungName) {
		Fortbildung fortbildung = Pruefung.fortbildung(fortbildungName);
		Sachbearbeiter sachbearbeiter = Pruefung.sachbearbeiter(sachbearbeiterName);
		sachbearbeiter.bestehe(fortbildung);
	}


}
