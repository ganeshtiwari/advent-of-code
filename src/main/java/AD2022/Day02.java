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
    static Map<String, Integer> outComes = Map.of(
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

    static Map<String, Integer> choiceScores = Map.of(
            "X", 1,
            "Y", 2,
            "Z", 3,
            "A", 1,
            "B", 2,
            "C", 3
    );
    public static void main(String[] args) throws URISyntaxException, IOException {
        URL resource = FileUtils.readResource("2022/2022-02.txt");

        try (Stream<String> fileStream = Files.lines(Paths.get(resource.toURI()))) {
            Iterator<String> fileIterator = fileStream.iterator();

            int play1Score = 0;
            int play2Score = 0;
            while (fileIterator.hasNext()) {
                String[] choices = fileIterator.next()
                        .trim()
                        .split(" ");

                play1Score += play1(choices[0], choices[1]);
                play2Score += play2(choices[0], choices[1]);
            }

            System.out.println("play1 score: " + play1Score + " play2 Score: " + play2Score);
        }
    }

    private static int play2(String opponentChoice, String outCome) {
        String myChoice = "";

        for (Map.Entry<String, Integer> entry: outComes.entrySet()) {
            int value = entry.getValue();
            String key = entry.getKey();

            if (outCome.equals("X")) {
                if (value == 0 && key.startsWith(opponentChoice)) {
                    myChoice = String.valueOf(key.toCharArray()[1]);
                    break;
                }
            }
            if (outCome.equals("Y")) {
                if (value == 3 && key.startsWith(opponentChoice)) {
                    myChoice = String.valueOf(key.toCharArray()[1]);
                    break;
                }
            }
            if (outCome.equals("Z")) {
                if (value == 6  && key.startsWith(opponentChoice)) {
                    myChoice = String.valueOf(key.toCharArray()[1]);
                    break;
                }
            }
        }

        return play1(opponentChoice, myChoice);
    }
    private static int play1(String opponentChoice, String myChoice) {
        int choiceScore = choiceScores.get(myChoice);

        String key = opponentChoice + myChoice;
        int score = outComes.get(key);
        System.out.println("key: " + key + " score: " + score);

        return choiceScore + score;
    }
}
