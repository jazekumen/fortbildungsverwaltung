package fortbildungsverwaltung;

public class StartAAS extends BasisAAS {
    public StartAAS() { super(R.layout.aktion_aas); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        text(view, R.id.title, "Fortbildungsverwaltung");
        text(view, R.id.details, "Angemeldet als " + (kontrolle.istAdminRolle() ? "Administrator" : "Normalsachbearbeiter") + ".\nWählen Sie oben rechts im Menü einen Anwendungsfall.");
        view.findViewById(R.id.ok).setVisibility(android.view.View.GONE); view.findViewById(R.id.cancel).setVisibility(android.view.View.GONE);
    }
}
