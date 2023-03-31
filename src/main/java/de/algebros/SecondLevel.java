package de.algebros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondLevel {
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

        final List<String> lines = Common.readFile("solution.in");
        int rounds = Integer.parseInt(lines.get(0).split(" ")[0]);

        final List<List<Fighter>> tournaments = lines.subList(1, lines.size())
                .stream()
                .map(tournament -> tournament.chars().mapToObj(fighterId -> (char) fighterId).collect(Collectors.toList()))
                .map(fighterIds -> fighterIds.stream().map(fighterId -> Fighter.findFighterById(fighterId.toString())).collect(Collectors.toList()))
                .collect(Collectors.toList());

        for (List<Fighter> tournament : tournaments) {
            final List<Fighter> winners = runTournament(tournament, rounds, 0);
            final List<String> winnerIds = winners.stream().map(Fighter::getId).collect(Collectors.toList());
            System.out.println(String.join("", winnerIds));
        }
    }

    public static List<Fighter> runTournament(List<Fighter> fighters, int rounds, int round) {
        if (round >= rounds) {
            return fighters;
        }
        if (round == 5) {
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
}
