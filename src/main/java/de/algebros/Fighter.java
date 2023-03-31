package de.algebros;

import java.util.ArrayList;
import java.util.List;

public class Fighter {
    public static final List<Fighter> FIGHTERS = new ArrayList<>();

    public static Fighter findFighterById(String id) {
        return FIGHTERS.stream().filter(fighter -> fighter.id.equals(id)).findFirst().orElse(null);
    }

    private String id;
    private List<Fighter> winsAgainst = new ArrayList<>();

    public Fighter(String id) {

        this.id = id;
    }

    public boolean winsAgains(Fighter fighter) {
        if (this.id.equals(fighter.getId())) {
            return true;
        }
        return this.winsAgainst.stream().anyMatch(value -> value.id.equals(fighter.getId()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Fighter> getWinsAgainst() {
        return winsAgainst;
    }

    public void setWinsAgainst(List<Fighter> winsAgainst) {
        this.winsAgainst = winsAgainst;
    }
}
