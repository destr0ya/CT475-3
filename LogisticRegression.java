import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.StrictMath.exp;

public class LogisticRegression {

    String [] row = new String[10];
    int l = row.length;
    static double [] bodyLength = new double[150];
    static double [] wingLength = new double[150];
    static double [] bodyWidth = new double[150];
    static double [] wingWidth = new double[150];
    static String [] type = new String[150];

    public static void main (String [] args) {
        String csvFile ="/Users/Emma/Documents/Uni/Final Year/Machine Learning & Data Mining/Assignments/Assignment 3/owls15.csv";
        String line = "";
        String del = ",";
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            while ((line = br.readLine()) != null) {
                String[] row = line.split(del);
                bodyLength[i] = Double.parseDouble(row[0]);
                wingLength[i] = Double.parseDouble(row[1]);
                bodyWidth[i] = Double.parseDouble(row[2]);
                wingWidth[i] = Double.parseDouble(row[3]);
                type[i] = row[4];

                System.out.println("Body Length: " + bodyLength[i] + " Wing Length: " + wingLength[i] + " Body Width: " + bodyWidth[i] + " Wing Width: " + wingWidth[i] + " Type: " + type[i] + "\n");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    double predict (int row, float [] coefficients) {
//        float yhat = coefficients[0];
//        for (int i =0; i <= l; i++) {
//            yhat+= coefficients[i + 1] * row[i];
//        }
//        return 1.0/(1.0 +exp(-yhat));
//    }

}
