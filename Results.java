public class Results {

    String values;
    double accuracy;

    public Results(String val, double acc) {
        this.values = val;
        this.accuracy = acc;
    }

    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public String toString() {
        return values;
    }
}
