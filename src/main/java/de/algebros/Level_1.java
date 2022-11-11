package de.algebros;

import java.util.concurrent.atomic.AtomicInteger;

public class Level_1 {
    public static void main(String[] args) {
        final String fileName = "level1/level1_1.in";
        final AtomicInteger counter = new AtomicInteger();
        //Common.readFile(fileName, (index, line) -> counter.getAndAdd(countCoins(line)));
        System.out.println("Amount of Coins: " + counter.get());
    }

    private static int countCoins(String line) {
        int counter = 0;
        for (char c : line.toCharArray()) {
            if (c == 'C' || c == 'c') {
                counter++;
            }
        }
        return counter;
    }
}
