package fortbildungsverwaltung;

public final class NormalSachbearbeiterBearbeitenAAS extends SachbearbeiterBearbeitenAAS {
    private static final NormalSachbearbeiterBearbeitenAAS INSTANCE = new NormalSachbearbeiterBearbeitenAAS();
    private NormalSachbearbeiterBearbeitenAAS() { }
    public static NormalSachbearbeiterBearbeitenAAS getInstance() { return INSTANCE; }
    public void oeffnen() { modifiziereSachbearbeiter(false); }
}
