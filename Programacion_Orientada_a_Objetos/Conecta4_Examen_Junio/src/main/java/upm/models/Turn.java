package upm.models;

public class Turn {
    private Color color;

    public Turn() {
        this.start();
    }

    public Color getColor() {
        return color;
    }

    public void change() {
        color = (color == Color.RED) ? Color.YELLOW : Color.RED;
    }

    public void start() {
        this.color = Color.RED;
    }
}