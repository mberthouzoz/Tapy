package model;

import java.awt.*;
import java.util.LinkedList;

public class Line implements Cloneable {

    private int number;
    private LinkedList<Note> notes;
    private Color color;

    public Line() {
        notes = new LinkedList<Note>();
    }

    public Line(int number) {
        this.number = number;

        notes = new LinkedList<Note>();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
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

    public Line clone() {
        Line clone = null;

        try {
            clone = (Line) super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        clone.notes = new LinkedList<Note>();

        return clone;
    }
}
