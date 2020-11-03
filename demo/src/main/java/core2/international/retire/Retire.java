package core2.international.retire;

import com.fasterxml.jackson.databind.util.ClassUtil;
import core2.international.retire.Retire.GBC;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author zenghh
 * @version 1.0
 * @email 625111833@qq.com
 * @date 2020/9/22 4:37 PM
 */
public class Retire {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new RetireFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
    public static class GBC extends GridBagConstraints{

        public GBC(int gridx, int gridy) {
            this.gridx = gridx;
            this.gridy = gridy;
        }
        public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
            this.gridx = gridx;
            this.gridy = gridy;
            this.gridwidth = gridwidth;
            this.gridheight = gridheight;
        }

        public GBC setAnchor(int position){
            this.anchor = position;
            return this;
        }

        public GBC setFill(int fill){
            this.fill = fill;
            return this;
        }

        public GBC setWeight(double weightX,double weightY){
            this.weightx = weightX;
            this.weighty = weightY;
            return this;
        }
    }
}

class RetireFrame extends JFrame {
    private JTextField savingsField = new JTextField(10);
    private JTextField contribField = new JTextField(10);
    private JTextField incomeField = new JTextField(10);
    private JTextField currentAgeField = new JTextField(4);
    private JTextField retireAgeField = new JTextField(4);
    private JTextField deathAgeField = new JTextField(4);
    private JTextField inflationPercentField = new JTextField(6);
    private JTextField investPercentField = new JTextField(6);
    private JTextArea retireText = new JTextArea(10, 25);
    private RetireComponent retireCanvas = new RetireComponent();
    private JButton computeButton = new JButton();
    private JLabel languageLabel = new JLabel();
    private JLabel savingsLabel = new JLabel();
    private JLabel contribLabel = new JLabel();
    private JLabel incomeLabel = new JLabel();
    private JLabel currentAgeLabel = new JLabel();
    private JLabel retireAgeLabel = new JLabel();
    private JLabel deathAgeLabel = new JLabel();
    private JLabel inflationPercentLabel = new JLabel();
    private JLabel investPercentLabel = new JLabel();
    private RetireInfo info = new RetireInfo();
    private Locale[] locales = {Locale.US,Locale.CHINA,Locale.GERMANY};
    private Locale currentLocale;
    //TODO: java核心代码写错啦
    private JComboBox<Locale> localeCombo = new JComboBox<>(locales);
    private ResourceBundle res;
    private ResourceBundle resStrings;
    private NumberFormat currencyFmt;
    private NumberFormat numberFmt;
    private NumberFormat percentFmt;

    public RetireFrame(){
        setLayout(new GridBagLayout());
        add(languageLabel,new GBC(0,0).setAnchor(GBC.EAST));
        add(savingsLabel,new GBC(0,1).setAnchor(GBC.EAST));
        add(contribLabel,new GBC(2,1).setAnchor(GBC.EAST));
        add(incomeLabel,new GBC(4,1).setAnchor(GBC.EAST));
        add(currentAgeLabel,new GBC(0,2).setAnchor(GBC.EAST));
        add(retireAgeLabel,new GBC(2,2).setAnchor(GBC.EAST));
        add(deathAgeLabel,new GBC(4,2).setAnchor(GBC.EAST));
        add(inflationPercentLabel,new GBC(0,3).setAnchor(GBC.EAST));
        add(investPercentLabel,new GBC(2,3).setAnchor(GBC.EAST));
        add(localeCombo,new GBC(1,0,3,1));
        add(savingsField,new GBC(1,1).setWeight(100,0).setFill(GBC.HORIZONTAL));
        add(contribField,new GBC(3,1).setWeight(100,0).setFill(GBC.HORIZONTAL));
        add(incomeField,new GBC(5,1).setWeight(100,0).setFill(GBC.HORIZONTAL));
        add(currentAgeField,new GBC(1,2).setWeight(100,0).setFill(GBC.HORIZONTAL));
        add(retireAgeField,new GBC(3,2).setWeight(100,0).setFill(GBC.HORIZONTAL));
        add(deathAgeField,new GBC(5,2).setWeight(100,0).setFill(GBC.HORIZONTAL));
        add(inflationPercentField,new GBC(1,3).setWeight(100,0).setFill(GBC.HORIZONTAL));
        add(investPercentField,new GBC(3,3).setWeight(100,0).setFill(GBC.HORIZONTAL));
        add(retireCanvas,new GBC(0,4,4,1).setWeight(100,100).setFill(GBC.BOTH));
        add(new JScrollPane(retireText),new GBC(4,4,2,1).setWeight(0,100).setFill(GBC.BOTH));

        computeButton.setName("computeButton");

        computeButton.addActionListener(e -> {
            getInfo();
            updateData();
            updateGraph();
        });

        add(computeButton,new GBC(5,3));

        retireText.setEditable(false);
        retireText.setFont(new Font("Monospaced",Font.PLAIN,10));

        info.setSavings(0);
        info.setContrib(9000);
        info.setIncome(60000);
        info.setCurrentAge(35);
        info.setRetireAge(65);
        info.setDeathAge(85);
        info.setInvestPercent(0.1);
        info.setInflationPercent(0.05);

        int localeIndex = 0;
        for (int i = 0; i < locales.length; i++) {
            if (getLocale().equals(locales[i])){
                localeIndex  = i;
            }
        }
       setCurrentLocale(locales[localeIndex]);

        localeCombo.addActionListener(e -> {
            setCurrentLocale((Locale) localeCombo.getSelectedItem());
            validate();
        });

        pack();
    }

