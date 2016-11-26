package io.planner.grupa1;

import javax.swing.*;

/**
 * Created by Marcin on 09.12.2016.
 */

import java.util.ArrayList;

public class ListRoom extends AbstractListModel {

    ArrayList<Room> rooms = new ArrayList<Room> ();
    JList JListRooms = new JList();

    public ListRoom(){
        JListRooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JListRooms.setModel(this);
    }

    @Override
    public int getSize() {
        return rooms.size();
    }

    @Override
    public Object getElementAt(int index) {
        return (rooms.get(index).getBuilding() + ", numer: " + rooms.get(index).getNumber());
    }

    public void add (Room Sala)
    {
        rooms.add(Sala);
        fireContentsChanged(this,0,getSize());
    }

    public void remove(int index)
    {
        rooms.remove(index);
        fireContentsChanged(this,0,getSize());
    }

    public void update()
    {
        fireContentsChanged(this,0,getSize());
    }
}