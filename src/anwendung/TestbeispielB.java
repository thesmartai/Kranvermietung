package anwendung;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import modell.Autokran;
import modell.Kran;
import modell.Kranvermietung;
import modell.Turmkran;

public class TestbeispielB {
    public static void main(String[] args) {
        List<Kran> bestand = new ArrayList<>();

        addMehrfach(bestand, new Turmkran("TK-200 City", 55, 12, 65, 200), 1);
        addMehrfach(bestand, new Turmkran("TK-350 Flex", 110, 18, 76, 380), 7);
        addMehrfach(bestand, new Turmkran("TK-450 HighRise", 160, 26, 80, 520), 1);
        addMehrfach(bestand, new Turmkran("TK-70 Fast", 260, 6, 40, 75), 7);

        addMehrfach(bestand, new Autokran("AK-60 Mobile", 48, 70, 46, 4), 4);
        addMehrfach(bestand, new Autokran("AK-100 AllRoad", 76, 120, 66, 5), 1);
        addMehrfach(bestand, new Autokran("AK-250 ProLift", 84, 260, 75, 7), 1);
        addMehrfach(bestand, new Autokran("AK-450 Mega", 120, 500, 50, 9), 1);

        Kranvermietung vermietung = new Kranvermietung("KranKontor", "Industriepark 5, Essen", bestand);

        System.out.println("=== Bestand (gruppiert) ===");
        ausgabeGruppiert(vermietung.getAlleKrane());

        System.out.println("\n=== Suche Test 1 ===");
        List<Kran> passende1 = vermietung.suchePassendeKrane(25, 10, 120);
        passende1.forEach(System.out::println);

        System.out.println("\n=== Suche Test 2 ===");
        List<Kran> passende2 = vermietung.suchePassendeKrane(130, 45, 30);
        passende2.forEach(System.out::println);

        System.out.println("\n=== Verleihe TK-350 Flex ===");
        vermietung.verleiheKran("TK-350 Flex");
        ausgabeGruppiert(vermietung.getAlleKrane());
    }

    private static void addMehrfach(List<Kran> bestand, Kran kran, int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            bestand.add(kran instanceof Turmkran turmkran
                    ? new Turmkran(turmkran.getModellbezeichnung(), turmkran.getEigengewicht(),
                    turmkran.getMaxZulaessigesLastgewicht(), turmkran.getMaxAuslegerlaenge(),
                    turmkran.getMaxLastmoment())
                    : new Autokran(((Autokran) kran).getModellbezeichnung(), kran.getEigengewicht(),
                    kran.getMaxZulaessigesLastgewicht(), kran.getMaxAuslegerlaenge(), ((Autokran) kran).getAchsen()));
        }
    }

    private static void ausgabeGruppiert(List<Kran> krane) {
        Map<String, Integer> zaehlung = new LinkedHashMap<>();
        for (Kran kran : krane) {
            String schluessel = kran.getClass().getSimpleName() + " - " + kran.getModellbezeichnung();
            zaehlung.put(schluessel, zaehlung.getOrDefault(schluessel, 0) + 1);
        }
        zaehlung.forEach((modell, anzahl) -> System.out.println(modell + ": " + anzahl + "x"));
    }
}
