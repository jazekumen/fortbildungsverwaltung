package fortbildungsverwaltung;

public final class FortbildungsverwaltungHS {
    private static final FortbildungsverwaltungHS INSTANCE = new FortbildungsverwaltungHS();
    private final FortbildungsverwaltungHK kontrolle = new FortbildungsverwaltungHK();
    private FortbildungsverwaltungHS() { }
    public static void main(String[] args) { INSTANCE.oeffnen(); }
    void oeffnen() {
        kontrolle.laden(); System.out.println("Willkommen zur Fortbildungsverwaltung");
        try {
            do { LoginAAS.getInstance().oeffnen(); }
            while (Auswaehlen.waehleAus(new String[]{"Login", "Beenden"}).equals("Login"));
        } catch (java.util.NoSuchElementException ex) { System.out.println("Eingabe beendet."); }
        finally { kontrolle.speichern(); }
    }
}
