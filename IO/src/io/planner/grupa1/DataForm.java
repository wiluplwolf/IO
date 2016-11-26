package io.planner.grupa1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DataForm extends JFrame implements ActionListener {

    public JMenuBar JMBAREnterData;
    public JMenu JMShowData;
    public JButton JBdelete;
    public JButton JBadd;
    public JFrame JFEnterData;
    public JMenuItem JMIShowProfessors;
    public JMenuItem JMIShowGroups;
    public JMenuItem JMIShowRooms;
    public JMenuItem JMIShowClasses;
    public JMenuItem JMIExit;

    // klasy z Arraylistami zawierającymi dane i metody pozwalające na dodawanie/wyświetlanie/zmienianie itd.
    ListProfessor listProfessor = new ListProfessor();
    ListRoom listRooms = new ListRoom();
    ListClasses listClasses = new ListClasses();
    ListGroup listGroup = new ListGroup();

    // JSplitPane, służy do podziału między wyświetlaną listę (lewy komponent) a daną jednostką na niej zaznaczoną (wyświetlana na prawym komponencie)
    JSplitPane JSpane = new JSplitPane();

    // JPanele
    JPanel JPProfessors = new JPanel();
    JPanel JPRooms = new JPanel();
    JPanel JPGroups = new JPanel();
    JPanel JPClasses = new JPanel();

    // JLabele z danymi
    JLabel JLBBuilding = new JLabel();
    JLabel JLBNumber = new JLabel();
    JLabel JLBRoomType = new JLabel();

    JLabel JLBname = new JLabel();
    JLabel JLBGroupType = new JLabel();
    JLabel JLBGroup = new JLabel();
    JLabel JLBProfessor = new JLabel();

    JLabel JLBSubject = new JLabel();
    JLabel JLBBeginningTime = new JLabel();

    JLabel JLBFirstName = new JLabel();
    JLabel JLBSurname = new JLabel();
    JLabel JLBTitle = new JLabel();
    JLabel JLBEmail = new JLabel();
    JLabel JLBindeks = new JLabel();

    JLabel JLBProfessorTitle = new JLabel();
    JLabel JLBRoomTitle = new JLabel();
    JLabel JLBClassTitle = new JLabel();
    JLabel JLBGroupsTitle = new JLabel();

    char index;

    public DataForm() {
        JFEnterData = new JFrame("Wprowadź dane");
        JFEnterData.setVisible(true);
        JFEnterData.setSize(600, 430);
        JFEnterData.setLayout(null);
        JFEnterData.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // główne menu
        JMBAREnterData = new JMenuBar();
        JFEnterData.setJMenuBar(JMBAREnterData);

        // kategorie menu
        JMShowData = new JMenu("Zmień listę");
        JMBAREnterData.add(JMShowData);

        // obiekty podkategorii
        JMIShowProfessors = new JMenuItem("Prowadzący");
        JMIShowGroups = new JMenuItem("Group");
        JMIShowRooms = new JMenuItem("Room");
        JMIShowClasses = new JMenuItem("Zajęcia");
        JMIExit = new JMenuItem ("Wyjście");

        Image icon_professor = new ImageIcon (this.getClass().getResource("icons/ico_professor.png")).getImage();
        JMIShowProfessors.setIcon(new ImageIcon(icon_professor));
        Image icon_room = new ImageIcon (this.getClass().getResource("icons/ico_room.png")).getImage();
        JMIShowRooms.setIcon(new ImageIcon(icon_room));
        Image icon_group = new ImageIcon (this.getClass().getResource("icons/ico_group.png")).getImage();
        JMIShowGroups.setIcon(new ImageIcon(icon_group));
        Image icon_class = new ImageIcon (this.getClass().getResource("icons/ico_class.png")).getImage();
        JMIShowClasses.setIcon(new ImageIcon(icon_class));

        JMShowData.add(JMIShowProfessors);
        JMShowData.add(JMIShowGroups);
        JMShowData.add(JMIShowRooms);
        JMShowData.add(JMIShowClasses);
        JMShowData.addSeparator();
        JMShowData.add(JMIExit);

        JMIShowProfessors.addActionListener(this);
        JMIShowRooms.addActionListener(this);
        JMIShowGroups.addActionListener(this);
        JMIShowClasses.addActionListener(this);
        JMIExit.addActionListener(this);
        JMIExit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));

        for(int i=0; i<GlobalLists.professors.size(); i++){
            listProfessor.add(GlobalLists.professors.get(i));
        }

        for(int i=0; i<GlobalLists.rooms.size(); i++){
            listRooms.add(GlobalLists.rooms.get(i));
        }

        for(int i=0; i<GlobalLists.groups.size(); i++){
            listGroup.add(GlobalLists.groups.get(i));
        }

        for(int i=0; i<GlobalLists.classes.size(); i++){
            listClasses.add(GlobalLists.classes.get(i));
        }

        JSpane.setVisible(true);
        JSpane.setSize(545,280);
        JSpane.setDividerLocation(255);

        JBadd = new JButton("Dodaj");
        JBadd.setBounds(270, 300, 75, 30);

        JBdelete = new JButton("Usuń");
        JBdelete.setBounds(470, 300, 75, 30);

        JBadd.addActionListener(this);
        JBdelete.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {                                            // Actionlistener
        Object Source = e.getSource();
        JFEnterData.add(JBadd);
        JFEnterData.add(JBdelete);
        JSpane.setSize(545,280);
        JSpane.setDividerLocation(255);

        if (Source == JMIShowProfessors) {
            index = 'P';
            JSpane.setLeftComponent(listProfessor.JListProfessors);
            JPProfessors.add(JLBFirstName);
            JPProfessors.add(JLBSurname);
            JPProfessors.add(JLBEmail);
            JPProfessors.add(JLBTitle);
            JPProfessors.add(JLBindeks);
            JPProfessors.add(JLBProfessorTitle);
            JSpane.setRightComponent(JPProfessors);

            listProfessor.JListProfessors.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    JLBProfessorTitle.setBounds(50, 30, 150, 30);
                    JLBProfessorTitle.setText("Dane prowadzącego: ");
                    JLBProfessorTitle.setForeground(Color.RED);

                    int item = listProfessor.JListProfessors.getSelectedIndex();
                    JLBFirstName.setBounds(50, 70, 150, 30);
                    JLBFirstName.setText("Imię: " + listProfessor.professors.get(item).getName());
                    JLBSurname.setBounds(50, 100, 150, 30);
                    JLBSurname.setText("Nazwisko: " + listProfessor.professors.get(item).getSurname());
                    JLBEmail.setBounds(50,130,250,30);
                    JLBEmail.setText("Adres mailowy: " + listProfessor.professors.get(item).getMail());
                    JLBTitle.setBounds(50,160,150,30);
                    JLBTitle.setText("Tytuł: " + GlobalLists.getDegreeName(listProfessor.professors.get(item).getDegree()));

                }
            });
            JFEnterData.add(JSpane);
            JFEnterData.revalidate();
            JFEnterData.repaint();
        }

        if (Source == JMIShowRooms) {
            index = 'S';
            JSpane.setLeftComponent(listRooms.JListRooms);
            JPRooms.add(JLBBuilding);
            JPRooms.add(JLBNumber);
            JPRooms.add(JLBRoomType);
            JPRooms.add(JLBRoomTitle);
            JSpane.setRightComponent(JPRooms);

            listRooms.JListRooms.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int item = listRooms.JListRooms.getSelectedIndex();

                    JLBRoomTitle.setBounds(50, 30, 150, 30);
                    JLBRoomTitle.setText("Informacje nt. sali: ");
                    JLBRoomTitle.setForeground(Color.RED);
                    JLBBuilding.setBounds(50,70,250,30);
                    JLBBuilding.setText("Budynek: " + listRooms.rooms.get(item).getBuilding());
                    JLBNumber.setBounds(50,100,250,30);
                    JLBNumber.setText("Numer Sali: " + listRooms.rooms.get(item).getNumber());
                    JLBRoomType.setBounds(50,130,250,30);
                    JLBRoomType.setText("Typ Sali: " + listRooms.rooms.get(item).getType());
                }
            });

            JFEnterData.add(JSpane);
            JFEnterData.revalidate();
            JFEnterData.repaint();
        }

        if (Source == JMIShowGroups) {
            index = 'G';
            JSpane.setLeftComponent(listGroup.JListGroups);
            JPGroups.add(JLBname);
            JPGroups.add(JLBGroupType);
            JPGroups.add(JLBGroupsTitle);
            JSpane.setRightComponent(JPGroups);

            listGroup.JListGroups.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int item = listGroup.JListGroups.getSelectedIndex();

                    JLBGroupsTitle.setBounds(50, 30, 150, 30);
                    JLBGroupsTitle.setText("Informacje nt. grupy: ");
                    JLBGroupsTitle.setForeground(Color.RED);
                    JLBname.setBounds(50,100,250,30);
                    JLBname.setText("Nazwa grupy: " + listGroup.groups.get(item).getName());
                }
            });

            JFEnterData.add(JSpane);
            JFEnterData.revalidate();
            JFEnterData.repaint();
        }

        if (Source == JMIShowClasses) {
            index = 'Z';
            JSpane.setLeftComponent(new JScrollPane(listClasses.JListClasses));
            JPClasses.add(JLBSubject);
            JPClasses.add(JLBBeginningTime);
            JPClasses.add(JLBClassTitle);
            JPClasses.add(JLBGroup);
            JPClasses.add(JLBProfessor);
            JSpane.setRightComponent(JPClasses);

            listClasses.JListClasses.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int item = listClasses.JListClasses.getSelectedIndex();

                    JLBClassTitle.setBounds(50, 30, 150, 30);
                    JLBClassTitle.setText("Informacje nt. zajęć: ");
                    JLBClassTitle.setForeground(Color.RED);

                    JLBSubject.setBounds(50,70,250,30);
                    JLBSubject.setText("Subject: " + listClasses.classes.get(item).getSubject().name);
                    JLBBeginningTime.setBounds(50,100,250,30);
                    JLBBeginningTime.setText("Ilość godzin: " + listClasses.classes.get(item).getAmountOfHours());
                    JLBGroup.setBounds(50, 130, 250, 30);
                    JLBGroup.setText("Grupa: " + listClasses.classes.get(item).getGroup().name);
                    JLBProfessor.setBounds(50, 160, 250, 30);
                    JLBProfessor.setText("Prowadzący: " + GlobalLists.getDegreeName(listClasses.classes.get(item).getProfessor().getDegree()) + "" +
                            " " + listClasses.classes.get(item).getProfessor().getName() + " " +
                            "" + listClasses.classes.get(item).getProfessor().getSurname());
                }
            });

            JFEnterData.add(JSpane);
            JFEnterData.revalidate();
            JFEnterData.repaint();
        }

        if (Source == JBadd) {
            if (index == 'P') {
                JF_Professors jfProfessors = new JF_Professors(this);
                jfProfessors.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jfProfessors.pack();
            }

            if(index == 'S') {
                JF_Rooms jfRooms = new JF_Rooms(this);
                jfRooms.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jfRooms.pack();
            }

            if(index == 'G') {
                JF_Groups jfGroups = null;
                try {
                    jfGroups = new JF_Groups(this);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                jfGroups.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jfGroups.pack();
            }

            if(index == 'Z') {
                JF_Classes jfClasses = new JF_Classes(this);
                jfClasses.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jfClasses.pack();
            }
        }

        if (Source == JBdelete) {
            if (index == 'P') {
                try {
                    int whichOneRemove = listProfessor.JListProfessors.getSelectedIndex();
                    int id = listProfessor.professors.get(whichOneRemove).id;
                    listProfessor.remove(whichOneRemove);
                    GlobalLists.professors.remove(whichOneRemove);
                    DatabaseHelper.delete(id, "prowadzacy");
                } catch (IndexOutOfBoundsException w) {
                    JOptionPane.showMessageDialog(new JFrame(), "Nie wybrano elementu z listy!", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            if (index == 'S') {
                try {
                    int whichOneRemove = listRooms.JListRooms.getSelectedIndex();
                    int id = listRooms.rooms.get(whichOneRemove).id;
                    listRooms.remove(whichOneRemove);
                    GlobalLists.rooms.remove(whichOneRemove);
                    DatabaseHelper.delete(id, "sale");
                } catch (IndexOutOfBoundsException w) {
                    JOptionPane.showMessageDialog(new JFrame(), "Nie wybrano elementu z listy!", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            if (index == 'G') {
                try {
                    int whichOneRemove = listGroup.JListGroups.getSelectedIndex();
                    int id = listGroup.groups.get(whichOneRemove).id;
                    listGroup.remove(whichOneRemove);
                    GlobalLists.groups.remove(whichOneRemove);
                    DatabaseHelper.delete(id, "grupy");
                } catch (IndexOutOfBoundsException w) {
                    JOptionPane.showMessageDialog(new JFrame(), "Nie wybrano elementu z listy!", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            if (index == 'Z') {
                try {
                    int whichOneRemove = listClasses.JListClasses.getSelectedIndex();
                    int id = listClasses.classes.get(whichOneRemove).id;
                    listClasses.remove(whichOneRemove);
                    GlobalLists.classes.remove(whichOneRemove);
                    DatabaseHelper.delete(id, "zajecia");
                } catch (IndexOutOfBoundsException w) {
                    JOptionPane.showMessageDialog(new JFrame(), "Nie wybrano elementu z listy!", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }


        if (Source == JMIExit) {
           JFEnterData.dispose();
        }
    }

    public JPanel getEnterData() {
        return JPEnterData;
    }

    public void setEnterData(JPanel JPwprowadzDane) {
        this.JPEnterData = JPwprowadzDane;
    }

    private JPanel JPEnterData;

}
