import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class GUI extends JFrame {

    //SOR: variables to store text box contents
    String fileInStr;
    int numTestsInt;
    int iterInt;
    double lrDouble;
    double splitDouble;
    String fileOutStr;

    //EM: Various components needed for GUI
    private Container container;
    private GridBagLayout layout;
    private JLabel fileInLabel, numTestsLabel, iterationsLabel, learningRateLabel, percentageSplitLabel, fileOutLabel;
    private JTextArea fileIn, numTests,  iterations, learningRate, percentageSplit, fileOut;
    private GridBagConstraints constraints;

    public GUI() {

        super("Logistic Regression Test GUI");

        //EM: Initialising layout
        container = getContentPane();
        layout = new GridBagLayout();
        container.setLayout(layout);
        constraints = new GridBagConstraints();
        //SOR: altered constraints so components fill space
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        //EM: Initialisation of labels and text boxes.
        fileInLabel = new JLabel("CSV File Location: ");
        numTestsLabel = new JLabel("Number of Tests: ");
        iterationsLabel = new JLabel("Number of Iterations: ");
        learningRateLabel = new JLabel("Learning Rate: ");
        percentageSplitLabel = new JLabel("Percentage of data for training: ");
        fileOutLabel = new JLabel("Results File Location: ");

        //EM: Default values for items, which can all be amended by the user within the constraints (see error handling below)
        fileIn = new JTextArea("/Users/Emma/Documents/Uni/Final Year/Machine Learning & Data Mining/Assignments/Assignment 3/owls15.csv");
        numTests = new JTextArea("10");
        iterations = new JTextArea("100000");
        learningRate = new JTextArea("0.01");
        percentageSplit = new JTextArea("0.66");
        fileOut = new JTextArea("/Users/Emma/Documents/Uni/Final Year/Machine Learning & Data Mining/Assignments/Assignment 3/owls.txt");

        JButton start = new JButton("Go!");

        //EM: Adding the components to the JFrame
        addComponent(fileInLabel, 1, 1, 2, 2);
        addComponent(numTestsLabel, 3, 1, 2, 2);
        addComponent(iterationsLabel, 5, 1, 2, 2);
        addComponent(learningRateLabel, 7, 1, 2, 2);
        addComponent(percentageSplitLabel, 9, 1, 2, 2);
        addComponent(fileOutLabel, 11, 1, 2, 2);

        addComponent(fileIn, 1, 4, 10, 2);
        addComponent(numTests, 3, 4, 10, 2);
        addComponent(iterations, 5, 4, 10, 2);
        addComponent(learningRate, 7, 4, 10, 2);
        addComponent(percentageSplit, 9, 4, 10, 2);
        addComponent(fileOut, 11, 4, 10, 2);

        addComponent(start, 14, 4, 1, 1);

        //EM: Visualises the GUI and implements a handler for the button.
        ButtonHandler handler = new ButtonHandler();
        start.addActionListener(handler);

        setSize(1000, 500);
        setVisible(true);

    }

    //EM: Basic method for adding components with GridBagLayout
    private void addComponent(Component component, int row, int column, int width, int height) {
        constraints.gridx = column;
        constraints.gridy = row;

        constraints.gridwidth = width;
        constraints.gridheight = height;

        layout.setConstraints(component, constraints);
        container.add(component);
    }

    //EM: Main method to start the program
    public static void main(String args[]){
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //EM: Private inner class to handle button action
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //SOR: implemented error checking, wrote text box contents to variables
            checkInput();
            fileInStr = fileIn.getText();
            fileOutStr = fileOut.getText();
            //EM: Added separate method in order to get the test results and write to file.
            try {
                doLogisticRegression();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null,"Invalid input: Invalid file write location.");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
    }

    //SOR: Error handling for GUI inputs
    private void checkInput() {

        try{
            numTestsInt = Integer.parseInt(numTests.getText());
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Invalid input: Number of tests must be an integer.");
        }
        try{
            iterInt = Integer.parseInt(iterations.getText());
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Invalid input: Number of iterations must be an integer.");
        }
        try{
            lrDouble = Double.parseDouble(learningRate.getText());
            if (lrDouble > 1){
                throw new Exception();
            }
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Invalid input: Number of iterations must be a double less than 1.");
        }
        try{
            splitDouble = Double.parseDouble(percentageSplit.getText());
            if (lrDouble > 1){
                throw new Exception();
            }
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Invalid input: Percentage split must be a double less than 1.");
        }
    }

    //EM
    //This appeared to be the most reasonable solution to retrieve the predicted/actual values of
    //the test set, display the accuracy for each individual test and to calculate the overall average.
    private void doLogisticRegression() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileOutStr, "UTF-8");
        double accuracy = 0.0;
        ArrayList<Results> resultsList = new ArrayList<>();
        for (int i = 0; i < numTestsInt; i++) {
            CSVReader csvReader = new CSVReader(splitDouble, fileInStr);
            LogisticRegression lr = new LogisticRegression(lrDouble, iterInt, csvReader, fileOutStr);

            lr.train();
            resultsList.add(lr.test());
            accuracy += lr.test().getAccuracy();
        }
        for (Results res : resultsList) {
            writer.println(res);
        }
        Double overallAccuracy = accuracy/numTestsInt;
        writer.println("\nOverall accuracy for " + numTestsInt + " tests: " + overallAccuracy + "%");
        writer.close();
    }
}
