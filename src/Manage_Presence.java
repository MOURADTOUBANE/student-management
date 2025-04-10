import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Manage_Presence extends JFrame {
    JPanel panel_1, panel_2;
    JTabbedPane tabbedPane;
    DefaultTableModel tableModel, tableModel_2;
    JTable tableStudents, tableStudents_2;
    JScrollPane tableScroller, tableScroller_2;
    JLabel lblId, lblName, lblSecondName, lblPresence, lblId_2, lblDay, lblHours;
    JTextField txtId, txtName, txtSecondName, txtPresence, txtId_2, txtDay, txtHours;
    JButton buttonAdd, buttonSearch,buttonModify,buttonFinishEdit;

    public Manage_Presence() {
        managePresence();
    }

    private void managePresence() {
        this.setTitle("Manage Presence");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);

        //Add Panels
        panel_1 = new JPanel(null);
        panel_2 = new JPanel(null);

        //Add Panels To TabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.add("Add Student", panel_1);
        tabbedPane.add("Find Student", panel_2);
        this.add(tabbedPane);

        //Start Panel_1
        //ID
        lblId = new JLabel("ID:");
        txtId = new JTextField();
        lblId.setBounds(80, 130, 30, 20);
        txtId.setBounds(110, 130, 100, 20);
        panel_1.add(lblId);
        panel_1.add(txtId);

        //Name
        lblName = new JLabel("Name:");
        txtName = new JTextField();
        lblName.setBounds(57, 160, 100, 20);
        txtName.setBounds(110, 160, 100, 20);
        panel_1.add(lblName);
        panel_1.add(txtName);

        //SecondName
        lblSecondName = new JLabel("Second Name:");
        txtSecondName = new JTextField();
        lblSecondName.setBounds(10, 190, 100, 20);
        txtSecondName.setBounds(110, 190, 100, 20);
        panel_1.add(lblSecondName);
        panel_1.add(txtSecondName);

        //Presence
        lblPresence = new JLabel("Presence:");
        txtPresence = new JTextField();
        lblPresence.setBounds(35, 220, 100, 20);
        txtPresence.setBounds(110, 220, 100, 20);
        panel_1.add(lblPresence);
        panel_1.add(txtPresence);

        //Day
        lblDay = new JLabel("Day:");
        txtDay = new JTextField();
        lblDay.setBounds(40, 250, 100, 20);
        txtDay.setBounds(110, 250, 100, 20);
        panel_1.add(lblDay);
        panel_1.add(txtDay);

        //Hours
        lblHours = new JLabel("Hours:");
        txtHours = new JTextField();
        lblHours.setBounds(40, 280, 100, 20);
        txtHours.setBounds(110, 280, 100, 20);
        panel_1.add(lblHours);
        panel_1.add(txtHours);

        //Button
        buttonAdd = new JButton("Add");
        buttonAdd.setBounds(110, 320, 100, 20);
        panel_1.add(buttonAdd);

        //Table
        tableModel = new DefaultTableModel();
        tableStudents = new JTable(tableModel);
        tableScroller = new JScrollPane(tableStudents);
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("SecondName");
        tableModel.addColumn("Monday");
        tableModel.addColumn("Tuesday");
        tableModel.addColumn("Wednesday");
        tableModel.addColumn("Tursday");
        tableModel.addColumn("Friday");
        tableModel.addColumn("Saturday");
        tableModel.addColumn("Hours Of Absence");
        tableScroller.setBounds(250, 50, 1000, 400);
        panel_1.add(tableScroller);

        //Button_Modify
        buttonModify=new JButton("Modify");
        buttonModify.setBounds(650,470,100,20);
        panel_1.add(buttonModify);

        //Buton_Finish_Edit
        buttonFinishEdit=new JButton("finish edit");
        buttonFinishEdit.setBounds(800,470,100,20);
        panel_1.add(buttonFinishEdit);

        readCSVFileStudent("src/file/Presence.csv");
        //End Panel_1

        //Start Panel_2
        //Id
        lblId_2 = new JLabel("ID:");
        txtId_2 = new JTextField();
        lblId_2.setBounds(40, 130, 30, 20);
        txtId_2.setBounds(70, 130, 100, 20);
        panel_2.add(lblId_2);
        panel_2.add(txtId_2);

        //Button Find
        buttonSearch = new JButton("Search");
        buttonSearch.setBounds(70, 180, 100, 20);
        panel_2.add(buttonSearch);

        //Table
        tableModel_2 = new DefaultTableModel();
        tableStudents_2 = new JTable(tableModel_2);
        tableScroller_2 = new JScrollPane(tableStudents_2);
        tableModel_2.addColumn("ID");
        tableModel_2.addColumn("Name");
        tableModel_2.addColumn("SecondName");
        tableModel_2.addColumn("Monday");
        tableModel_2.addColumn("Tuesday");
        tableModel_2.addColumn("Wednesday");
        tableModel_2.addColumn("Tursday");
        tableModel_2.addColumn("Friday");
        tableModel_2.addColumn("Saturday");
        tableModel_2.addColumn("Hours Of Absence");

        tableScroller_2.setBounds(250, 50, 1000, 400);
        panel_2.add(tableScroller_2);

        readCSVFileStudent2("src/file/Presence.csv");


        //End Panel_2

        //Actions
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText();
                String name = txtName.getText();
                String secondName = txtSecondName.getText();
                String presence = txtPresence.getText();
                String day = txtDay.getText();
                String hours = txtHours.getText();

                if (tableModel.getRowCount() == 0) {
                    Object[] newrow = new Object[tableModel.getColumnCount()];
                    newrow[0] = id;
                    newrow[1] = name;
                    newrow[2] = secondName;

                    for (int i = 3; i < tableModel.getColumnCount(); i++) {
                        if (tableModel.getColumnName(i).equalsIgnoreCase(day)) {
                            newrow[i] = presence;
                        }
                    }
                    newrow[tableModel.getColumnCount() - 1] = hours;
                    tableModel.addRow(newrow);
                } else {

                    // check id if already exist
                    for (int row = 0; row < tableModel.getRowCount(); row++) {
                        if (tableModel.getValueAt(row, 0).equals(id)) {
                            for (int col = 3; col < tableModel.getColumnCount(); col++) {
                                if (tableModel.getColumnName(col).equalsIgnoreCase(day)) {
                                    tableModel.setValueAt(presence, row, col);
                                }
                            }
                            tableModel.setValueAt(hours, row, tableModel.getColumnCount() - 1);

                            JOptionPane.showMessageDialog(null,"This id already exist");
                            return;
                        }
                    }

                    Object[] newrow = new Object[tableModel.getColumnCount()];
                    newrow[0] = id;
                    newrow[1] = name;
                    newrow[2] = secondName;

                    for (int i = 3; i < tableModel.getColumnCount(); i++) {
                        if (tableModel.getColumnName(i).equalsIgnoreCase(day)) {
                            newrow[i] = presence;
                        }
                    }
                    newrow[tableModel.getColumnCount() - 1] = hours;
                    tableModel.addRow(newrow);

                    saveUpdatedDataToCSV();

                    txtId.setText("");
                    txtName.setText("");
                    txtSecondName.setText("");
                    txtPresence.setText("");
                    txtDay.setText("");
                    txtHours.setText("");
                }

            }
        });

        //get data from table to txtfield
        buttonModify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableStudents.getSelectedRow();
                if (row != -1) {
                    Object id=tableModel.getValueAt(row, 0).toString();
                    txtId.setText(id.toString());

                    String name=tableModel.getValueAt(row, 1).toString();
                    txtName.setText(name);

                    String secondName=tableModel.getValueAt(row, 2).toString();
                    txtSecondName.setText(secondName);

                    txtPresence.setText("");
                    txtDay.setText("");
                    txtHours.setText("");

                    txtDay.requestFocus();

                }
            }
        });

        buttonFinishEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtName.setText("");
                txtSecondName.setText("");
                txtPresence.setText("");
                txtDay.setText("");
                txtHours.setText("");
                tableStudents.clearSelection();
            }
        });

        //update data

        txtHours.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateHours();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateHours();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateHours();
            }

            private void updateHours() {
                int row = tableStudents.getSelectedRow();
                if (row != -1 && !txtHours.getText().isEmpty()) {
                    tableModel.setValueAt(txtHours.getText(), row, tableModel.getColumnCount() - 1);
                    saveUpdatedDataToCSV();
                }
            }
        });

        txtPresence.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePresence();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePresence();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

               updatePresence();
            }
            private void updatePresence() {
                int row = tableStudents.getSelectedRow();
                String day=txtDay.getText();
                if (row != -1 && !day.isEmpty() && !txtPresence.getText().isEmpty()) {
                    for (int i = 3; i < tableModel.getColumnCount(); i++) {
                        if (tableModel.getColumnName(i).equalsIgnoreCase(day)) {
                            tableModel.setValueAt(txtPresence.getText(), row, i);

                            saveUpdatedDataToCSV();
                            break;
                        }

                    }

                }


            }

        });

        //Find id
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=txtId_2.getText();
                for (int row = tableModel_2.getRowCount() - 1; row >= 0; row--) {
                    Object value = tableModel_2.getValueAt(row, 0);
                    if (value != null && !value.equals(id)) {
                        tableModel_2.removeRow(row);
                    }
                }
            }
        });

    }

    //read csv file for panel_1
    private void readCSVFileStudent(String file) {
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader("src/file/Presence.csv"));
            String header = reader.readLine();
            if (header != null) {
                String[] columns = header.split(",");
                tableModel.setColumnIdentifiers(columns);
            }

            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");

                for (int i = 0; i < rowData.length; i++) {
                    if ("null".equalsIgnoreCase(rowData[i].trim())) {
                        rowData[i] = "";
                    }
                    rowData[i] = rowData[i].replace("[", "").replace("]", "");
                }

                tableModel.addRow(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //read csv file for panel_2
    private void readCSVFileStudent2(String CSVFile) {
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader("src/file/Presence.csv"));
            String header = reader.readLine();
            if (header != null) {
                String[] columns = header.split(",");
                tableModel_2.setColumnIdentifiers(columns);
            }
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");

                for (int i = 0; i < rowData.length; i++) {
                    if ("null".equalsIgnoreCase(rowData[i].trim())) {
                        rowData[i] = "";
                    }
                    rowData[i] = rowData[i].replace("[", "").replace("]", "");
                }

                tableModel_2.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        private void saveUpdatedDataToCSV() {
            String file = "src/file/Presence.csv";
            try (FileWriter fw = new FileWriter(file, false)) {
                // Write header
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    fw.append(tableModel.getColumnName(i));
                    if (i < tableModel.getColumnCount() - 1) {
                        fw.append(",");
                    }
                }
                fw.append("\n");

                // Write data
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        if (tableModel.getValueAt(i, j) != null) {
                            fw.append(tableModel.getValueAt(i, j).toString());
                        }
                        if (j < tableModel.getColumnCount() - 1) {
                            fw.append(",");
                        }
                    }
                    fw.append("\n");
                }
                fw.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

}


