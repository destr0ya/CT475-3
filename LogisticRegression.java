import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.StrictMath.exp;

public class LogisticRegression {

    String [] row = new String[10];
    int l = row.length;

    public static void main (String [] args) {
        String csvFile ="/Users/Emma/Documents/Uni/Final Year/Machine Learning & Data Mining/Assignments/Assignment 3/owls15.csv";
        String line = "";
        String del = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            while ((line = br.readLine()) != null) {
                String[] row = line.split(del);
                System.out.println("Test" + row[4]);
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
