package de.algebros;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class Level_4 {
    public static void main(String[] args) {
        final String fileName = "level3/level3_7.in";

        final List<String> lines = Common.readFile(fileName);
        final int lineCount = Integer.parseInt(lines.get(0));
        final List<String> coinGround = lines.subList(1, lineCount + 1);
        final List<String> ghostGround = new ArrayList<>(coinGround);


        int coinCounter = 0;

        // Ghost Scope
        final List<Ghost> ghosts = discoverGhosts(lines, lineCount);


        // Player Scope
        final Coordinate playerCoordinate = new Coordinate(lines.get(lineCount + 1));
        final char[] actions = lines.get(lineCount + 3).toCharArray();

        while (coinExists(lines)) {

        }

        for (char action : actions) {
            move(action, playerCoordinate);
            ghosts.forEach(ghost -> ghost.step(ghostGround));
            char elementPlayer = getElement(coinGround, playerCoordinate);
            char elementGhost = getElement(ghostGround, playerCoordinate);
            // DEATH
            if (elementGhost == 'G' || elementPlayer == 'W') {
                System.out.println("Gesammelte Coins: " + coinCounter + " NO");
                return;
            }
            // COIN
            if (elementPlayer == 'C') {
                coinCounter++;
                setElement(coinGround, playerCoordinate, 'X');
            }
            System.out.println("Gesammelte Coins: " + coinCounter + " YES");
        }
    }

    private static boolean coinExists(List<String> lines) {
        return lines.stream().anyMatch(line -> line.contains("C"));
    }

    private static void move(char action, Coordinate coordinate) {
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
    }

    private static List<Ghost> discoverGhosts(List<String> lines, int lineCount) {
        final List<Ghost> ghosts = new ArrayList<>();
        System.out.println("Amount Of Ghosts: " +  lines.get(lineCount + 4));
        int amountOfGhosts = Integer.parseInt(lines.get(lineCount + 4));
        int k = lineCount + 5;
        for (int i = 0; i < amountOfGhosts; i++) {
            final Coordinate coordinate = new Coordinate(lines.get(k));
            k+=2;
            final char[] actions = lines.get(k).toCharArray();
            Ghost ghost = new Ghost(coordinate, actions);
            ghosts.add(ghost);
            k++;
        }
        return ghosts;
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

    private static class Ghost {
        private Coordinate coordinate;
        private Stack<Character> actions;

        public Ghost(Coordinate coordinate, char[] actions) {
            this.coordinate = coordinate;
            this.actions = new Stack<>();
            for (int i = actions.length - 1; i >= 0; i--) {
                this.actions.push(actions[i]);
            }
        }

        public void step(List<String> ghostGround) {
            setElement(ghostGround, this.coordinate, 'O');
            char action = actions.pop();
            move(action, coordinate);
            setElement(ghostGround, this.coordinate, 'G');
        }

        public Coordinate getCoordinate() {
            return coordinate;
        }
    }
}
