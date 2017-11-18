import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//EM
public class CSVReader {

    static ArrayList<Instance> instances = new ArrayList<>();

    public static void main (String [] args) {
        String csvFile = "/Users/Emma/Documents/Uni/Final Year/Machine Learning & Data Mining/Assignments/Assignment 3/owls15.csv";
        String line = "";
        String del = ",";
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                Instance instance;
                double[] attributes = new double[4];
                String[] row = line.split(del);
                attributes[0] = Double.parseDouble(row[0]);
                attributes[1] = Double.parseDouble(row[1]);
                attributes[2] = Double.parseDouble(row[2]);
                attributes[3] = Double.parseDouble(row[3]);
                String label = row[4];

                instance = new Instance(attributes, label);
                instances.add(instance);
                System.out.println(instance.toString());
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
