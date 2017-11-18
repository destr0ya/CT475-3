import sun.security.jca.GetInstance;

import java.util.ArrayList;

public class LogisticRegression {

    private double alpha;
    private double[][] coefficients;
    private int epochs;
    private ArrayList<GetInstance.Instance> instances;
    private int numLabels;

    //SOR: Constructor for Logistic Regression object
    public LogisticRegression(double rate, int iter, CSVReader csv) {
        this.alpha = rate; //SOR: learning rate
        this.epochs = iter; //SOR: number of iterations during training

        instances = csv.getInstances; //SOR: instances read from file
        numLabels = csv.getNumLabels; //SOR: number of possible classifications
        coefficients = new double[numLabels][instances.size()];
    }

    //SOR: Method to implement sigmoid function
    private double sigmoid(double logit){
        logit = 1.0 / (1.0 + Math.exp(logit));
        return logit;
    }

    public void train(){

    }
}