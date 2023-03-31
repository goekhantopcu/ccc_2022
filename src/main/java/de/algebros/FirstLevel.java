package de.algebros;

public class FirstLevel {
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
    }
}
