package com.maple;

public class Path {

    public static final String PATH_DELIMITER = "/";
    private String pathText;
    private String[] pathFieldValues;

    public Path(String incomingPath) {
        pathText = incomingPath;
        pathFieldValues = incomingPath.split(PATH_DELIMITER);
    }

    public String getPathText() {
        return pathText;
    }

    public String[] getPathFieldValues(){
        return pathFieldValues;
    }


}
