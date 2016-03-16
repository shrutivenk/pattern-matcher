import com.maple.Path;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PathTest {

    @Test
    public void setAndCheckPathText() {
        Path path = new Path("test");

        assertEquals("test", path.getPathText());
    }

    @Test
    public void checkPathValuesArray(){
        Path path = new Path("t/e/s/t");
        String[] valuesArray = {"t", "e", "s", "t"};

        assertArrayEquals(valuesArray, path.getPathFieldValues());
    }

}
