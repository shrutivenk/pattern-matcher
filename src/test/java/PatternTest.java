import com.maple.Pattern;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PatternTest {

    @Test
    public void setAndCheckPatternText() {
        Pattern pattern = new Pattern("test");

        assertEquals("test", pattern.getPatternText());
    }

}
