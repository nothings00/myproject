package core2.database;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This program uses metadata to display arbitrary tables in a database
 *
 * @author zenghh
 * @version 1.0
 * @email 625111833@qq.com
 * @date 2020/9/15 9:49 AM
 */
public class ViewDB {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new ViewDBFrame();
            frame.setTitle("ViewDB");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}

class ViewDBFrame extends JFrame {
    private JButton previousBt;
    private JButton nextBt;
    private JButton deleteBt;
    private JButton saveBt;
    private DataPanel dataPanel;
    private Component scrollPane;
    private JComboBox<String> tableNames;
    private Properties props;
    private CachedRowSet crs;
    private Connection conn;

    public ViewDBFrame() {
        tableNames = new JComboBox<>();
        try {
            readDatabaseProperties();
            conn = TestDB.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            try(ResultSet mrs = meta.getTables(null,null,null,new String[]{"TABLE"})){
                while (mrs.next()){
                    tableNames.addItem(mrs.getString(3));
                }
            }
        } catch (SQLException throwables) {
            for (Throwable e : throwables) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableNames.addActionListener(e -> {
            showTable((String) tableNames.getSelectedItem(),conn);
        });
        add(tableNames,BorderLayout.NORTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try{
                    if (conn!=null){
                        conn.close();
                    }
                } catch (SQLException throwables) {
                    for (Throwable t : throwables){
                        t.printStackTrace();
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        add(buttonPanel,BorderLayout.SOUTH);

        previousBt = new JButton("Previous");
        previousBt.addActionListener(e -> {
            showPreviousRow();
        });
        buttonPanel.add(previousBt);

        nextBt = new JButton("Next");
        nextBt.addActionListener(e->{
            showNextRow();
        });
        buttonPanel.add(nextBt);

        deleteBt = new JButton("Delete");
        deleteBt.addActionListener(e->{
            deleteRow();
        });
        buttonPanel.add(deleteBt);

        saveBt = new JButton("Save");
        saveBt.addActionListener(e->{
            saveChanges();;
        });
        buttonPanel.add(saveBt);

        if (tableNames.getItemCount() < 0){
            showTable(tableNames.getItemAt(0),conn);
        }

    }

    public void showTable(String tableName,Connection connection){
        try(Statement stat = connection.createStatement();
            ResultSet resultSet = stat.executeQuery("select * from "+ tableName)){
            RowSetFactory factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.setTableName(tableName);
            crs.populate(resultSet);

            if (null!=scrollPane){
                remove(scrollPane);
            }
            dataPanel = new DataPanel(crs);
            scrollPane = new JScrollPane(dataPanel);
            add(scrollPane,BorderLayout.CENTER);
            pack();
            showNextRow();
        } catch (SQLException throwables) {
            for(Throwable t : throwables){
                t.printStackTrace();
            }
        }
    }

    public void showPreviousRow(){
        try{
            if (crs == null || crs.isFirst()){
                return;
            }
            crs.previous();
            dataPanel.showRow(crs);
        } catch (SQLException throwables) {
            for (Throwable t : throwables){
                t.printStackTrace();
            }
        }
    }
    public void showNextRow(){
        try{
            if (crs == null || crs.isLast()){
                return;
            }
            crs.next();
            dataPanel.showRow(crs);
        } catch (SQLException throwables) {
            for (Throwable t : throwables){
                t.printStackTrace();
            }
        }
    }

    public void deleteRow(){
        if (crs == null) {
            return;
        }
        new SwingWorker<Void,Void>(){
            @Override
            protected Void doInBackground() throws Exception {
                crs.deleteRow();
                crs.acceptChanges();
                if (crs.isAfterLast()){
                    if (!crs.last()){
                        crs = null;
                    }
                }
                return  null;
            }

            @Override
            protected void done() {
                dataPanel.showRow(crs);
            }
        }.execute();
    }

    public void saveChanges(){
        if (crs == null) {
            return;
        }

        new SwingWorker<Void,Void>(){
            @Override
            protected Void doInBackground() throws Exception {
                dataPanel.setRow(crs);
                crs.acceptChanges(conn);
                return null;
            }
        }.execute();

    }
    public void readDatabaseProperties() throws IOException {
        props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(ViewDB.class.getResource("/").getPath(), "database.properties"))) {
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
    }

}

class DataPanel extends JPanel {
    private List<JTextField> fields;

    public DataPanel(RowSet rs) throws SQLException {
        fields = new ArrayList<>();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            gbc.gridy = i - 1;
            String columnName = rsmd.getColumnName(i);
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            add(new JLabel(columnName), gbc);

            int columnWidth = rsmd.getColumnDisplaySize(i);
            JTextField tf = new JTextField(columnWidth);
            if (!rsmd.getColumnClassName(i).equals("java.lang.String")) {
                tf.setEditable(false);
            }
            fields.add(tf);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            add(tf, gbc);
        }
    }

    public void showRow(ResultSet rs) {
        try {
            if (rs == null) {
                return;
            }
            for (int i = 1; i <= fields.size(); i++) {
                String field = rs == null ? "" : rs.getString(i);
                JTextField tf = fields.get(i - 1);
                tf.setText(field);
            }
        } catch (SQLException ex) {
            for (Throwable e : ex) {
                e.printStackTrace();
            }
        }
    }

    public void setRow(RowSet rs) throws SQLException {
        for (int i = 1; i <= fields.size(); i++) {
            String field = rs.getString(i);
            JTextField tf = fields.get(i - 1);
            if (!field.equals(tf.getText())) {
                rs.updateString(i, tf.getText());
            }
        }
        rs.updateRow();
    }
}
