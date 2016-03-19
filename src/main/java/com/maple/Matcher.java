package com.maple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matcher {
    private Path path;
    private List<Pattern> allPossiblePatterns;
    public static final String NO_MATCH = "NO MATCH";

    public Matcher (Path path, List<Pattern> sameLengthPatterns) {
        this.path = path;
        allPossiblePatterns = sameLengthPatterns;
    }

    /**
     * findBestMatchedPattern returns the Pattern text for the best matched pattern for a path
     * @return String representing best matched Pattern text for the path
     */
    public String findBestMatchedPattern() {
        List<Pattern> bestMatchedPatternsList = findMatchedPatternsWithLeastWildcards();

        switch (bestMatchedPatternsList.size()) {
            case 0:
                return NO_MATCH;
            case 1:
                return bestMatchedPatternsList.get(0).getPatternText();
            default:
                return resolveTieForBestPattern(bestMatchedPatternsList);
        }
    }

    /**
     * findBestMatchedPattern checks the list of allPossiblePatterns available for the path. It uses the stream parallel
     * optimization to check patterns in parallel. if the pattern is a match, it adds it into a map grouped
     * by the number of wildcards in the pattern.
     * @return List of all patterns with the least number of wildcards
     */
    private List<Pattern> findMatchedPatternsWithLeastWildcards() {
        Map<Integer, List<Pattern>> patternMap = allPossiblePatterns.stream()
                .filter(this::isPatternAMatch)
                .collect(Collectors.groupingBy(this::numberOfWildcardsInPattern));

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
        List<String> patternElements = pattern.getPatternFieldValues();
        List<String> pathElements    = path.getPathFieldValues();

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
        return ((int) pattern.getPatternFieldValues().
                stream()
                .filter(i -> i.equals(Pattern.WILDCARD))
                .count());
    }

    /**
     * resolveTieForBestPattern uses stream reduce to resolve tie between 2 patterns at a time and return the winning pattern
     * @param patterns
     * @return best matched pattern for this path
     */
    private String resolveTieForBestPattern(List<Pattern> patterns) {
        Optional<Pattern> winningPattern = patterns.stream().reduce(this::resolveTieBetweenTwoPatterns);
        return winningPattern.get().getPatternText();
    }

    /**
     * resolveTieBetweenTwoPatterns sets the parent pattern to itself and passes the two patterns to be resolved to the
     * resolveTieBetweenTwoPatternsRecursive method
     * @param pattern1
     * @param pattern2
     * @return best matched pattern between two patterns
     */
    private Pattern resolveTieBetweenTwoPatterns(Pattern pattern1, Pattern pattern2) {
        pattern1.setSelfAsParentPattern();
        pattern2.setSelfAsParentPattern();

        return resolveTieBetweenTwoPatternsRecursive(pattern1, pattern2);
    }

    /**
     * resolveTieBetweenTwoPatternsRecursive resolves the tie between two patterns. The pattern with the leftmost wildcard
     * in the rightmost position wins. If there is still a tie, the rule is applied recursively
     * @param pattern1
     * @param pattern2
     * @return best matched pattern between two pattern
     */
    private Pattern resolveTieBetweenTwoPatternsRecursive(Pattern pattern1, Pattern pattern2) {
        int pattern1FirstWildcardIndex = pattern1.getPatternFieldValues().indexOf(Pattern.WILDCARD);
        int pattern2FirstWildcardIndex = pattern2.getPatternFieldValues().indexOf(Pattern.WILDCARD);

        if (pattern1FirstWildcardIndex == pattern2FirstWildcardIndex) {
            String childPatternText1 = PatternMatcherUtils.removeStringAtIndex(pattern1.getPatternFieldValues(),
                                                                                pattern1FirstWildcardIndex, Pattern.PATTERN_DELIMITER);
            Pattern childPattern1 = new Pattern(childPatternText1);
            childPattern1.setParentPattern(pattern1.getParentPattern());

            String childPatternText2 = PatternMatcherUtils.removeStringAtIndex(pattern2.getPatternFieldValues(),
                                                                                pattern2FirstWildcardIndex, Pattern.PATTERN_DELIMITER);
            Pattern childPattern2 = new Pattern(childPatternText2);
            childPattern2.setParentPattern(pattern2.getParentPattern());

            return resolveTieBetweenTwoPatternsRecursive(childPattern1, childPattern2);
        }

        return pattern1FirstWildcardIndex > pattern2FirstWildcardIndex ?
                pattern1.getParentPattern() : pattern2.getParentPattern();
    }
}



