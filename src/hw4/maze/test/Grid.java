package hw4.maze.test;

import java.util.ArrayList;

public class Grid {

    private ArrayList<Row> rows;

    // Constructor
    public Grid(ArrayList<Row> rows) {
        this.rows = rows;
    }

    // Getter
    public ArrayList<Row> getRows() {
        return rows;
    }

    // Setter
    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Grid [rows=" + rows + "]";
    }
}
