
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

void main() {
    File file = new File("input.txt");
    List<String> data = getInput(file);

    File test = new File("test1.txt");
    List<String> testData = getInput(test);

    assert part1(testData) == 1514 : "not good";
    int result1 = part1(data);
    System.out.println(result1);

    assert decipher("qzmt-zixmtkozy-ivhz", 343).equals("very encrypted name") : "not good :(";
    int result2 = part2(data);
    System.out.println(result2);

}

List<String> getInput(File file) {
    List<String> list = new ArrayList<>();
    try(Scanner scanner = new Scanner(file)) {
        while(scanner.hasNext()) {
            list.add(scanner.nextLine().strip());
        }
    } catch (FileNotFoundException _) {}
    return list;
}

int part1(List<String> data) {
    int sum = 0;
    for(String string : data) {
        int split = string.lastIndexOf('-');
        String left = string.substring(0, split);
        String right = string.substring(split+1);

        int split2 = right.indexOf('[');
        int checksum = Integer.parseInt(right.substring(0, split2));
        String verify = right.substring(split2+1, right.length()-1);
        //System.out.println(checksum);
        Map<Character, Integer> characters = new HashMap<>();
        String[] strings = left.split("-");
        //System.out.println(left);
        //System.out.println(Arrays.toString(strings));
        for(String s : strings) {
            for(Character c : s.toCharArray()) {
                if(characters.keySet().contains(c)) {
                    characters.put(c, characters.get(c)+1);
                } else {
                    characters.put(c, 1);
                }
            }
        }
        List<Character> sortedCharacters = new ArrayList<>(characters.keySet());
        Collections.sort(sortedCharacters, (c1, c2) -> {
            int con = characters.get(c2) - characters.get(c1);
            if(con == 0) {
                return c1 < c2 ? -1 : 1;
            }
            return con;
        });
        StringBuilder joined = new StringBuilder();
        for(Character c : sortedCharacters) {
            joined.append(c);
        }
        //System.out.println(STR."characters: \{characters}");
        //System.out.println(STR."correct: \{verify}\nmine: \{joined.subSequence(0, 5)}\n");
        if(joined.toString().substring(0,5).equals(verify)) {
            sum += checksum;
        }
    }
    //System.out.println(sum);
    return sum;
}

int part2(List<String> data) {
    for(String string : data) {
        int split = string.lastIndexOf('-');
        String left = string.substring(0, split);
        String right = string.substring(split+1);

        int split2 = right.indexOf('[');
        int checksum = Integer.parseInt(right.substring(0, split2));
        //String verify = right.substring(split2+1, right.length()-1);

        String deciphered = decipher(left, checksum);
        if(deciphered.equals("northpole object storage"))
            return checksum;
    }
    return 0;
}

String decipher(String code, int number) {
    number %= 26;
    char[] characters = code.toCharArray();
    for(int i=0; i<characters.length; ++i) {
        if(characters[i]=='-')
            continue;
        if('z' - characters[i] >= number) {
            characters[i] = (char)(characters[i] + number);
        } else {
            characters[i] = (char)('a' + number - ('z' - characters[i]) - 1);
        }
    }
    String result = new String(characters);
    result = result.replace('-', ' ');
    return result;
}