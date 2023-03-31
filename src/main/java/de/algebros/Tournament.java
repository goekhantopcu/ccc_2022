package de.algebros;

import java.util.List;
import java.util.stream.Collectors;

public class Tournament {
    private List<Fight> fights;

    public Tournament(List<Fight> fights) {
        this.fights = fights;
    }

    public List<Fighter> findWinners() {
        return this.fights.stream().map(Fight::findWinner).collect(Collectors.toList());
    }

    public List<Fight> getFights() {
        return fights;
    }

    public void setFights(List<Fight> fights) {
        this.fights = fights;
    }
}
