import com.maple.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PatternFinderTest {

    Map<Integer, List<Pattern>> patternMap;
    List<Path> pathList;
    PatternFinder patternFinder;
    List<PrintableOutput> expectedResultOutput;

    @Before
    public void testSetup() {
        List<Pattern> patternList = new ArrayList<Pattern>();
        pathList = new ArrayList<>();
        expectedResultOutput = new ArrayList<>();

        patternList.add(new Pattern("a,b,c,d,*"));
        patternList.add(new Pattern("a,*,c,d,e"));
        patternList.add(new Pattern("1,2,3,4,5"));
        patternList.add(new Pattern("*,Yesthisisdog"));
        patternList.add(new Pattern("hi,hello,*,yo,*"));
        patternList.add(new Pattern("hi,hello,*.*,sup"));
        patternList.add(new Pattern("*,*,*"));
        patternList.add(new Pattern("!,@,#,$,%,^,&,(,)"));
        patternList.add(new Pattern("test,string,*"));
        patternList.add(new Pattern("bikes,beets,boris*,becker*"));

        patternMap = patternList.stream().collect(Collectors.groupingBy(i -> i.getPatternFieldValues().size()));

        pathList.add(new Path("a/b/c/d/e"));
        pathList.add(new Path("a/hi/c/d/e"));
        pathList.add(new Path("hi/hello/grr/yo/sup"));
        pathList.add(new Path("Hello/Yesthisisdog"));
        pathList.add(new Path("bikes/beets/boris*/becker*/"));
        pathList.add(new Path("test/string"));
        pathList.add(new Path("grumpy/sleepy/sneezy"));
        pathList.add(new Path("!/@/#/$/%/^/&/(/)"));
        pathList.add(new Path(""));

        expectedResultOutput.add(new PrintableOutput("a,b,c,d,*"));
        expectedResultOutput.add(new PrintableOutput("a,*,c,d,e"));
        expectedResultOutput.add(new PrintableOutput("hi,hello,*,yo,*"));
        expectedResultOutput.add(new PrintableOutput("*,Yesthisisdog"));
        expectedResultOutput.add(new PrintableOutput("bikes,beets,boris*,becker*"));
        expectedResultOutput.add(new PrintableOutput(Matcher.NO_MATCH));
        expectedResultOutput.add(new PrintableOutput("*,*,*"));
        expectedResultOutput.add(new PrintableOutput("!,@,#,$,%,^,&,(,)"));
        expectedResultOutput.add(new PrintableOutput(Matcher.NO_MATCH));

        patternFinder = new PatternFinder(pathList, patternMap);
    }

    @Test
    public void getBestMatches() {
        List<PrintableOutput> resultList = patternFinder.getBestMatches();

        assertEquals(expectedResultOutput, resultList);
    }

}
