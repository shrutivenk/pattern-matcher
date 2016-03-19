package com.maple;

import java.util.Arrays;
import java.util.List;

public class Path {

    public static final String PATH_DELIMITER = "/";
    private String pathText;
    private List<String> pathFieldValues;

    public Path(String pathText) {
        this.pathText = pathText;
        pathFieldValues = Arrays.asList(pathText.split(PATH_DELIMITER));
    }

    public String getPathText() {
        return pathText;
    }

    public List<String> getPathFieldValues() {
        return pathFieldValues;
    }

}
