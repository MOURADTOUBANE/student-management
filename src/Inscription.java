import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inscription extends JFrame {
    JPanel panelInscription;
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
    JButton buttonAdd,buttonClear,buttonDeleteSelected,buttonDeleteAll,buttonNext;
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

        //Panel
        panelInscription=new JPanel(null);
        panelInscription.setBorder(BorderFactory.createTitledBorder("Inscription"));
        panelInscription.setBounds(50,50,450,400);

        //NAME
        lblName=new JLabel("Name:");
        txtName=new JTextField();
        panelInscription.add(lblName);
        panelInscription.add(txtName);
        lblName.setBounds(20,20,60,20);
        txtName.setBounds(140,20,100,20);


        //SecondName
        lblSecondName=new JLabel("SecondName:");
        txtSecondName=new JTextField();
        panelInscription.add(lblSecondName);
        panelInscription.add(txtSecondName);
        lblSecondName.setBounds(20,50,100,20);
        txtSecondName.setBounds(140,50,100,20);

        //CNI
        lblCNI=new JLabel("CNI:");
        txtCNI=new JTextField();
        panelInscription.add(lblCNI);
        panelInscription.add(txtCNI);
        lblCNI.setBounds(20,80,60,20);
        txtCNI.setBounds(140,80,100,20);

        //NATIONALITY
        String[]nationality={"Select","Albanian",	"Algerian",	"American",	"Brazilian", "British", "Canadian", "Egyptian", "Emirati", "French", "German", "Moroccan"};
        lblNationality=new JLabel("Nationality:");
        comboBoxNationality=new JComboBox<>(nationality);
        panelInscription.add(lblNationality);
        panelInscription.add(comboBoxNationality);
        lblNationality.setBounds(20,110,100,20);
        comboBoxNationality.setBounds(140,110,100,20);

        //ADDRESS
        lblAddress=new JLabel("Address:");
        txtAddress=new JTextField();
        panelInscription.add(lblAddress);
        panelInscription.add(txtAddress);
        lblAddress.setBounds(20,140,100,20);
        txtAddress.setBounds(140,140,100,20);

        //LANGUAGE
        lblLanguage=new JLabel("Language:");
        arabic=new JCheckBox("Arabic");
        french=new JCheckBox("French");
        english=new JCheckBox("English");
        panelInscription.add(lblLanguage);
        panelInscription.add(arabic);
        panelInscription.add(french);
        panelInscription.add(english);
        lblLanguage.setBounds(20,180,100,20);
        arabic.setBounds(140,180,100,20);
        english.setBounds(250,180,100,20);
        french.setBounds(350,180,90,20);

        //Gender
        lblGender=new JLabel("Gender:");
        male=new JRadioButton("male");
        female=new JRadioButton("female");
        buttonGroup=new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);
        panelInscription.add(lblGender);
        panelInscription.add(male);
        panelInscription.add(female);
        lblGender.setBounds(20,220,90,20);
        male.setBounds(140,220,100,20);
        female.setBounds(250,220,100,20);

        //AGE
        lblAge=new JLabel("Age:");
        spinnerModel=new SpinnerNumberModel(6,6,30,1);
        spinnerAge=new JSpinner(spinnerModel);
        panelInscription.add(lblAge);
        panelInscription.add(spinnerAge);
        lblAge.setBounds(20,260,90,20);
        spinnerAge.setBounds(140,260,100,20);

        //GRADE
        lblGrade=new JLabel("Grade:");
        txtGrade=new JTextField();
        panelInscription.add(lblGrade);
        panelInscription.add(txtGrade);
        lblGrade.setBounds(20,300,90,20);
        txtGrade.setBounds(140,300,100,20);

        //BUTTONS
        buttonAdd=new JButton("Add");
        buttonClear=new JButton("Clear");
        panelInscription.add(buttonAdd);
        panelInscription.add(buttonClear);
        buttonAdd.setBounds(80,340,80,20);
        buttonClear.setBounds(200,340,80,20);

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



        tableScroller.setBounds(600, 50, 600, 350);
        this.add(tableScroller);

        //CSV FILE
        readCSVFileStudent("src/file/Student.csv");

        //BUTTONS_TABLE
        buttonDeleteSelected=new JButton("Delete Selected");
        buttonDeleteAll=new JButton("Delete All");
        this.add(buttonDeleteSelected);
        this.add(buttonDeleteAll);
        buttonDeleteSelected.setBounds(770,420,150,20);
        buttonDeleteAll.setBounds(960,420,100,20);

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

                    writeToCSVFileStudent(gender ,name, secondName, CNI,nationality, String.valueOf(selectedValue),grade,selectedOption.toString(),adresse);
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
                final Object initialValue = spinnerAge.getValue();
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
                final Object initialSpinnerValue = spinnerAge.getValue();
                spinnerAge.setValue( initialSpinnerValue);
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

                 updateLabelFromCSV("src/file/Student.csv");
             }
         });

        this.add(panelInscription);
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
    private void writeToCSVFileStudent(String gender,String name,String secondName,String cni,String nationality,String age,String grade,String language,String adresse) {
        String file="src/file/Student.csv";
        try(FileWriter out=new FileWriter(file,true)) {
            out.append(gender).append(",")
                    .append(name).append(",")
                    .append(secondName).append(",")
                    .append(cni).append(",")
                    .append(nationality).append(",")
                    .append(age).append(",")
                    .append(grade).append(",")
                    .append(language).append(",")
                    .append(adresse).append("\n");
        }catch (IOException e){
            e.printStackTrace();
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


    private void updateLabelFromCSV(String filePath) {
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
}