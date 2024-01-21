package fitness.com;

public class Exercise {

    String name;
    String rep;
    String recovery_t;
    String weight;
    int image;

    public Exercise(String name, String rep, String recovery_t, String weight, int image) {
        this.name = name;
        this.rep = rep;
        this.recovery_t = recovery_t;
        this.weight = weight;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRecovery_t() {
        return recovery_t;
    }

    public void setRecovery_t(String recovery_t) {
        this.recovery_t = recovery_t;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
