package ML3;

import java.util.ArrayList;
import java.util.Arrays;

public class LogisticRegression {

    private double alpha;
    private double[][] coefficients;
    private int epochs;
    private ArrayList<Instance> trainInstances;
    private ArrayList<Instance> testInstances;
    private ArrayList<String> labels;
    private int numAttributes;
    private String fileOut;
    double binaryLabel;

    //SOR: Constructor for Logistic Regression object
    public LogisticRegression(double rate, int iter, CSVReader csv, String output) {
        this.alpha = rate; //SOR: learning rate
        this.epochs = iter; //SOR: number of iterations during training
        this.fileOut = output; //SOR: string specifying file output location

        trainInstances = csv.getTrainInstances(); //SOR: instances for algorithm training
        testInstances = csv.getTestInstances(); //SOR: instances for algorithm testing
        numAttributes = csv.getNumAttributes(); //SOR: number of attributes per instance
        labels = csv.getLabels(); //SOR: list of possible classifications
        coefficients = new double[labels.size()][numAttributes + 1];
    }

    //SOR: Method to implement sigmoid function
    private double sigmoid(double logit){
        logit = 1.0 / (1.0 + Math.exp(-logit));
        return logit;
    }

    //SOR: Method to predict label
    private double classify(double[] coeffs, Instance ins){
        double logit = 0.0;
        double[] attribs = ins.getAttributes();
        logit += coeffs[0];
        for(int i = 1; i <= numAttributes; i++){
            logit += coeffs[i] * attribs[i - 1];
        }
        return sigmoid(logit);
    }

    //SOR: train algorithm
    public void train(){

        //SOR: fill array of coefficients with zeros
        coefficients = new double[labels.size()][numAttributes + 1];

        //SOR: iterate number of times specified for each label and each instance
        //run independent binary regressions
        for (int lbl = 0; lbl < labels.size(); lbl++){
            //SOR: variable to track convergence of coefficients
            double convergence = 1.0;
            //SOR: recalculate coefficients until number of iterations or certain level of convergence is reached
            for (int iteration = 0; iteration < epochs; iteration++){
                if (Math.abs(convergence) > 0.00001) {

                    double[] deltaCoeff = new double[numAttributes + 1];

                    for (Instance inst : trainInstances) {
                        //SOR: assign binary value to label, either is label under test or is not label under test
                        if (labels.get(lbl).equals(inst.getLabel())) {
                            binaryLabel = 1.0; //SOR: if label of instance is label under test, label is 1
                        } else {
                            binaryLabel = 0.0; //SOR: if label of instance is not label under test, label is 0
                        }
                        //SOR: run classification for current label
                        double yhat = classify(coefficients[lbl], inst);

                        //SOR: run gradient descent calculations
                        deltaCoeff[0] += yhat - binaryLabel;
                        for (int i = 1; i <= numAttributes; i++) {
                            deltaCoeff[i] += (inst.getAttributes()[i-1]) * (yhat - binaryLabel);
                        }

                    }

                    for (int j = 0; j < numAttributes + 1; j++) {
                        deltaCoeff[j] = deltaCoeff[j] / trainInstances.size();
                        if (deltaCoeff[j] > convergence){
                            convergence = deltaCoeff[j];
                        }
                        coefficients[lbl][j] -= alpha * (deltaCoeff[j]);
                    }
                }
            }

        }

    }

    public void test(){

        double accuracy = 0.0;
        for (Instance inst : testInstances) {

            double max = -1.0;
            String predLabel = "";
            double yhat;

            for (int lbl = 0; lbl < labels.size(); lbl++) {

                //SOR: run classification for current label
                yhat = classify(coefficients[lbl], inst);

                if (yhat > max){
                    max = yhat;
                    predLabel = labels.get(lbl);
                }
            }

            System.out.println(inst.toString() + "\t\tPredicted label: " + predLabel);
            if (inst.getLabel().equals(predLabel)){
                accuracy++;
            }

        }

        accuracy = accuracy/testInstances.size();
        System.out.println("Overall accuracy = " + accuracy + "%");
    }
}
