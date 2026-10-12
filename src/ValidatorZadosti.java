import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ValidatorZadosti {

    // Regulární výrazy pro jednotlivá pole
    private Pattern patternJmeno = Pattern.compile("^[A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ][a-záčďéěíňóřšťúůýž]{2,18}$");
    private Pattern patternRasa = Pattern.compile("^Elf$|^Trpaslík$|^Člověk$|^Ork$|^Hobit$");
    private Pattern patternDatumNarozeni = Pattern.compile("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$");
    private Pattern patternTypZbrane = Pattern.compile("^Meč$|^Kuše$|^Hůl$|^Sekera$|^Dýka$");
    private Pattern patternLevelZkusenosti = Pattern.compile("^(100|[0-9]\\d{0,1})$");

    boolean jePlatnyRadek(String radek) {
        // osetrene nacteni radku
        if (radek == null || radek.trim().isEmpty()) {
            System.out.println("Chyba: Prázdný řádek!");
            return false;
        }

        // oddeleni udaju
        String[] udaje = radek.split(";", -1);

        // validace poctu udaju
        if (udaje.length != 5) {
            System.out.println("Chyba: Špatný počet údajů (očakáváno 5, nalezeno " + udaje.length + ")");
            return false;
        }

        boolean jeVseOk = true;

        // rozdeleni udaju
        String jmeno = udaje[0].trim();
        String rasa = udaje[1].trim();
        String datumNarozeni = udaje[2].trim();
        String typZbrane = udaje[3].trim();
        String levelZkusenosti = udaje[4].trim();

        // validace udaje pomoci pattern jmena
        if (!patternJmeno.matcher(jmeno).matches()) {
            System.out.println(" - Neplatné jméno");
            jeVseOk = false;
        }

        // validace udaje pomoci pattern rasy
        if (!patternRasa.matcher(rasa).matches()) {
            System.out.println(" - Neplatná rasa");
            jeVseOk = false;
        }

        // validace udaje pomoci pattern datumu narozeni
        if (!patternDatumNarozeni.matcher(datumNarozeni).matches()) {
            System.out.println(" - Neplatné datum narození");
            jeVseOk = false;
        } else {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");
                LocalDate dNarozeni = LocalDate.parse(datumNarozeni, dtf);

                // Kontrola, zda datum není v budoucnosti
                if (dNarozeni.isAfter(LocalDate.now())) {
                    System.out.println(" - Neplatné datum narození");
                    jeVseOk = false;
                }
                if (dNarozeni.getMonthValue() == 2 &&  dNarozeni.getDayOfMonth() > 29) {
                    System.out.println(" - Neplatné datum narození");
                    jeVseOk = false;
                }
            } catch (Exception e) {
                System.out.println(" - Neplatné datum narození");
                jeVseOk = false;
            }
        }

        // validace udaje pomoci pattern typu zbrane
        if (!patternTypZbrane.matcher(typZbrane).matches()) {
            System.out.println(" - Neplatný typ zbraně");
            jeVseOk = false;
        }

        // validace udaje pomoci pattern level zkusenosti
        if (!patternLevelZkusenosti.matcher(levelZkusenosti).matches()) {
            System.out.println(" - Neplatny level zkusenosti");
            jeVseOk = false;
        }

        return jeVseOk;
    }
}