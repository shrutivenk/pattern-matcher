package com.maple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<String> valuesList = new ArrayList<String>();
        valuesList.add(0,"t");
        valuesList.add(1,"e");
        valuesList.add(2,"s");
        valuesList.add(3,"t");

        assertEquals(valuesList, path.getPathFieldValues());
    }
}
