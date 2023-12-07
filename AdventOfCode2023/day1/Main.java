package AdventOfCode2023.day1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Main {


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
        return str.chars().filter(ch -> Character.isDigit(ch)).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
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
