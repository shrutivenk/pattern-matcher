package com.maple;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        inputReader.readIncomingData();

        for (Path path : inputReader.getPathList())
        {
            int pathSize = path.getPathFieldValues().size();
            String bestPattern = Matcher.NO_MATCH;

            if (inputReader.getPatternMap().containsKey(pathSize)) {

                List<Pattern> allPatternsOfSameSize = inputReader.getPatternMap().get(pathSize);
                Matcher matcher = new Matcher(path, allPatternsOfSameSize);
                bestPattern = matcher.findBestMatchedPattern();
            }

            printString(bestPattern);
        }
    }

    private static void printString(String patternText)
    {
        patternText = patternText.concat("\n");
        String encodedString = new String(patternText.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        System.out.print(encodedString);
    }
}
