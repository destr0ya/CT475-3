import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    //SOR: variables to store text box contents
    String fileInStr;
    int numTestsInt;
    int iterInt;
    double lrDouble;
    double splitDouble;
    String fileOutStr;

    private Container container;
    private JPanel panel;
    private GridBagLayout layout;
    private JLabel fileInLabel, numTestsLabel, iterationsLabel, learningRateLabel, percentageSplitLabel, fileOutLabel;
    private JTextArea fileIn, numTests,  iterations, learningRate, percentageSplit, fileOut;
    private JButton startML;
    private GridBagConstraints constraints;

    public GUI() {

        super("Logistic Regression Test GUI");

        //EM: actually getting it to work
        container = getContentPane();
        layout = new GridBagLayout();
        container.setLayout(layout);
        constraints = new GridBagConstraints();
        //SOR: altered constraints so components fill space
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        fileInLabel = new JLabel("CSV File Location: ");
        numTestsLabel = new JLabel("Number of Tests: ");
        iterationsLabel = new JLabel("Number of Iterations: ");
        learningRateLabel = new JLabel("Learning Rate: ");
        percentageSplitLabel = new JLabel("Percentage of data for test: ");
        fileOutLabel = new JLabel("Results File Location: ");

        fileIn = new JTextArea("/Users/Emma/Documents/Uni/Final Year/Machine Learning & Data Mining/Assignments/Assignment 3/owls15.csv");
        numTests = new JTextArea("100");
        iterations = new JTextArea("1000");
        learningRate = new JTextArea("0.01");
        percentageSplit = new JTextArea("0.5");
        fileOut = new JTextArea("/Users/Emma/Documents/Uni/Final Year/Machine Learning & Data Mining/Assignments/Assignment 3/owls.txt");

        JButton start = new JButton("Go!");

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
        ButtonHandler handler = new ButtonHandler();
        start.addActionListener(handler);

        setSize(1000, 500);
        setVisible(true);

    }

    private void addComponent(Component component, int row,
                              int column, int width, int height)
    {
        constraints.gridx = column;
        constraints.gridy = row;

        constraints.gridwidth = width;
        constraints.gridheight = height;

        layout.setConstraints(component, constraints);
        container.add(component);
    }

    public static void main(String args[]){
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //SOR: implemented error checking, wrote text box contents to variables
            checkInput();
            fileInStr = fileIn.getText();
            fileOutStr = fileOut.getText();

            //EM
            CSVReader csvReader = new CSVReader(splitDouble, fileInStr);
            LogisticRegression lr = new LogisticRegression(lrDouble, iterInt, csvReader, fileOutStr);

            lr.train();
            lr.test();
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
}
