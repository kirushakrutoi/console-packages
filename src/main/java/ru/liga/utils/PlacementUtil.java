package ru.liga.utils;

import java.util.List;

public class PlacementUtil {
    public static void sortPackage(List<char[][]> packages) {
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
}
