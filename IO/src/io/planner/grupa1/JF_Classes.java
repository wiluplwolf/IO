package io.planner.grupa1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JF_Classes extends JFrame implements ActionListener {

    private DataForm enterData;
    public JTextField TXTamountOfHours;
    public JLabel JLBamountOfHours, JLBsubject, JLBprofessor, JLBroom, JLBgroup;
    public JFrame JFclasses;
    public JComboBox<String> CBXsubject;
    JComboBox<String> CBXprofessor;
    JComboBox<String> CBXroom;
    JComboBox<String> CBXgroup;
    public JButton JBTNsave, JBTNexit;

    public JF_Classes(DataForm enterData) {
        this.enterData = enterData;
        JFclasses = new JFrame("Dodaj zajecia");
        JFclasses.setSize(345, 400);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JFclasses.setLocation(dim.width / 2 - JFclasses.getSize().width / 2, dim.height / 2 - JFclasses.getSize().height / 2);
        JFclasses.setVisible(true);
        JFclasses.setLayout(null);
        JFclasses.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLBamountOfHours = new JLabel("Liczba godzin: ");
        JLBamountOfHours.setBounds(25, 25, 80, 20);
        JFclasses.add(JLBamountOfHours);
        JLBsubject = new JLabel("Subject: ");
        JLBsubject.setBounds(25, 70, 80, 20);
        JFclasses.add(JLBsubject);
        JLBprofessor = new JLabel("Prowadzacy: ");
        JLBprofessor.setBounds(25, 115, 80, 20);
        JFclasses.add(JLBprofessor);
        JLBroom = new JLabel("Sala: ");
        JLBroom.setBounds(25, 160, 80, 20);
        JFclasses.add(JLBroom);
        JLBgroup = new JLabel("Grupa: ");
        JLBgroup.setBounds(25, 205, 80, 20);
        JFclasses.add(JLBgroup);

        JBTNsave = new JButton("Zapisz");
        JBTNsave.setBounds(60, 270, 80, 30);
        JFclasses.add(JBTNsave);
        JBTNsave.addActionListener(this);

        JBTNexit = new JButton("Wyjdź");
        JBTNexit.setBounds(190, 270, 80, 30);
        JFclasses.add(JBTNexit);
        JBTNexit.addActionListener(this);


        TXTamountOfHours = new JTextField("");
        TXTamountOfHours.setBounds(125, 25, 180, 20);
        TXTamountOfHours.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                TXTamountOfHours.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = TXTamountOfHours.getText();
                if (!(Integer.parseInt(text)>0 && Integer.parseInt(text)<100))
                    TXTamountOfHours.setText("Wprowadź poprawnie dane.");
            }
        });
        JFclasses.add(TXTamountOfHours);

        CBXsubject = new JComboBox<>();
        for(int i=0; i<GlobalLists.subjects.size(); i++){
            CBXsubject.addItem(GlobalLists.subjects.get(i).name);
        }
        CBXsubject.setBounds(125, 70, 180, 20);
        JFclasses.add(CBXsubject);

        CBXprofessor = new JComboBox<>();
        for(int i=0; i<GlobalLists.professors.size(); i++){
            CBXprofessor.addItem(GlobalLists.getDegreeName(GlobalLists.professors.get(i).getDegree()) + "" +
                    " " + GlobalLists.professors.get(i).getName() + " " + GlobalLists.professors.get(i).getSurname());
        }
        CBXprofessor.setBounds(125, 115, 180, 20);
        JFclasses.add(CBXprofessor);

        CBXroom = new JComboBox<>();
        for(int i=0; i<GlobalLists.rooms.size(); i++){
            CBXroom.addItem(GlobalLists.rooms.get(i).getBuilding() + "-" + GlobalLists.rooms.get(i).getNumber());
        }
        CBXroom.setBounds(125, 160, 180, 20);
        JFclasses.add(CBXroom);

        CBXgroup = new JComboBox<>();
        for(int i=0; i<GlobalLists.groups.size(); i++){
            CBXgroup.addItem(GlobalLists.groups.get(i).getName());
        }
        CBXgroup.setBounds(125, 205, 180, 20);
        JFclasses.add(CBXgroup);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == JBTNsave) {
            if (!(TXTamountOfHours.getText().equals("Wprowadź poprawnie dane.")
            )) {
                Classes classes = new Classes();
                for(int i=0; i<GlobalLists.subjects.size(); i++){
                    if(GlobalLists.subjects.get(i).name.equals(CBXsubject.getSelectedItem().toString()))
                        classes.setSubject(GlobalLists.subjects.get(i));
                }
                String[] professor = CBXprofessor.getSelectedItem().toString().split("\\s+");

                for(int i=0; i<GlobalLists.professors.size(); i++){
                    if(GlobalLists.getDegreeName(GlobalLists.professors.get(i).getDegree()).equals(professor[0]) && GlobalLists.professors.get(i).getName().equals(professor[1])
                            && GlobalLists.professors.get(i).getSurname().equals(professor[2]))
                    classes.setProfessor(GlobalLists.professors.get(i));
                }
                classes.setAmountOfHours(Integer.parseInt(TXTamountOfHours.getText()));
                for(int i=0; i<GlobalLists.groups.size(); i++){
                    if(GlobalLists.groups.get(i).getName().equals(CBXgroup.getSelectedItem().toString()))
                        classes.setGroup(GlobalLists.groups.get(i));
                }
                String[] room = CBXroom.getSelectedItem().toString().split("-");
                for(int i=0; i<GlobalLists.rooms.size(); i++){
                    if(GlobalLists.rooms.get(i).building.equals(room[0]) && GlobalLists.rooms.get(i).number.equals(room[1]))
                        classes.setRoom(GlobalLists.rooms.get(i));
                }
                DatabaseHelper.addClass(classes);
                enterData.listClasses.add(classes);
                JFclasses.dispose();
                DatabaseHelper.getAllClasses();
            }
        }

        if (source == JBTNexit)
            JFclasses.dispose();
    }
}