package fortbildungsverwaltung;

class SachbearbeiterBearbeitenK {
	String schreibeSachbearbeiter(String alterBenutzername, String neuerBenutzername, String passwort, boolean istAdmin) {
		Sachbearbeiter sachbearbeiter = Pruefung.sachbearbeiter(alterBenutzername);
		
		String fehlermeldung = sachbearbeiter.setzeBenutzername(neuerBenutzername);
		if (fehlermeldung == null) {
			 String altesPasswort = sachbearbeiter.gibPasswort();
		     fehlermeldung = sachbearbeiter.setzePasswort(passwort);
		     if (fehlermeldung == null) {
		    	 fehlermeldung = sachbearbeiter.setzeIstAdmin(istAdmin);
		    	 if (fehlermeldung != null) {
		    		 // rollback benutzername und passwort
		    		 sachbearbeiter.setzeBenutzername(alterBenutzername);
		    		 sachbearbeiter.setzePasswort(altesPasswort);
		    	 }
		     } else {
		    	 // rollback des Benutzernamens, weil passwort falsch
		    	 sachbearbeiter.setzeBenutzername(alterBenutzername);
		     }
		}
			
		return fehlermeldung;
	}

	boolean gibBerechtigung(String altenBenutzernamen) {
		return new SachbearbeiterK().gibBerechtigung(altenBenutzernamen);
	}

}
