package de.algebros;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TournamentTree {
    Fighter[] fighters;

    public TournamentTree(int treeHeight, String o) {
        // generate valid starting configuration for Rock Paper Scissors so that
        // Scissors wins
        // this means after treeHeight -1 rounds there may not be Rock and there has to
        // be Scissors

        int availableRock = getRocks(o);
        int availablePaper = getPapers(o);
        int availableScissors = getScissors(o);

        // after treeHeight-1 rounds rock should not survive.
        fighters = new Fighter[2 << (treeHeight)];
        for (int i = 0; i < fighters.length; i++) {
            if (availableRock > 0) {
                fighters[i] = new Fighter("R");
                availableRock--;
            } else if (availablePaper > 0) {
                fighters[i] = new Fighter("P");
                availablePaper--;
            } else if (availableScissors > 0) {
                fighters[i] = new Fighter("S");
                availableScissors--;
            }
        }

    }

    public String getStartingSetup() {
        try {

            if (fighters == null) {
                return "";
            }
            if (fighters.length == 0) {
                return "";
            }
            return Arrays.stream(fighters).map(Fighter::getId).collect(Collectors.joining(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getRocks(String line) {
        // Format: 8R 5P 19S
        return Integer.parseInt(line.split(" ")[0].split("R")[0]);
    }

    public static int getPapers(String line) {
        // Format: 8R 5P 19S
        return Integer.parseInt(line.split(" ")[1].split("P")[0]);
    }

    public static int getScissors(String line) {
        // Format: 8R 5P 19S
        return Integer.parseInt(line.split(" ")[2].split("S")[0]);
    }
}
