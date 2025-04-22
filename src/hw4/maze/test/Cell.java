package hw4.maze.test;

public class Cell {
	private CellComponents left;
    private CellComponents right;
    private CellComponents up;
    private CellComponents down;

    public Cell(CellComponents left, CellComponents right, CellComponents up, CellComponents down) {
        this.left = (left != null) ? left : CellComponents.WALL;
        this.right = (right != null) ? right : CellComponents.WALL;
        this.up = (up != null) ? up : CellComponents.WALL;
        this.down = (down != null) ? down : CellComponents.WALL;
    }

    public CellComponents getLeft() {
        return left;
    }

    public void setLeft(CellComponents left) {
        if (left != null) this.left = left;
    }

    public CellComponents getRight() {
        return right;
    }

    public void setRight(CellComponents right) {
        if (right != null) this.right = right;
    }

    public CellComponents getUp() {
        return up;
    }

    public void setUp(CellComponents up) {
        if (up != null) this.up = up;
    }

    public CellComponents getDown() {
        return down;
    }

    public void setDown(CellComponents down) {
        if (down != null) this.down = down;
    }

    @Override
    public String toString() {
        return "Cell [left=" + left + ", right=" + right + ", up=" + up + ", down=" + down + "]";
    }
}
