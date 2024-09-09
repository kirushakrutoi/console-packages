package ru.liga;

import org.junit.jupiter.api.Test;
import ru.liga.services.PlacementService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PlacementServiceTest {

    private final PlacementService placementService = new PlacementService(6,6);

    @Test
    void emptyList() {
        List<char[][]> packages = new ArrayList<>();

        List<char[][]> bodies = placementService.placementPackage(packages);

        assertEquals(1, bodies.size());
        assertEquals(6, bodies.get(0).length);
        assertArrayEquals(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '}}, bodies.get(0));

    }

    @Test
    void singlePackage() {
        char[][] package1 = {{'4', '4', '4', '4'}};
        List<char[][]> packages = new ArrayList<>();
        packages.add(package1);

        List<char[][]> bodies = placementService.placementPackage(packages);


        assertEquals(1, bodies.size());
        assertArrayEquals(new char[][] {
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {'4', '4', '4', '4', ' ', ' '}}, bodies.get(0));
    }

    @Test
    void multiplePackagesFitInOneBody() {
        char[][] package1 = {{'4', '4', '4', '4'}};
        char[][] package2 = {{'6', '6', '6'}, {'6', '6', '6'}};
        char[][] package3 = {{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}};
        char[][] package4 = {{'1'}};
        char[][] package5 = {{'2', '2'}};
        char[][] package6 = {{'3', '3', '3'}};
        List<char[][]> packages = new ArrayList<>();
        packages.add(package1);
        packages.add(package2);
        packages.add(package3);
        packages.add(package4);
        packages.add(package5);
        packages.add(package6);

        List<char[][]> bodies = placementService.placementPackage(packages);

        assertEquals(1, bodies.size());
        assertArrayEquals(new char[][] {
                {' ', ' ', ' ', ' ', ' ', ' '},
                {'1', ' ', ' ', ' ', ' ', ' '},
                {'4', '4', '4', '4', '2', '2'},
                {'9', '9', '9', '3', '3', '3'},
                {'9', '9', '9', '6', '6', '6'},
                {'9', '9', '9', '6', '6', '6'}}, bodies.get(0));
    }

    @Test
    void multipleBodies() {
        char[][] package1 = {{'4', '4', '4', '4'}};
        char[][] package2 = {{'6', '6', '6'}, {'6', '6', '6'}};
        char[][] package3 = {{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}};
        char[][] package4 = {{'1'}};
        char[][] package5 = {{'2', '2'}};
        char[][] package6 = {{'3', '3', '3'}};
        char[][] package7 = {{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}};
        char[][] package8 = {{'3', '3', '3'}};
        char[][] package9 = {{'7', '7', '7'}, {'7', '7', '7', '7'}};
        List<char[][]> packages = new ArrayList<>();
        packages.add(package1);
        packages.add(package2);
        packages.add(package3);
        packages.add(package4);
        packages.add(package5);
        packages.add(package6);
        packages.add(package7);
        packages.add(package8);
        packages.add(package9);

        List<char[][]> bodies = placementService.placementPackage(packages);

        assertEquals(2, bodies.size());
        assertArrayEquals(new char[][] {
                {'3', '3', '3', '6', '6', '6'},
                {'7', '7', '7', '6', '6', '6'},
                {'7', '7', '7', '7', '2', '2'},
                {'9', '9', '9', '9', '9', '9'},
                {'9', '9', '9', '9', '9', '9'},
                {'9', '9', '9', '9', '9', '9'}}, bodies.get(0));
        assertArrayEquals(new char[][] {
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {'3', '3', '3', ' ', ' ', ' '},
                {'4', '4', '4', '4', '1', ' '}}, bodies.get(1));
    }


}
