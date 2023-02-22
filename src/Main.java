/**
 * Here, the logic is to fill the board such that all the rows are filled first
 * When a number is placed we check if placing the number will violate rules of the game.
 * If so, we go back and keep changing the number till we find the number that wont.
 * Placing a number checking rules and going back till we find the number is where backtracking occurs.
 * */


class SudokuSolution {

    public static void solveSudoku(char[][] board) {
        driver(board, 0, 0);
    }

    public static boolean driver(char[][] board, int row, int col) {
        //if at last row return true
        if(row == board.length) {
            return true;
        }
        //as name implies save next row and column
        int nextRow = 0;
        int nextCol = 0;

        //used to move to next row if at last column
        if(col == board.length-1) {
            nextRow = row + 1;
            nextCol = 0;
        }
        //used to move to next column till we reach end
        else {
            nextRow = row;
            nextCol = col + 1;
        }
        //if board not empty we move to next row, column
        if(board[row][col] != '.') {
            if(driver(board, nextRow, nextCol)) {
                return true;
            }
        } else {
            //after moving to next column we check if the board is empty
            //if so, we try to fill it
            for(int i=1; i<=9; i++) {
                //since we are trying to fill the board, we must check if it's okay to place a number on board
                if(isSafeToPlace(board, row, col, i)) {
                    board[row][col] = Character.forDigit(i, 10);
                    //if isSafeToPlace returns true we move to next step -> i.e. try to fill next spot
                    if(driver(board, nextRow, nextCol))
                        return true;
                    //else, we don't fill the spot
                    //notice, here back tacking is happening. We move back. Fill the spot with next number and then
                    //check if it's safe to place number and keep repeating till we find the number.
                    else
                        board[row][col] = '.';
                }
            }
        }

        return false;
    }
    //check if the number Placed in a given row and column follows the rules
    public static boolean isSafeToPlace(char[][] board, int row, int col, int numberPlaced) {
        //here we check if column has same Char as the one we have placed in the cell -> if so, return false
        for(int i=0; i<board.length; i++) {
            if(board[i][col] == Character.forDigit(numberPlaced, 10)) {
                return false;
            }
        }
        //here we check if row has same Char as the one we have placed in the cell  -> if so, return false
        for(int j=0; j<board.length; j++) {
            if(board[row][j] == Character.forDigit(numberPlaced, 10)) {
                return false;
            }
        }
        //here we check if row has same Char as the one we have placed in the cell  -> if so, return false
        //row/3 gives quotient and multiplying it with three gives the starting of grid.
        //e.g. for board[2][2] -> startRow = 0 & startCol= 0 which is true
        //board[5][5] -> startRow = 3 & startCol= 3 which is true
        int startRow = 3 * (row/3);
        int startCol = 3 * (col/3);

        //since grid has 3 rows and colum we only iterate 3 steps
        for(int i = startRow; i< startRow +3; i++) {
            for(int j = startCol; j< startCol +3; j++) {
                //again if match return false
                if(board[i][j] == Character.forDigit(numberPlaced, 10)) {
                    return false;
                }
            }
        }
        // will execute if no. in grid does not match any other numberPlaced -> implies its safe to place numberPlaced
        return true;
    }
    public static void main(String args[]){
        //a 9*9 board given as input in this case -> It was copied from some online program
        char board [] [] = {
                {'.','3','.','.','7','.','.','.','.',},
                {'.','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'.','.','.','8','.','3','.','.','.'},
                {'7','.','.','.','.','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','.','.','.','5'},
                {'3','.','.','.','8','6','.','.','9'}
        };

        //call to the solver
        solveSudoku(board);
        //print the completed board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }


}
