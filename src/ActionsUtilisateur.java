import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionsUtilisateur extends JFrame {
    JButton btnInscription,btnModules,btnPresence;
    public ActionsUtilisateur(){
        createAndShowGUI();
    }
    private void createAndShowGUI(){
        this.setTitle("Actions");
        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setVisible(true);

        btnInscription=new JButton("Inscription");
        btnModules=new JButton("Edit Modules");
        btnPresence=new JButton("Presence");

        btnInscription.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnModules.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPresence.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.add(btnInscription);
        this.add(btnModules);
        this.add(btnPresence);

        btnInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Inscription();
            }
        });
        btnModules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditModulesFormateures();
            }
        });
        btnPresence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manage_Presence();
            }
        });

    }
}
