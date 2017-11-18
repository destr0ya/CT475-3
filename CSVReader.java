import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//EM
public class CSVReader {

    static ArrayList<Instance> instances = new ArrayList<>();
    static double percentage;
    int targetsize;
    static int numAttributes;
    static String fileLocation;

    public CSVReader(double percentage, String fileLocation) {
        this.percentage = percentage;
        this.fileLocation = fileLocation;

        readCSVFile();
        targetsize = (int)Math.round(instances.size()*percentage);
    }

    public static void readCSVFile() {
        String line = "";
        String del = ",";
        int i = 0;

        //Read in every line of the CSV file, assign the first four columns as the attributes,
        //and the final column as the label.
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            while ((line = br.readLine()) != null) {
                Instance instance;
                String[] row = line.split(del);
                numAttributes = row.length - 1;
                double[] attributes = new double[numAttributes];
                for (int j = 0; j < numAttributes; j++) {
                    attributes[j] = Double.parseDouble(row[j]);
                }
                String label = row[row.length - 1];

                instance = new Instance(attributes, label);
                instances.add(instance);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Shuffle the ArrayList, and separate into training and testing data
        Collections.shuffle(instances);

    }

    public ArrayList<Instance> getTrainInstances() {
        ArrayList<Instance> training = new ArrayList<>();
        training.addAll(instances.subList(0, targetsize));
        return training;
    }

    public ArrayList<Instance> getTestInstances() {
        ArrayList<Instance> testing = new ArrayList<>();
        testing.addAll(instances.subList(targetsize, instances.size()));
        return testing;
    }

    public int getNumAttributes() {
        return numAttributes;
    }

    public ArrayList<String> getLabels() {
        ArrayList<String> labels =  new ArrayList<>();
        labels.add(instances.get(0).getLabel());
        for (Instance instance : instances) {
            if (!(labels.contains(instance.getLabel()))) {
                labels.add(instance.getLabel());
            }
        }
        return labels;
    }
}
