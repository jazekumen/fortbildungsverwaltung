package fortbildungsverwaltung;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

class FortbildungszuordnungAuswaehlenK {
    String[] gibBestandeneFortbildungenNamen(String benutzername) {
    	Sachbearbeiter sachbearbeiter = Pruefung.sachbearbeiter(benutzername);   
    	return wandleInStrings(sachbearbeiter.gibBestandeneFortbildungen()).toArray(new String[0]);
    }
    
    String[] gibBelegteFortbildungenNamen(String benutzername) {
    	Sachbearbeiter sachbearbeiter = Pruefung.sachbearbeiter(benutzername);   	
    	return wandleInStrings(sachbearbeiter.gibBelegteFortbildungen()).toArray(new String[0]);   	
    }
    
    Set<String> wandleInStrings(Collection<Fortbildung> fortbildungen) {
    	Set<String> strings = new LinkedHashSet<>();
    	
    	for (Fortbildung fortbildung : fortbildungen) {
    		strings.add(fortbildung.gibName());
    	}
    	
    	return strings;
    }

}
