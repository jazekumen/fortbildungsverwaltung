package fortbildungsverwaltung;

class SachbearbeiterLoeschenK {
	String loescheSachbearbeiter(String benutzername) {
		return Pruefung.sachbearbeiter(benutzername).loesche();
	}

}
