package ru.liga;

import java.io.*;
import java.util.*;

public class ConsolePackages {
    private static final int LENGTH_BODY = 6;
    private static final int WIDTH_BODY = 6;

    private static final Map<Integer, String> ships = new HashMap<>();

    static {
        ships.put(1, "1 1");
        ships.put(2, "1 2");
        ships.put(3, "1 3");
        ships.put(4, "1 4");
        ships.put(5, "1 5");
        ships.put(6, "2 3 3");
        ships.put(7, "2 3 4");
        ships.put(8, "2 4 4");
        ships.put(9, "3 3 3 3");
    }

    public static void main( String[] args ) {
        try {
            Map<Integer, Integer> packages = readFile(new File(args[0]));
            System.out.println(packages);
            printBody(initBody());
            List<Character[][]> a = simplestPlacementPackage(packages);
            for(Character[][] characters : a){
                printBody(characters);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not exist");
        } catch (IOException e){
            System.out.println("Error");
        }
    }

    public static List<Character[][]> simplestPlacementPackage(Map<Integer, Integer> packages) {
        List<Character[][]> bodies = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : packages.entrySet()) {
            int count = entry.getValue();
            int figure = entry.getKey();
            String[] strings = ships.get(figure).split(" ");

            for (int i = 0; i < count; i++) {
                Character[][] body = initBody();
                int drawingLines = 0;
                int countLines= Integer.parseInt(strings[0]);

                for (int j = WIDTH_BODY - 1; j > WIDTH_BODY - 1 - countLines; j--) {
                    for (int k = 1; k < 1 + Integer.parseInt(strings[strings.length - 1 - drawingLines]); k++) {
                        body[j][k] = (char) (figure + 48);
                    }

                    drawingLines++;
                }

                bodies.add(body);
            }
        }

        return bodies;
    }

    public static Character[][] initBody() {
        Character[][] body = new Character[WIDTH_BODY + 1][LENGTH_BODY + 2];

        for (int i = 0; i < WIDTH_BODY + 1; i++) {
            body[i][0] = '+';
            body[i][LENGTH_BODY + 2 - 1] = '+';
        }

        for (int i = 0; i < LENGTH_BODY + 1; i++) {
            body[WIDTH_BODY + 1 - 1][i] = '+';
        }

        for (int i = 0; i < WIDTH_BODY; i++) {
            for (int j = 1; j < LENGTH_BODY + 1; j++) {
                body[i][j] = ' ';
            }
        }

        return body;
    }

    public static void printBody(Character[][] body) {
        for (int i = 0; i < WIDTH_BODY + 1; i++) {
            for (int j = 0; j < LENGTH_BODY + 2; j++) {
                System.out.print(body[i][j]);
            }
            System.out.println();
        }
    }

    public static Map<Integer, Integer> readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Map<Integer, Integer> packages = new HashMap<>();
        String line;

        while((line = reader.readLine()) != null){
            if(!line.isEmpty()) {
                int x = line.charAt(0) - 48;
                if (packages.containsKey(x))
                    packages.put(x, packages.get(x) + 1);
                else packages.put(x, 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : packages.entrySet()) {
            int key = entry.getKey();

            if(key == 6 || key == 7 || key == 8) {
                entry.setValue(entry.getValue() / 2);
            }

            if(key == 9) {
                entry.setValue(entry.getValue() / 3);
            }
        }

        return packages;
    }
}
/*    //private static final char[][] body = new char[WIDTH_BODY + 1][LENGTH_BODY + 2];

    static {
        for (int i = 0; i < WIDTH_BODY + 1; i++) {
            body[i][0] = '+';
            body[i][LENGTH_BODY + 2 - 1] = '+';
        }

        for (int i = 0; i < LENGTH_BODY + 1; i++) {
            body[WIDTH_BODY + 1 - 1][i] = '+';
        }
    }*/        /*ships.add(new Integer[][]{new Integer[]{1}});
        ships.add(new Integer[][]{new Integer[]{2,2}});
        ships.add(new Integer[][]{new Integer[]{3,3,3}});
        ships.add(new Integer[][]{new Integer[]{4,4,4,4}});
        ships.add(new Integer[][]{new Integer[]{5,5,5,5,5}});
        ships.add(new Integer[][]{new Integer[]{6,6,6}, new Integer[]{6,6,6}});
        ships.add(new Integer[][]{new Integer[]{7,7,7}, new Integer[]{7,7,7,7}});
        ships.add(new Integer[][]{new Integer[]{8,8,8,8}, new Integer[]{8,8,8,8}});
        ships.add(new Integer[][]{new Integer[]{9,9,9}, new Integer[]{9,9,9}, new Integer[]{9,9,9}});*/


//private static final List<Integer[][]> ships = new ArrayList<>();