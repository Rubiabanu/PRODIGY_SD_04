public class SudokuSolver {

    public static void main(String[] args) {
        int[][] sudokuGrid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuSolver sudokuSolver = new SudokuSolver();
        if (sudokuSolver.solveSudoku(sudokuGrid)) {
            System.out.println("Sudoku solved successfully:");
            sudokuSolver.printSudoku(sudokuGrid);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }

    public boolean solveSudoku(int[][] grid) {
        int[] emptyCell = findEmptyCell(grid);

        if (emptyCell == null) {
            // No empty cells left, the Sudoku is solved
            return true;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        for (int num = 1; num <= 9; num++) {
            if (isValidMove(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(grid)) {
                    return true; // Recursively try to solve the rest of the puzzle
                }

                // If placing the number in the current cell doesn't lead to a solution,
                // backtrack and try a different number
                grid[row][col] = 0;
            }
        }

        return false; // No valid number found for the current empty cell
    }

    private int[] findEmptyCell(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // No empty cells left
    }

    private boolean isValidMove(int[][] grid, int row, int col, int num) {
        // Check if 'num' is not present in the current row, column, and 3x3 subgrid
        return !usedInRow(grid, row, num) &&
               !usedInColumn(grid, col, num) &&
               !usedInSubgrid(grid, row - row % 3, col - col % 3, num);
    }

    private boolean usedInRow(int[][] grid, int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInColumn(int[][] grid, int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean usedInSubgrid(int[][] grid, int startRow, int startCol, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private void printSudoku(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }
}
