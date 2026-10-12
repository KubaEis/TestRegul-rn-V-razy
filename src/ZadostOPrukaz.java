import java.time.LocalDate;

public class ZadostOPrukaz {
    private String jmeno;
    private String rasa;
    private LocalDate datumNarozeni;
    private String typZbrane;
    private int levelZkusenosti;

    // konstruktor zadosti
    public ZadostOPrukaz(String jmeno, String rasa, LocalDate datumNarozeni, String typZbrane, int levelZkusenosti) {
        this.jmeno = jmeno;
        this.rasa = rasa;
        this.datumNarozeni = datumNarozeni;
        this.typZbrane = typZbrane;
        this.levelZkusenosti = levelZkusenosti;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public LocalDate getDatumNarozeni() {
        return datumNarozeni;
    }

    public void setDatumNarozeni(LocalDate datumNarozeni) {
        this.datumNarozeni = datumNarozeni;
    }

    public String getTypZbrane() {
        return typZbrane;
    }

    public void setTypZbrane(String typZbrane) {
        this.typZbrane = typZbrane;
    }

    public int getLevelZkusenosti() {
        return levelZkusenosti;
    }

    public void setLevelZkusenosti(int levelZkusenosti) {
        this.levelZkusenosti = levelZkusenosti;
    }

    @Override
    public String toString() {
        return "Jmeno: "+ jmeno +" Rasa: " + rasa + " Datum narozeni: " + datumNarozeni.getDayOfMonth() + "." + datumNarozeni.getMonthValue() + "." + datumNarozeni.getYear() + " Typ zbrane: " + typZbrane + " Level zkusenosti: " + levelZkusenosti;
    }
}
