package fortbildungsverwaltung;

class SachbearbeiterK {
	String gibPasswort(String benutzername) {
		return Pruefung.sachbearbeiter(benutzername).gibPasswort();
	}
	
	boolean gibBerechtigung(String benutzername) {
		return Pruefung.sachbearbeiter(benutzername).istAdmin();		
	}

}
