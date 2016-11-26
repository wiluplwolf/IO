package io.planner.grupa1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton BTNEnterData;
    private JButton BTNEnterPreferences;
    private JButton BTNGenerate;
    private JButton BTNShow;
    private JButton BTNDocuments;
    private JPanel menuPanel;
    private JLabel JLab1;

    public Menu() {
        BTNEnterData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataForm enterData = new DataForm();
                enterData.pack();
            }
        });
        BTNEnterPreferences.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Kreator wprowadzania preferencji");
            }
        });
        BTNGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Kreator generowania planu");
            }
        });
        BTNShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Podgląd planu");
            }
        });
        BTNDocuments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Kreator generowania dokumentów");
            }
        });
    }

    public static void main(String[] args) {
        DatabaseHelper.connect();
        GlobalLists.fillTypes();
        DatabaseHelper.getAllSubjects();
        DatabaseHelper.getAllSpecializations();
        DatabaseHelper.getAllDegrees();
        DatabaseHelper.closeConnection();

        DatabaseHelper.getAllProfessors();
        DatabaseHelper.getAllGroups();
        DatabaseHelper.getAllRooms();
        DatabaseHelper.getAllClasses();

        JFrame jframe = new JFrame ("Aplikacja");
        jframe.setContentPane(new Menu().menuPanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setVisible(true);
    }
}
