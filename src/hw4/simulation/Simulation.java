package hw4.simulation;

import hw4.maze.*;
import hw4.player.*;
import hw4.game.*;
import java.util.Scanner;

public class Simulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int gridSize = 0;

        // Get valid grid size from user
        while (gridSize < 3 || gridSize > 7) {
            System.out.print("Enter grid size (3-7): ");
            gridSize = scanner.nextInt();
        }

        Game game = new Game(gridSize);
        Grid grid = game.createRandomGrid(gridSize);
        Player player = new Player(
            grid.getRows().get(gridSize - 1),
            grid.getRows().get(gridSize - 1).getCells().get(gridSize - 1)
        );

        System.out.println("Initial Grid:");
        printGrid(grid, player);

        while (true) {
            System.out.print("Move (W=Up, S=Down, A=Left, D=Right): ");
            String move = scanner.next().toUpperCase();
            Movement direction;

            switch (move) {
                case "W":
                    direction = Movement.UP;
                    break;
                case "S":
                    direction = Movement.DOWN;
                    break;
                case "A":
                    direction = Movement.LEFT;
                    break;
                case "D":
                    direction = Movement.RIGHT;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
                    continue;
            }

            boolean escaped = game.play(direction, player);
            printGrid(grid, player);

            if (escaped) {
                System.out.println("\nCongratulations! You escaped the maze.");
                break;
            }
        }
        scanner.close();
    }

    /**
     * Prints the current state of the grid with agent position.
     */
    private static void printGrid(Grid grid, Player player) {
        for (int i = 0; i < grid.getRows().size(); i++) {
            Row row = grid.getRows().get(i);
            for (int j = 0; j < row.getCells().size(); j++) {
                Cell cell = row.getCells().get(j);
                if (player.getCurrentRow() == row && player.getCurrentCell() == cell) {
                    System.out.print("A ");
                } else if (cell.getLeft() == CellComponents.EXIT && j == 0) {
                    System.out.print("E ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }
}
