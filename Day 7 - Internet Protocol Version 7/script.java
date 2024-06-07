import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

void main() throws FileNotFoundException {
    File file = new File("input.txt");
    List<String> data = getInput(file);
    file = new File("test1.txt");
    List<String> testData1 = getInput(file);
    // assert part1(testData1) == 2;
    // int result1 = part1(data);
    // System.out.println(result1);
    file = new File("test2.txt");
    List<String> testData2 = getInput(file);
    assert part2(testData2) == 3;
    int result2 = part2(data);
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

int part1(List<String> data) {
    int count = 0;
    for(String s : data) {
        char[] arr = s.toCharArray();
        boolean in = false;
        boolean supports = false;
        for(int i=0; i<s.length()-1; ++i) {
            if(arr[i] == '[') {
                in = true;
            } else if(arr[i] == ']') {
                in = false;
            } else if(i > 1) {
                if(arr[i]==arr[i-1] && arr[i-2]==arr[i+1] && arr[i]!=arr[i+1]) {
                    if(in) {
                        supports = false;
                        break;
                    } else {
                        supports = true;
                    }
                }
            }
        }
        if(supports) {
            ++count;
        }
    }
    return count;
}

int part2(List<String> data) {
    int count = 0;
    for(String s : data) {
        char[] arr = s.toCharArray();
        Set<String> outer = new HashSet<>();
        Set<String> inner = new HashSet<>();
        boolean in = false;
        boolean supports = false;
        for(int i=0; i<s.length()-1; ++i) {
            if(arr[i] == '[') {
                in = true;
            } else if(arr[i] == ']') {
                in = false;
            } else if(i > 0) {
                if(arr[i-1]==arr[i+1] && arr[i-1]!=arr[i]) {
                    String inverted = STR."\{arr[i]}\{arr[i-1]}\{arr[i]}";
                    if(in) {
                        if(outer.contains(inverted)) {
                            ++count;
                            break;
                        } else {
                            inner.add(STR."\{arr[i-1]}\{arr[i]}\{arr[i+1]}");
                        }
                    } else {
                        if(inner.contains(inverted)) {
                            ++count;
                            break;
                        } else {
                            outer.add(STR."\{arr[i-1]}\{arr[i]}\{arr[i+1]}");
                        }
                    }
                }
            }
        }
    }
    return count;
}