package anwendung;

import java.util.ArrayList;
import java.util.List;

import modell.Autokran;
import modell.Kran;
import modell.Kranvermietung;
import modell.Turmkran;

public class TestbeispielA {
    public static void main(String[] args) {
        List<Kran> bestand = new ArrayList<>();
        Turmkran tk1 = new Turmkran("TK-90 Compact", 40, 10, 50, 150);
        Turmkran tk2 = new Turmkran("TK-200 City", 55, 12, 65, 200);
        Turmkran tk3 = new Turmkran("TK-350 Flex", 110, 18, 76, 380);
        Autokran ak1 = new Autokran("AK-60 Mobile", 48, 70, 46, 4);
        Autokran ak2 = new Autokran("AK-100 AllRoad", 76, 120, 66, 5);
        Autokran ak3 = new Autokran("AK-250 ProLift", 84, 260, 75, 7);

        bestand.add(tk1);
        bestand.add(tk2);
        bestand.add(tk3);
        bestand.add(ak1);
        bestand.add(ak2);
        bestand.add(ak3);

        Kranvermietung vermietung = new Kranvermietung("KranPlus", "Hafenstrasse 12, Bremen", bestand);

        System.out.println("=== Gesamter Kranbestand ===");
        vermietung.getAlleKrane().forEach(System.out::println);

        System.out.println("\n=== ueberschreitetMaxLastgewicht ===");
        System.out.println(tk1.getModellbezeichnung() + " bei 11 t: " + tk1.ueberschreitetMaxLastgewicht(11));
        System.out.println(ak1.getModellbezeichnung() + " bei 65 t: " + ak1.ueberschreitetMaxLastgewicht(65));

        System.out.println("\n=== typGrenzwertEingehalten ===");
        System.out.println(tk2.getModellbezeichnung() + " bei 10 t, max 120 t: "
                + tk2.typGrenzwertEingehalten(10, 120));
        System.out.println(ak2.getModellbezeichnung() + " bei 80 t, max 40 t: "
                + ak2.typGrenzwertEingehalten(80, 40));

        System.out.println("\n=== istGeeignetFuer (Turmkran) ===");
        System.out.println(tk3.getModellbezeichnung() + " fuer 15 t / 20 m: "
                + tk3.istGeeignetFuer(15, 20));
        System.out.println(tk2.getModellbezeichnung() + " fuer 12 t / 70 m: "
                + tk2.istGeeignetFuer(12, 70));

        System.out.println("\n=== verleiheKran ===");
        Kran verliehen = vermietung.verleiheKran("AK-60 Mobile");
        System.out.println("Verliehen: " + (verliehen != null ? verliehen : "kein Kran"));
        Kran nichtVorhanden = vermietung.verleiheKran("AK-999 Phantom");
        System.out.println("Verliehen: " + (nichtVorhanden != null ? nichtVorhanden : "kein Kran"));

        System.out.println("\n=== suchePassendeKrane (Test 1) ===");
        List<Kran> passende1 = vermietung.suchePassendeKrane(10, 30, 120);
        passende1.forEach(System.out::println);

        System.out.println("\n=== suchePassendeKrane (Test 2) ===");
        List<Kran> passende2 = vermietung.suchePassendeKrane(80, 40, 50);
        passende2.forEach(System.out::println);
    }
}
