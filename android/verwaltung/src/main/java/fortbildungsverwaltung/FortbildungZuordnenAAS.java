package fortbildungsverwaltung;

public class FortbildungZuordnenAAS extends BasisAAS {
    public FortbildungZuordnenAAS() { super(R.layout.aktion_aas); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        text(view, R.id.title, "Fortbildung zuordnen"); text(view, R.id.details, name() + ": " + requireArguments().getString("titel") + (requireArguments().getBoolean("bestanden") ? " als bestanden eintragen?" : " belegen?"));
        view.findViewById(R.id.ok).setOnClickListener(v -> ausfuehren(view, () -> kontrolle.zuordnen(name(), requireArguments().getString("titel"), requireArguments().getBoolean("bestanden"))));
        view.findViewById(R.id.cancel).setOnClickListener(v -> getParentFragmentManager().popBackStack());
    }
}
