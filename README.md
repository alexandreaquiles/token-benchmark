# token-benchmark

## Overview

This project benchmarks different methods of determining if a given string token represents a valid integer.

It uses the **Java Microbenchmark Harness (JMH)** to provide reliable and precise benchmarking results.

## Approaches measured

1. **Using `Integer.parseInt()`**
    - Attempts to parse the token and catch a `NumberFormatException` in case it is not a valid integer.

2. **Using Regex (Uncompiled)**
    - Uses a simple regex `"-?\\d+"` to determine if the token matches.

3. **Using Compiled Regex**
    - Leverages a precompiled `Pattern` object for regex matching.

4. **Using Character Checks**
    - A manual approach that iterates over the characters of the token to check if they form a valid integer.

- **Mode**: Average Time (`Mode.AverageTime`) - The average time taken per operation is measured.
- **Unit**: Nanoseconds (`TimeUnit.NANOSECONDS`) - Results are given in nanoseconds for precision. 

The output will indicate the performance of each method and help choose the most efficient one for integer validation.

## Results


```
Benchmark                             (token)  Mode  Cnt    Score    Error  Units
RPNTokenBenchmark.usingCharCheck           42  avgt   25    2.741 ±  0.005  ns/op
RPNTokenBenchmark.usingCharCheck            +  avgt   25    1.246 ±  0.019  ns/op
RPNTokenBenchmark.usingCharCheck          -13  avgt   25    3.159 ±  0.023  ns/op
RPNTokenBenchmark.usingCharCheck        hello  avgt   25    1.444 ±  0.005  ns/op
RPNTokenBenchmark.usingCharCheck            -  avgt   25    1.189 ±  0.017  ns/op
RPNTokenBenchmark.usingParseInt            42  avgt   25    3.737 ±  0.016  ns/op
RPNTokenBenchmark.usingParseInt             +  avgt   25  993.799 ±  6.810  ns/op
RPNTokenBenchmark.usingParseInt           -13  avgt   25    4.158 ±  0.008  ns/op
RPNTokenBenchmark.usingParseInt         hello  avgt   25  992.356 ±  5.334  ns/op
RPNTokenBenchmark.usingParseInt             -  avgt   25  992.391 ±  4.740  ns/op
RPNTokenBenchmark.usingRegex               42  avgt   25   85.985 ±  0.721  ns/op
RPNTokenBenchmark.usingRegex                +  avgt   25   85.185 ±  0.918  ns/op
RPNTokenBenchmark.usingRegex              -13  avgt   25   91.964 ±  3.020  ns/op
RPNTokenBenchmark.usingRegex            hello  avgt   25   68.243 ±  4.317  ns/op
RPNTokenBenchmark.usingRegex                -  avgt   25   66.229 ±  1.719  ns/op
RPNTokenBenchmark.usingRegexCompiled       42  avgt   25   35.011 ±  0.328  ns/op
RPNTokenBenchmark.usingRegexCompiled        +  avgt   25   44.729 ± 12.679  ns/op
RPNTokenBenchmark.usingRegexCompiled      -13  avgt   25   39.952 ±  0.442  ns/op
RPNTokenBenchmark.usingRegexCompiled    hello  avgt   25   59.894 ± 10.058  ns/op
RPNTokenBenchmark.usingRegexCompiled        -  avgt   25   46.192 ± 14.258  ns/op
```

Method | Pros | Cons | When to Use
-------|------|------|------------
usingCharCheck | Fastest, low allocation, minimal GC pressure | Requires more manual logic | High-performance parsing, tight loops
usingParseInt | Easy to write | Extremely slow on invalid input (exceptions) | Only when input is always a number
usingRegex | Flexible | Very slow (regex compiled every time) | Rarely — mostly for one-off parsing
usingRegexCompiled | Clean, reusable patterns | Still relatively slow | Good balance if perf isn't critical

## Technologies Used
- **Java**: Core language used for the implementation and benchmarks.
- **JMH**: Java Microbenchmark Harness for precise benchmarking.
- **JMH Plugin**: Gradle plugin for JMH integration in the project.

## How to Run the Benchmarks

To run the benchmarks:

```shell
./gradlew jmh
```

This will execute the benchmarks and provide a detailed report of performance metrics in the console.

## Project Structure
Here is the key file structure of the project:
- `build.gradle`: Gradle configuration file for setting up dependencies and plugins.
- `TokenBenchmark.java`: Contains the benchmarking code.

## License

This project is licensed under the [MIT License](LICENSE).