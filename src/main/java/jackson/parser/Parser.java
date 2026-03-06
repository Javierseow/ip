package jackson.parser;

public class Parser {
    public String getCommandWord(String line) {
        return line.trim().split(" ")[0];
    }

    public static String[] splitInput(String line, String splitCharacter, int maxSplit) {
        return line.trim().split("\\s*" + splitCharacter + "\\s*", maxSplit);
    }
}