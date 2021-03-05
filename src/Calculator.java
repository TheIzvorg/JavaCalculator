import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Calculator {
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private JButton btn_0;
    private JButton btn_1;
    private JButton btn_2;
    private JButton btn_3;
    private JButton btn_4;
    private JButton btn_5;
    private JButton btn_6;
    private JButton btn_7;
    private JButton btn_8;
    private JButton btn_9;
    private JButton btn_sin;
    private JButton btn_cos;
    private JButton btn_AC;
    private JButton btn_C;
    private JButton btn_plus;
    private JButton btn_minus;
    private JButton btn_multiply;
    private JButton btn_divide;
    private JButton btn_equals;
    private JButton btn_dot;
    private JButton btn_LBrace;
    private JButton btn_RBrace;

    //private double[] Numbers = new double[];
    private ArrayList<String> Numbers = new ArrayList<String>();

    private Calculator(){
        frame = new JFrame("Калькулятор");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(280,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLocationRelativeTo(null); // Будет появляться в центре экрана
        frame.setResizable(false); // Не растягивать окно

        frame.add(panel = new JPanel());

        //переносим кнопки

        String[] btn_str = {
                btn_sin.getText(),btn_cos.getText(),"","",
                btn_AC.getText(),btn_C.getText(),btn_divide.getText(),btn_multiply.getText(),
                btn_1.getText(),btn_2.getText(),btn_3.getText(),btn_minus.getText(),
                btn_4.getText(),btn_5.getText(),btn_6.getText(),btn_plus.getText(),
                btn_7.getText(),btn_8.getText(),btn_9.getText(),btn_equals.getText(),
                btn_dot.getText(),btn_0.getText(),btn_LBrace.getText(),btn_RBrace.getText()
        };

        JButton[] Buttons = new JButton[btn_str.length];

        for(int i = 0, number = 0; i < 6; i++){
            for(int j = 0; j < 4; j++,number++){
                Buttons[number] = new JButton();
                Buttons[number].setText(btn_str[number]);
                Buttons[number].setBackground(Color.GRAY);
                Buttons[number].setForeground(Color.WHITE);
                Buttons[number].setFont(new Font("Comic Sans",Font.BOLD,15));
                Buttons[number].setMargin(new Insets(0,0,0,5));
                Buttons[number].setBounds(15+j*60,75+i*60,50,50);
                Buttons[number].setFocusable(true);
                frame.add(Buttons[number]);

                ActionListener btn_act = new btn_action();
                Buttons[number].addActionListener(btn_act);
            }

        }

        textField = new JTextField();
        textField.setFont(new Font("Comic Sans",Font.BOLD,15));
        textField.setBorder(null);
        textField.setBackground(Color.GRAY);
        textField.setForeground(Color.WHITE);
        textField.setBounds(15,15,230,50);
        textField.setMargin(new Insets(0,10,0,0));
        frame.add(textField);

        class KeyDispetcher implements KeyEventDispatcher {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    result();
                }
                return false;
            }
        }

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyDispetcher());
    }




    public class btn_action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton) e.getSource()).getText();

            if(name.equals("AC") || name.equals("=") || name.equals("Cos") || name.equals("Sin")) {}
            else {textField.setText(textField.getText() + name);}

            if(name.equals("C")) {
                String str = textField.getText();
                textField.setText(null);
                str = str.substring(0,str.length()-2);
                textField.setText(str);
            }
            if(name == "AC") {textField.setText("");}
            if(name == "=") {
                result();
            }
            if(name == "Sin") {
                // TODO: Функционал синуса
                textField.setText(textField.getText() + "Sin");
            }
            if(name == "Cos") {
                // TODO: Функционал косинуса
                textField.setText(textField.getText() + "Cos");
            }
        }
    }

    private void result()
    {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        try
        {
            textField.setText("" + engine.eval(textField.getText()));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
