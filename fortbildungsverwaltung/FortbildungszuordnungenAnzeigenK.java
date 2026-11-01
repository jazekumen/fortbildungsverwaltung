package fortbildungsverwaltung;

class FortbildungszuordnungenAnzeigenK {
	
	   String[] gibBestandeneFortbildungenNamen(String benutzername) {
		   return new FortbildungszuordnungAuswaehlenK().gibBestandeneFortbildungenNamen(benutzername);  	
	    }
	    
	    String[] gibBelegteFortbildungenNamen(String benutzername) {
			   return new FortbildungszuordnungAuswaehlenK().gibBelegteFortbildungenNamen(benutzername);  	
	    }

}
