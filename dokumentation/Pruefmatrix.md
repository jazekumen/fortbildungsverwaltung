# Prüfmatrix

| Prüfung | Anwendungsfall | Schicht | Verhalten |
|---|---|---|---|
| Leere/ungültige Menüwahl | alle Menüs | Schnittstelle: Auswaehlen | Erneute Eingabe statt ungültigem Arrayzugriff |
| Leere Auswahl | Auswählen/Zuordnen | Kontrolle und Schnittstelle | Hinweis statt Endlosschleife |
| Benutzername: mindestens ein Zeichen, nur deutsches Buchstabenalphabet und Unterstrich | Erfassen/Bearbeiten | Entität: Sachbearbeiter | Vor Übernahme prüfen |
| Benutzername eindeutig; eigener unveränderter Name erlaubt | Erfassen/Bearbeiten | Entität | Map-Schlüssel und Objektname bleiben konsistent |
| Passwort: mindestens 10 Zeichen, je 2 Groß-/Kleinbuchstaben, Ziffern und Sonderzeichen | Erfassen/Bearbeiten | Entität | Ungültige Änderung ablehnen |
| Passwort passt zum Namen | Login | LoginK mit Entitätsdaten | Einheitliche Fehlermeldung, keine Sitzung |
| Gewählte Adminrolle erlaubt | Login | LoginK / FortbildungsService | Normale Benutzer dürfen nicht als Admin anmelden |
| Admin darf bewusst die Normalrolle wählen | Login/Änderungen | Kontrollfassade | Sitzungsrolle bestimmt Rechte |
| Normalrolle darf nicht erfassen, löschen oder Rechte ändern | Benutzerverwaltung | Kontrollfassade und Rollenmenü | Auch direkte Serviceaufrufe werden geprüft |
| Letzter Administrator darf nicht gelöscht/herabgestuft werden | Löschen/Bearbeiten | Entität | Änderung zurücknehmen und Hinweis ausgeben |
| Unbekannter Benutzer oder Kurs | Referenzierende Aktionen | Pruefung / Kontrollklassen | Fehler statt NullPointerException |
| Kombinierte Änderung vollständig oder gar nicht | Bearbeiten | SachbearbeiterBearbeitenK | Rollback von Name/Passwort bei späterem Fehler |
| Keine doppelte Belegung; keine Belegung nach Bestehen | Zuordnen | Entität und Kontrollauswahl | Ablehnen / aus Auswahl ausschließen |
| Alle Voraussetzungen müssen bestanden sein | Belegen | Entität; FortbildungAuswaehlenK | Ungültige Belegung verhindern |
| Bestehen nur nach Belegung | Bestehen | Entität | Belegung entfernen, Bestehen eintragen |
| Bestandene Voraussetzung einer belegten ODER bestandenen Fortbildung schützen | Zuordnung löschen | Entität / Kontrolle | Löschung mit Hinweis ablehnen |
| Nicht vorhandene Zuordnung nicht als gelöscht melden | Zuordnung löschen | Kontrolle | Fehlermeldung |
| Abbruch übernimmt nichts | Löschen / Album Cancel | Schnittstelle / ViewModel | Entität unverändert |
| Defekte Speicherdatei | Start | Datenhaltung + Hauptschnittstelle | Laden abbrechen; Datei nicht überschreiben |
| Gelöschtes/herabgestuftes Sitzungskonto | Benutzerverwaltung | Kontrollfassade | Sitzung verliert ihre Gültigkeit |
