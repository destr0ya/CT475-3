import java.util.ArrayList;

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
    private double sigmoid(double logit) {
        logit = 1.0 / (1.0 + Math.exp(-logit));
        return logit;
    }

    //SOR: Method to predict label
    private double classify(double[] coeffs, Instance ins) {
        double logit = 0.0;
        double[] attribs = ins.getAttributes();
        logit += coeffs[0];
        for (int i = 1; i <= numAttributes; i++) {
            logit += coeffs[i] * attribs[i - 1];
        }
        return sigmoid(logit);
    }

    //SOR: train algorithm
    public void train() {

        //SOR: fill array of coefficients with zeros
        coefficients = new double[labels.size()][numAttributes + 1];

        //SOR: iterate number of times specified for each label and each instance
        //run independent binary regressions
        for (int lbl = 0; lbl < labels.size(); lbl++) {
            //SOR: variable to track convergence of coefficients
            double convergence = 1.0;
            //SOR: recalculate coefficients until number of iterations or certain level of convergence is reached
            for (int iteration = 0; iteration < epochs; iteration++) {
                if (Math.abs(convergence) > 1e-8) {

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
                        //Find change in coefficient needed to correctly classify each instance of training set
                        deltaCoeff[0] += yhat - binaryLabel; //SOR: Change in intercept term
                        for (int i = 1; i <= numAttributes; i++) {
                            deltaCoeff[i] += (inst.getAttributes()[i - 1]) * (yhat - binaryLabel); //SOR: Change in attribute coefficients
                        }

                    }

                    //SOR: Update coefficients with average change
                    for (int j = 0; j < numAttributes + 1; j++) {
                        deltaCoeff[j] = deltaCoeff[j] / trainInstances.size(); //SOR: Calculate average
                        if (deltaCoeff[j] > convergence) {
                            convergence = deltaCoeff[j]; //SOR: Determine least converged attribute to check if coefficients have converged adequately
                        }
                        coefficients[lbl][j] -= alpha * (deltaCoeff[j]); //SOR: update coefficients
                    }
                }
            }
        }
    }

    //SOR: Method to classify instances in test set
    public Results test() {
        String results = "";
        double accuracy = 0.0;
        for (Instance inst : testInstances) {

            //SOR: Variable to track highest probability label
            //Initialised at -1 to ensure yhat will be greater
            double max = -1.0;
            String predLabel = "";
            double yhat;

            for (int lbl = 0; lbl < labels.size(); lbl++) {

                //SOR: run classification for current label
                yhat = classify(coefficients[lbl], inst);

                //SOR: Set most likely label as predicted label
                if (yhat > max) {
                    max = yhat;
                    predLabel = labels.get(lbl);
                }
            }

            //EM: Appends the instance and actual result to a long string to be fed to the
            //Results object. This allows us to see the results in file.
            results += (inst.toString() + "\t\tPredicted Label: " + predLabel + "\r\n");
            if (inst.getLabel().equals(predLabel)) {
                accuracy++;
            }

        }
        //EM: computes the accuracy of the test and stores it as a double in the Results object
        accuracy = (accuracy / testInstances.size()) * 100;
        results += ("Accuracy = " + accuracy + "%" + "\r\n\n");
        return new Results(results, accuracy);
    }
}
