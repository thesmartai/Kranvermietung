package modell;

public class Turmkran extends Kran {
    private final double maxLastmoment;

    public Turmkran(String modellbezeichnung, double eigengewicht, double maxZulaessigesLastgewicht,
                    double maxAuslegerlaenge, double maxLastmoment) {
        super(modellbezeichnung, eigengewicht, maxZulaessigesLastgewicht, maxAuslegerlaenge);
        if (maxLastmoment <= 0) {
            throw new IllegalArgumentException("Maximales Lastmoment muss positiv sein.");
        }
        this.maxLastmoment = maxLastmoment;
    }

    public double getMaxLastmoment() {
        return maxLastmoment;
    }

    @Override
    public boolean typGrenzwertEingehalten(double benoetigtesLastgewicht, double maxZulaessigesGesamtgewicht) {
        return getEigengewicht() + benoetigtesLastgewicht <= maxZulaessigesGesamtgewicht;
    }

    public boolean istGeeignetFuer(double benoetigtesLastgewicht, double benoetigteAuslegerlaenge) {
        if (ueberschreitetMaxLastgewicht(benoetigtesLastgewicht)) {
            return false;
        }
        if (benoetigteAuslegerlaenge > getMaxAuslegerlaenge()) {
            return false;
        }
        double benoetigtesMoment = benoetigtesLastgewicht * benoetigteAuslegerlaenge;
        return benoetigtesMoment <= maxLastmoment;
    }

    @Override
    public String toString() {
        return String.format("%s, MaxMoment=%.2f tm", super.toString(), maxLastmoment);
    }
}
