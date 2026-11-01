package fortbildungsverwaltung;

public final class FortbildungsZuordnungenAnzeigenAAS {
    private static final FortbildungsZuordnungenAnzeigenAAS INSTANCE = new FortbildungsZuordnungenAnzeigenAAS();
    private final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    private FortbildungsZuordnungenAnzeigenAAS() { }
    public static FortbildungsZuordnungenAnzeigenAAS getInstance() { return INSTANCE; }
    public void oeffnen() {
        String name = SachbearbeiterAuswaehlenAAS.getInstance().oeffnen();
        System.out.println("Belegt: " + java.util.Arrays.toString(kontrolle.zuordnungen(name, false)));
        System.out.println("Bestanden: " + java.util.Arrays.toString(kontrolle.zuordnungen(name, true)));
    }
}
