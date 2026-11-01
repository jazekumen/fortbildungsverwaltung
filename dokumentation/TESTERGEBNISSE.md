# Testprotokoll – 18.10.2026

| Prüfung | Ergebnis |
|---|---|
| Java-17-Kompilierung mit UTF-8 und `-Xlint:all` | Erfolgreich |
| RegressionTest | 27 Prüfungen bestanden |
| ServiceTest (Rollen, Sitzung, Bearbeitung, Zuordnungen) | 28 Prüfungen bestanden |
| Konsolenabläufe einschließlich Neustart, Fehleingaben und EOF | 7 Szenarien bestanden |
| AlbumViewModelTest | 5 Tests, 0 Fehler, 0 übersprungen |
| Verwaltungs-App assembleDebug | Erfolgreich |
| Album-App assembleDebug | Erfolgreich |
| Verwaltung lintDebug | 0 Fehler, 12 Warnungen |
| Album lintDebug | 0 Fehler, 9 Warnungen |
| Beide assembleDebugAndroidTest | Erfolgreich; Test-APKs kompiliert |
| UI-Tests auf Gerät/Emulator | Nicht ausgeführt |

Das sind 60 automatische Einzelprüfungen plus sieben Konsolenszenarien.
Die Lint-Warnungen betreffen direkt gesetzte Layouttexte und die zusätzliche
Backup-Konfiguration für Android vor Version 12. Die vollständigen Berichte
liegen in `lint-verwaltung.txt` und `lint-album.txt`.

Build-Umgebung: JDK 17, Gradle 8.9, AGP 8.7.3, Android SDK 35.
Die ausgelieferten APKs sind Debug-Builds; der Build-Erfolg bestätigt keine
visuelle oder interaktive Geräteprüfung. Die zwei Espresso-Tests liegen als
Quellen bei und wurden zusammen mit ihren Test-APKs erfolgreich gebaut.

## Noch am Gerät prüfen

- Beide Apps installieren, starten und mit Bildschirmtastatur bedienen.
- Login als Admin und Normal, Auswählen, Bearbeiten, Löschen mit Ja/Nein.
- Verwaltungs-App neu starten und gespeicherte Daten kontrollieren.
- Album auswählen, Classical/Composer, Apply/Cancel und Listenaktualisierung.
- Hoch-/Querformat, kleine Displays, Tastatur, Zurück-Navigation und Rotation.
- `connectedDebugAndroidTest` auf einem frischen Testgerät ausführen.

Die Mockups der Dokumentation sind Entwurfsansichten, keine Geräte-Screenshots.
Formale Übereinstimmung mit nicht vorliegenden Felix-Vorlagen ist nicht bestätigt.
