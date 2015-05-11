package model;

import java.util.LinkedList;

public class Line {

    private int number;
    private LinkedList<Note> notes;

    public Line(int number) {
        this.number = number;

        notes = new LinkedList<Note>();
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

    //    notes;
}
