package core2.international;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

/**
 * This program demonstrates formatting numbers under various locales.
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/21 3:48 PM
 * @version 1.0
 */
public class NumberFormatTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new NumberFormatFrame();
            frame.setTitle("NumberFormatTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

    }
}

class NumberFormatFrame extends JFrame{
    private Locale[] locales;
    private double currentNumber;
    private JComboBox<String> localeCombo = new JComboBox<>();
    private JButton parseButton = new JButton("Parse");
    private JTextField numberText = new JTextField(30);
    private JRadioButton numberRBt = new JRadioButton("Number");
    private JRadioButton currencyRBt = new JRadioButton("Currency");
    private JRadioButton percentRBt = new JRadioButton("Percent");
    private ButtonGroup rbGroup = new ButtonGroup();
    private NumberFormat currentNumberFormat;

    public NumberFormatFrame() throws HeadlessException {
        setLayout( new GridBagLayout());

        ActionListener listener = e -> updateDisplay();
        JPanel p = new JPanel();
        addRadioButton(p,numberRBt,rbGroup,listener);
        addRadioButton(p,currencyRBt,rbGroup,listener);
        addRadioButton(p,percentRBt,rbGroup,listener);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Locale:"),gbc);

        gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(p,gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(2,0,0,0);
        add(parseButton,gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(localeCombo,gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(numberText,gbc);

        locales = NumberFormat.getAvailableLocales().clone();
        Arrays.sort(locales,Comparator.comparing(Locale::getDisplayName));
        for (Locale loc : locales){
            localeCombo.addItem(loc.getDisplayName());
        }
        localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
        currentNumber = 123456.78;
        updateDisplay();

        localeCombo.addActionListener(listener);

        parseButton.addActionListener(e -> {
            String s = numberText.getText().trim();
            try {
                Number n = currentNumberFormat.parse(s);
                if (n != null){
                    currentNumber = n.doubleValue();
                    updateDisplay();
                }else {
                    numberText.setText("Parse error: "+s);
                }
            } catch (ParseException parseException) {
                numberText.setText("Parse error: "+ s);
            }
        });
        pack();
    }

    public void addRadioButton(Container p, JRadioButton b, ButtonGroup g, ActionListener listener){
        b.setSelected(g.getButtonCount() == 0);
        b.addActionListener(listener);
        g.add(b);
        p.add(b);
    }

    /**
     * Updates the display and formats the number according to the user settings.
     */
    public void updateDisplay(){
        Locale currentLocale = locales[localeCombo.getSelectedIndex()];
        currentNumberFormat = null;
        if (numberRBt.isSelected()){
            currentNumberFormat = NumberFormat.getNumberInstance(currentLocale);
        }else if (currencyRBt.isSelected()){
            currentNumberFormat = NumberFormat.getCurrencyInstance(currentLocale);
        }else if (percentRBt.isSelected()){
            currentNumberFormat = NumberFormat.getPercentInstance(currentLocale);
        }
        String formatted = currentNumberFormat.format(currentNumber);
        numberText.setText(formatted);
    }
}
