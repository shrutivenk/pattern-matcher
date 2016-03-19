package com.maple;

import java.nio.charset.StandardCharsets;

public class PrintableOutput{

    private String outputToPrint;

    public PrintableOutput(String outputToPrint) {
        this.outputToPrint = outputToPrint;
    }

    public void printOutput() {
        String encodedString = new String(outputToPrint.concat("\n").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        System.out.print(encodedString);
    }

    @Override
    public boolean equals(Object obj) {
        PrintableOutput otherPrintableOutput = (PrintableOutput)obj;
        return this.outputToPrint.equals(otherPrintableOutput.outputToPrint);
    }
}
