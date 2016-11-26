package io.planner.grupa1;

import javax.swing.*;

/**
 * Created by Marcin on 09.12.2016.
 */

import java.util.ArrayList;

public class ListGroup extends AbstractListModel {

    ArrayList<Group> groups = new ArrayList<Group> ();
    JList JListGroups = new JList();

    public ListGroup() { // konstuktor
        JListGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JListGroups.setModel(this);
    }

    @Override
    public int getSize() {
        return groups.size();
    }

    @Override
    public Object getElementAt(int index) {
        return (groups.get(index).getName() + ", kierunek: " + GlobalLists.getSpecializationName(groups.get(index).getSpecialization()));
    }

    public void add (Group grupa)
    {
        groups.add(grupa);
        fireContentsChanged(this,0,getSize());
    }

    public void remove(int index)
    {
        groups.remove(index);
        fireContentsChanged(this,0,getSize());
    }

    public void update()
    {
        fireContentsChanged(this,0,getSize());
    }
}