    public void setCurrentLocale(Locale locale) {
        currentLocale = locale;
        localeCombo.setLocale(currentLocale);
        localeCombo.setSelectedItem(currentLocale);
        res  = ResourceBundle.getBundle(Retire.class.getPackage().getName()+".RetireResources",currentLocale);
        resStrings = ResourceBundle.getBundle("retire/RetireStrings",currentLocale);
        currencyFmt = NumberFormat.getCurrencyInstance(currentLocale);
        numberFmt = NumberFormat.getNumberInstance(currentLocale);
        percentFmt = NumberFormat.getPercentInstance(currentLocale);

        updateDisplay();
        updateInfo();
        updateData();
        updateGraph();
    }

    public void updateDisplay(){
        languageLabel.setText(resStrings.getString("language"));
        savingsLabel.setText(resStrings.getString("savings"));
        contribLabel.setText(resStrings.getString("contrib"));
        incomeLabel.setText(resStrings.getString("income"));
        currentAgeLabel.setText(resStrings.getString("currentAge"));
        retireAgeLabel.setText(resStrings.getString("retireAge"));
        deathAgeLabel.setText(resStrings.getString("deathAge"));
        inflationPercentLabel.setText(resStrings.getString("inflationPercent"));
        investPercentLabel.setText(resStrings.getString("investPercent"));
        computeButton.setText(resStrings.getString("computeButton"));
    }

    public void updateInfo(){
        savingsField.setText(currencyFmt.format(info.getSavings()));
        contribField.setText(currencyFmt.format(info.getContrib()));
        incomeField.setText(currencyFmt.format(info.getIncome()));
        currentAgeField.setText(numberFmt.format(info.getCurrentAge()));
        retireAgeField.setText(numberFmt.format(info.getRetireAge()));
        deathAgeField.setText(numberFmt.format(info.getDeathAge()));
        investPercentField.setText(percentFmt.format(info.getInvestPercent()));
        inflationPercentField.setText(percentFmt.format(info.getInflationPercent()));
    }

    public void updateData(){
        retireText.setText("");
        MessageFormat retireMsg = new MessageFormat("");
        retireMsg.setLocale(currentLocale);
        retireMsg.applyPattern(resStrings.getString("retire"));

        for (int i = info.getCurrentAge(); i <= info.getDeathAge(); i++) {
            Object[] args = {i,info.getBalance(i)};
            retireText.append(retireMsg.format(args) + "\n");
        }
    }


    public void updateGraph(){
        retireCanvas.setColorPre((Color) res.getObject("colorPre"));
        retireCanvas.setColorGain((Color) res.getObject("colorGain"));
        retireCanvas.setColorLoss((Color) res.getObject("colorLoss"));
        retireCanvas.setInfo(info);
        repaint();
    }

