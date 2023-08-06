package AD2022;

import Utils.CommonUtils;
import Utils.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Day05 {
    public static void main(String[] args) throws IOException, URISyntaxException {

        URL resource = FileUtils.readResource("2022/2022-05.txt");

        assert resource != null;

        try (Stream<String> fileStream =  Files.lines(Paths.get(resource.toURI()))) {

            Iterator<String> lines = fileStream.iterator();
                    List<List<String>> crateStacks = new LinkedList<>();
            List<List<Integer>> operations = new LinkedList<>();

            // parse crates
            while (lines.hasNext()) {
            String line = lines.next();
            if (line.contains("[")) {

                System.out.println("Parsing stack line: " + line);
                char[] chars = line.toCharArray();
                int trackCrateIndex = 0;
                StringBuilder crate = new StringBuilder();
                for (int i=0; i<chars.length; i++) {

                    if (i % 4 == 2) {
                        trackCrateIndex += 1;
                        if (crateStacks.size() < trackCrateIndex) {
                            crateStacks.add(new ArrayList<>());
                        }
                    }

                    char token = chars[i];
                    if (token == ' ') {
                    } else {
                        crate.append(token);
                        if (token == ']') {
                            crateStacks.get(trackCrateIndex - 1).add(crate.toString());
                            crate = new StringBuilder();
                        }
                    }
                }
            }

             // parse operation
            if (line.contains("move")) {
                String[] tokens = line.split(" ");
                List<Integer> operation = new ArrayList<>();
                for (String token: tokens) {
                    if (CommonUtils.isDigit(token)) {
                        operation.add(Integer.parseInt(token));
                    }
                }
                operations.add(operation);
            }
        }

        crateStacks.forEach(System.out::println);

        // perform operation
        for(List<Integer> operation: operations) {
            System.out.println(operation);
            int numCratesToMove = operation.get(0);
            int sourceStackIndex = operation.get(1) - 1;
            int destinationStackIndex = operation.get(2) - 1;

            System.out.println("Source: " + crateStacks.get(sourceStackIndex));
            System.out.println("destination: " + crateStacks.get(destinationStackIndex));

            List<String> moveAbleCrates = crateStacks
                    .get(sourceStackIndex)
                    .subList(0, numCratesToMove);

            crateStacks.get(destinationStackIndex)
                    .addAll(0, moveAbleCrates);

            moveAbleCrates.clear();

            System.out.println("Source: " + crateStacks.get(sourceStackIndex));
            System.out.println("destination: " + crateStacks.get(destinationStackIndex));
            System.out.println();
        }

        for (List<String> stacks: crateStacks) {
            for(char c: stacks.get(0).toCharArray()) {
                if (c != '[' && c != ']') {
                    System.out.print(c);
                }
            }
        }
        System.out.println();
        }
    }
}

