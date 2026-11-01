package fortbildungsverwaltung;

public class FortbildungszuordnungenAnzeigenAAS extends BasisAAS {
    public FortbildungszuordnungenAnzeigenAAS() { super(R.layout.aktion_aas); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        text(view, R.id.title, "Fortbildungen: " + name()); view.findViewById(R.id.ok).setVisibility(android.view.View.GONE);
        text(view, R.id.cancel, "Zurück"); view.findViewById(R.id.cancel).setOnClickListener(v -> getParentFragmentManager().popBackStack());
        try {
            StringBuilder s = new StringBuilder();
            for (boolean bestanden : new boolean[]{false,true}) {
                s.append(bestanden ? "Bestanden\n" : "Belegt\n"); String[] titel = kontrolle.zuordnungen(name(), bestanden);
                if (titel.length == 0) s.append("Keine\n");
                for (String t : titel) s.append("• ").append(t).append('\n'); s.append('\n');
            }
            text(view, R.id.details, s.toString());
        } catch (RuntimeException ex) { fehler(view, ex); }
    }
}
