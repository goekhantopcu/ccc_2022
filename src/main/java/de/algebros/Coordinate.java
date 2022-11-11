package de.algebros;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(String input) {
        final String[] coordinates = input.split(" ");
        this.x = Integer.parseInt(coordinates[0]) - 1;
        this.y = Integer.parseInt(coordinates[1]) - 1;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance(Coordinate coordinate) {
        return Math.sqrt(Math.pow(this.x - coordinate.x, 2) + Math.pow(this.y - coordinate.y, 2));
    }
}
