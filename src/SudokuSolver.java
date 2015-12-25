/**
 * A simple command line sudoku solver using recursive backtracking
 *
 * @author Patrick Cheung
 * @version 1.0
 * Created by patch on 2015-12-20.
 */
class SudokuSolver {
    //sudoku grid size of NxN
    private final static int N = 9;

    //0 means empty cell
    private final static int E = 0;

    /**
     * Checks if number inserted does not appear twice in the same row, column, and subgrid according to standard
     * sudoku rules.
     * @param grid - sudoku array
     * @param num - number to be inserted
     * @param row - row where number will be inserted
     * @param col - column where number will be inserted
     * @return true if number does not appear twice in same row, column, and subgrid and false if it appears twice
     */
    private static boolean isValid(int[][] grid, int num, int row, int col){
        return checkRow() && checkCol() && checkSubgrid();
    }

    /**
     * Searches the grid for an empty cell and updates the static row and col variables and true is returned.
     * If no empty cell exits, false is returned.
     * @param grid - sudoku grid to be evaluated
     * @param row - row index in 2D array
     * @param col - col index in 2D array
     * @return true if an empty cell is found and false if there are no empty cells
     */
    private static boolean findEmptyCell(int[][] grid, Row row, Col col){
        //noinspection ConstantConditions
        for(row.rowIndex = 0; row.rowIndex < N; ++row.rowIndex)
            //noinspection ConstantConditions
            for (col.colIndex = 0; col.colIndex < N; ++col.colIndex)
                if (grid[row.rowIndex][col.colIndex] == E)
                    return true;
        return false;
    }// end findEmptyCell

    /**
     * Attempts to solve sudoku grid by finding an empty cell, tries a digit from 1-9 that is valid and attempts to
     * complete the rest of the grid recursively. Reverts changes if that digit is invalid and continuously cycles
     * through the digits until 1-9 fails for that cell.
     * @param grid - the array to be solved
     * @return true if puzzle is solvable and false if it is not
     */
    private static boolean solveSudoku(int[][] grid){
        //clone the grid, do recursive work on the clone
        int[][] temp = grid.clone();

        //no empty cells mean the puzzle is already solved
        if(!findEmptyCell(temp, new Row(), new Col()))
            return true; //solved

        /*for(){

        }*/

        return false; //triggers backtracking
    }// end solveSudoku

    /* function for printing grid */
    private static void printSolution(int[][] grid){
        for(int i = 0; i < N; ++i){
            for(int j = 0; j < N; ++j) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }// end printSolution

    public static void main(String[] args){
        //0 means empty cell
        int[][] grid = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        if(solveSudoku(grid))
            printSolution(grid);
        else
            System.out.println("No Solution Exists.");
    }// end main
}// end SudokuSolver

/**
 * Row of sudoku grid for the sake of encapsulation
 */
class Row {
    public int rowIndex;
}// end Row

/**
 * Col of sudoku grid for the sake of encapsulation
 */
class Col {
    public int colIndex;
}// end Col
