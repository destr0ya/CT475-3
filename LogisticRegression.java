package ML3;

import java.util.ArrayList;

public class LogisticRegression {

    private double alpha;
    private double[][] coefficients;
    private int epochs;
    private ArrayList<Instance> trainInstances;
    private ArrayList<Instance> testInstances;
    private ArrayList<String> labels;
    private int numLabels;

    //SOR: Constructor for Logistic Regression object
    public LogisticRegression(double rate, int iter, CSVReader csv) {
        this.alpha = rate; //SOR: learning rate
        this.epochs = iter; //SOR: number of iterations during training

        trainInstances = csv.getTrainInstances; //SOR: instances for algorithm training
        testInstances = csv.getTestInstances; //SOR: instances for algorithm testing
        labels = csv.getLabels; //SOR: number of possible classifications
        coefficients = new double[labels.size()][trainInstances.size()];
    }

    //SOR: Method to implement sigmoid function
    private double sigmoid(double logit){
        logit = 1.0 / (1.0 + Math.exp(logit));
        return logit;
    }

    //SOR: train algorithm
    public void train(){

        for(int iteration = 0; iteration <= epochs; iteration++){
            for()
        }

    }
}
