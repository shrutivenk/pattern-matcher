package com.maple;

import java.nio.charset.StandardCharsets;

public class PrintableOutput{

    private String outputToPrint;

    public PrintableOutput(String outputToPrint) {
        this.outputToPrint = outputToPrint;
    }

    /**
     * printOutput adds a newLine character to the end of the pattern text. The string is then encoded to UTF-8 and printed.
     */
    public void printOutput() {
        String encodedString = new String(outputToPrint.concat("\n").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        System.out.print(encodedString);
    }

    /**
     * Added so assertEquals can be called on a PrintableObject
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        PrintableOutput otherPrintableOutput = (PrintableOutput)obj;
        return this.outputToPrint.equals(otherPrintableOutput.outputToPrint);
    }
}
