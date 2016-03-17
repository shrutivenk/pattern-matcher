import com.maple.Matcher;
import com.maple.Path;
import com.maple.Pattern;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MatcherTest {

    @Test
    public void findBestMatchedPatternTest_NoMatches()
    {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("a,c,d,f,s"));
        patternList.add(new Pattern("s,g,d,s,g"));

        Path path = new Path("a/b/c/d/e");

        Matcher matcher = new Matcher(path, patternList);

        List<Pattern> bestPatterns = matcher.findBestMatchedPattern();

        assertEquals(0, bestPatterns.size());
    }

    @Test
    public void findBestMatchedPatternTest_ExactMatch() {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("a,*,c,*,e"));
        patternList.add(new Pattern("*,b,c,d,*"));
        patternList.add(new Pattern("a,*,*,*,e"));
        patternList.add(new Pattern("a,b,*,d,e"));
        patternList.add(new Pattern("a,*,c,d,e"));
        patternList.add(new Pattern("a,b,c,d,e"));
        patternList.add(new Pattern("a,c,d,f,s"));
        patternList.add(new Pattern("s,g,d,s,g"));

        Path path = new Path("a/b/c/d/e");

        Matcher matcher = new Matcher(path, patternList);

        List<Pattern> bestPatterns = matcher.findBestMatchedPattern();

        assertEquals("a,b,c,d,e", bestPatterns.get(0).getPatternText());
    }

    @Test
    public void findBestMatchedPatternTest_OneWildcard() {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("a,*,c,*,e"));
        patternList.add(new Pattern("*,b,c,d,*"));
        patternList.add(new Pattern("a,*,*,*,e"));
        patternList.add(new Pattern("a,b,*,d,e"));
        patternList.add(new Pattern("a,*,c,d,e"));
        patternList.add(new Pattern("a,c,d,f,s"));
        patternList.add(new Pattern("s,g,d,s,g"));

        Path path = new Path("a/b/c/d/e");

        Matcher matcher = new Matcher(path, patternList);

        List<Pattern> bestPatterns = matcher.findBestMatchedPattern();

        assertEquals("a,b,*,d,e", bestPatterns.get(0).getPatternText());
    }

    @Test
    public void findBestMatchedPatternTest_TwoWildcards() {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(new Pattern("a,*,c,*,e"));
        patternList.add(new Pattern("*,b,c,d,*"));
        patternList.add(new Pattern("a,*,*,*,e"));
        patternList.add(new Pattern("a,c,d,f,s"));
        patternList.add(new Pattern("s,g,d,s,g"));

        Path path = new Path("a/b/c/d/e");

        Matcher matcher = new Matcher(path, patternList);

        List<Pattern> bestPatterns = matcher.findBestMatchedPattern();

        assertEquals("a,*,c,*,e", bestPatterns.get(0).getPatternText());
    }
}
