package de.algebros;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ThirdLevel {
    public static void main(String[] args) {
        final Fighter rock = new Fighter("R");
        final Fighter paper = new Fighter("P");
        final Fighter scissor = new Fighter("S");

        Fighter.FIGHTERS.add(rock);
        Fighter.FIGHTERS.add(paper);
        Fighter.FIGHTERS.add(scissor);

        rock.getWinsAgainst().add(scissor);
        paper.getWinsAgainst().add(rock);
        scissor.getWinsAgainst().add(paper);

        final List<String> lines = Common.readFile("level3/level3_1.in");
        /*
         * int rounds = Integer.parseInt(lines.get(0).split(" ")[0]);
         * System.out.println("Rocks:" + getRocks(lines.get(1)));
         * System.out.println("Papers:" + getPapers(lines.get(1)));
         * System.out.println("Scissors:" + getScissors(lines.get(1)));
         */

        final List<String> originals = lines.subList(1, lines.size());
        final List<String> rigged = originals.stream().map(normal -> rigLine(normal)).collect(Collectors.toList());
        // rigged.forEach(System.out::println);
        Common.writeFile(rigged, "level4/solution.out");
    }

    private static String rigLine(String normal) {
        // after 2 rounds rock should not survive,
        // we need tu use all rocks and then put papers in with groups of 3 rocks
        // after this is done, we can be sure there are no rocks in the second stage and
        // we fill with remaining paper and scissors

        int availableRock = getRocks(normal);
        int availablePaper = getPapers(normal);
        int availableScissors = getScissors(normal);
        StringBuilder result = new StringBuilder();
        int papercount = 0;
        if (availableRock % 4 == 3 && availableScissors > 1) {
            result.append("R");
            availableRock--;
            result.append("S");
            availableScissors--;
            papercount = 2;
        }
        while (availableRock > 0) {
            papercount++;
            if (papercount == 4) {
                result.append("P");
                availablePaper--;
                papercount = 0;
            } else {

                result.append("R");
                availableRock--;
            }
        }
        while (availablePaper > 0) {
            result.append("P");
            availablePaper--;
        }
        while (availableScissors > 0) {
            result.append("S");
            availableScissors--;
        }
        return result.toString();
    }

    public static List<Fighter> runTournament(List<Fighter> fighters, int rounds, int round) {
        if (round >= rounds) {
            return fighters;
        }
        if (round == 2) {
            return fighters;
        }
        final List<Fight> fights = toPartitions(fighters, 2).stream()
                .map(Fight::new)
                .collect(Collectors.toList());
        final List<Fighter> winners = fights.stream().map(Fight::findWinner).collect(Collectors.toList());
        return runTournament(winners, rounds, round + 1);
    }

    public static <T> List<List<T>> toPartitions(List<T> tournaments, int partitionSize) {
        final List<List<T>> partitions = new LinkedList<>();
        for (int i = 0; i < tournaments.size(); i += partitionSize) {
            partitions.add(tournaments.subList(i, Math.min(i + partitionSize, tournaments.size())));
        }
        return partitions;
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
