import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private Container container;
    private JPanel panel;
    private BoxLayout layout;
    private JTextArea fileIn, numTests,  iterations, learningRate, percentageSplit, fileOut;
    private JButton startML;

    public MLGUI() {

        super("Logistic Regression Test GUI");

        //SOR: Initialise components and layout manager
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        fileIn = new JTextArea();
        fileIn.setName("Location of data set (.csv files only): ");
        panel.add(fileIn);

        numTests = new JTextArea();
        numTests.setName("Number of tests: ");
        numTests.setText("10");
        panel.add(numTests);

        iterations = new JTextArea();
        iterations.setName("Number of epochs: ");
        iterations.setText("100");
        panel.add(iterations);

        learningRate = new JTextArea();
        learningRate.setName("Learning rate: ");
        learningRate.setText("0.01");
        panel.add(learningRate);

        percentageSplit = new JTextArea();
        percentageSplit.setName("Desired proportion of data used for training: ");
        percentageSplit.setText("0.33");
        panel.add(percentageSplit);

        fileOut = new JTextArea();
        fileOut.setName("Result file output location: ");
        panel.add(fileOut);

        startML = new JButton("Begin test");
        panel.add(startML);

        //SOR: initialise container
        container = new Container();
        container = getContentPane();
        container.add(panel, BorderLayout.CENTER);
        container.setVisible(true);
        //startML.add

    }

    public static void main(String args[]){
        MLGUI gui = new MLGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
