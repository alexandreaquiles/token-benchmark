package br.com.alexandreaquiles.bench;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class TokenBenchmark {

    private static final Pattern NUM_REGEX = Pattern.compile("-?\\d+");

    @Param({"42", "+", "-13", "hello", "-"})
    public String token;

    @Benchmark
    public boolean usingParseInt() {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Benchmark
    public boolean usingRegex() {
        return token.matches("-?\\d+");
    }

    @Benchmark
    public boolean usingRegexCompiled() {
        return NUM_REGEX.matcher(token).matches();
    }

    @Benchmark
    public boolean usingCharCheck() {
        if (token == null || token.isEmpty()) return false;
        int start = token.charAt(0) == '-' ? 1 : 0;
        if (start == 1 && token.length() == 1) return false;
        for (int i = start; i < token.length(); i++) {
            if (!Character.isDigit(token.charAt(i))) return false;
        }
        return true;
    }
}
