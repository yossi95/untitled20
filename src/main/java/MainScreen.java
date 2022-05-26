import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {
    static NewsHandler nh=new NewsHandler();
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 500;
    public static void main(String[] args) {


        JFrame f=new JFrame();

        TextArea te=new TextArea();
        te.setBounds(120,350,250, 100);
        f.add(te);


        nh.setTextAre( te);

        JLabel jl=new JLabel("Insert a word to ignore");
        jl.setBounds(130,100,100, 40);
        f.add(jl);

        JTextField textField =new JTextField();
        textField.setBounds(130,150,100, 30);
        f.add(textField);

        JButton button =new JButton("add");
        button.setBounds(130,200,100, 40);
        f.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nh.addIgnore(textField.getText());
            }
        });

        JComboBox cb=new JComboBox();
        button.setBounds(130,200,100, 40);
        f.add(cb);


        JButton button1 =new JButton("Start scanning");
        button1.setBounds(120,270,120, 50);
        f.add(button1);
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nh.start();
            }
        });
        JComboBox cb1=new JComboBox();
        button1.setBounds(120,260,120, 50);
        f.add(cb1);


        f.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
    }
}