package scans.model;

import android.graphics.Rect;

public class DrawRectangles {
    private int id;
    private Rect rect;
    private String text;
    private boolean visited;
    public DrawRectangles(Rect rect, String text) {
        this.rect = rect;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isVisited() {
        return !visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
