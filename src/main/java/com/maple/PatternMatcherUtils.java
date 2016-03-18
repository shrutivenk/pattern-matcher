package com.maple;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PatternMatcherUtils {

    public static String trimLeadingAndTrailingDelimiter(String stringToTrim, String delimiter)
    {
        StringBuilder sb = new StringBuilder(stringToTrim);
        if (stringToTrim.startsWith(delimiter))
            sb.deleteCharAt(0);
        if (stringToTrim.endsWith(delimiter))
            sb.deleteCharAt(sb.lastIndexOf(delimiter));

        return sb.toString();
    }

    public static String removeStringAtIndex(List<String> patternElements, int indexToRemove, String joinString)
    {
        return IntStream.range(0, patternElements.size())
                .filter(i -> i != indexToRemove)
                .mapToObj(patternElements::get)
                .collect(Collectors.joining(joinString));
    }
}
