package fortbildungsverwaltung;

public class SachbearbeiterBearbeitenAAS extends BasisAAS {
    public SachbearbeiterBearbeitenAAS() { super(R.layout.sachbearbeiter_bearbeiten_aas); }
    protected boolean darfRolleAendern() { return false; }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        try {
            android.widget.EditText name = view.findViewById(R.id.name), passwort = view.findViewById(R.id.password);
            android.widget.RadioGroup rolle = view.findViewById(R.id.role);
            if (state == null) { name.setText(name()); rolle.check(kontrolle.berechtigung(name()) ? R.id.admin : R.id.normal); }
            passwort.setHint("Leer lassen: Passwort beibehalten");
            for (int i=0; i<rolle.getChildCount(); i++) rolle.getChildAt(i).setEnabled(darfRolleAendern() && kontrolle.istAdminRolle());
            text(view, R.id.ok, "Speichern");
            view.findViewById(R.id.ok).setOnClickListener(v -> ausfuehren(view, () -> kontrolle.bearbeiten(name(), name.getText().toString(), passwort.getText().toString(), rolle.getCheckedRadioButtonId() == R.id.admin)));
        } catch (RuntimeException ex) { fehler(view, ex); view.findViewById(R.id.ok).setEnabled(false); }
    }
}
