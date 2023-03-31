package de.algebros;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FourthLevel {
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

        final List<String> lines = Common.readFile("level4/level4_1.in");

        int rounds = Integer.parseInt(lines.get(0).split(" ")[0]);

        final List<Map<String, Integer>> fights = lines.subList(1, lines.size())
                .stream()
                .map(FourthLevel::findAmounts)
                .collect(Collectors.toList());

        fights.forEach(fight -> {

        });
        
    }

    public static Map<String, Integer> findAmounts(String fight) {
        final Map<String, Integer> fighterAmounts = new HashMap<>();
        final String[] fighters = fight.split(" ");
        fighterAmounts.put("R", Integer.parseInt(fighters[0].replace("R", "")));
        fighterAmounts.put("P", Integer.parseInt(fighters[1].replace("P", "")));
        fighterAmounts.put("S", Integer.parseInt(fighters[2].replace("S", "")));
        return fighterAmounts;
    }
}
