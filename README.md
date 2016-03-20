Solution Approach
==================

Quick summary of my approach:

**Classes**

- `Pattern`: Class representing the pattern
- `Path`: Class representing the path
- `PatternFinder`: Class responsible for creating a Matcher object for each Path with the corresponding list of same sized Patterns
- `Matcher`: Class responsible for matching a path against a of patterns, resolve ties and return the best matched pattern
- `InputReader`: Class that reads patterns, paths reads the paths and patterns and returns an InputData object containing a list of paths and a map of patterns
grouped by the size
- `PrintableOutput`: Class responsible for displaying matches / no matches


Instructions To Run
===================

**Requirements:**

- `Java` 1.8.0_73
- `Maven` >= 3.3.9 (Preferably use Homebrew to install Maven `brew install maven`. If it's installed otherwise, set the environment variable  M2_HOME to the the maven bin directory )


**Build Instructions**

- Clone the repo
- From the `project root` directory of the project run `mvn clean compile install`
- From the `project root` CLI, run `java -jar target/pattern-matcher-1.0-SNAPSHOT.jar`
- Feed the input in the format described in the PROBLEM_README

Algorithmic Complexity
======================

The time complexity of my algorithm is quadratic. However, I have made one small optimization on it. Since we group the patterns by size at the time of reading, for each path, we only need to run matches on patterns of corresponding size. In the worst case where all the paths and patterns are of the same size, the time complexity is O(M X N)
We can make the program run faster for large data sets by by running matching on paths in parallel. I did try using **parallelStream** but for smaller data sets, I saw this causes the program to take longer to run due to the overhead of parallel processing. I left it out until I could write suitable tests to validate using this.
