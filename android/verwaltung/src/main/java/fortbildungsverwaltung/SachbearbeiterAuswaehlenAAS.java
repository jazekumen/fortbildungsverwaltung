package fortbildungsverwaltung;

public class SachbearbeiterAuswaehlenAAS extends BasisAAS {
    public SachbearbeiterAuswaehlenAAS() { super(R.layout.auswahl_aas); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        text(view, R.id.title, "Sachbearbeiter auswählen");
        try {
            String[] namen = kontrolle.namen(); android.widget.ListView liste = view.findViewById(R.id.list);
            liste.setAdapter(new android.widget.ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, namen));
            if (namen.length == 0) text(view, R.id.error, "Keine Sachbearbeiter vorhanden.");
            liste.setOnItemClickListener((parent, row, position, id) -> {
                android.os.Bundle args = argumente(); args.putString("name", namen[position]);
                androidx.fragment.app.Fragment ziel;
                switch (args.getInt("aktion")) {
                    case 2: ziel = new SachbearbeiterLoeschenAAS(); break;
                    case 3: ziel = kontrolle.istAdminRolle() ? new AdminSachbearbeiterBearbeitenAAS() : new NormalSachbearbeiterBearbeitenAAS(); break;
                    case 4: ziel = new FortbildungAuswaehlenAAS(); break;
                    case 5: ziel = new FortbildungszuordnungAuswaehlenAAS(); break;
                    default: ziel = new FortbildungszuordnungenAnzeigenAAS();
                }
                wechseln(ziel, args);
            });
        } catch (RuntimeException ex) { fehler(view, ex); }
    }
}
