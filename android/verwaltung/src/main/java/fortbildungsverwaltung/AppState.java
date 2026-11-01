package fortbildungsverwaltung;

public class AppState extends android.app.Application {
    public final FortbildungsService kontrolle = new FortbildungsService();
    public String ladefehler;
    @Override public void onCreate() {
        super.onCreate();
        try { kontrolle.laden(new java.io.File(getFilesDir(), "Entitaeten.ser")); }
        catch (RuntimeException ex) { ladefehler = "Daten konnten nicht geladen werden. Die Speicherdatei bleibt unverändert."; }
    }
}
