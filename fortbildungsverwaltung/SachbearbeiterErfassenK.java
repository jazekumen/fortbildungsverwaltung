package fortbildungsverwaltung;

class SachbearbeiterErfassenK {
	String erzeugeSachbearbeiter(String benutzername, String 
			passwort, boolean istAdmin) {
		String fehlermeldung = Sachbearbeiter.erzeuge(benutzername, passwort, istAdmin);
		
		return fehlermeldung;
	}
}
