package fortbildungsverwaltung;

public class SachbearbeiterErfassenAAS extends BasisAAS {
    public SachbearbeiterErfassenAAS() { super(R.layout.sachbearbeiter_erfassen_aas); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        text(view, R.id.ok, "Erfassen");
        view.findViewById(R.id.ok).setOnClickListener(v -> ausfuehren(view, () -> kontrolle.erfassen(
            ((android.widget.EditText) view.findViewById(R.id.name)).getText().toString(),
            ((android.widget.EditText) view.findViewById(R.id.password)).getText().toString(),
            ((android.widget.RadioGroup) view.findViewById(R.id.role)).getCheckedRadioButtonId() == R.id.admin)));
    }
}
