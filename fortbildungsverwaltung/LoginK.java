package fortbildungsverwaltung;

class LoginK {
	String passwortPasstZuBenutzername(String benutzername, String passwort) {
		Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
		String fehlermeldung = "Login ist leider fehlgeschlagen";
		if (sachbearbeiter == null) {
			return fehlermeldung;
		} else {
			String passwortVonSachbearbeiter = sachbearbeiter.gibPasswort();
			if (passwortVonSachbearbeiter.equals(passwort)) {
				return null;
			} else {
				return fehlermeldung;
			}
		}
	}

	String gewaehlteBerechtigungPasstZuSachbearbeiter(String benutzername, boolean istAdmin) {
		if (!istAdmin) {
			return null;
		} else {
			Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
			if (sachbearbeiter != null && sachbearbeiter.istAdmin()) {
				return null;
			} else {
				return "Sie haben leider keine Berechtigung als Administrator";
			}
		}
	}

	boolean istAdmin(String benutzername) {
		return Sachbearbeiter.gib(benutzername).istAdmin();
	}

}
