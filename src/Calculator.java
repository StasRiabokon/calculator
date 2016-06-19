import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by yasta on 19.06.2016.
 */
public class Calculator extends JFrame {

    private JMenuBar menuBar;
    private JMenu file;
    private JMenu edit;
    private JMenu help;
    private JMenuItem close;
    private JMenuItem copy;
    private JMenuItem view;
    private JMenuItem about;

    private JTextField display;

    private JButton clear;
    private JButton equals;
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton zero;
    private JButton decimal;
    private JButton posneg;

    private JButton addit;
    private JButton subtract;
    private JButton multip;
    private JButton divis;

    private double firstNum = 0.0;
    private double secondNum = 0.0;

    private boolean[] operation = new boolean[4];

    public double getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(double secondNum) {
        this.secondNum = secondNum;
    }

    public double getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(double firstNum) {
        this.firstNum = firstNum;
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Could not load System look.");
        }
        new Calculator();
    }

    public Calculator() {
        super("Calculator");
        sendMenuBar();
        sendDisplay();
        sendButtons();
        sendUI(this);
    }

    private void sendMenuBar() {
        menuBar = new JMenuBar();
        file = new JMenu(" File ");
        edit = new JMenu(" Edit ");
        help = new JMenu(" Help ");
        close = new JMenuItem("Close");
        copy = new JMenuItem("Copy");
        view = new JMenuItem("View help");
        about = new JMenuItem("About me...");

        setJMenuBar(menuBar);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = display.getText();
                StringSelection string = new StringSelection(temp);
                Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                system.setContents(string, string);
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Help", "Calculator", JOptionPane.PLAIN_MESSAGE);
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Stas Ryabokon (c) 2016", "Calculator", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        file.add(close);
        edit.add(copy);
        help.add(view);
        help.add(about);
    }

    private void sendDisplay() {
        display = new JTextField("0");
        display.setBounds(10, 10, 280, 40);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 32));
        add(display);

    }

    private void sendButtons() {
        addit = new JButton("+");
        addit.setBounds(226, 256, 65, 55);
        addit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFirstNum(Double.parseDouble(display.getText()));
                operation[3] = true;
                display.setText("0");
            }
        });
        add(addit);

        subtract = new JButton("-");
        subtract.setBounds(226, 194, 65, 55);
        subtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFirstNum(Double.parseDouble(display.getText()));
                operation[2] = true;
                display.setText("0");
            }
        });
        add(subtract);

        multip = new JButton("*");
        multip.setBounds(226, 132, 65, 55);
        multip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFirstNum(Double.parseDouble(display.getText()));
                operation[1] = true;
                display.setText("0");
            }
        });
        add(multip);

        divis = new JButton("/");
        divis.setBounds(226, 70, 65, 55);
        divis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFirstNum(Double.parseDouble(display.getText()));
                operation[0] = true;
                display.setText("0");
            }
        });
        add(divis);


        clear = new JButton("Clear");
        clear.setBounds(154, 318, 137, 55);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("0");
                setFirstNum(0.0);
                for (int i = 0; i < 4; i++) {
                    operation[i] = false;
                }
            }
        });
        add(clear);

        equals = new JButton("=");
        equals.setBounds(10, 318, 137, 55);
        equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (operation[0]) {
                    display.setText(Double.toString(Math.round(getFirstNum() / Double.parseDouble(display.getText()) * 10000000000.0) / 10000000000.0));
                } else if (operation[1]) {
                    display.setText(Double.toString(Math.round(getFirstNum() * Double.parseDouble(display.getText()) * 10000000000.0) / 10000000000.0));
                } else if (operation[2]) {
                    display.setText(Double.toString(getFirstNum() - Double.parseDouble(display.getText())));
                } else if (operation[3]) {
                    display.setText(Double.toString(getFirstNum() + Double.parseDouble(display.getText())));
                }
                if (display.getText().endsWith(".0")) {
                    display.setText(display.getText().replace(".0", ""));
                }
                setFirstNum(0.0);
                for (int i = 0; i < 4; i++) {
                    operation[i] = false;
                }

            }
        });
        add(equals);

        zero = new JButton("0");
        zero.setBounds(10, 256, 65, 55);
        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10
                        || display.getText().equalsIgnoreCase("0")) {
                    return;
                }

                display.setText(display.getText() + "0");
            }
        });
        add(zero);

        decimal = new JButton(".");
        decimal.setBounds(82, 256, 65, 55);
        decimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().contains(".")) {
                    return;
                }
                display.setText(display.getText() + ".");
            }
        });
        add(decimal);

        posneg = new JButton("+/-");
        posneg.setBounds(154, 256, 65, 55);
        posneg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().equalsIgnoreCase("0")) {
                    return;
                }
                display.setText(Double.toString(Double.parseDouble(display.getText()) * (-1)));
                if (display.getText().endsWith(".0")) {
                    display.setText(display.getText().replace(".0", ""));
                }
            }
        });
        add(posneg);

        one = new JButton("1");
        one.setBounds(10, 194, 65, 55);
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("1");
                    return;
                }
                display.setText(display.getText() + "1");
            }
        });
        add(one);

        two = new JButton("2");
        two.setBounds(82, 194, 65, 55);
        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("2");
                    return;
                }
                display.setText(display.getText() + "2");
            }

        });
        add(two);

        three = new JButton("3");
        three.setBounds(154, 194, 65, 55);
        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("3");
                    return;
                }
                display.setText(display.getText() + "3");
            }

        });
        add(three);

        four = new JButton("4");
        four.setBounds(10, 132, 65, 55);
        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("4");
                    return;
                }
                display.setText(display.getText() + "4");
            }
        });
        add(four);

        five = new JButton("5");
        five.setBounds(82, 132, 65, 55);
        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("5");
                    return;
                }
                display.setText(display.getText() + "5");
            }

        });
        add(five);

        six = new JButton("6");
        six.setBounds(154, 132, 65, 55);
        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("6");
                    return;
                }
                display.setText(display.getText() + "6");
            }

        });
        add(six);

        seven = new JButton("7");
        seven.setBounds(10, 70, 65, 55);
        seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("7");
                    return;
                }
                display.setText(display.getText() + "7");
            }
        });
        add(seven);

        eight = new JButton("8");
        eight.setBounds(82, 70, 65, 55);
        eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("8");
                    return;
                }
                display.setText(display.getText() + "8");
            }

        });
        add(eight);

        nine = new JButton("9");
        nine.setBounds(154, 70, 65, 55);
        nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {
                    display.setText("9");
                    return;
                }
                display.setText(display.getText() + "9");
            }

        });
        add(nine);
    }

    private void sendUI(Calculator app) {
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(308, 430);
        app.setResizable(false);
        app.setLayout(null);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }


}
