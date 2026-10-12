void main() throws IOException {
    ValidatorZadosti val =  new ValidatorZadosti();
    List<ZadostOPrukaz> obj = new ArrayList<>();
    Path objednavky = Path.of("data", "data.txt");

    if (!Files.exists(objednavky.getParent())) {
        Files.createDirectories(objednavky.getParent());
    }

    try {
        try (BufferedReader zadostiReader = Files.newBufferedReader(objednavky))
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");
            String[] radkyObjednavky = zadostiReader.lines().toArray(String[]::new);
            for (int i=0; i<radkyObjednavky.length; i++) {
                System.out.println(" - - - - - - - - - - - - \n");
                if (val.jePlatnyRadek(radkyObjednavky[i])){
                    String[] radka = radkyObjednavky[i].split(";");
                    obj.add(new ZadostOPrukaz(radka[0],radka[1], LocalDate.parse(radka[2], dtf),radka[3],Integer.parseInt(radka[4])));
                    System.out.println("Žádost postavy "+radka[0]+" je validní.\n");
                }else{
                    System.out.println("Žádost není validní.\n");
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Chyba při čtení souboru:");
        e.printStackTrace();
    }

    System.out.println("\nSeznam všech postav mladších než 18 let.");
    for (int i=0; i<obj.size(); i++) {
        if ((2026 - obj.get(i).getDatumNarozeni().getYear()) < 18 ){
            System.out.println("    "+obj.get(i).toString());
        }
    }
    System.out.println("\nPočet validních žádostí podle rasy.");
    int pocetElfu = 0;
    int pocetTrpasliku = 0;
    int pocetLidi = 0;
    int pocetOrku = 0;
    int pocetHobitu = 0;
    for (int i=0; i<obj.size(); i++) {
        if (obj.get(i).getRasa().equals("Elf")){
            pocetElfu++;
        }
        if (obj.get(i).getRasa().equals("Trpaslík")){
            pocetTrpasliku++;
        }
        if (obj.get(i).getRasa().equals("Člověk")){
            pocetLidi++;
        }
        if (obj.get(i).getRasa().equals("Ork")){
            pocetOrku++;
        }
        if (obj.get(i).getRasa().equals("Hobit")){
            pocetHobitu++;
        }
    }
    System.out.println("    Elfové: " + pocetElfu);
    System.out.println("    Trpaslíci: " + pocetTrpasliku);
    System.out.println("    Lidé: " + pocetLidi);
    System.out.println("    Orcit: " + pocetOrku);
    System.out.println("    Hobiti: " + pocetHobitu);

    System.out.println("\nJméno a zbraň postavy s nejvyšším levelem.");
    ZadostOPrukaz postava = obj.getFirst();
    for (int i=0; i<obj.size(); i++) {
        if (postava.getLevelZkusenosti() < obj.get(i).getLevelZkusenosti()) {
            postava = obj.get(i);
        }
    }
    System.out.println("    Postava s nejvyšším levelem zkušenosti je " + postava.getJmeno() + "[" + postava.getLevelZkusenosti() + "], jeho zbraň je " + postava.getTypZbrane());
}
