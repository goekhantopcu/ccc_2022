package de.algebros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fight {
    private List<Fighter> fighters = new ArrayList<>();

    public Fight(String fighters) {
        int tournamentsize = fighters.length();
        String a = "";
        String b = "";
        boolean large = false;
        if (tournamentsize > 2) {
            a = new Fight(fighters.substring(0, tournamentsize / 2)).findWinner().getId();
            b = new Fight(fighters.substring(tournamentsize / 2)).findWinner().getId();
            large = true;
        }
        if (large) {

            this.fighters.add(Fighter.findFighterById(a));
            this.fighters.add(Fighter.findFighterById(b));
        } else {
            this.fighters.add(Fighter.findFighterById(fighters.substring(0, 1)));
            this.fighters.add(Fighter.findFighterById(fighters.substring(1)));
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
        final List<String> lines = Common.readFile("level2/level2_1.in");
        final List<Fight> fights = lines.subList(1, lines.size()).stream()
                .map(Fight::new)
                .collect(Collectors.toList());
        fights.forEach(fight -> System.out.println(fight.findWinner().getId()));
    }
}
