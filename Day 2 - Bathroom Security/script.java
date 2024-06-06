
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

void main() {
    File file = new File("input.txt");
    List<String> data = getInput(file);

    file = new File("test1.txt");
    List<String> testData = getInput(file);
    assert part1(testData).equals("1985");
    assert part2(testData).equals("5DB3");

    String result1 = part1(data);
    System.out.println(result1);

    String result2 = part2(data);
    System.out.println(result2);


}

List<String> getInput(File file) {
    List<String> list = new ArrayList<>();
    try(Scanner scanner = new Scanner(file)) {
        while(scanner.hasNext()) {
            String line = scanner.nextLine().strip();
            list.add(line);
        }
    } catch (FileNotFoundException _) {}
    return list;
}

String part1(List<String> data) {
    final char[][] numpad = {
        {'1', '2', '3'}, 
        {'4', '5', '6'}, 
        {'7', '8', '9'}
    };
    StringBuilder result = new StringBuilder();
    int x=1;
    int y=1;
    for(String string : data) {
        for(char c : string.toCharArray()) {
            switch(c) {
                case 'U' -> y = Math.max(0, y-1);
                case 'R' -> x = Math.min(2, x+1);
                case 'D' -> y = Math.min(2, y+1);
                case 'L' -> x = Math.max(0, x-1);
            }
        }
        result.append(numpad[y][x]);
    }
    return result.toString();
}

String part2(List<String> data) {
    final char[][] numpad = {
        {'\0', '\0', '1', '\0', '\0'},
         {'\0', '2', '3', '4', '\0'},
          {'5', '6', '7', '8', '9'},
         {'\0', 'A', 'B', 'C', '\0'},
        {'\0', '\0', 'D', '\0', '\0'}
    };
    StringBuilder result = new StringBuilder();
    int x=0;
    int y=2;
    for(String string : data) {
        for(char c : string.toCharArray()) {
            switch(c) {
                case 'U' -> {if(Math.abs(x-2) + Math.abs(y-3) < 3) y = Math.max(0, y-1);}
                case 'R' -> {if(Math.abs(x-1) + Math.abs(y-2) < 3) x = Math.min(4, x+1);}
                case 'D' -> {if(Math.abs(x-2) + Math.abs(y-1) < 3) y = Math.min(4, y+1);}
                case 'L' -> {if(Math.abs(x-3) + Math.abs(y-2) < 3) x = Math.max(0, x-1);}
            }
        }
        result.append(numpad[y][x]);
    }
    return result.toString();
}