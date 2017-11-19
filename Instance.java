//SOR: class to store data for specific instance
public class Instance {

    public double[] attributes;
    public String label;

    public Instance(double[] attrib, String lbl) {
        this.attributes = attrib; //SOR: set attributes of instance
        this.label = lbl; //SOR: set label
    }

    public double[] getAttributes() {
        return attributes;
    }

    public String getLabel() {
        return label;
    }

    //EM
    //Simple toString() method used for testing.
    public String toString() {
        String returnString = "";
        for (int i = 0; i < attributes.length; i++) {
            returnString += attributes[i];
            returnString += " ";
        }
        returnString += label;
        return returnString;
    }
}