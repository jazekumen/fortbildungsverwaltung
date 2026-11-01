package fortbildungsverwaltung;

public abstract class MenueAS extends androidx.appcompat.app.AppCompatActivity {
    protected FortbildungsService kontrolle;
    protected abstract boolean adminMenue();
    @Override public void onCreate(android.os.Bundle state) {
        super.onCreate(state); kontrolle = ((AppState) getApplication()).kontrolle;
        if (!kontrolle.istAngemeldet() || (adminMenue() && !kontrolle.istAdminRolle())) { abmelden(); return; }
        setContentView(R.layout.menu_as); Insets.anwenden(this);
        if (state == null) startseite();
    }
    @Override public boolean onCreateOptionsMenu(android.view.Menu menu) {
        if (adminMenue()) { menu.add(0,1,0,"Sachbearbeiter erfassen"); menu.add(0,2,1,"Sachbearbeiter löschen"); }
        menu.add(0,3,2,"Sachbearbeiter bearbeiten"); menu.add(0,4,3,"Fortbildung zuordnen");
        menu.add(0,5,4,"Zuordnung löschen"); menu.add(0,6,5,"Fortbildungen anzeigen"); menu.add(0,7,6,"Abmelden");
        return true;
    }
    @Override public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int aktion = item.getItemId(); if (aktion == 7) { abmelden(); return true; }
        if (aktion < 1 || aktion > 6) return super.onOptionsItemSelected(item);
        androidx.fragment.app.Fragment ziel = aktion == 1 ? new SachbearbeiterErfassenAAS() : new SachbearbeiterAuswaehlenAAS();
        android.os.Bundle args = new android.os.Bundle(); args.putInt("aktion", aktion); ziel.setArguments(args);
        getSupportFragmentManager().popBackStackImmediate(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ziel).addToBackStack(null).commit();
        return true;
    }
    public void startseite() {
        if (!kontrolle.istAngemeldet()) { abmelden(); return; }
        getSupportFragmentManager().popBackStackImmediate(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StartAAS()).commit();
    }
    public void abmelden() {
        if (kontrolle != null) kontrolle.abmelden();
        android.content.Intent intent = new android.content.Intent(this, FortbildungsverwaltungHS.class);
        intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent); finish();
    }
}
