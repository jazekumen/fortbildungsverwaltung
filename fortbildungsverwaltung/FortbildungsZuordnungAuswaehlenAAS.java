package fortbildungsverwaltung;

public final class FortbildungsZuordnungAuswaehlenAAS {
    private static final FortbildungsZuordnungAuswaehlenAAS INSTANCE = new FortbildungsZuordnungAuswaehlenAAS();
    private final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    private FortbildungsZuordnungAuswaehlenAAS() { }
    public static FortbildungsZuordnungAuswaehlenAAS getInstance() { return INSTANCE; }
    public String oeffnen(String name) {
        java.util.List<String> titel = new java.util.ArrayList<>(), zeilen = new java.util.ArrayList<>();
        for (boolean bestanden : new boolean[]{false, true}) for (String t : kontrolle.zuordnungen(name, bestanden)) {
            titel.add(t); zeilen.add(t + (bestanden ? " (bestanden)" : " (belegt)"));
        }
        if (titel.isEmpty()) throw new IllegalArgumentException("Keine Zuordnungen vorhanden.");
        return titel.get(zeilen.indexOf(Auswaehlen.waehleAus(zeilen.toArray(new String[0]))));
    }
}
