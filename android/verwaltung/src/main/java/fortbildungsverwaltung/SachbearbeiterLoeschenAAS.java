package fortbildungsverwaltung;

public class SachbearbeiterLoeschenAAS extends BasisAAS {
    public SachbearbeiterLoeschenAAS() { super(R.layout.aktion_aas); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        text(view, R.id.title, "Sachbearbeiter löschen"); text(view, R.id.details, "Soll Sachbearbeiter " + name() + " wirklich gelöscht werden?");
        view.findViewById(R.id.ok).setOnClickListener(v -> ausfuehren(view, () -> kontrolle.loeschen(name())));
        view.findViewById(R.id.cancel).setOnClickListener(v -> getParentFragmentManager().popBackStack());
    }
}
