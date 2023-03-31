package de.algebros;

import java.util.ArrayList;
import java.util.List;

public class Fight {
    private List<Fighter> fighters = new ArrayList<>();

    public Fight(String fighters) {
        for (char c : fighters.toCharArray()) {
            this.fighters.add(Fighter.findFighterById("" + c));
        }
    }

    public Fight(List<Fighter> fighters) {
        this.fighters = fighters;
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
}
