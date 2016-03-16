package com.maple;
public class Pattern {

    public static final String PATTERN_DELIMITER = ",";
    public static final String WILDCARD = "*";
    private String patternText;

    public Pattern(String patternText){
        this.patternText = patternText;
    }

    public String getPatternText(){
        return this.patternText;
    }

}
