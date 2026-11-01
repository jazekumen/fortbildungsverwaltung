package fortbildungsverwaltung;

public class FortbildungszuordnungAuswaehlenAAS extends BasisAAS {
    public FortbildungszuordnungAuswaehlenAAS() { super(R.layout.auswahl_aas); }
    @Override public void onViewCreated(android.view.View view, android.os.Bundle state) {
        text(view, R.id.title, "Zuordnung für " + name() + " auswählen");
        try {
            java.util.List<String> titel = new java.util.ArrayList<>(), zeilen = new java.util.ArrayList<>();
            java.util.List<Boolean> status = new java.util.ArrayList<>();
            for (boolean bestanden : new boolean[]{false,true}) for (String t : kontrolle.zuordnungen(name(), bestanden)) {
                titel.add(t); status.add(bestanden); zeilen.add(t + (bestanden ? " (bestanden)" : " (belegt)"));
            }
            if (titel.isEmpty()) text(view, R.id.error, "Keine passende Zuordnung vorhanden.");
            android.widget.ListView liste = view.findViewById(R.id.list);
            liste.setAdapter(new android.widget.ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, zeilen));
            liste.setOnItemClickListener((parent,row,position,id) -> {
                android.os.Bundle args = argumente(); args.putString("titel", titel.get(position)); args.putBoolean("bestanden", status.get(position));
                wechseln(new FortbildungszuordnungLoeschenAAS(), args);
            });
        } catch (RuntimeException ex) { fehler(view, ex); }
    }
}
