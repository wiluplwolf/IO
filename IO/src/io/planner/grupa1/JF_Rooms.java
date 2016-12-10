package io.planner.grupa1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JF_Rooms extends JFrame implements ActionListener {

    private DataForm enterData;
    public JTextField TXTbuilding, TXTnumber;
    public JLabel JLBbuilding, JLBnumber, JLBroomType;
    public JFrame JFrooms;
    public JComboBox<String> CBXroomType;
    public JButton JBTNsave, JBTNexit;


    public JF_Rooms(DataForm enterData) {
        this.enterData = enterData;
        JFrooms = new JFrame("Dodaj prowadzącego");
        JFrooms.setSize(345, 400);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JFrooms.setLocation(dim.width / 2 - JFrooms.getSize().width / 2, dim.height / 2 - JFrooms.getSize().height / 2);
        JFrooms.setVisible(true);
        JFrooms.setLayout(null);
        JFrooms.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JLBbuilding = new JLabel("Budynek: ");
        JLBbuilding.setBounds(25, 25, 80, 20);
        JFrooms.add(JLBbuilding);
        JLBnumber = new JLabel("Numer: ");
        JLBnumber.setBounds(25, 70, 80, 20);
        JFrooms.add(JLBnumber);
        JLBroomType = new JLabel("Typ sali: ");
        JLBroomType.setBounds(25, 115, 80, 20);
        JFrooms.add(JLBroomType);

        JBTNsave = new JButton("Zapisz");
        JBTNsave.setBounds(60, 270, 80, 30);
        JFrooms.add(JBTNsave);
        JBTNsave.addActionListener(this);

        JBTNexit = new JButton("Wyjdź");
        JBTNexit.setBounds(190, 270, 80, 30);
        JFrooms.add(JBTNexit);
        JBTNexit.addActionListener(this);

        TXTbuilding = new JTextField("");
        TXTbuilding.setBounds(125, 25, 180, 20);
        TXTbuilding.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                TXTbuilding.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = TXTbuilding.getText();
                if (text.length() == 0)      // poprawic
                    TXTbuilding.setText("Uzupełnij!");
            }
        });
        JFrooms.add(TXTbuilding);

        TXTnumber = new JTextField("");
        TXTnumber.setBounds(125, 70, 180, 20);
        TXTnumber.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                TXTnumber.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = TXTnumber.getText();
                if (text.length() == 0) {         //poprawic
                    TXTnumber.setText("Uzupełnij!");
                }
            }
        });
        JFrooms.add(TXTnumber);

        CBXroomType = new JComboBox<>();
        for(int i=0; i<GlobalLists.types.size(); i++){
            CBXroomType.addItem(GlobalLists.types.get(i).name);
        }
        CBXroomType.setBounds(125, 115, 180, 20);
        JFrooms.add(CBXroomType);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object Zrodlo = e.getSource();

        if (Zrodlo == JBTNsave) {
            if (!(TXTbuilding.getText().equals("Wprowadź poprawnie dane.") ||
                    TXTnumber.getText().equals("Wprowadź poprawnie dane.")
            )) {

                Room room = new Room();

                room.setBuilding(TXTbuilding.getText());
                room.setNumber(TXTnumber.getText());
                for(int i=0; i<GlobalLists.types.size(); i++){
                    if(GlobalLists.types.get(i).name.equals(CBXroomType.getSelectedItem()))
                        room.setType(GlobalLists.types.get(i).id);
                }
                DatabaseHelper.addRoom(room);
                enterData.listRooms.add(room);
                JFrooms.dispose();
            }
        }

        if (Zrodlo == JBTNexit)
            JFrooms.dispose();
    }
}