import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EditModulesFormateures extends JFrame {
    DefaultTableModel tableModel;
    JTable tableModules;
    JScrollPane tableScroller;
    JLabel lblmoduleName,lblformateurName;
    JTextField txtModule,txtFormateur;
    JButton buttonAdd,buttonClear,buttonDelete,buttonDeleteAll,buttonConfirm;
    public EditModulesFormateures(){
        EditListModulesFormateures();

    }
    private void EditListModulesFormateures(){
        this.setTitle("Edit List");
        this.setSize(1000,500);
        ImageIcon imageIcon=new ImageIcon("src/Images/edit.png");
        Image image=imageIcon.getImage();
        this.setIconImage(image);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);

        //MODULE
        lblmoduleName=new JLabel("Module_Name:");
        txtModule=new JTextField();
        lblmoduleName.setBounds(50,80,100,20);
        txtModule.setBounds(170,80,100,20);
        this.add(lblmoduleName);
        this.add(txtModule);

        //FORMATEUR
        lblformateurName=new JLabel("Formateur_Name:");
        txtFormateur=new JTextField();
        lblformateurName.setBounds(50,120,110,20);
        txtFormateur.setBounds(170,120,100,20);
        this.add(lblformateurName);
        this.add(txtFormateur);

        //BUTTONS`
        buttonAdd=new JButton("Add");
        buttonClear=new JButton("Clear");
        buttonAdd.setBounds(70,160,80,20);
        buttonClear.setBounds(170,160,80,20);
        this.add(buttonAdd);
        this.add(buttonClear);

        //TABLE
        tableModel=new DefaultTableModel();
        tableModules=new JTable(tableModel);
        tableScroller=new JScrollPane(tableModules);
        tableModel.addColumn("Module");
        tableModel.addColumn("Formateur");
        tableScroller.setBounds(350,20,600,300);
        this.add(tableScroller);

        readCSVFileModules("src/file/Modules.csv");

        //BUTTONS_TABLE
        buttonDelete=new JButton("Delete Selected");
        buttonDeleteAll=new JButton("Delete All");
        buttonDelete.setBounds(500,330,140,20);
        buttonDeleteAll.setBounds(700,330,100,20);
        this.add(buttonDelete);
        this.add(buttonDeleteAll);

        //BUTTON_CONFIRM
        buttonConfirm=new JButton("Confirm");
        buttonConfirm.setBounds(300,400,100,20);
        buttonConfirm.setBackground(Color.red);
        buttonConfirm.setForeground(Color.white);
        buttonConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(buttonConfirm);

        //Actions
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String module=txtModule.getText();
                String formateur=txtFormateur.getText();
                tableModel.addRow(new Object[]{module,formateur});

                writeToCSVFileModules(module,formateur);
                showMsg("Module added successfully!");
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtModule.setText("");
                txtFormateur.setText("");
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndice=tableModules.getSelectedRows();
                for (int i=selectedIndice.length-1;i>=0;i--){
                    tableModel.removeRow(selectedIndice[i]);
                }
            }
        });
        buttonDeleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
            }
        });
    }
    private void readCSVFileModules(String file){
        BufferedReader reader=null;
        String line="";
        try {
            reader=new BufferedReader(new FileReader("src/file/Modules.csv"));
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
    private void writeToCSVFileModules(String moduleName,String formateur){
        String file="src/file/Modules.csv";
        try (FileWriter out=new FileWriter(file,true)){
            out.append(moduleName).append(",")
                    .append(formateur).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showMsg(String msg){
        JOptionPane.showMessageDialog(this,msg);
    }

}
