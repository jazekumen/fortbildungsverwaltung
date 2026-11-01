package fortbildungsverwaltung;

public class NormalSachbearbeiterBearbeitenAAS extends SachbearbeiterBearbeitenAAS {
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        super.onViewCreated(view, state); android.widget.RadioGroup rolle = view.findViewById(R.id.role);
        for (int i=0; i<rolle.getChildCount(); i++) rolle.getChildAt(i).setEnabled(false);
    }
}
