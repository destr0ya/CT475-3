import java.util.ArrayList;

public class Test {

    // EM
    // Generic testing class for checking file read-in and parsing of instances.
    public static void main (String [] args) {
        CSVReader csv = new CSVReader(0.5, "/Users/Emma/Documents/Uni/Final Year/Machine Learning & Data Mining/Assignments/Assignment 3/owls15.csv");
        ArrayList<Instance> trainInstances = csv.getTrainInstances(); //SOR: instances for algorithm training
        ArrayList<Instance> testInstances = csv.getTestInstances(); //SOR: instances for algorithm testing
        int numAttributes = csv.getNumAttributes(); //SOR: number of attributes per instance
        ArrayList<String> labels = csv.getLabels();

        System.out.println("Training Instances: \n" + trainInstances.toString());
        System.out.println("\nTesting Instances: \n" + testInstances.toString());
        System.out.println("\nNumber of attributes: " + numAttributes);
        System.out.println("\n Labels: " + labels.toString());
    }

}
