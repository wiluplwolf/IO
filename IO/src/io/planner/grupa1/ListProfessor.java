package io.planner.grupa1;

import javax.swing.*;

/**
 * Created by Marcin on 09.12.2016.
 */

import java.util.ArrayList;

public class ListProfessor extends AbstractListModel {

    ArrayList<Professor> professors = new ArrayList<Professor> ();
    JList<Professor> JListProfessors = new JList<>();

    public ListProfessor() {
        JListProfessors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JListProfessors.setModel(this);
    }

    @Override
    public int getSize() {
        return professors.size();
    }

    @Override
    public Object getElementAt(int index) {
        return (GlobalLists.getDegreeName(professors.get(index).getDegree()) + " " + professors.get(index).getName() + " " + professors.get(index).getSurname());
    }

    public void add (Professor Professor)
    {
        professors.add(Professor);
        fireContentsChanged(this,0,getSize());
    }

    public void remove(int index)
    {
        professors.remove(index);
        fireContentsChanged(this,0,getSize());
    }

    public void update()
    {
        fireContentsChanged(this,0,getSize());
    }
}
