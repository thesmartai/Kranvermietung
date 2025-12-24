package modell;

public abstract class Kran {
    private final String modellbezeichnung;
    private final double eigengewicht;
    private final double maxZulaessigesLastgewicht;
    private final double maxAuslegerlaenge;

    public Kran(String modellbezeichnung, double eigengewicht, double maxZulaessigesLastgewicht, double maxAuslegerlaenge) {
        if (modellbezeichnung == null || modellbezeichnung.isBlank()) {
            throw new IllegalArgumentException("Modellbezeichnung darf nicht leer sein.");
        }
        if (eigengewicht <= 0 || maxZulaessigesLastgewicht <= 0 || maxAuslegerlaenge <= 0) {
            throw new IllegalArgumentException("Gewichte und Auslegerlaenge muessen positiv sein.");
        }
        this.modellbezeichnung = modellbezeichnung;
        this.eigengewicht = eigengewicht;
        this.maxZulaessigesLastgewicht = maxZulaessigesLastgewicht;
        this.maxAuslegerlaenge = maxAuslegerlaenge;
    }

    public String getModellbezeichnung() {
        return modellbezeichnung;
    }

    public double getEigengewicht() {
        return eigengewicht;
    }

    public double getMaxZulaessigesLastgewicht() {
        return maxZulaessigesLastgewicht;
    }

    public double getMaxAuslegerlaenge() {
        return maxAuslegerlaenge;
    }

    public boolean ueberschreitetMaxLastgewicht(double benoetigtesLastgewicht) {
        return benoetigtesLastgewicht > maxZulaessigesLastgewicht;
    }

    public abstract boolean typGrenzwertEingehalten(double benoetigtesLastgewicht, double maxZulaessigesGesamtgewicht);

    @Override
    public String toString() {
        return String.format("%s{Modell='%s', EG=%.2f t, MaxLast=%.2f t, L=%.2f m}",
                getClass().getSimpleName(), modellbezeichnung, eigengewicht, maxZulaessigesLastgewicht, maxAuslegerlaenge);
    }
}
