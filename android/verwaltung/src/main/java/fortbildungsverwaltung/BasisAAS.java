package fortbildungsverwaltung;

public abstract class BasisAAS extends androidx.fragment.app.Fragment {
    protected FortbildungsService kontrolle;
    protected BasisAAS(int layout) { super(layout); }
    @Override public void onCreate(android.os.Bundle state) {
        super.onCreate(state); kontrolle = ((AppState) requireActivity().getApplication()).kontrolle;
    }
    protected String name() { return requireArguments().getString("name", ""); }
    protected void text(android.view.View view, int id, String text) { ((android.widget.TextView) view.findViewById(id)).setText(text); }
    protected void fehler(android.view.View view, RuntimeException ex) { text(view, R.id.error, ex.getMessage()); }
    protected void ausfuehren(android.view.View view, Runnable action) {
        try {
            action.run(); kontrolle.speichern();
            view.findViewById(R.id.ok).setEnabled(false);
            android.widget.Toast.makeText(requireContext(), "Gespeichert", android.widget.Toast.LENGTH_SHORT).show();
            ((MenueAS) requireActivity()).startseite();
        } catch (RuntimeException ex) { fehler(view, ex); }
    }
    protected android.os.Bundle argumente() { return new android.os.Bundle(requireArguments()); }
    protected void wechseln(androidx.fragment.app.Fragment ziel, android.os.Bundle args) {
        ziel.setArguments(args);
        getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, ziel).addToBackStack(null).commit();
    }
}
