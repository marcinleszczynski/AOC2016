import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

void main() throws FileNotFoundException {
    File file = new File("input.txt");
    List<String> data = getInput(file);
    int result1 = part1(data);
    System.out.println(result1);
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
    boolean[][] map = new boolean[6][50];
    for(String command : data) {
        String[] args = command.split(" ");
        if(args.length == 2) {
            String[] cords = args[1].split("x");
            int w = Integer.parseInt(cords[0]);
            int h = Integer.parseInt(cords[1]);
            for(int i=0; i<h; ++i) {
                for(int j=0; j<w; ++j) {
                    map[i][j] = true;
                }
            }
        } else {
            String[] cords = args[2].split("=");
            int index = Integer.parseInt(cords[1]);
            int by = Integer.parseInt(args[4]);
            if(cords[0].equals("x")) {
                for(int _ : IntStream.range(0, by).toArray()) {
                    boolean temp = map[5][index];
                    for(int i=map.length-2; i>=0; --i) {
                        map[i+1][index] = map[i][index];
                    }
                    map[0][index] = temp;
                }
            } else {
                for(int _ : IntStream.range(0, by).toArray()) {
                    boolean temp = map[index][49];
                    for(int j=map[0].length-2; j>=0; --j) {
                        map[index][j+1] = map[index][j];
                    }
                    map[index][0] = temp;
                }
            }
        }
    }
    int count = 0;
    for(boolean[] arr : map) {
        for(boolean b : arr) {
            if(b) {
                ++count;
                System.out.print("#");
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    return count;
}