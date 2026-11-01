package fortbildungsverwaltung;

public class LoginAAS extends BasisAAS {
    public LoginAAS() { super(R.layout.login_aas); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        text(view, R.id.ok, "Anmelden");
        String fehler = ((AppState) requireActivity().getApplication()).ladefehler;
        if (fehler != null) { text(view, R.id.error, fehler); view.findViewById(R.id.ok).setEnabled(false); return; }
        view.findViewById(R.id.ok).setOnClickListener(v -> {
            try {
                String name = ((android.widget.EditText) view.findViewById(R.id.name)).getText().toString();
                String passwort = ((android.widget.EditText) view.findViewById(R.id.password)).getText().toString();
                boolean admin = ((android.widget.RadioGroup) view.findViewById(R.id.role)).getCheckedRadioButtonId() == R.id.admin;
                kontrolle.anmelden(name, passwort, admin);
                startActivity(new android.content.Intent(requireContext(), admin ? AdminAS.class : NormalAS.class));
                requireActivity().finish();
            } catch (RuntimeException ex) { fehler(view, ex); }
        });
    }
}
