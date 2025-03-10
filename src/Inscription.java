import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inscription extends JFrame {
    JPanel panelInscription;
    JLabel lblName,lblSecandeName,lblCNI,lblNationality,lblLanguage,lblGender,lblAge,lblGrade,lblAddress;
    JTextField txtName;
    JTextField txtSecandeName;
    JTextField txtCNI;
    JTextField txtAddress;
    JTextField txtGrade;
    JComboBox comboBoxNationality;
    JCheckBox arabic,french,english;
    ButtonGroup buttonGroup;
    JRadioButton homme,femme;
    SpinnerNumberModel spinnerModel;
    JSpinner spinnerAge;
    JButton buttonAdd,buttonClear,buttonDeleteSelected,buttonDeleteAll,buttonNext;
    DefaultTableModel tableModel;
    JTable tableEtudiants;
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


        //SECANDENAME
        lblSecandeName=new JLabel("SecandeName:");
        txtSecandeName=new JTextField();
        panelInscription.add(lblSecandeName);
        panelInscription.add(txtSecandeName);
        lblSecandeName.setBounds(20,50,100,20);
        txtSecandeName.setBounds(140,50,100,20);

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
        homme=new JRadioButton("Homme");
        femme=new JRadioButton("Femme");
        buttonGroup=new ButtonGroup();
        buttonGroup.add(homme);
        buttonGroup.add(femme);
        panelInscription.add(lblGender);
        panelInscription.add(homme);
        panelInscription.add(femme);
        lblGender.setBounds(20,220,90,20);
        homme.setBounds(140,220,100,20);
        femme.setBounds(250,220,100,20);

        //AGE
        lblAge=new JLabel("Age:");
        spinnerModel=new SpinnerNumberModel(6,6,21,1);
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
        tableEtudiants=new JTable(tableModel);
        tableScroller=new JScrollPane(tableEtudiants);
        tableModel.addColumn("Gender");
        tableModel.addColumn("Name");
        tableModel.addColumn("Secande_Name");
        tableModel.addColumn("CNI");
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
        String regex_1="[a-zA-Z]+[a-zA-Z]+[a-zA-Z]+";
        Pattern pattern1=Pattern.compile(regex_1);
        String regex_2="[a-zA-Z]+[a-zA-Z]+[0-9]+";
        Pattern pattern2=Pattern.compile(regex_2);

        //ACTIONS
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Matcher matcher_1=pattern1.matcher(txtName.getText());
                Matcher matcher_2=pattern1.matcher(txtSecandeName.getText());
                Matcher matcher_3=pattern2.matcher(txtCNI.getText());
                String name=null;
                String secandeName=null;
                String CNI=null;
                if (matcher_1.matches()){
                    if (matcher_2.matches()){
                        if (matcher_3.matches()){
                            CNI=txtCNI.getText();
                        }else {
                            showMsg("Invalid CNI");
                        }
                        secandeName=txtSecandeName.getText();
                    }else {
                        showMsg("Invalid Secand_Name");}
                    name=txtName.getText();
                }else showMsg("invalid name");
                String gender=homme.isSelected()?"homme":"femme";
                tableModel.addRow(new Object[]{gender,name ,secandeName,CNI});

            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtSecandeName.setText("");
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
                 int [] selectedIndices=tableEtudiants.getSelectedRows();
                 for( int i=selectedIndices.length-1; i>=0; i-- )
                 {
                     tableModel.removeRow( selectedIndices[i] );
                 }
                 }
         });
         buttonDeleteAll.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 tableModel.setRowCount(0);
             }
         });
         buttonNext.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 ShowInformation showInformation=new ShowInformation();
                 showInformation.afficheName.setText(txtName.getText());
                 showInformation.afficheSecandeName.setText(txtSecandeName.getText());
                 showInformation.afficheCNI.setText(txtCNI.getText());
                 showInformation.afficheNationality.setText((String) comboBoxNationality.getSelectedItem());
                 showInformation.afficheAdress.setText(txtAddress.getText());
                 showInformation.afficheGender.setText(homme.isSelected()?"Homme":"Femme");
                 int selectedValue=(int)spinnerAge.getValue();
                 showInformation.afficheAge.setText(String.valueOf(selectedValue));
                 showInformation.afficheGrade.setText(txtGrade.getText());

                 StringBuilder selectedOption=new StringBuilder();
                 if (arabic.isSelected()){
                     selectedOption.append("Arabic, ");
                 }
                 if (french.isSelected()){
                     selectedOption.append("French, ");
                 }
                 if (english.isSelected()){
                     selectedOption.append("English, ");
                 }
                 showInformation.afficheLanguage.setText(selectedOption.toString());
             }
         });

        this.add(panelInscription);
    }

    // MSG FOR VERIFICATION
    public void showMsg(String msg){
        JOptionPane.showMessageDialog(this,msg);
    }

    //READ CSV FILE
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
}