import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

class App
{
    public static native int calcBill(int [] params);

    static{System.loadLibrary("calc");}

    public static void main(String args[])
    {
        Login mf = new Login();
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
    }
}

class AppFrame extends Frame
{
    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 600;

    private Hashtable<String,Integer> processor;
    private Hashtable<String,Integer> ram;
    private Hashtable<String,Integer> graphicsCard;

    JLabel resultLabel2;

    public AppFrame()
    {
        setTitle("Computer Shopping Portal");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(new GridLayout(5,2));

        processor = new Hashtable<String,Integer>();
        processor.put("i3", 10000);
        processor.put("i5", 15000);
        processor.put("i7", 20000);

        JPanel panel1 = new JPanel();
        JLabel processorLabel = new JLabel("Select Processor :");
        panel1.add(processorLabel);

        JPanel panel2 = new JPanel();
        Set<String> processorKeys = processor.keySet();
        JComboBox processorComboBox = new JComboBox(processorKeys.toArray());
        panel2.add(processorComboBox);

        ram = new Hashtable<String,Integer>();
        ram.put("2 GB", 3000);
        ram.put("4 GB", 5000);
        ram.put("8 GB", 8000);

        JPanel panel3 = new JPanel();
        JLabel ramLabel = new JLabel("Select RAM :");
        panel3.add(ramLabel);

        JPanel panel4 = new JPanel();
        Set<String> ramKeys = ram.keySet();
        JComboBox ramComboBox = new JComboBox(ramKeys.toArray());
        panel4.add(ramComboBox);

        graphicsCard = new Hashtable<String,Integer>();
        graphicsCard.put("2 GB", 3000);
        graphicsCard.put("4 GB", 5000);
        graphicsCard.put("8 GB", 8000);

        JPanel panel5 = new JPanel();
        JLabel graphicsCardLabel = new JLabel("Select Graphics Card :");
        panel5.add(graphicsCardLabel);

        JPanel panel6 = new JPanel();
        Set<String> graphicsCardKeys = graphicsCard.keySet();
        JComboBox graphicsCardComboBox = new JComboBox(graphicsCardKeys.toArray());
        panel4.add(graphicsCardComboBox);

        JPanel panel7 = new JPanel();
        JButton calculateButton = new JButton("Calculate Bill");
        calculateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                int processorPrice = processor.get((String)processorComboBox.getSelectedItem());
                int ramPrice = ram.get((String)ramComboBox.getSelectedItem());
                int graphicsCardPrice = graphicsCard.get((String)graphicsCardComboBox.getSelectedItem());

                int params[] = new int[3];
                params[0] = processorPrice;
                params[1] = ramPrice;
                params[2] = graphicsCardPrice;

                int result = App.calcBill(params);
                resultLabel2.setText("" + result);
            }
        });

        panel7.add(calculateButton);

        JPanel panel8 = new JPanel();

        JPanel panel9 = new JPanel();
        JLabel resultLabel = new JLabel("Your estimated amount :");
        panel9.add(resultLabel);

        JPanel panel10 = new JPanel();
        resultLabel2 = new JLabel("");
        panel10.add(resultLabel2);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        add(panel6);
        add(panel7);
        add(panel8);
        add(panel9);
        add(panel10);
    }
}

class Login extends JFrame implements ActionListener
{
    JLabel l1, l2;
    JTextField tf1;
    JTextField tf2;
    JButton b;
    public static int Cnt = 3;

    public Login()
    {
        super("e-Shopping Portal");
        setContentPane(new JLabel(new ImageIcon("./logo.png")));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Username");
        l1.setBounds(50, 70, 150, 20);

        tf1 = new JTextField();
        tf1.setBounds(50, 100, 200, 20);
        tf1.setToolTipText("Enter your username");

        l2 = new JLabel("Password");
        l2.setBounds(50, 130, 150, 20);

        tf2 = new JTextField();
        tf2.setBounds(50, 160, 200, 20);
        tf2.setToolTipText("Enter your password");

        b = new JButton("Login");
        b.setBounds(90, 200, 80, 30);

        b.addActionListener(this);
        add(l1);
        add(tf1);
        add(l2);
        add(tf2);
        add(b);

        setSize(300,300);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        CheckAuthentication();
    }

    public void CheckAuthentication()
    {
        String username = tf1.getText();
        String password = tf2.getText();

        if((username.equals("anthrax")) && (password.equals("anthrax@123")))
        {
            AppFrame af = new AppFrame();
            af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            af.setVisible(true);
            dispose();
        }
        else
        {
            --Cnt;

            if(Cnt == 0)
            {
                JOptionPane.showMessageDialog(this, "Number of attempts finished");
                dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Authentication Failed");
                tf1.setText("");
                tf2.setText("");
            }
        }
    }
}