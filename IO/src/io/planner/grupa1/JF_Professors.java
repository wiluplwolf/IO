package io.planner.grupa1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JF_Professors extends JFrame implements ActionListener {

    private DataForm enterData;
    public JTextField TXTname, TXTsurname, TXTemail;
    public JLabel JLBname, JLBsurname, JLBemail, JLBdegree;
    public JFrame JFprofessor;
    public JComboBox<String> CBXdegree;
    public JButton JBTNsave, JBTNexit;

    public JF_Professors(DataForm enterData) {
        this.enterData = enterData;
        JFprofessor = new JFrame("Dodaj prowadzącego");
        JFprofessor.setSize(345, 400);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JFprofessor.setLocation(dim.width / 2 - JFprofessor.getSize().width / 2, dim.height / 2 - JFprofessor.getSize().height / 2);
        JFprofessor.setVisible(true);
        JFprofessor.setLayout(null);
        JFprofessor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLBname = new JLabel("Imię: ");
        JLBname.setBounds(25, 25, 80, 20);
        JFprofessor.add(JLBname);
        JLBsurname = new JLabel("Nazwisko: ");
        JLBsurname.setBounds(25, 70, 80, 20);
        JFprofessor.add(JLBsurname);
        JLBemail = new JLabel("Email: ");
        JLBemail.setBounds(25, 115, 80, 20);
        JFprofessor.add(JLBemail);
        JLBdegree = new JLabel("Tytuł: ");
        JLBdegree.setBounds(25, 205, 80, 20);
        JFprofessor.add(JLBdegree);

        JBTNsave = new JButton("Zapisz");
        JBTNsave.setBounds(60, 270, 80, 30);
        JFprofessor.add(JBTNsave);
        JBTNsave.addActionListener(this);

        JBTNexit = new JButton("Wyjdź");
        JBTNexit.setBounds(190, 270, 80, 30);
        JFprofessor.add(JBTNexit);
        JBTNexit.addActionListener(this);

        TXTname = new JTextField("");
        TXTname.setBounds(125, 25, 180, 20);
        TXTname.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                TXTname.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = TXTname.getText();
                if (!text.matches("[A-Z][a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]*"))
                    TXTname.setText("Wprowadź poprawnie dane.");
            }
        });
        JFprofessor.add(TXTname);

        TXTsurname = new JTextField("");
        TXTsurname.setBounds(125, 70, 180, 20);
        TXTsurname.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                TXTsurname.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = TXTsurname.getText();
                if (!text.matches("[A-Z][a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]*")) {
                    TXTsurname.setText("Wprowadź poprawnie dane.");
                }
            }
        });
        JFprofessor.add(TXTsurname);

        TXTemail = new JTextField("");
        TXTemail.setBounds(125, 115, 180, 20);
        TXTemail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                TXTemail.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = TXTemail.getText();
                if (!text.matches(".+@{1}\\w+\\.{1}.+")) {
                    TXTemail.setText("Wprowadź poprawnie e-mail.");
                }
            }
        });
        JFprofessor.add(TXTemail);

        CBXdegree = new JComboBox<>();
        for(int i=0; i<GlobalLists.degrees.size(); i++){
            CBXdegree.addItem(GlobalLists.degrees.get(i).name);
        }
        CBXdegree.setBounds(125, 205, 180, 20);
        JFprofessor.add(CBXdegree);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == JBTNsave) {
            if (!(TXTname.getText().equals("Wprowadź poprawnie dane.") ||
                    TXTsurname.getText().equals("Wprowadź poprawnie dane.") ||
                    TXTemail.getText().equals("Wprowadź poprawnie e-mail.")
            )) {

                Professor professor = new Professor();
                professor.setName(TXTname.getText());
                professor.setSurname(TXTsurname.getText());
                professor.setMail(TXTemail.getText());
                professor.setDegree(CBXdegree.getSelectedItem().toString());

                enterData.listProfessor.add(professor);
                try {
                    DatabaseHelper.addProfessor(professor);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                JFprofessor.dispose();
            }
        }

        if (source == JBTNexit)
            JFprofessor.dispose();
    }
}