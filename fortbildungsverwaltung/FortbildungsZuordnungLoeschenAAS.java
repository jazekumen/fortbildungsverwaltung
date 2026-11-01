package fortbildungsverwaltung;

public final class FortbildungsZuordnungLoeschenAAS {
    private static final FortbildungsZuordnungLoeschenAAS INSTANCE = new FortbildungsZuordnungLoeschenAAS();
    private final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    private FortbildungsZuordnungLoeschenAAS() { }
    public static FortbildungsZuordnungLoeschenAAS getInstance() { return INSTANCE; }
    public void oeffnen() {
        String name = SachbearbeiterAuswaehlenAAS.getInstance().oeffnen();
        String titel = FortbildungsZuordnungAuswaehlenAAS.getInstance().oeffnen(name);
        kontrolle.zuordnungLoeschen(name, titel); System.out.println("Zuordnung gelöscht.");
    }
}
