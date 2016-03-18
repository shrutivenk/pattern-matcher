import com.maple.Pattern;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PatternTest {

    @Test
    public void setAndCheckPatternText() {
        Pattern pattern = new Pattern("test");

        assertEquals("test", pattern.getPatternText());
    }

    @Test
    public void setSelfAsParentPatternTest()
    {
        Pattern pattern = new Pattern("test");
        pattern.setSelfAsParentPattern();

        assertEquals(pattern.getParentPattern(), pattern);
    }

    @Test
    public void setParentPatternTest()
    {
        Pattern pattern = new Pattern("test");
        Pattern parentPattern = new Pattern("parentTest");
        pattern.setParentPattern(parentPattern);

        assertEquals(pattern.getParentPattern().getPatternText(), "parentTest");
    }

    @Test
    public void getPatternFieldValuesTest()
    {
        Pattern pattern = new Pattern("t,e,s,t");
        List<String> valuesList = new ArrayList<String>();
        valuesList.add(0,"t");
        valuesList.add(1,"e");
        valuesList.add(2,"s");
        valuesList.add(3,"t");

        assertEquals(pattern.getPatternFieldValues(), valuesList);
    }
}
