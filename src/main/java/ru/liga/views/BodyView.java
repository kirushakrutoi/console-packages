package ru.liga.views;

import ru.liga.services.PlacementService;

public class BodyView {
    private final int LENGTH_BODY;
    private final int WIDTH_BODY;

    public BodyView(int LENGTH_BODY, int WIDTH_BODY) {
        this.LENGTH_BODY = LENGTH_BODY;
        this.WIDTH_BODY = WIDTH_BODY;
    }

    public void printBody(char[][] body) {
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
}
