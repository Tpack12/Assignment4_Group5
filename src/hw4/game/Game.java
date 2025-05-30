package hw4.game;

import hw4.maze.*;
import hw4.player.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * Main game logic for Tears, Despair & Debugging.
 */
public class Game {
    private final int gridSize;
    private Grid grid;
    private final Random rand = new Random();

    public Game(int gridSize) {
        this.gridSize = gridSize;
    }

    public Game(Grid grid) {
        this.grid = grid;
        this.gridSize = grid.getRows().size();
    }

    public Grid createRandomGrid(int size) {
        if (size < 3 || size > 7) return null;

        ArrayList<Row> rows = new ArrayList<>();
        int exitRow = rand.nextInt(size);

        for (int i = 0; i < size; i++) {
            ArrayList<Cell> cells = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                CellComponents left = randComponent();
                CellComponents right = randComponent();
                CellComponents up = randComponent();
                CellComponents down = randComponent();

                if (left != CellComponents.APERTURE && right != CellComponents.APERTURE &&
                    up != CellComponents.APERTURE && down != CellComponents.APERTURE) {
                    switch (rand.nextInt(4)) {
                        case 0 -> left = CellComponents.APERTURE;
                        case 1 -> right = CellComponents.APERTURE;
                        case 2 -> up = CellComponents.APERTURE;
                        case 3 -> down = CellComponents.APERTURE;
                    }
                }

                if (j == 0 && i == exitRow) {
                    left = CellComponents.EXIT;
                } else if (j == 0) {
                    left = CellComponents.WALL;
                }

                cells.add(new Cell(left, right, up, down));
            }
            rows.add(new Row(cells));
        }

        grid = new Grid(rows);
        return grid;
    }

    public boolean play(Movement move, Player player) {
        if (move == null || player == null) return false;

        Row currentRow = player.getCurrentRow();
        Cell currentCell = player.getCurrentCell();
        ArrayList<Row> rows = grid.getRows();
        int rowIndex = rows.indexOf(currentRow);
        int colIndex = currentRow.getCells().indexOf(currentCell);

        int newRow = rowIndex;
        int newCol = colIndex;

        switch (move) {
            case UP -> {
                if (currentCell.getUp() == CellComponents.APERTURE && rowIndex > 0) {
                    System.out.println("Moving UP");
                    newRow--;
                } else {
                    System.out.println("Blocked UP");
                    return false;
                }
            }
            case DOWN -> {
                if (currentCell.getDown() == CellComponents.APERTURE && rowIndex < gridSize - 1) {
                    System.out.println("Moving DOWN");
                    newRow++;
                } else {
                    System.out.println("Blocked DOWN");
                    return false;
                }
            }
            case LEFT -> {
                if (currentCell.getLeft() == CellComponents.EXIT && colIndex == 0) {
                    System.out.println("Escaped LEFT!");
                    return true;
                } else if (currentCell.getLeft() == CellComponents.APERTURE && colIndex > 0) {
                    System.out.println("Moving LEFT");
                    newCol--;
                } else {
                    System.out.println("Blocked LEFT");
                    return false;
                }
            }
            case RIGHT -> {
                if (currentCell.getRight() == CellComponents.APERTURE && colIndex < gridSize - 1) {
                    System.out.println("Moving RIGHT");
                    newCol++;
                } else {
                    System.out.println("Blocked RIGHT");
                    return false;
                }
            }
        }

        player.setCurrentRow(rows.get(newRow));
        player.setCurrentCell(rows.get(newRow).getCells().get(newCol));
        return false;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private CellComponents randComponent() {
        return switch (rand.nextInt(3)) {
            case 0 -> CellComponents.WALL;
            case 1 -> CellComponents.APERTURE;
            case 2 -> CellComponents.APERTURE;
            default -> CellComponents.WALL;
        };
    }
}
