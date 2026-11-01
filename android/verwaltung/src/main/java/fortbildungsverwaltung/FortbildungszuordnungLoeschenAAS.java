package fortbildungsverwaltung;

public class FortbildungszuordnungLoeschenAAS extends BasisAAS {
    public FortbildungszuordnungLoeschenAAS() { super(R.layout.aktion_aas); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        text(view, R.id.title, "Zuordnung löschen"); text(view, R.id.details, requireArguments().getString("titel") + " für " + name() + " wirklich löschen?");
        view.findViewById(R.id.ok).setOnClickListener(v -> ausfuehren(view, () -> kontrolle.zuordnungLoeschen(name(), requireArguments().getString("titel"))));
        view.findViewById(R.id.cancel).setOnClickListener(v -> getParentFragmentManager().popBackStack());
    }
}
