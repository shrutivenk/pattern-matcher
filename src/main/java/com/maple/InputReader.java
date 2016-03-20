package com.maple;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputReader {

    public InputData readIncomingData() {

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name()).useDelimiter("\\n");
        Map <Integer,List<Pattern>> patternMap = readPatterns(scanner);
        List<Path> pathList = readPaths(scanner);
        scanner.close();

        return new InputData(pathList, patternMap);
    }

    private Map<Integer, List<Pattern>> readPatterns(Scanner scanner) {

        List<Pattern> patternList = new ArrayList<Pattern>();

        while (!scanner.hasNextInt())
            scanner.nextLine();

        int numberOfPatterns = Integer.parseInt(scanner.nextLine());

        IntStream.range(0, numberOfPatterns)
                .forEach(i -> {
                    if (scanner.hasNext()) {
                        Pattern pattern = new Pattern(scanner.nextLine());
                        patternList.add(pattern);
                    }
                });

        return patternList.stream().collect(Collectors.groupingBy(i -> i.getPatternFieldValues().size()));
    }

    private List<Path> readPaths(Scanner scanner) {

        List<Path> pathList = new ArrayList<Path>();

        while (!scanner.hasNextInt())
            scanner.nextLine();

        int numberOfPaths = Integer.parseInt(scanner.nextLine());

        IntStream.range(0, numberOfPaths)
                .forEach(i -> {
                    if (scanner.hasNext()) {
                        Path path = new Path(PatternMatcherUtils.trimLeadingAndTrailingDelimiter(scanner.nextLine(), Path.PATH_DELIMITER));
                        pathList.add(path);
                    }
                });

        return pathList;
    }
}
