package fortbildungsverwaltung;
import java.io.File;
public class ServiceTest {
    private static int checks;
    private static void check(boolean ok) { checks++; if (!ok) throw new AssertionError("Check " + checks); }
    private static void reject(Runnable action) { boolean rejected=false; try { action.run(); } catch (IllegalArgumentException ex) { rejected=true; } check(rejected); }
    public static void main(String[] args) {
        File datei=new File("Entitaeten.ser"); check(!datei.exists());
        FortbildungsService k=new FortbildungsService(); k.laden(datei);
        reject(()->k.anmelden("unbekannt","aaAA11&&aa",false));
        k.anmelden("admin","aaAA11&&aa",true); k.erfassen("Otto","aaAA11&&aa",false);
        reject(()->k.erfassen("Otto","aaAA11&&aa",false));
        reject(()->k.erfassen("Otto2","aaAA11&&aa",false));
        reject(()->k.erfassen("Susi","kurz",true));
        k.erfassen("Susi","aaAA11&&aa",true);
        reject(()->k.anmelden("Otto","aaAA11&&aa",true)); check(!k.istAngemeldet());
        reject(()->k.anmelden("Otto","falsch",false));
        k.anmelden("Susi","aaAA11&&aa",false); check(!k.istAdminRolle());
        reject(()->k.erfassen("Test","aaAA11&&aa",false)); reject(()->k.loeschen("Otto"));
        reject(()->k.bearbeiten("Otto","Otto","",true)); reject(()->k.bearbeiten("admin","admin","",false));
        reject(()->k.bearbeiten("Otto","Otto1","",false)); reject(()->k.bearbeiten("Otto","Otto","falsch",false));
        k.bearbeiten("Otto","Otto_neu","",false); check(!k.berechtigung("Otto_neu"));
        reject(()->k.zuordnen("Otto_neu","Mathematik 2",false)); reject(()->k.zuordnen("Otto_neu","Mathematik 1",true));
        k.zuordnen("Otto_neu","Mathematik 1",false); check(k.zuordnungen("Otto_neu",false).length==1);
        reject(()->k.zuordnen("Otto_neu","Mathematik 2",false));
        k.zuordnen("Otto_neu","Mathematik 1",true); check(k.zuordnungen("Otto_neu",false).length==0);
        k.zuordnen("Otto_neu","Mathematik 2",false); reject(()->k.zuordnungLoeschen("Otto_neu","Mathematik 1"));
        k.zuordnungLoeschen("Otto_neu","Mathematik 2"); k.zuordnungLoeschen("Otto_neu","Mathematik 1");
        check(k.zuordnungen("Otto_neu",true).length==0);
        k.anmelden("admin","aaAA11&&aa",true); k.loeschen("Otto_neu");
        check(java.util.Arrays.stream(k.namen()).noneMatch("Otto_neu"::equals));
        k.bearbeiten("admin","Chef","",true); check(k.istAngemeldet());
        k.loeschen("Chef"); check(!k.istAngemeldet()); reject(()->k.erfassen("Test","aaAA11&&aa",false));
        k.anmelden("Susi","aaAA11&&aa",true); reject(()->k.loeschen("Susi")); k.speichern();
        System.out.println(checks+" service checks passed.");
    }
}
