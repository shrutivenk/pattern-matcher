package com.maple;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatternFinder {
    private List<Path> pathList;
    private Map<Integer, List<Pattern>> patternMap;

    public PatternFinder(List<Path> pathList, Map<Integer, List<Pattern>> patternMap)
    {
        this.pathList = pathList;
        this.patternMap = patternMap;
    }

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
