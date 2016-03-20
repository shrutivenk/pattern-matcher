package com.maple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MatcherTest {

    @Test
    public void findBestMatchedPatternTest_NoMatch() {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("a,*,c,*,e"));
        patternList.add(new Pattern("*,b,c,d,*"));
        patternList.add(new Pattern("a,b,c,*,*"));

        Path path = new Path("g/h/i/j/k");

        Matcher matcher = new Matcher(path, patternList);

        String winningPattern = matcher.findBestMatchedPattern();

        assertEquals(winningPattern, Matcher.NO_MATCH);
    }

    @Test
    public void findBestMatchedPattern_ExactMatch() {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("a,b,c,d,*"));
        patternList.add(new Pattern("a,b,c,d,e"));
        patternList.add(new Pattern("a,b,c,*,*"));

        Path path = new Path("a/b/c/d/e");

        Matcher matcher = new Matcher(path, patternList);

        String winningPattern = matcher.findBestMatchedPattern();

        assertEquals(winningPattern, "a,b,c,d,e");
    }

    @Test
    public void findWinningPatternTest_PicksLeastWildCard_NoTie() {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("*,b,*,d,e"));
        patternList.add(new Pattern("*,b,c,*,e"));
        patternList.add(new Pattern("a,*,c,d,e"));

        Path path = new Path("a/b/c/d/e");

        Matcher matcher = new Matcher(path, patternList);

        String winningPattern = matcher.findBestMatchedPattern();

        assertEquals(winningPattern, "a,*,c,d,e");
    }

    @Test
    public void findWinningPatternTest_PicksLeastWildCard_ResolveTie() {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("*,b,*,*,e,f"));
        patternList.add(new Pattern("*,b,*,d,*,f"));
        patternList.add(new Pattern("*,b,*,d,e,*"));

        Path path = new Path("a/b/c/d/e/f");

        Matcher matcher = new Matcher(path, patternList);

        String winningPattern = matcher.findBestMatchedPattern();

        assertEquals(winningPattern, "*,b,*,d,e,*");
    }

    @Test
    public void findWinningPatternTest_ResolveTie_fieldContainAsterisk() {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("*,b,*,*,e*,f,g"));
        patternList.add(new Pattern("*,b,*,d,*,f,g"));
        patternList.add(new Pattern("*,b,*,d,e*,*,g"));

        Path path = new Path("a/b/c/d/e*/f/g");

        Matcher matcher = new Matcher(path, patternList);

        String winningPattern = matcher.findBestMatchedPattern();

        assertEquals(winningPattern, "*,b,*,d,e*,*,g");
    }

    @Test
    public void findWinningPatternTest_SpecialChars() {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("!!!,@@@,###,$$$"));
        patternList.add(new Pattern("!**,@**,*,$**"));
        patternList.add(new Pattern("1,2,3,4,*"));

        Path path = new Path("!!!/@@@/###/$$$");

        Matcher matcher = new Matcher(path, patternList);

        String winningPattern = matcher.findBestMatchedPattern();

        assertEquals(winningPattern, "!!!,@@@,###,$$$");
    }
}