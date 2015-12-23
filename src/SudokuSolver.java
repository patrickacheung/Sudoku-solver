/**
 * A simple command line sudoku solver using recursive backtracking
 *
 * @author Patrick Cheung
 * @version 1.0
 * Created by patch on 2015-12-20.
 */
class SudokuSolver {
    //sudoku grid size of NxN
    private static final int N = 9;

    //empty array for unsolvable puzzles
    private static final int[] EMPTY_ARRAY = {};

    /* function for solving sudoku grid */
    private static boolean solveSudoku(int grid[][]){
        return true;
    }

    /* function for printing grid */
    private static void printGrid(int grid[][]){
        for(int i = 0; i < N; ++i){
            for(int j = 0; j < N; ++j){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }// end printGrind

    public static void main(String[] args){
        //0 means empty cell
        int grid[][] = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        if(solveSudoku(grid) == true) // return an empty array if unsolvable...
            printGrid(grid);
        else
            System.out.println("No Solution Exists.");
    }// end main
}// end SudokuSolver
