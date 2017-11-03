import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class NewForm {

    private JPanel mainPanel;
    private JLabel infoLabel;
    private JButton clickButton;
    private JTextField mainTextField;
    private JProgressBar mainBar;
    private JLabel checkLabel;
    private JProgressBar progressBar1;
    private JButton sortButton;
    private JTextField arrayLength;
    private JLabel timeTrack;
    String text = "";
    Integer val = 0;
    String result = "";


    public void mainTest(int[] list) {
        SortTester[] interArr = new SortTester[] {
                new SortTester(new GnSort(), "gnomeSort"),
                new SortTester(new BubbleSort(), "bubbleSort"),
                new SortTester(new selectionSort(), "selectionSort"),
                new SortTester(new BucketSort(), "bucketSort"),
                //new SortTester(new CocktailSort(), "cocktailSort")
        };

        for (SortTester alg: interArr) {
            int[] li = list.clone();
            System.out.println(alg.name);
            text = alg.name;
            alg.sort(li);
            result = result + alg.name + ": " + String.valueOf(alg.time) + "\n";
            val +=  100/(interArr.length);
            System.out.println("VAL: " + val);
        }
    }

    public NewForm() {


        mainTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    if (Integer.parseInt(mainTextField.getText()) < 10 && Integer.parseInt(mainTextField.getText()) > 0) {
                        checkLabel.setText("OOOKKK");
                    } else {
                        checkLabel.setText("NOOO");

                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    checkLabel.setText("ERROR");

                }


            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                System.out.println("removeUpdate");
            }

            @Override
            public void changedUpdate(DocumentEvent e) { System.out.println("insertUpdate");

            }

        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBar1.setValue(0);
                timeTrack.setText("");
                progressBar1.setMaximum(100);
                val = 0;
                result = "";
                Thread one = new Thread(() -> {
                    try{
                        while (val < 100) {
                            try{
                                if (Integer.parseInt(arrayLength.getText()) > 0) {
                                    Integer length = Integer.parseInt(arrayLength.getText());
                                    timeTrack.setText("Creating Array");
                                    text = "Creating Array";
                                    int[] list = TimeTracker.getData(length);
                                    mainTest(list);
                                } else {
                                    text = "Error";
                                    val = 100;
                                    result = "Error";
                                }
                            } catch (Exception ex) {
                                text = "Error";
                                val = 100;
                                result = "Error";
                            }
                            Thread.sleep(1);
                            JOptionPane.showMessageDialog(null, result, "result", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (InterruptedException v) {
                        System.out.println(v.getMessage());
                    }
                });
                one.start();
                Thread two = new Thread(() -> {
                    try {
                        while (val < 100) {
                            timeTrack.setText(text);
                            progressBar1.setValue(val);
                            Thread.sleep(1);

                        }
                    } catch (InterruptedException v) {
                        System.out.println(v.getMessage());
                    }
                });
                two.start();
            }
        });

        clickButton.addActionListener(new ActionListener() {

            int a  = 1;
            @Override
            public void actionPerformed(ActionEvent e) {

                infoLabel.setText(Integer.toString(a));
                // JOptionPane.showMessageDialog(null, "Hello, World!!", "Click",
                        //JOptionPane.INFORMATION_MESSAGE);
                ++a;
                mainBar.setValue(a);
            }
        });
    }

    public static void main(String[] arg) {

        JFrame frame = new JFrame();
        frame.setContentPane(new NewForm().mainPanel);
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
