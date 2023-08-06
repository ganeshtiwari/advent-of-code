package AD2022;

import Utils.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day02 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        URL resource = FileUtils.readResource("2022/2022-02.txt");

        try (Stream<String> fileStream = Files.lines(Paths.get(resource.toURI()))) {
            Iterator<String> fileIterator = fileStream.iterator();

            int totalScore = 0;
            while (fileIterator.hasNext()) {
                String[] choices = fileIterator.next()
                        .trim()
                        .split(" ");

                totalScore += play(choices[0], choices[1]);
            }

            System.out.println(totalScore);
        }
    }

    private static int play(String opponentChoice, String myChoice) {
        Map<String, Integer> outComes = Map.of(
                "AX", 3,
                "AY", 6,
                "AZ", 0,
                "BX", 0,
                "BY", 3,
                "BZ", 6,
                "CX", 6,
                "CY", 0,
                "CZ", 3
        );

        Map<String, Integer> choiceScores = Map.of(
                "X", 1,
                "Y", 2,
                "Z", 3,
                "A", 1,
                "B", 2,
                "C", 3
        );

        int choiceScore = choiceScores.get(myChoice);

        String key = opponentChoice + myChoice;
        int score = outComes.get(key);
        System.out.println("key: " + key + " score: " + score);

        return choiceScore + score;
    }
}
