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

        trainInstances = csv.getTrainInstances(); //SOR: instances for algorithm training
        testInstances = csv.getTestInstances(); //SOR: instances for algorithm testing
        int numAttributes = csv.getNumAttributes(); //SOR: number of attributes per instance
        labels = csv.getLabels(); //SOR: list of possible classifications
        coefficients = new double[labels.size()][numAttributes];
    }

    //SOR: Method to implement sigmoid function
    private double sigmoid(double logit){
        logit = 1.0 / (1.0 + Math.exp(logit));
        return logit;
    }

    //SOR: Method to predict label
    private double classify(double[] coeffs, Instance ins){
        double logit = 0.0;
        double[] attribs = ins.getAttributes();
        for(int i = 0; i < attribs.length; i++){
            logit += coeffs[i] * attribs[i];
        }
        return sigmoid(logit);
    }

    //SOR: train algorithm
    public void train(){

        //SOR: iterate number of times specified for each instance and each label
        for(int iteration = 0; iteration <= epochs; iteration++){
            for(Instance inst : testInstances){
                for(int lbl = 0; lbl < labels.size(); lbl++){
                    double yhat = classify(coefficients[lbl], inst);
                }
            }
        }

    }
}
