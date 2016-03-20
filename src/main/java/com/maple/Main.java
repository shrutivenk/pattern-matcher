package com.maple;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        InputData inputData = inputReader.readIncomingData();

        new PatternFinder(inputData.getPathList(), inputData.getPatternMap())
                .getBestMatches()
                .stream()
                .forEach(p -> p.printOutput());
    }
}
