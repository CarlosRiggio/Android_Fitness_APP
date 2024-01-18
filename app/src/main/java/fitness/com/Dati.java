package fitness.com;

public class Dati {
    private String data;
    private String weight;
    private String bmi;

    public Dati(String data, String weight, String bmi) {
        this.data = data;
        this.weight = weight;
        this.bmi = bmi;
    }

    public String getData() {
        return data;
    }

    public String getPeso() {
        return weight;
    }

    public String getBmi() {
        return bmi;
    }

}