package com.maple;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputReader {

    private List<Pattern> patternList;
    private List<Path> pathList;

    public InputReader() {
        patternList = new ArrayList<Pattern>();
        pathList = new ArrayList<Path>();
    }

    public void readIncomingData() {

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name()).useDelimiter("\\n");
        readPatternsAndAddToList(scanner);
        readPathsAndAddToList(scanner);
        scanner.close();
    }

    public Map<Integer, List<Pattern>> getPatternMap() {
        return patternList.stream().collect(Collectors.groupingBy(i -> i.getPatternFieldValues().size()));
    }

    public List<Path> getPathList() {
        return pathList;
    }

    private void readPatternsAndAddToList(Scanner scanner) {

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
    }

    private void readPathsAndAddToList(Scanner scanner) {

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
    }
}
