package modell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kranvermietung {
    private final String name;
    private final String adresse;
    private final List<Kran> bestand;

    public Kranvermietung(String name, String adresse, List<Kran> initialBestand) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        }
        if (adresse == null || adresse.isBlank()) {
            throw new IllegalArgumentException("Adresse darf nicht leer sein.");
        }
        this.name = name;
        this.adresse = adresse;
        this.bestand = new ArrayList<>(initialBestand == null ? List.of() : initialBestand);
    }

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<Kran> getAlleKrane() {
        return Collections.unmodifiableList(new ArrayList<>(bestand));
    }

    public Kran verleiheKran(String modellbezeichnung) {
        for (int i = 0; i < bestand.size(); i++) {
            Kran kran = bestand.get(i);
            if (kran.getModellbezeichnung().equals(modellbezeichnung)) {
                bestand.remove(i);
                return kran;
            }
        }
        // Kein Kran mit dieser Modellbezeichnung vorhanden.
        return null;
    }

    public List<Kran> suchePassendeKrane(double benoetigtesLastgewicht, double benoetigteAuslegerlaenge,
                                        double maxZulaessigesGesamtgewicht) {
        List<Kran> passende = new ArrayList<>();
        for (Kran kran : bestand) {
            if (kran.ueberschreitetMaxLastgewicht(benoetigtesLastgewicht)) {
                continue;
            }
            if (!kran.typGrenzwertEingehalten(benoetigtesLastgewicht, maxZulaessigesGesamtgewicht)) {
                continue;
            }
            if (benoetigteAuslegerlaenge > kran.getMaxAuslegerlaenge()) {
                continue;
            }
            if (kran instanceof Turmkran turmkran) {
                if (!turmkran.istGeeignetFuer(benoetigtesLastgewicht, benoetigteAuslegerlaenge)) {
                    continue;
                }
            }
            passende.add(kran);
        }
        return passende;
    }

    @Override
    public String toString() {
        return String.format("Kranvermietung{Name='%s', Adresse='%s', Bestand=%d}", name, adresse, bestand.size());
    }
}
