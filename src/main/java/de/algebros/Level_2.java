package de.algebros;

import java.util.List;

public class Level_2 {
    public static void main(String[] args) {
        final String fileName = "level2/level2_5.in";

        final List<String> lines = Common.readFile(fileName);
        final int lineCount = Integer.parseInt(lines.get(0));
        final List<String> playGround = lines.subList(1, lineCount + 1);

        Coordinate coordinate = new Coordinate(lines.get(lineCount + 1));



        int coinCounter = 0;
        final char[] actions = lines.get(lines.size() - 1).toCharArray();
        for (char action : actions) {
            switch (action) {
                case 'L':
                    coordinate.setY(coordinate.getY() - 1);
                    break;
                case 'R':
                    coordinate.setY(coordinate.getY() + 1);
                    break;
                case 'D':
                    coordinate.setX(coordinate.getX() + 1);
                    break;
                case 'U':
                    coordinate.setX(coordinate.getX() - 1);
                    break;
            }
            char element = getElement(playGround, coordinate);
            System.out.println("Element: " + element);
            if (element == 'C') {
                coinCounter++;
                setElement(playGround, coordinate, 'X');
            }
        }
        System.out.println("Gesammelte Coins: " + coinCounter);
    }

    private static char getElement(List<String> lines, Coordinate coordinate) {
        return lines.get(coordinate.getX()).charAt(coordinate.getY());
    }

    private static void setElement(List<String> lines, Coordinate coordinate, char value) {
        String line = lines.get(coordinate.getX());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < line.toCharArray().length; i++) {
            if (i == coordinate.getY()) {
                builder.append(value);
            } else {
                builder.append(line.charAt(i));
            }
        }
        lines.set(coordinate.getX(), builder.toString());
    }
}
