package io.planner.grupa1;

import javax.swing.*;
import java.util.ArrayList;

public class ListClasses extends AbstractListModel {

    ArrayList<Classes> classes = new ArrayList<Classes> ();
    JList JListClasses = new JList();

    public ListClasses() { // konstuktor
        JListClasses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JListClasses.setModel(this);
    }

    @Override
    public int getSize() {
        return classes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return (classes.get(index).getSubject().name + ", grupa: " + classes.get(index).getGroup().name);
    }

    public void add (Classes aClasses)
    {
        classes.add(aClasses);
        fireContentsChanged(this,0,getSize());
    }

    public void remove(int index)
    {
        classes.remove(index);
        fireContentsChanged(this,0,getSize());
    }

    public void update()
    {
        fireContentsChanged(this,0,getSize());
    }
}