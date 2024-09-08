package ru.liga;

import java.io.*;
import java.util.*;

public class ConsolePackages {
    private static final int LENGTH_BODY = 6;
    private static final int WIDTH_BODY = 6;

    public static void main(String[] args) {
        try {
            List<char[][]> packages = readFile(new File(args[0]));

            sort(packages);

            List<char[][]> bodies = placementPackage(packages);

            for(char[][] body : bodies) {
                printBody(body);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not exist");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static List<char[][]> placementPackage(List<char[][]> packages) {
        List<char[][]> bodies = new ArrayList<>();
        char[][] startBody = initBody();
        bodies.add(startBody);

        for(char[][] pack : packages) {
            boolean f = true;

            for(char[][] body : bodies) {
                f = searchPlaceAndInsert(pack, body);
                if(f) break;
            }

            if(!f) {
                char[][] newBody = initBody();
                bodies.add(newBody);
                searchPlaceAndInsert(pack, newBody);
            }
        }

        return bodies;
    }

    public static boolean searchPlaceAndInsert(char[][] pack, char[][] body) {
        boolean f = true;
        for (int i = WIDTH_BODY - 1; i >= pack.length - 1; i--) {
            for (int j = 0; j < WIDTH_BODY - pack[pack.length - 1].length + 1; j++) {
                f = true;
                int k = 0;

                for (int l = 0; l < pack.length; l++) {
                    for (int m = 0; m < pack[pack.length - k - 1].length; m++) {
                        if (body[i - l][j + m] != ' ') {
                            f = false;
                            break;
                        }
                    }
                    k++;
                }

                if (f) {
                    insertPack(pack, body, i, j);
                    break;
                }
            }
            if (f) {
                break;
            }
        }

        return f;
    }

    public static void sort(List<char[][]> packages) {
        for (int i = 0; i < packages.size() - 1; i++) {
            for (int j = i + 1; j < packages.size(); j++) {
                if(packages.get(i)[0][0] < packages.get(j)[0][0]) {
                    char[][] temp = packages.get(i);
                    packages.set(i, packages.get(j));
                    packages.set(j, temp);
                }
            }
        }
    }

    public static List<char[][]> simplestPlacementPackage(List<char[][]> packages) {
        List<char[][]> bodies = new ArrayList<>();
        sort(packages);

        for (char[][] pack : packages) {
            char[][] body = initBody();

            insertPack(pack, body, LENGTH_BODY - 1, 0);

            bodies.add(body);
        }

        return bodies;
    }

    public static void insertPack(char[][] pack, char[][] body, int i, int j) {
        int k = 0;

        for (int l = 0; l < pack.length; l++) {
            for (int m = 0; m < pack[pack.length - k - 1].length; m++) {
                body[i - l][j + m] = pack[0][0];
            }
            k++;
        }
    }

    public static char[][] initBody() {
        char[][] body = new char[WIDTH_BODY][LENGTH_BODY];

        for (int i = 0; i < WIDTH_BODY; i++) {
            for (int j = 0; j < LENGTH_BODY; j++) {
                body[i][j] = ' ';
            }
        }

        return body;
    }

    public static void printBody(char[][] body) {
        for (int i = 0; i < WIDTH_BODY; i++) {
            System.out.print('+');
            for (int j = 0; j < LENGTH_BODY; j++) {
                System.out.print(body[i][j]);
            }
            System.out.print('+');
            System.out.println();
        }

        for (int j = 0; j < LENGTH_BODY + 2; j++) {
            System.out.print('+');
        }
        System.out.println("\n");
    }

    public static List<char[][]> readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<char[][]> packages = new ArrayList<>();
        String line;
        List<String> lines = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty() && !lines.isEmpty()) {
                char[][] chars = new char[lines.size()][];
                int i = 0;
                for (String s : lines) {
                    chars[i] = new char[s.length()];
                    for (int j = 0; j < s.length(); j++) {
                        chars[i][j] = s.charAt(j);
                    }
                    i++;
                }
                packages.add(chars);
                lines.clear();
            } else {
                lines.add(line);
            }
        }

        return packages;
    }
}