/**
 * A simple command line sudoku solver using recursive backtracking
 *
 * @author Patrick Cheung
 * @version 1.1
 * Created by patch on 2015-12-20.
 */
class SudokuSolver {
    //sudoku grid size of NxN
    private final static int N = 9;

    //0 means empty cell
    private final static int E = 0;

    /**
     * Checks if number inserted appears twice in its 3x3 subgrid
     * @param grid - sudoku array to be evaluated
     * @param num - number to be inserted and evaluated
     * @param row - row where number will be inserted
     * @param col - col where number will be iserted
     * @return true if number doesn't appear twice in its 3x3 subgrid and false if it does
     */
    private static boolean checkSubgrid(int[][] grid, int num, int row, int col){
        //mod 3 to determine the (0,0) coordinate of the subgrid
        int xCoor = row - (row % 3);
        int yCoor = col - (col % 3);

        for(int i = 0; i < 3; ++i)
            for(int j = 0; j < 3; ++j)
                if(grid[i + xCoor][j + yCoor] == num)
                    return false;
        return true;
    }// end checkSubgrid

    /**
     * Checks if number inserted appears twice in the same column
     * @param grid - sudoku array to be evaluated
     * @param num - number to be inserted and evaluated
     * @param col - col where number will be inserted
     * @return true if number doesn't appear twice in the same column and false if it does
     */
    private static boolean checkCol(int[][] grid, int num, int col){
        for(int i = 0; i < N; ++i)
            if(grid[i][col] == num)
                return false;
        return true;
    }// end checkCol

    /**
     * Checks if number inserted appears twice in the same row
     * @param grid - sudoku array to be evaluated
     * @param num - number to be inserted and evaluated
     * @param row - row where number will be inserted
     * @return true if number doesn't appear twice in the same row and false if it does
     */
    private static boolean checkRow(int[][] grid, int num, int row){
        for(int i = 0; i < N; ++i)
            if (grid[row][i] == num)
                return false;
        return true;
    }// end checkRow

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
        return checkRow(grid, num, row) && checkCol(grid, num, col) && checkSubgrid(grid, num, row, col);
    }// end isValid

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
        Row r = new Row();
        Col c = new Col();

        //no empty cells mean the puzzle is already solved
        if(!findEmptyCell(grid,r, c))
            return true; //solved

        //cycle through possible digits
        for(int tryNum = 1; tryNum <= N; ++tryNum){
            if(isValid(grid, tryNum, r.rowIndex, c.colIndex)){
                grid[r.rowIndex][c.colIndex] = tryNum;

                //if current tryNum works, try to recursively fill the rest
                if(solveSudoku(grid))
                    return true;

                //if it fails, rollback and set all previous filled to empty
                grid[r.rowIndex][c.colIndex] = E;
            }
        }
        return false; //triggers backtracking (try next possible digit that would be valid)
    }// end solveSudoku

    /**
     * Verifies that there are no misinputs by checking there are exactly the same number of each digit
     * @param grid - sudoku puzzle to be evaluated
     * @return true if there are exactly the same number of each digit and false if there are different
     *          numbers of each digit
     */
    private static boolean verify(int[][] grid){
        int count = 9;
        int[] countDigits = new int[9];

        //count number each digit appears in sudoku puzzle
        for(int i = 0; i < N; ++i)
            for(int j = 0; j < N; ++j)
                countDigits[grid[i][j] - 1] += 1;

        //check if each digit appears exactly nine times
        for(int i : countDigits)
            if(i != count)
                return false;
        return true;
    }// end verify

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
        int[][] grid2 = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        int[][] grid = {{8, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 3, 6, 0, 0, 0, 0, 0},
                        {0, 7, 0, 0, 9, 0, 2, 0, 0},
                        {0, 5, 0, 0, 0, 7, 0, 0, 0},
                        {0, 0, 0, 0, 4, 5, 7, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 3, 0},
                        {0, 0, 1, 0, 0, 0, 0, 6, 8},
                        {0, 0, 8, 5, 0, 0, 0, 1, 0},
                        {0, 9, 0, 0, 0, 0, 4, 0, 0}};

        if(solveSudoku(grid) && verify(grid))
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
