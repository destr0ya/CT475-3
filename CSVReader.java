import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//EM
//This class reads in a CSV file and creates an ArrayList of instances.
//It also splits the data into training/testing based on a percentage given.
public class CSVReader {

    private static ArrayList<Instance> instances = new ArrayList<>();
    private static double percentage;
    private int targetsize;
    private static int numAttributes;
    private static String fileLocation;

    //EM
    //Constructor for CSV Reader. User must give a percentage to split the
    //data, and the location of the CSV file.
    public CSVReader(double percentage, String fileLocation) {
        this.percentage = percentage;
        this.fileLocation = fileLocation;

        instances.clear();
        readCSVFile();
        targetsize = (int) Math.round(instances.size() * percentage);
    }

    public static void readCSVFile() {
        String line = "";
        String del = ",";
        int i = 0;

        //EM
        //Read in every line of the CSV file, assign the first k-1 columns as attributes
        //of the new Instance, and the final column k as the label.
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
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Invalid input: Invalid file read location.");
        }

        //EM
        //Shuffle the ArrayList
        Collections.shuffle(instances);


    }

    //EM
    //Returns the training data, based on the percentage specified.
    public ArrayList<Instance> getTrainInstances() {
        ArrayList<Instance> training = new ArrayList<>();
        training.addAll(instances.subList(0, targetsize));
        return training;
    }

    //EM
    //Returns the testing data, based on the percentage specified.
    public ArrayList<Instance> getTestInstances() {
        ArrayList<Instance> testing = new ArrayList<>();
        testing.addAll(instances.subList(targetsize, instances.size()));
        return testing;
    }

    //EM
    //Returns the number of attributes of the item in question.
    public int getNumAttributes() {
        return numAttributes;
    }

    //EM
    //Returns the labels in the dataset.
    public ArrayList<String> getLabels() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add(instances.get(0).getLabel());
        for (Instance instance : instances) {
            if (!(labels.contains(instance.getLabel()))) {
                labels.add(instance.getLabel());
            }
        }
        return labels;
    }
}
