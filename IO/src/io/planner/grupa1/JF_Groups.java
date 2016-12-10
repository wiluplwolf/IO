package io.planner.grupa1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

public class JF_Groups extends JFrame implements ActionListener {

    private DataForm dataForm;
    public JTextField TXTyear;
    public JLabel JLBtype, JLByear;
    public JFrame JFgroups;
    public JComboBox<String> CBXspecialization;
    public JButton JBTNsave, JBTNexit;


    public JF_Groups(DataForm dataForm) throws SQLException {
        this.dataForm = dataForm;
        JFgroups = new JFrame("Dodaj grupe");
        JFgroups.setSize(345, 400);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JFgroups.setLocation(dim.width / 2 - JFgroups.getSize().width / 2, dim.height / 2 - JFgroups.getSize().height / 2);
        JFgroups.setVisible(true);
        JFgroups.setLayout(null);
        JFgroups.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLByear = new JLabel("Rok rozpoczecia: ");
        JLByear.setBounds(25, 100, 80, 20);
        JFgroups.add(JLByear);
        JLBtype = new JLabel("Kierunek: ");
        JLBtype.setBounds(25, 70, 80, 20);
        JFgroups.add(JLBtype);

        JBTNsave = new JButton("Zapisz");
        JBTNsave.setBounds(60, 270, 80, 30);
        JFgroups.add(JBTNsave);
        JBTNsave.addActionListener(this);

        JBTNexit = new JButton("Wyjdź");
        JBTNexit.setBounds(190, 270, 80, 30);
        JFgroups.add(JBTNexit);
        JBTNexit.addActionListener(this);

        TXTyear = new JTextField("");
        TXTyear.setBounds(125, 100, 180, 20);
        TXTyear.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                TXTyear.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = TXTyear.getText();
                if (!(Integer.parseInt(text) >= 2009 && Integer.parseInt(text) <= 2018))
                    TXTyear.setText("Rok musi być liczbą z przedziału między 2009 a 2018.");
            }
        });
        JFgroups.add(TXTyear);
        CBXspecialization = new JComboBox<>();
        for(int i=0; i<GlobalLists.specialization.size(); i++){
            CBXspecialization.addItem(GlobalLists.specialization.get(i).name);
        }
        CBXspecialization.setBounds(125, 70, 180, 20);
        JFgroups.add(CBXspecialization);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Source = e.getSource();

        if (Source == JBTNsave) {
                Group group = new Group();
                group.setYear(Integer.parseInt(TXTyear.getText()));
                group.setSpecialization(CBXspecialization.getSelectedItem().toString());
                group.setName();
                DatabaseHelper.addGrupa(group);
                dataForm.listGroup.add(group);
                JFgroups.dispose();
        }

        if (Source == JBTNexit)
            JFgroups.dispose();
    }
}