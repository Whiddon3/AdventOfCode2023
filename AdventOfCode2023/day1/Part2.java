package AdventOfCode2023.day1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {

    private static Map<String, String> numberKeyMap;

    static {
        numberKeyMap = new HashMap<>();
        numberKeyMap.put("one", "o1e");
        numberKeyMap.put("two", "t2o");
        numberKeyMap.put("three", "t3e");
        numberKeyMap.put("four", "4");
        numberKeyMap.put("five", "5e");
        numberKeyMap.put("six", "6");
        numberKeyMap.put("seven", "7n");
        numberKeyMap.put("eight", "e8t");
        numberKeyMap.put("nine", "n9e");
    }

    public static List<String> readIntoList(String filePath){
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        }
        catch(IOException e) {
            e.printStackTrace();
        } 
        return lines;
    }

    public static String removeAlpha(String str){
        StringBuffer newStr = new StringBuffer(str);
        for (Map.Entry<String,String> entry : numberKeyMap.entrySet()){
            String parsedStr = newStr.toString();
            newStr.delete(0, newStr.length()).append(parsedStr.replaceAll(entry.getKey(), entry.getValue()));
        }
        return newStr.chars().filter(ch -> Character.isDigit(ch)).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static void main(String[] args) {
        List<String> lines = readIntoList("AdventOfCode2023\\day1\\input.txt");
        Integer totalSum = 0;
        for (String str: lines){
            String parsedStr = removeAlpha(str);
            if (parsedStr.length() >= 1){
                String newStr = String.format("%c%c", parsedStr.charAt(0), parsedStr.charAt(parsedStr.length()-1));
                totalSum += Integer.parseInt(newStr);
                System.out.println(String.format("String %s parsed to %s and added %d", str, parsedStr, Integer.parseInt(newStr)));
            }
        }
        System.out.println(String.format("Total is %d", totalSum));
    }

}
