package fortbildungsverwaltung;

public final class AdminSachbearbeiterBearbeitenAAS extends SachbearbeiterBearbeitenAAS {
    private static final AdminSachbearbeiterBearbeitenAAS INSTANCE = new AdminSachbearbeiterBearbeitenAAS();
    private AdminSachbearbeiterBearbeitenAAS() { }
    public static AdminSachbearbeiterBearbeitenAAS getInstance() { return INSTANCE; }
    public void oeffnen() { modifiziereSachbearbeiter(true); }
}
