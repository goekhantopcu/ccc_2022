package de.algebros;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.HashMap;

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

    private static List<Coordinate> findNeighboors(List<String> lines, Coordinate coordinate) {
        final List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(coordinate.getX() + 1, coordinate.getY()));
        coordinates.add(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
        coordinates.add(new Coordinate(coordinate.getX(), coordinate.getY() + 1));
        coordinates.add(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
        return coordinates.stream()
                .filter(c -> getElement(lines, c) != 'G' && getElement(lines, c) != 'W')
                .collect(Collectors.toList());
    }

    private static String findPath(List<String> lines, int lineCount, Coordinate start, Coordinate ende) {


        final Coordinate rootStart = new Coordinate(0, 0);
        final Coordinate rootEnde = new Coordinate(lines.size(), lines.get(0).length());

        boolean[][] checked = new boolean[lineCount][lineCount];
        HashMap<Coordinate, BetterCoordinate> bcMap = new HashMap<>();

        boolean[][] walls = new boolean[lineCount][lineCount];

        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.toCharArray().length; x++) {
                if (line.charAt(x) == 'G' || line.charAt(x) == 'W') {
                    walls[x][y] = false;
                } else {
                    walls[x][y] = true;
                }
            }
        }

        while(!bcMap.containsKey(ende))
        {
        }



        StringBuilder builder = new StringBuilder();

        return builder.toString();
    }

    private static List<Coordinate> coinCoordinates(List<String> lines) {
        final List<Coordinate> coordinates = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            String current = lines.get(y);
            for (int x = 0; x < current.length(); x++) {
                if (current.charAt(x) == 'C') {
                    coordinates.add(new Coordinate(x, y));
                }
            }
        }
        return coordinates;
    }

    private static List<Ghost> discoverGhosts(List<String> lines, int lineCount) {
        final List<Ghost> ghosts = new ArrayList<>();
        System.out.println("Amount Of Ghosts: " + lines.get(lineCount + 4));
        int amountOfGhosts = Integer.parseInt(lines.get(lineCount + 4));
        int k = lineCount + 5;
        for (int i = 0; i < amountOfGhosts; i++) {
            final Coordinate coordinate = new Coordinate(lines.get(k));
            k += 2;
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

    class pair {
        int Item1, Item2;

        pair(int f, int s) {
            Item1 = f;
            Item2 = s;
        }
    }

    private static class BetterCoordinate {
        private double futureCost;
        private double previousCost;
        private double overallCost;

        public BetterCoordinate(Coordinate start, Coordinate destination, Coordinate current) {
            this.previousCost = current.distance(start);
            this.futureCost = current.distance(destination);
            this.overallCost = this.previousCost + this.futureCost;
        }
    }
}