    public void getInfo(){
        try{
            info.setSavings(currencyFmt.parse(savingsField.getText()).doubleValue());
            info.setContrib(currencyFmt.parse(contribField.getText()).doubleValue());
            info.setIncome(currencyFmt.parse(incomeField.getText()).doubleValue());
            info.setCurrentAge(numberFmt.parse(currentAgeField.getText()).intValue());
            info.setRetireAge(numberFmt.parse(retireAgeField.getText()).intValue());
            info.setDeathAge(numberFmt.parse(deathAgeField.getText()).intValue());
            info.setInvestPercent(percentFmt.parse(investPercentField.getText()).doubleValue());
            info.setInflationPercent(percentFmt.parse(inflationPercentField.getText()).doubleValue());
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
}


class RetireInfo {
    private double savings;
    private double contrib;
    private double income;

    private int currentAge;
    private int retireAge;
    private int deathAge;

    private double inflationPercent;
    private double investPercent;

    private int age;
    private double balance;

    /**
     * Gets the available balance for a given year;
     * @param year the year for which to compute the balance
     * @return the amount of money available(or required) in that year
     */
    public double getBalance(int year) {
        if (year < currentAge) {
            return 0;
        } else if (year == currentAge) {
            age = year;
            balance = savings;
            return balance;
        } else if (year == age) {
            return balance;
        }

        if (year != age + 1) {
            getBalance(year - 1);
        }
        age = year;
        if (age < retireAge) {
            balance += contrib;
        } else {
            balance -= income;
        }
        balance = balance * (1 + (investPercent - inflationPercent));

        return balance;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getContrib() {
        return contrib;
    }


    public void setContrib(double contrib) {
        this.contrib = contrib;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge = currentAge;
    }

    public int getRetireAge() {
        return retireAge;
    }

    public void setRetireAge(int retireAge) {
        this.retireAge = retireAge;
    }

    public int getDeathAge() {
        return deathAge;
    }

    public void setDeathAge(int deathAge) {
        this.deathAge = deathAge;
    }

    public double getInflationPercent() {
        return inflationPercent;
    }

    public void setInflationPercent(double inflationPercent) {
        this.inflationPercent = inflationPercent;
    }

    public double getInvestPercent() {
        return investPercent;
    }

    public void setInvestPercent(double investPercent) {
        this.investPercent = investPercent;
    }
}

class RetireComponent extends JComponent{
    private static final int PANEL_WIDTH =400;
    private static final int PANEL_HEIGHT =200;
    private static final Dimension PREFERRED_SIZE = new Dimension(800,600);

    private RetireInfo info = null;
    private Color colorPre;
    private Color colorGain;
    private Color colorLoss;

    public RetireComponent(){
        setSize(PANEL_WIDTH,PANEL_HEIGHT);
    }

    public void setInfo(RetireInfo newinfo){
        info = newinfo;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2  = (Graphics2D) g;
        if (info == null){
            return;
        }

        double minValue = 0;
        double maxValue = 0;
        int i;
        for (i = info.getCurrentAge(); i < info.getDeathAge();i++){
            double v = info.getBalance(i);
            if (minValue > v) {
                minValue = v;
            }
            if (maxValue < v) {
                maxValue =v;
            }
        }
        if (maxValue == minValue){
            return;
        }

        int barWidth = getWidth() / (info.getDeathAge() - info.getCurrentAge() +1);
        double scale = getHeight() / (maxValue - minValue);
        for (i = info.getCurrentAge(); i <= info.getDeathAge();i++){
            int x1 = (i- info.getCurrentAge())*barWidth + 1;
            int y1;
            double v = info.getBalance(i);
            int height;
            int yOrigin  = (int) (maxValue * scale);
            if ( v>= 0){
                y1 = (int) ((maxValue-v)*scale);
                height = yOrigin - y1;
            }else {
                y1 = yOrigin;
                height = (int) (-v *scale);
            }

            if ( i < info.getRetireAge()){
                g2.setPaint(colorPre);
            }else if (v >=0){
                g2.setPaint(colorGain);
            }else {
                g2.setPaint(colorLoss);
            }

            Rectangle2D bar = new Rectangle2D.Double(x1,y1,barWidth-2,height);
            g2.fill(bar);
            g2.setPaint(Color.black);
            g2.draw(bar);
        }
    }

    public void setColorPre(Color colorPre) {
        this.colorPre = colorPre;
        repaint();
    }

    public void setColorGain(Color colorGain) {
        this.colorGain = colorGain;
        repaint();
    }

    public void setColorLoss(Color colorLoss) {
        this.colorLoss = colorLoss;
        repaint();
    }

    @Override
    public  Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
}

