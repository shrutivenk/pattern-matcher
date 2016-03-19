package com.maple;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatternFinder {
    private List<Path> pathList;
    private Map<Integer, List<Pattern>> patternMap;

    public PatternFinder(List<Path> pathList, Map<Integer, List<Pattern>> patternMap) {
        this.pathList = pathList;
        this.patternMap = patternMap;
    }

    /**
     * getBestMatches takes a list of paths. For each path, it gets the list of patterns with the same size. It then creates a
     * Matcher object for the path and eligible pattern list and calls findBestMatchedPattern on it.
     * @return a List of PrintableObjects, each represents the best matched pattern for the corresponding path
     */
    public List<PrintableOutput> getBestMatches() {

            return pathList.stream()
                .map(p -> {
                    if (patternMap.containsKey(p.getPathFieldValues().size())) {
                        Matcher matcher = new Matcher(p, patternMap.get(p.getPathFieldValues().size()));
                        return new PrintableOutput(matcher.findBestMatchedPattern());
                    } else
                        return new PrintableOutput(Matcher.NO_MATCH);
                })
                .collect(Collectors.toList());
    }
}
