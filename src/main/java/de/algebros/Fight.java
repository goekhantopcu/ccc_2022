package de.algebros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fight {
    private List<Fighter> fighters = new ArrayList<>();

    public Fight(String fighters) {
        for (char c : fighters.toCharArray()) {
            this.fighters.add(Fighter.findFighterById("" + c));
            System.out.println("Fighters: " + this.fighters.size());
        }
    }

    public Fighter findWinner() {
        for (int i = 0; i < this.fighters.size(); i++) {
            final Fighter fighterA = this.fighters.get(i);
            for (int j = 1; j < this.fighters.size(); j++) {
                final Fighter fighterB = this.fighters.get(j);
                if (fighterA.winsAgains(fighterB)) {
                    return fighterA;
                }
                if (fighterB.winsAgains(fighterA)) {
                    return fighterB;
                }
            }
        }
        return null;
    }

    public static void initFights() {
        final List<String> lines = Common.readFile("level1/level1_1.in");
        final List<Fight> fights = lines.subList(1, lines.size()).stream()
                .map(Fight::new)
                .collect(Collectors.toList());
        fights.forEach(fight -> System.out.println(fight.findWinner().getId()));
    }
}
