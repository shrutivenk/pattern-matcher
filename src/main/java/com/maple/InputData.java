package com.maple;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class InputData {

    private Map<Integer, List<Pattern>> patternMap;
    private List<Path> pathList;

    InputData(List<Path> pathList, Map<Integer, List<Pattern>> patternMap)
    {
        this.pathList = pathList;
        this.patternMap = patternMap;

        Collections.unmodifiableList(this.pathList);
        Collections.unmodifiableMap(this.patternMap);
    }

    public List<Path> getPathList(){
        return pathList;
    }

    public Map<Integer, List<Pattern>> getPatternMap(){
        return patternMap;
    }



}
