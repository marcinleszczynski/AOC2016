
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

void main() throws FileNotFoundException {
    File file = new File("input.txt");
    List<String> data = getInput(file);
    file = new File("test1.txt");
    List<String> testData = getInput(file);
    assert part1(testData).equals("easter");
    String result1 = part1(data);
    System.out.println(result1);
    assert part2(testData).equals("advent");
    String result2 = part2(data);
    System.out.println(result2);
}

List<String> getInput(File file) throws FileNotFoundException {
    List<String> list = new ArrayList<>();
    try(Scanner scanner = new Scanner(file)) {
        while(scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
    }
    return list;
}

String part1(List<String> data) {
    int characters = data.get(0).length();
    List<Map<Character, Integer>> list = new ArrayList<>();
    for(int _ : IntStream.range(0, characters).toArray()) {
        list.add(new HashMap<>());
    }
    for(String s : data) {
        for(int i=0; i<s.length(); ++i) {
            if(list.get(i).keySet().contains(s.charAt(i))) {
                list.get(i).put(s.charAt(i), list.get(i).get(s.charAt(i))+1);
            } else {
                list.get(i).put(s.charAt(i), 1);
            }
        }
    }
    StringBuilder sb = new StringBuilder();
    for(Map<Character, Integer> map : list) {
        List<Character> l = new ArrayList<>(map.keySet());
        char c = Collections.max(l, (c1, c2) -> map.get(c1) - map.get(c2));
        sb.append(c);
    }
    return sb.toString();
}

String part2(List<String> data) {
    int characters = data.get(0).length();
    List<Map<Character, Integer>> list = new ArrayList<>();
    for(int _ : IntStream.range(0, characters).toArray()) {
        list.add(new HashMap<>());
    }
    for(String s : data) {
        for(int i=0; i<s.length(); ++i) {
            if(list.get(i).keySet().contains(s.charAt(i))) {
                list.get(i).put(s.charAt(i), list.get(i).get(s.charAt(i))+1);
            } else {
                list.get(i).put(s.charAt(i), 1);
            }
        }
    }
    StringBuilder sb = new StringBuilder();
    for(Map<Character, Integer> map : list) {
        List<Character> l = new ArrayList<>(map.keySet());
        char c = Collections.min(l, (c1, c2) -> map.get(c1) - map.get(c2));
        sb.append(c);
    }
    return sb.toString();
}