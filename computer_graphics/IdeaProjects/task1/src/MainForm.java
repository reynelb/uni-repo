import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPanel panelMain;

    private JButton actionButton;

    private JTextField textField;
    private JButton actionButtonColor;
    private JPanel panelColor;

    public MainForm() {
        this.setTitle("test");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("Hello world!");
            }
        });

        actionButtonColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelColor.setBackground(Color.red);
            }
        });
    }
    }
