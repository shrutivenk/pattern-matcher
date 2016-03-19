Pattern-Matching Paths
======================

The time complexity of my algorithm is quadratic. However, I have made one small optimization on it. Since we group the patterns by size at the time of reading, for each path, we only need to run matches on patterns of corresponding size. In the worst case where all the paths are patterns are of the same size, the time complexity is O(M X N)
We can make the program run faster for large data sets by by running matching on paths in parallel. I did try using parallelStream but for smaller data sets this causes the program to take longer to run due to the overhead of parallel processing so I left it out until I could write suitable tests to validate using this.