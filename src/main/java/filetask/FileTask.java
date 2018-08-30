package filetask;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileTask {

    public static final String FILE_PATH = "c://test.txt";

    public Map<String, Long> getWordNumbers() {

        Map<String, Long> wordNumbers = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE_PATH))){
            wordNumbers = br.lines().flatMap(line -> Stream.of(line.split(" ")))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordNumbers;
    }

    public static void main(String[] args) {
        FileTask fileTask = new FileTask();
        System.out.println(fileTask.getWordNumbers());
    }
}
