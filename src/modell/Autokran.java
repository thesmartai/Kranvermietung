package modell;

public class Autokran extends Kran {
    private final int achsen;

    public Autokran(String modellbezeichnung, double eigengewicht, double maxZulaessigesLastgewicht,
                    double maxAuslegerlaenge, int achsen) {
        super(modellbezeichnung, eigengewicht, maxZulaessigesLastgewicht, maxAuslegerlaenge);
        if (achsen <= 0) {
            throw new IllegalArgumentException("Achsenanzahl muss positiv sein.");
        }
        this.achsen = achsen;
    }

    public int getAchsen() {
        return achsen;
    }

    @Override
    public boolean typGrenzwertEingehalten(double benoetigtesLastgewicht, double maxZulaessigesGesamtgewicht) {
        return (getEigengewicht() + benoetigtesLastgewicht) / achsen <= maxZulaessigesGesamtgewicht;
    }

    @Override
    public String toString() {
        return String.format("%s, Achsen=%d", super.toString(), achsen);
    }
}
