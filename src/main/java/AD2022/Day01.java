package AD2022;

import Utils.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day01 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        URL resource = FileUtils.readResource("2022/2022-01.txt");

        assert resource != null;

        try (Stream<String> fileStream =  Files.lines(Paths.get(resource.toURI()))) {
            Iterator<String> fileIterator = fileStream.iterator();

            List<Integer> calories = new ArrayList<>();

            int caloriesSum = 0;
            while (fileIterator.hasNext()) {

                String line = fileIterator.next().trim();


                if (!line.isEmpty()) {
                    caloriesSum += Integer.parseInt(line);
                } else {
                    calories.add(caloriesSum);
                    caloriesSum = 0;
                }
            }


            List<Integer> tops = calories.stream()
                    .sorted(Comparator.reverseOrder())
                    .limit(3)
                    .collect(Collectors.toList());

            tops.stream().limit(1)
                    .forEach(s -> System.out.println("top: " + s)
                    );

            int topSum = tops.stream().mapToInt(Integer::intValue).sum();
            System.out.println("topSum: " + topSum);
        }
    }
}
