package com.maple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PatternMatcherUtilsTest {

    @Test
    public void trimLeadingAndTrailingDelimiterTest() {
        String trimmedTestString = "a/b/c/d/e";

        String testString1 = "/a/b/c/d/e";
        assertEquals(PatternMatcherUtils.trimLeadingAndTrailingDelimiter(testString1, Path.PATH_DELIMITER), trimmedTestString);

        String testString2 = "a/b/c/d/e/";
        assertEquals(PatternMatcherUtils.trimLeadingAndTrailingDelimiter(testString2, Path.PATH_DELIMITER), trimmedTestString);

        String testString3 = "/a/b/c/d/e/";
        assertEquals(PatternMatcherUtils.trimLeadingAndTrailingDelimiter(testString3, Path.PATH_DELIMITER), trimmedTestString);
    }
    @Test
    public void removeStringAtIndexTest() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("test");
        stringList.add("remove");
        stringList.add("string");
        stringList.add("at");
        stringList.add("index");

        String actualString = PatternMatcherUtils.removeStringAtIndex(stringList, 1, Pattern.PATTERN_DELIMITER);
        String expectedString = "test,string,at,index";

        assertEquals(expectedString, actualString);
    }
}
