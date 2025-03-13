import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AfficheModules extends JFrame {
    DefaultTableModel tableModel;
    JTable tableModulesFormateurs;
    JScrollPane tableScroller;
    JButton buttonConfirm;
    public AfficheModules(){
        CreateTableModulesForamteures();
    }
    private void CreateTableModulesForamteures(){
        this.setTitle("Table Of Modules And Formateurs");
        this.setSize(700,500);
        ImageIcon imageIcon=new ImageIcon("src/images/book.png");
        Image image=imageIcon.getImage();
        this.setIconImage(image);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);

        //TABLE
        tableModel=new DefaultTableModel();
        tableModulesFormateurs=new JTable(tableModel);
        tableScroller=new JScrollPane(tableModulesFormateurs);
        tableModel.addColumn("Modules");
        tableModel.addColumn("Formateurs");
        tableScroller.setBounds(50,50,600,300);
        this.add(tableScroller);

        //CSV FILE
        readCSVFileModules("src/file/Modules.csv");

        //BUTTON CONFIRM
        buttonConfirm=new JButton("Confirm");
        buttonConfirm.setBounds(300,400,90,20);
        this.add(buttonConfirm);

        //ACTIONS
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(buttonConfirm,"student registry success","success",JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }
        });
    }
    private void readCSVFileModules (String file){
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
}
