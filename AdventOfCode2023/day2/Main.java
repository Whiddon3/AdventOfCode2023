package AdventOfCode2023.day2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";


    public static Map<String, Integer> parseGame(String[] gameSetStrings){
        Map<String, Integer> game = new HashMap<String, Integer>();
        for (String set: gameSetStrings){
            String[] draws = set.split(",");
            for (String draw: draws){
                String[] drawInfo = draw.trim().split(" ");
                String colorName = drawInfo[1];
                Integer highestCount = game.get(drawInfo[1]) != null
                        ? Math.max(game.get(drawInfo[1]), Integer.parseInt(drawInfo[0]))
                        : Integer.parseInt(drawInfo[0]);
                game.put(colorName, highestCount);
            }
        }
        return game;
    }

    public static ArrayList<Map<String, Integer>> parseLines(String filePath){
        ArrayList<Map<String, Integer>> games = new ArrayList<Map<String, Integer>>();
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        for (String line: lines){
            String[] gameSetStrings = line.split(":")[1].split(";");
            games.add(parseGame(gameSetStrings));
        }
        //System.out.println(String.format("Parsed %d games", games.size()));
        //System.out.println(games);
        return games;
    }

    private static Integer calculateValidIdSum(ArrayList<Map<String, Integer>> games, Integer redMax, Integer blueMax, Integer greenMax) {
        Integer validIdSum = 0;
        
        for (int i = 0; i < games.size(); i++){
            Map<String, Integer> game = games.get(i);
            Integer id = i + 1;
            Integer redCt = game.get(RED) != null ? game.get(RED) : 0;
            Integer greenCt = game.get(GREEN) != null ? game.get(GREEN) : 0;
            Integer blueCt = game.get(BLUE) != null ? game.get(BLUE) : 0;
            System.out.println(String.format("Red %d --> %d, green %d --> %d, blue %d --> %d", redCt, redMax, greenCt,
                    greenMax, blueCt, blueMax));
            if (redCt <= redMax && greenCt <= greenMax && blueCt <= blueMax){
                validIdSum += id;
            }
        }
        return validIdSum;
    }

    public static void main(String[] args) {
        Integer red = 12;
        Integer blue = 14;
        Integer green = 13;
        ArrayList<Map<String, Integer>> games = parseLines("AdventOfCode2023\\day2\\input.txt"); 
        Integer idSum = calculateValidIdSum(games, red, blue, green);
        System.out.println(String.format("%d is the sum of the valid Ids", idSum));
    }

}