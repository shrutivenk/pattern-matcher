package com.maple;

import java.util.Arrays;
import java.util.List;

public class Pattern {

    public static final String PATTERN_DELIMITER = ",";
    public static final String WILDCARD = "*";
    private String patternText;
    private List<String> patternFieldValues;
    private Pattern parentPattern;

    public Pattern(String patternText) {
        this.patternText = patternText;
        this.patternFieldValues = Arrays.asList(patternText.split(PATTERN_DELIMITER));
    }

    public Pattern(Pattern pattern)
    {
        this.patternText = pattern.getPatternText();
        this.patternFieldValues = Arrays.asList(patternText.split(PATTERN_DELIMITER));
        this.parentPattern = pattern;
    }

    public String getPatternText() { return this.patternText; }

    public List<String> getPatternFieldValues() { return this.patternFieldValues; }

    public Pattern getParentPattern() { return this.parentPattern; }

    public void setParentPattern(Pattern parentPattern) { this.parentPattern = parentPattern; }
}
