import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    JFrame frame;
    Frame PLAY;
    JComboBox comboBox;
    JButton easy, medium, hard;
    JLabel label,label1;

    public Menu() {
        frame = new JFrame("MENU");
        label = new JLabel("CHOOSE DIFFICULTY");
        label.setFont(new Font("Serif", Font.BOLD, 25));
//        label1 = new JLabel("CHOOSE DIFFICULTY");
        label.setBounds(100,5,300,200);
//        label.setSize(300, 200);

        JButton play = new JButton("PLAY");
        play.setBounds(400, 150, 200, 100);

//        String difficulties[] = {"EASY - > 42 blank cells","MEDIUM - > 51 blank cells","HARD - > 58 blank cells"};
        String difficulties[] = {"EASY","MEDIUM","HARD"};
        comboBox = new JComboBox(difficulties);
        comboBox.setBounds(100, 150, 250, 100);
        comboBox.setFont(new Font("Serif", Font.BOLD, 25));

        frame.add(comboBox);
        frame.add(label);
        frame.add(play);
        frame.setLayout(null);
        frame.setSize(800, 800);
        frame.setVisible(true);
        play.addActionListener(this);

        /*frame = new JFrame("MENU");frame.setFont(new Font("Serif", Font.BOLD, 40));
        label = new JLabel("CHOOSE DIFFICULTY: \nEasy -> 42 blan cells\nMedium -> 51 blan cells\nHard -> 58 blank cells");
        label.setVisible(true);

        easy = new JButton("EASY");easy.setFont(new Font("Serif", Font.BOLD, 40));easy.setVisible(true);
        medium = new JButton("MEDIUM");medium.setFont(new Font("Serif", Font.BOLD, 40));easy.setVisible(true);
        hard = new JButton("HARD");hard.setFont(new Font("Serif", Font.BOLD, 40));easy.setVisible(true);

        frame.add(label);frame.add(easy);frame.add(medium);frame.add(hard);
        frame.setLayout(new GridLayout(4,1));
        frame.setSize(new Dimension(400,600));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        frame.dispose();
        new Frame(String.valueOf(comboBox.getItemAt(comboBox.getSelectedIndex())));
    }
}
