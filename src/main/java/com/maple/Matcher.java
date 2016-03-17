package com.maple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matcher {
    private Path path;
    private List<Pattern> allPossiblePatterns;
    public static final String NO_MATCH = "NO MATCH";

    public Matcher (Path path, List<Pattern> sameLengthPatterns)
    {
        this.path = path;
        allPossiblePatterns = sameLengthPatterns;
    }

    /**
     * findBestMatchedPattern checks the list of allPossiblePatterns available for the path. It uses the stream parallel
     * optimization to check patterns in parallel. if the pattern is a match, it adds it into a map of patterns grouped
     * by the number of wildcards in the pattern.
     * @return List of all patterns with the least number of wildcards
     */
    public List<Pattern> findBestMatchedPattern() {

        Map<Integer, List<Pattern>> patternMap = allPossiblePatterns.stream()
                .parallel()
                .filter(i -> isPatternAMatch(i))
                .collect(Collectors.groupingBy(i -> numberOfWildcardsInPattern(i)));

        return patternMap.isEmpty() ? new ArrayList<>() : patternMap.get(Collections.min(patternMap.keySet()));

    }

    /**
     * isPatternMatch method compares the Pattern to the path. It checks each pattern field and path field again each other.
     * If the pattern field and path field are exact matches or if the pattern field is a wildcard, it is a match. If all
     * fields are matched, it returns true.
     * @param pattern
     * @return True if all fields are matched, else False
     */
    private boolean isPatternAMatch(Pattern pattern){
        List<String> patternElements = Arrays.asList(pattern.getPatternText().split(Pattern.PATTERN_DELIMITER));
        List<String> pathElements = Arrays.asList(path.getPathText().split(Path.PATH_DELIMITER));

        return IntStream.range(0, pathElements.size())
                .allMatch(i ->(patternElements.get(i).equals(pathElements.get(i))
                        || patternElements.get(i).equals(Pattern.WILDCARD)));
    }

    /**
     * numberOfWildcardsinPattern counts the number of wildcards in the Pattern object passed to it
     * @param pattern
     * @return int representing number of wildcards in the pattern
     */
    private int numberOfWildcardsInPattern(Pattern pattern) {
        return ((int) Arrays.asList(pattern.getPatternText().split(Pattern.PATTERN_DELIMITER)).
                stream()
                .filter(i -> i.toString().equals(Pattern.WILDCARD))
                .count());
    }
}



