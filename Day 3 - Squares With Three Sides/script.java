import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

void main() {
    File file = new File("input.txt");
    List<List<Integer>> data = getInput(file);
    
    int count = part1(data);
    System.out.println(count);

    count = part2(data);
    System.out.println(count);

}
List<List<Integer>> getInput(File file) {
    List<List<Integer>> triangles = new ArrayList<>();
    try(Scanner scanneer = new Scanner(file)) {
        while(scanneer.hasNext()) {
            String[] strings = scanneer
                .nextLine()
                .strip()
                .replaceAll("\\s+", " ")
                .split(" ");
            List<Integer> triangle = Arrays.stream(strings).map(Integer::parseInt).toList();
            triangles.add(triangle);
        }
    } catch (FileNotFoundException ex) {}
    return triangles;
}

int part1(List<List<Integer>> data) {
    List<List<Integer>> filtered = data
        .stream()
        .filter(d -> {
            boolean con1 = d.get(0) + d.get(1) > d.get(2);
            boolean con2 = d.get(1) + d.get(2) > d.get(0);
            boolean con3 = d.get(0) + d.get(2) > d.get(1);
            return con1 && con2 && con3;
        })
        .toList();
    return filtered.size();
}

int part2(List<List<Integer>> data) {
    int count = 0;
    for(int i=0; i<3; ++i) {
        for(int j=0; j<data.size(); j += 3) {

            boolean con1 = data.get(j).get(i) + data.get(j+1).get(i) > data.get(j+2).get(i);
            boolean con2 = data.get(j).get(i) + data.get(j+2).get(i) > data.get(j+1).get(i);
            boolean con3 = data.get(j+1).get(i) + data.get(j+2).get(i) > data.get(j).get(i);
            if(con1 && con2 && con3) {
                ++count;
            }

        }
    }
    return count;
}