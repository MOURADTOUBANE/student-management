import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowInformation extends JFrame{
    JLabel lblName,lblSecandeName,lblCNI,lblNationality,lblLanguage,lblGender,lblAge,lblGrade,lblAddress;
    JLabel afficheName,afficheSecandeName,afficheCNI,afficheNationality,afficheLanguage,afficheGender,afficheGrade,afficheAdress,afficheAge;
    JButton buttonConfirm,buttonCancel;
    public ShowInformation (){
        ShowInfoFormule();
    }

        private void ShowInfoFormule(){
        this.setTitle("Student Information");
        this.setSize(300,400);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);

        //NAME
        lblName=new JLabel("Name:");
        afficheName=new JLabel();
        this.add(lblName);
        this.add(afficheName);
        lblName.setBounds(20,20,60,20);
        afficheName.setBounds(140,20,100,20);

        //SECANDENAME
        lblSecandeName=new JLabel("SecandeName:");
        afficheSecandeName=new JLabel();
        this.add(lblSecandeName);
        this.add(afficheSecandeName);
        lblSecandeName.setBounds(20,50,100,20);
        afficheSecandeName.setBounds(140,50,100,20);

        //CNI
        lblCNI=new JLabel("CNI:");
        afficheCNI=new JLabel();
        this.add(lblCNI);
        this.add(afficheCNI);
        lblCNI.setBounds(20,80,60,20);
        afficheCNI.setBounds(140,80,100,20);

        //NATIONALITY
        lblNationality=new JLabel("Nationality:");
        afficheNationality=new JLabel();
        this.add(lblNationality);
        this.add(afficheNationality);
        lblNationality.setBounds(20,110,100,20);
        afficheNationality.setBounds(140,110,100,20);

        //ADDRESS
        lblAddress=new JLabel("Address:");
        afficheAdress=new JLabel();
        this.add(lblAddress);
        this.add(afficheAdress);
        lblAddress.setBounds(20,140,100,20);
        afficheAdress.setBounds(140,140,100,20);

        //LANGUAGE
        lblLanguage=new JLabel("Language:");
        afficheLanguage=new JLabel();
        this.add(lblLanguage);
        this.add(afficheLanguage);
        lblLanguage.setBounds(20,180,100,20);
        afficheLanguage.setBounds(140,180,100,20);

        //Gender
        lblGender=new JLabel("Gender:");
        afficheGender=new JLabel();
        this.add(lblGender);
        this.add(afficheGender);
        lblGender.setBounds(20,220,90,20);
        afficheGender.setBounds(140,220,100,20);

        //AGE
        lblAge=new JLabel("Age:");
        afficheAge=new JLabel();
        this.add(lblAge);
        this.add(afficheAge);
        lblAge.setBounds(20,260,90,20);
        afficheAge.setBounds(140,260,100,20);

        //GRADE
        lblGrade=new JLabel("Grade:");
        afficheGrade=new JLabel();
        this.add(lblGrade);
        this.add(afficheGrade);
        lblGrade.setBounds(20,300,90,20);
        afficheGrade.setBounds(140,300,100,20);

        //BUTTONS
          buttonConfirm=new JButton("Confirm");
          buttonConfirm.setBounds(140,330,90,20);
          this.add(buttonConfirm);
          buttonCancel=new JButton("Cancel");
          buttonCancel.setBounds(40,330,80,20);
          this.add(buttonCancel);

          //Action
          buttonConfirm.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  new AfficheModules();
              }
          });
          buttonCancel.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  setVisible(false);
              }
          });
    }


}
