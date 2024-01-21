package fitness.com;

public class Datas {
    private String localData;
    private String weight;
    private String bmi;

    public Datas(String localData, String weight, String bmi) {
        this.localData = localData;
        this.weight = weight;
        this.bmi = bmi;
    }

    public String getLocalData() {
        return localData;
    }

    public String getWeight() {
        return weight;
    }

    public String getBmi() {
        return bmi;
    }

}