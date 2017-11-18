import java.util.ArrayList;

//SOR: class to store data for specific instance
public class Instance {

    public double[] attributes;
    public String label;

    public Instance(double[] attrib, String lbl){
        this.attributes = attrib; //SOR: set attributes of instance
        this.label = lbl; //SOR: set label
    }

    public double[] getAttributes() {
        return attributes;
    }

    public String getLabel() {
        return label;
    }


}