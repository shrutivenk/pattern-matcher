package com.maple;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        inputReader.readIncomingData();

        new PatternFinder(inputReader.getPathList(), inputReader.getPatternMap())
                .getBestMatches()
                .stream()
                .forEach(p -> p.printOutput());
    }
}
