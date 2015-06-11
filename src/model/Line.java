package model;

import java.util.LinkedList;

public class Line implements Cloneable {

    private int number;
    private LinkedList<Note> notes;

    public Line() {
        notes = new LinkedList<Note>();
    }

    public Line(int number) {
        this.number = number;

        notes = new LinkedList<Note>();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getFirstTick() {
        return notes.getFirst().getTick();
    }

    public long getLastTick() {
        return notes.getLast().getTick();
    }

    public void addNote(Note n) {
        notes.add(n);
    }

    public LinkedList<Note> getNotes() {
        return notes;
    }

    public int getNumber() {
        return number;
    }

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}
