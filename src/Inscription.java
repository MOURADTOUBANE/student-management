import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inscription extends JFrame {
    JLabel lblName,lblSecondName,lblCNI,lblNationality,lblLanguage,lblGender,lblAge,lblGrade,lblAddress;
    JTextField txtName;
    JTextField txtSecondName;
    JTextField txtCNI;
    JTextField txtAddress;
    JTextField txtGrade;
    JComboBox comboBoxNationality;
    JCheckBox arabic,french,english;
    ButtonGroup buttonGroup;
    JRadioButton male,female;
    SpinnerNumberModel spinnerModel;
    JSpinner spinnerAge;
    JButton buttonAdd,buttonClear,buttonDeleteSelected,buttonDeleteAll,buttonNext,buttonModify,buttonFinishEdit;
    DefaultTableModel tableModel;
    JTable   tableStudents;
    JScrollPane tableScroller;

    public Inscription(){
        CreateAndShowFormule();
    }
    private void CreateAndShowFormule(){
        this.setTitle("Inscription");
        this.setSize(600,500);
        ImageIcon imageIcon=new ImageIcon("src/images/inscription.png");
        Image image=imageIcon.getImage();
        this.setIconImage( image);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        //NAME
        lblName=new JLabel("Name:");
        txtName=new JTextField();
        this.add(lblName);
        this.add(txtName);
        lblName.setBounds(20,80,60,20);
        txtName.setBounds(140,80,100,20);


        //SecondName
        lblSecondName=new JLabel("SecondName:");
        txtSecondName=new JTextField();
        this.add(lblSecondName);
        this.add(txtSecondName);
        lblSecondName.setBounds(20,110,100,20);
        txtSecondName.setBounds(140,110,100,20);

        //CNI
        lblCNI=new JLabel("CNI:");
        txtCNI=new JTextField();
        this.add(lblCNI);
        this.add(txtCNI);
        lblCNI.setBounds(20,140,60,20);
        txtCNI.setBounds(140,140,100,20);

        //NATIONALITY
        String[]nationality={"Select","Albanian",	"Algerian",	"American",	"Brazilian", "British", "Canadian", "Egyptian", "Emirati", "French", "German", "Moroccan"};
        lblNationality=new JLabel("Nationality:");
        comboBoxNationality=new JComboBox<>(nationality);
        this.add(lblNationality);
        this.add(comboBoxNationality);
        lblNationality.setBounds(20,180,100,20);
        comboBoxNationality.setBounds(140,180,100,20);

        //ADDRESS
        lblAddress=new JLabel("Address:");
        txtAddress=new JTextField();
        this.add(lblAddress);
        this.add(txtAddress);
        lblAddress.setBounds(20,220,100,20);
        txtAddress.setBounds(140,220,100,20);

        //LANGUAGE
        lblLanguage=new JLabel("Language:");
        arabic=new JCheckBox("Arabic");
        french=new JCheckBox("French");
        english=new JCheckBox("English");
        this.add(lblLanguage);
        this.add(arabic);
        this.add(french);
        this.add(english);
        lblLanguage.setBounds(20,260,100,20);
        arabic.setBounds(140,260,100,20);
        english.setBounds(250,260,100,20);
        french.setBounds(350,260,90,20);

        //Gender
        lblGender=new JLabel("Gender:");
        male=new JRadioButton("male");
        female=new JRadioButton("female");
        buttonGroup=new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);
        this.add(lblGender);
        this.add(male);
        this.add(female);
        lblGender.setBounds(20,300,90,20);
        male.setBounds(140,300,100,20);
        female.setBounds(250,300,100,20);

        //AGE
        lblAge=new JLabel("Age:");
        spinnerModel=new SpinnerNumberModel(6,6,30,1);
        spinnerAge=new JSpinner(spinnerModel);
        this.add(lblAge);
        this.add(spinnerAge);
        lblAge.setBounds(20,340,90,20);
        spinnerAge.setBounds(140,340,100,20);

        //GRADE
        lblGrade=new JLabel("Grade:");
        txtGrade=new JTextField();
        this.add(lblGrade);
        this.add(txtGrade);
        lblGrade.setBounds(20,380,90,20);
        txtGrade.setBounds(140,380,100,20);

        //BUTTONS
        buttonAdd=new JButton("Add");
        buttonClear=new JButton("Clear");
        buttonFinishEdit=new JButton("Finish Edit");
        this.add(buttonFinishEdit);
        this.add(buttonAdd);
        this.add(buttonClear);
        buttonAdd.setBounds(180,420,80,20);
        buttonClear.setBounds(300,420,80,20);
        buttonFinishEdit.setBounds(50,420,100,20);

        //TABLE
        tableModel=new DefaultTableModel();
        tableStudents=new JTable(tableModel);
        tableScroller=new JScrollPane(tableStudents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableStudents.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableModel.addColumn("Gender");
        tableModel.addColumn("Name");
        tableModel.addColumn("Secande_Name");
        tableModel.addColumn("CNI");
        tableModel.addColumn("Nationality");
        tableModel.addColumn("Age");
        tableModel.addColumn("Grade");
        tableModel.addColumn("Language");
        tableModel.addColumn("Adress");
        tableScroller.setBounds(450, 50, 800, 350);
        this.add(tableScroller);

        //CSV FILE
        readCSVFileStudent("src/file/Student.csv");

        //BUTTONS_TABLE
        buttonDeleteSelected=new JButton("Delete Selected");
        buttonDeleteAll=new JButton("Delete All");
        buttonModify=new JButton("Modify");
        this.add(buttonModify);
        this.add(buttonDeleteSelected);
        this.add(buttonDeleteAll);
        buttonModify.setBounds(670,420,100,20);
        buttonDeleteSelected.setBounds(810,420,150,20);
        buttonDeleteAll.setBounds(990,420,100,20);

        //BUTTON_NEXT
        buttonNext=new JButton("Next");
        buttonNext.setBounds(500,500,100,20);
        buttonNext.setBackground(Color.red);
        buttonNext.setForeground(Color.white);
        this.add(buttonNext);

        //VERIFICATION INPUTS
        String regex_1 = "[a-zA-Z]+(\\s[a-zA-Z]+)*";
        Pattern pattern1=Pattern.compile(regex_1);
        String regex_2="[a-zA-Z]+[a-zA-Z]+[0-9]+";
        Pattern pattern2=Pattern.compile(regex_2);

        //ACTIONS
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Matcher matcher_1 = pattern1.matcher(txtName.getText());
                Matcher matcher_2 = pattern1.matcher(txtSecondName.getText());
                Matcher matcher_3 = pattern2.matcher(txtCNI.getText());

                if (!matcher_1.matches()) {
                    showMsg("Invalid Name");
                    return;
                }
                if (!matcher_2.matches()) {
                    showMsg("Invalid Second Name");
                    return;
                }
                if (!matcher_3.matches()) {
                    showMsg("Invalid CNI");
                    return;
                }

                String name = txtName.getText();
                String secondName = txtSecondName.getText();
                String CNI = txtCNI.getText();
                String nationality=(String) comboBoxNationality.getSelectedItem();
                String adresse=txtAddress.getText();
                String grade=txtGrade.getText();
                int selectedValue=(int)spinnerAge.getValue();
                String gender = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "");

                StringBuilder selectedOption=new StringBuilder();
                if (arabic.isSelected()){
                    selectedOption.append("Arabic ");
                }
                if (french.isSelected()){
                    selectedOption.append("French ");
                }
                if (english.isSelected()){
                    selectedOption.append("English ");
                }

                if (Objects.requireNonNull(nationality).equalsIgnoreCase("select")){
                    showMsg("Please select your nationality");
                    return;
                }

                if (adresse.isEmpty()){
                    showMsg("Please set your adresse");
                    return;
                }

                if (gender.isEmpty()) {
                    showMsg("Please select a gender");
                    return;
                }

                 tableModel.addRow(new Object[]{gender, name, secondName, CNI,nationality,selectedValue,grade,selectedOption.toString(),adresse});
                 saveUpdatedDataToCSV();
                    showMsg("Student added successfully!");

                txtName.setText("");
                txtSecondName.setText("");
                txtCNI.setText("");
                txtAddress.setText("");
                txtGrade.setText("");
                comboBoxNationality.setSelectedIndex(0);
                arabic.setSelected(false);
                french.setSelected(false);
                english.setSelected(false);
                buttonGroup.clearSelection();
                int initialValue = 6;
                spinnerAge.setValue( initialValue);

            }
        });
        
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtSecondName.setText("");
                txtCNI.setText("");
                txtAddress.setText("");
                txtGrade.setText("");
                comboBoxNationality.setSelectedIndex(0);
                arabic.setSelected(false);
                french.setSelected(false);
                english.setSelected(false);
                buttonGroup.clearSelection();
                int initialValue = 6;
                spinnerAge.setValue( initialValue);
            }
        });

         buttonDeleteSelected.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int [] selectedIndices=  tableStudents.getSelectedRows();
                 for( int i=selectedIndices.length-1; i>=0; i-- )
                 {
                     tableModel.removeRow( selectedIndices[i] );
                     removeRowCSV();
                     showMsg("Student Deleted Successful!");
                 }
                 }

         });
         buttonDeleteAll.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 tableModel.setRowCount(0);
                 removeRowCSV();
                 showMsg("All Students Removed!");
             }
         });

         buttonNext.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 showDataFromCSV("src/file/Student.csv");
             }
         });

        //get data from table to set in textfield
       buttonModify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                    int row = tableStudents.getSelectedRow();
                    if (row != -1) {

                        String genre = tableModel.getValueAt(row, 0).toString();

                        if (male.getText().equalsIgnoreCase(genre)) {
                            male.setSelected(true);
                        } else {
                            female.setSelected(true);
                        }

                        String name = tableModel.getValueAt(row, 1).toString();
                        txtName.setText(name);

                        String secondName = tableModel.getValueAt(row, 2).toString();
                        txtSecondName.setText(secondName);

                        String CNI = tableModel.getValueAt(row, 3).toString();
                        txtCNI.setText(CNI);

                        String nationality = tableModel.getValueAt(row, 4).toString();
                        comboBoxNationality.setSelectedItem(nationality);

                        Object age = tableModel.getValueAt(row, 5);
                        if (age != null) {
                            try {
                                int ageValue = Integer.parseInt(age.toString());
                                spinnerAge.setValue(ageValue);
                            } catch (NumberFormatException ex) {
                                spinnerAge.setValue(0);
                            }
                        }

                        String grade = tableModel.getValueAt(row, 6).toString();
                        txtGrade.setText(grade);

                        String languageString = tableModel.getValueAt(row, 7).toString();
                        String[] languages = languageString.split(" ");

                        for (String language : languages) {
                            if (language.equalsIgnoreCase("arabic")) {
                                arabic.setSelected(true);
                            } else if (language.equalsIgnoreCase("english")) {
                                english.setSelected(true);
                            } else if (language.equalsIgnoreCase("french")) {
                                french.setSelected(true);
                            }
                        }

                        String adresse = tableModel.getValueAt(row, 8).toString();
                        txtAddress.setText(adresse);

                    }
            }
       });

        buttonFinishEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //modify gender and language and age

                int row = tableStudents.getSelectedRow();
                if (row != -1) {
                String gender = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "");
                tableModel.setValueAt(gender,row,0);

                    StringBuilder selectedOption=new StringBuilder();
                    if (arabic.isSelected()){
                        selectedOption.append("Arabic ");
                    }
                    if (french.isSelected()){
                        selectedOption.append("French ");
                    }
                    if (english.isSelected()){
                        selectedOption.append("English ");
                    }
                    tableModel.setValueAt(selectedOption.toString(),row,7);

                    int selectedValueAge=(int)spinnerAge.getValue();
                    tableModel.setValueAt(selectedValueAge,row,5);

                    String nationality=(String) comboBoxNationality.getSelectedItem();
                    tableModel.setValueAt(nationality,row,4);

                saveUpdatedDataToCSV();
                }

                //clear all inputs after finish edit
                        txtName.setText("");
                        txtSecondName.setText("");
                        txtCNI.setText("");
                        txtAddress.setText("");
                        txtGrade.setText("");
                        comboBoxNationality.setSelectedIndex(0);
                        arabic.setSelected(false);
                        french.setSelected(false);
                        english.setSelected(false);
                        buttonGroup.clearSelection();
                        int initialValue = 6;
                        spinnerAge.setValue(initialValue);

                        tableStudents.clearSelection();
            }
        });

        //modify data of table

        txtName.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }
            public void changedUpdate(DocumentEvent e) {
                updateTable();
            }

            private void updateTable() {
                int row = tableStudents.getSelectedRow();
                if (row != -1 && !txtName.getText().isEmpty()) {
                    tableModel.setValueAt(txtName.getText(), row, 1);
                    saveUpdatedDataToCSV();
                }

            }

        });

        txtSecondName.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }
            public void changedUpdate(DocumentEvent e) {
                updateTable();
            }

            private void updateTable() {
                int row = tableStudents.getSelectedRow();
                if (row != -1 && !txtSecondName.getText().isEmpty()) {
                    tableModel.setValueAt(txtSecondName.getText(), row, 2);
                    saveUpdatedDataToCSV();
                }
            }
        });

        txtCNI.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
             updateCNI();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCNI();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCNI();
            }
            private void updateCNI(){
                int row =tableStudents.getSelectedRow();
                if (row != -1 && !txtCNI.getText().isEmpty()){
                    tableModel.setValueAt(txtCNI.getText(),row,3);
                    saveUpdatedDataToCSV();
                }
            }
        });

        txtGrade.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateGrade();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateGrade();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateGrade();
            }
            private void updateGrade(){
                int row =tableStudents.getSelectedRow();
                if (row != -1 && !txtGrade.getText().isEmpty()){
                    tableModel.setValueAt(txtGrade.getText(),row,6);
                    saveUpdatedDataToCSV();
                }
            }
        });

        txtAddress.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAdresse();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAdresse();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAdresse();
            }
            private void updateAdresse(){
                int row =tableStudents.getSelectedRow();
                if (row != -1 && !txtAddress.getText().isEmpty()){
                    tableModel.setValueAt(txtAddress.getText(),row,8);
                    saveUpdatedDataToCSV();
                }
            }
        });


    }

    public void showMsg(String msg){
        JOptionPane.showMessageDialog(this,msg);
    }

    private void readCSVFileStudent(String file){
        BufferedReader reader=null;
        String line="";
        try {
            reader=new BufferedReader(new FileReader("src/file/Student.csv"));
            String header = reader.readLine();
            if (header != null) {
                String[] columns = header.split(",");
                tableModel.setColumnIdentifiers(columns);
            }
            while ((line = reader.readLine()) != null){
                String[]rowData=line.split(",");
                tableModel.addRow(rowData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeRowCSV(){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter("src/file/Student.csv"))){
            writer.write("Gender,Name,Second Name,CNI,Nationality,Age,Grade,Language,Adresse");
            writer.newLine();
            for (int i = 0; i<tableModel.getRowCount();i++){
                writer.write(
                        tableModel.getValueAt(i, 0) + "," +
                                tableModel.getValueAt(i, 1) + "," +
                                tableModel.getValueAt(i, 2) + "," +
                                tableModel.getValueAt(i, 3) + "," +
                                tableModel.getValueAt(i, 4) + "," +
                                tableModel.getValueAt(i, 5) + "," +
                                tableModel.getValueAt(i, 6) + "," +
                                tableModel.getValueAt(i, 7) + "," +
                                tableModel.getValueAt(i, 8)
                );
                writer.newLine();

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showDataFromCSV(String filePath) {
        ShowInformation showInformation=new ShowInformation();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String lastLine = null;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
            if (lastLine != null) {
                String[] values = lastLine.split(",");
                showInformation.afficheGender.setText(values[0]);
                showInformation.afficheName.setText(values[1]);
                showInformation.afficheSecondName.setText(values[2]);
                showInformation.afficheCNI.setText(values[3]);
                showInformation.afficheNationality.setText(values[4]);
                showInformation.afficheAge.setText(values[5]);
                showInformation.afficheGrade.setText(values[6]);
                showInformation.afficheLanguage.setText(values[7]);
                showInformation.afficheAdress.setText(values[8]);
            } else {
               showMsg("empty");
            }
        } catch (IOException e) {
            e.printStackTrace();
    }
}
    private void saveUpdatedDataToCSV() {
        String file = "src/file/Student.csv";
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