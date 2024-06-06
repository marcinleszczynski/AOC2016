
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

void main() throws FileNotFoundException {
    File input = new File("input.txt");
    String string;
    try (Scanner scanner = new Scanner(input)) {
        string = scanner.nextLine();
    }
    assert part1("R2, L3") == 5;
    assert part1("R2, R2, R2") == 2;
    assert part1("R5, L5, R5, R3") == 12;
    int result1 = part1(string);
    System.out.println(result1);
    assert part2("R8, R4, R4, R8") == 4;
    int result2 = part2(string);
    System.out.println(result2);
}

int part1(String string) {
    int currentDirection = 0;
    int vertical = 0;
    int horizontal = 0;
    List<String> data = List.of(string.split(", "));
    for(String c : data) {
        char turn = c.charAt(0);
        int distance = Integer.parseInt(c.substring(1));
        currentDirection = turn == 'L' ? currentDirection - 1 : currentDirection + 1;
        if(currentDirection < 0) {
            currentDirection = 3;
        } else if(currentDirection > 3) {
            currentDirection = 0;
        }
        switch(currentDirection) {
            case 0 -> vertical -= distance;
            case 1 -> horizontal += distance;
            case 2 -> vertical += distance;
            case 3 -> horizontal -= distance;
        }
    }
    return Math.abs(horizontal) + Math.abs(vertical);
}

int part2(String string) {
    int currentDirection = 0;
    int vertical = 0;
    int horizontal = 0;

    Set<List<Integer>> visited = new HashSet<>();
    visited.add(List.of(0, 0));
    List<String> data = List.of(string.split(", "));
    int hsp = 0;
    int vsp = 0;
    for(String c : data) {
        char turn = c.charAt(0);
        int distance = Integer.parseInt(c.substring(1));
        currentDirection = turn == 'L' ? currentDirection - 1 : currentDirection + 1;
        if(currentDirection < 0) {
            currentDirection = 3;
        } else if(currentDirection > 3) {
            currentDirection = 0;
        }
        hsp=0;
        vsp=0;
        switch(currentDirection) {
            case 0 -> vsp=-1;
            case 1 -> hsp=1;
            case 2 -> vsp=1;
            case 3 -> hsp=-1;
        }
        for(Integer _ : IntStream.range(0, distance).toArray()) {
            //System.out.println(STR."x: \{horizontal}, y: \{vertical}");
            vertical += vsp;
            horizontal += hsp;
            List<Integer> candidate = List.of(horizontal, vertical);
            if(visited.contains(candidate)) {
                return Math.abs(horizontal) + Math.abs(vertical);
            }
            visited.add(candidate);
        }
    }
    return Math.abs(horizontal) + Math.abs(vertical);
}