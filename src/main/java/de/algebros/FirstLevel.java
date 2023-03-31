package de.algebros;

import java.util.ArrayList;

public class FirstLevel {
    public static void main(String[] args) {
        final Fighter rock = new Fighter("R");
        final Fighter paper = new Fighter("P");
        final Fighter scissor = new Fighter("S");

        rock.getWinsAgainst().add(scissor);
        paper.getWinsAgainst().add(rock);
        scissor.getWinsAgainst().add(paper);

        Fight.initFights();
    }
}
