package fortbildungsverwaltung;

final class LoginAAS {
    private static final LoginAAS INSTANCE = new LoginAAS();
    private final FortbildungsService kontrolle = FortbildungsverwaltungHK.SERVICE;
    private final SachbearbeiterS sicht = SachbearbeiterS.getInstance();
    private LoginAAS() { }
    static LoginAAS getInstance() { return INSTANCE; }
    void oeffnen() {
        String name = sicht.name("Benutzername:"); String passwort = sicht.passwort("Passwort:"); boolean admin = sicht.rolle();
        try {
            kontrolle.anmelden(name, passwort, admin);
            if (admin) AdminAS.getInstance().oeffnen(); else NormalAS.getInstance().oeffnen();
        } catch (IllegalArgumentException ex) { System.out.println(ex.getMessage()); }
        finally { kontrolle.abmelden(); }
    }
}
