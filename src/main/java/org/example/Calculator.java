package org.example;

import javax.swing.*; // Рамка и все внутри
import java.awt.*; // шрифты
import java.awt.event.*; // ActionListener


public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;

    JButton[] numberButtons;

    JButton[] functionButtons;
    JButton addButton;
    JButton subButton;
    JButton mulButton;
    JButton divButton;
    JButton decButton;
    JButton equButton;
    JButton delButton;
    JButton clrButton;

    JButton posNegButton;

    JPanel mainPanel;

    JPanel historyPanel; // my own

    JTextField historyPanelTitle; //my own

    JTextField[] history;

    int historySize = 6;

    Font myFont = new Font("Ink Free", Font.PLAIN, 30);

    double num1 = .0;
    double num2 = .0;
    double result = .0;
    char operator;

    public Calculator() {
        frame = new JFrame("My calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension frameDimension = new Dimension( 970, 650); // myOwn
        frame.setSize(frameDimension);
        frame.setResizable(false);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 500, 70);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("\u00D7");
        divButton = new JButton("\u00F7");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("\u232B");
        clrButton = new JButton("Clear");
        posNegButton = new JButton("\u00B1");

        functionButtons = new JButton[9];
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = posNegButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        equButton.setBounds(50, 505, 65, 70);
        delButton.setBounds(125,505, 70, 70);
        clrButton.setBounds(205,505, 145, 70);

        mainPanel = new JPanel();
        mainPanel.setBounds(50, 120, 300, 300); // 300 = 4x + 3 * 10
        mainPanel.setLayout(new GridLayout(4,4,10,10));

        mainPanel.add(numberButtons[1]);
        mainPanel.add(numberButtons[2]);
        mainPanel.add(numberButtons[3]);
        mainPanel.add(addButton);
        mainPanel.add(numberButtons[4]);
        mainPanel.add(numberButtons[5]);
        mainPanel.add(numberButtons[6]);
        mainPanel.add(subButton);
        mainPanel.add(numberButtons[7]);
        mainPanel.add(numberButtons[8]);
        mainPanel.add(numberButtons[9]);
        mainPanel.add(mulButton);
        mainPanel.add(decButton);
        mainPanel.add(numberButtons[0]);
        mainPanel.add(posNegButton);
        mainPanel.add(divButton);

        historyPanelTitle = new JTextField();
        historyPanelTitle.setEditable(false);
        historyPanelTitle.setFont(myFont);
        historyPanelTitle.setHorizontalAlignment(JTextField.CENTER);
        historyPanelTitle.setText("History");
        historyPanelTitle.setBounds(600, 25, 300, 70);

        historyPanel = new JPanel();
        historyPanel.setBounds(400, 120, 500, 455);
        historyPanel.setLayout(new GridLayout(historySize, 1, 10, 0));

        history = new JTextField[historySize];
        for (int i = 0; i < historySize; i++) {
            history[i] = new JTextField();
            history[i].setEditable(false);
            history[i].setFont(myFont);
            history[i].setText(String.valueOf(i));
            history[i].setHorizontalAlignment(JTextField.RIGHT);
//            history[i].setBackground(Color.GRAY);
            historyPanel.add(history[i]);
        }

        frame.add(historyPanelTitle);
        frame.add(historyPanel);
        frame.add(mainPanel);
        frame.add(equButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(textField.getText().length() < 24) {
            for (int i = 0; i < 10; i++) {
                if(e.getSource() == numberButtons[i]){
                    textField.setText(textField.getText() + i);
                    break;
                }
            }

            if(e.getSource() == posNegButton){
                String actStr = textField.getText();
                if(actStr.length() == 0 ) {
                    textField.setText("-");
                } else if(actStr.charAt(0) == '-') {
                    textField.setText(actStr.substring(1));
                } else {
                    textField.setText("-" + actStr);
                }
            }

            if(e.getSource() == decButton) {
                if(!textField.getText().contains(".") && !textField.getText().contains("Infinity")) {
                    textField.setText(textField.getText() + ".");
                }
            }
        }

        if(e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if(e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if(e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if(e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if(e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
            }
            if((int)result == result) {
                textField.setText(String.valueOf((int) result));
            } else {
                textField.setText(String.valueOf(result));
            }
            addToHistory();
        }
        if(e.getSource() == clrButton){
            textField.setText("");
        }
        if(e.getSource() == delButton){
            String actStr = textField.getText();
            if(actStr.length() != 0) {
                textField.setText(actStr.substring(0, actStr.length()-1));
            }
        }
    }

    private void addToHistory() {
        for (int i = 0; i < historySize-1; i++) {
            history[historySize - i - 1].setText(history[historySize - i - 2].getText());
        }

        StringBuilder newHistoryPoint = new StringBuilder();

        if((int) num1 == num1) { newHistoryPoint.append((int) num1); }
        else { newHistoryPoint.append(num1); }

        newHistoryPoint.append(" ").append(operator).append(" ");

        if((int) num2 == num2) { newHistoryPoint.append((int) num2); }
        else { newHistoryPoint.append(num2); }

        newHistoryPoint.append(" = ");

        if((int) result == result) { newHistoryPoint.append((int) result); }
        else { newHistoryPoint.append(result); }

        history[0].setText(newHistoryPoint.toString());
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

