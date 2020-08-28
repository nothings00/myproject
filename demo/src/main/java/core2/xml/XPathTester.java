package core2.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringJoiner;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/8/28 2:02 PM
 * @version 1.0
 */
public class XPathTester {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new XPathFrame();
            frame.setTitle("XPathTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class XPathFrame extends JFrame {
    private DocumentBuilder builder;
    private Document doc;
    private XPath path;
    private JTextField expression;
    private JTextField result;
    private JTextArea docText;
    private JComboBox<String> typeCombo;

    public XPathFrame() throws HeadlessException {
        JMenu menu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> openFile());
        menu.add(openItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        menu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);

        ActionListener listener = e -> evaluate();
        expression = new JTextField(20);
        expression.addActionListener(listener);
        JButton evaluateButton = new JButton("Evaluate");
        evaluateButton.addActionListener(listener);

        typeCombo = new JComboBox<>(new String[]{
           "STRING","NODE","NODESET","NUMBER","BOOLEAN"
        });
        typeCombo.setSelectedItem("STRING");

        JPanel panel = new JPanel();
        panel.add(expression);
        panel.add(typeCombo);
        panel.add(evaluateButton);
        docText = new JTextArea(10,40);
        result = new JTextField();
        result.setBorder(new TitledBorder("Result"));

        add(panel,BorderLayout.NORTH);
        add(new JScrollPane(docText),BorderLayout.CENTER);
        add(result,BorderLayout.SOUTH);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            JOptionPane.showMessageDialog(this,e);
        }

        XPathFactory xPathFactory = XPathFactory.newInstance();
        path = xPathFactory.newXPath();
        pack();
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("xpath"));

        fileChooser.setFileFilter(new FileNameExtensionFilter("XML files","xml"));
        int r = fileChooser.showOpenDialog(this);
        if (r!= JFileChooser.APPROVE_OPTION){
            return;
        }

        File file = fileChooser.getSelectedFile();
        try{
            docText.setText(new String(Files.readAllBytes(file.toPath())));
            doc = builder.parse(file);
        } catch (IOException | SAXException e) {
            JOptionPane.showMessageDialog(this,e);
        }
    }

    public void evaluate(){
        try {
            String typeName = (String) typeCombo.getSelectedItem();
            QName returnType = (QName) XPathConstants.class.getField(typeName).get(null);
            Object evalResult = path.evaluate(expression.getText(),doc,returnType);
            if ("NODESET".equals(typeName)){
                NodeList list = (NodeList) evalResult;
                //Can't use String.join since NodeList isn't Iterable
                StringJoiner joiner = new StringJoiner(",","{","}");
                for (int i = 0; i < list.getLength(); i++) {
                    joiner.add("" + list.item(i));
                }
                result.setText("" + joiner);
            }else {
                result.setText("" + evalResult);
            }
        } catch (IllegalAccessException | NoSuchFieldException | XPathExpressionException e) {
            result.setText(""+e);
        }


    }


}
