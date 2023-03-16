import java.util.Arrays;
import java.util.Random;

public class BoardMaker {

    int[][] Board;
    int[][] PomBoard;
    Random random;
    BoardSolver boardSolver;

    public BoardMaker() {
        boardSolver = new BoardSolver();
        Board = new int[9][9];
        PomBoard = new int[9][9];
        this.random = new Random();

        for(int i = 0;i<9;i++)
            for(int j = 0;j<9;j++) {
                PomBoard[i][j] = 0;
                Board[i][j] = 0;
            }
    }

    public int[][] fillBoard(int[][] PomBoard) {
        int num;
        boolean check;
        for (int square = 0; square <= 8; square += 4) {

            for (int i = (square / 3) * 3; i < ((square / 3) * 3) + 3; i++) {
                for (int j = (square % 3) * 3; j < ((square % 3) * 3) + 3; j++) {
                    check = false;
                    while (check == false) {
                        num = random.nextInt(9) + 1;
                        PomBoard[i][j] = num;
                        if (BoardSolver.validSquare(PomBoard)) {
                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        if(boardSolver.Solve(PomBoard,0,0)) {
            /*for(int[] row:PomBoard)
                for(int i : row) System.out.print(i + " ");
                System.out.println();*/
            return PomBoard;
        }
        else {
            /*for(int[] row:PomBoard)
                for(int i : row) System.out.print(i + " ");
                System.out.println();*/
            return PomBoard;
        }
    }
    public int[][] MakeBoard(String difficulty){
        int removeCells;
        int x;
        int y;

        if(difficulty=="EASY"){
            removeCells = 39;
            while(removeCells>=0){
                x = random.nextInt(9);
                y = random.nextInt(9);
                if(Board[x][y] == 0) {
                    Board[x][y] = PomBoard[x][y];
                    removeCells--;
                }
            }
            return Board;
        }
        else if(difficulty=="MEDIUM"){
            removeCells = 30;
            while(removeCells>=0){
                x = random.nextInt(9);
                y = random.nextInt(9);
                if(Board[x][y] == 0) {
                    Board[x][y] = PomBoard[x][y];
                    removeCells--;
                }
            }
            return Board;

        } else if(difficulty=="HARD"){
            removeCells = 23;
            while(removeCells>=0){
                x = random.nextInt(9);
                y = random.nextInt(9);
                if(Board[x][y] == 0) {
                    Board[x][y] = PomBoard[x][y];
                    removeCells--;
                }
            }
            return Board;
        } else return null;

    }

    public int[][] getBoard() {
        return Board;
    }

    public int[][] getPomBoard() {
        return PomBoard;
    }
    /*private int[][] board = new int[9][9];
    private int numOfCells;
    BoardChecker boardChecker;
    public Random random = new Random();
    private int[][] pointsOnBoard;

    public BoardMaker() {
        boardChecker = new BoardChecker();
        this.pointsOnBoard = new int[9][9];
        for(int i = 0;i<9;i++)
            for(int j = 0;j<9;j++)
                pointsOnBoard[i][j] = 0;
    }

    public int[][] makeBoard(String difficulty){
        int x;
        int y;
        boolean check;
        int num;
        if(difficulty=="easy"){
            numOfCells = 39;
            while(numOfCells>0){

                check = false;
                x = random.nextInt(9);
                y = random.nextInt(9);
                //System.out.println(x + " "+ y);


                if (pointsOnBoard[x][y] == 0){
                    while(!check){
                        num = random.nextInt(8)+1;
                        if(boardChecker.assumption(pointsOnBoard,x,y,num)){
                            pointsOnBoard[x][y] = num;
                            check = true;
                            numOfCells--;
                        }
                    }
                }
            }
            return pointsOnBoard;
        } else if(difficulty=="medium"){
            numOfCells = 30;
            while(numOfCells>0){

                check = false;
                x = random.nextInt(9);
                y = random.nextInt(9);


                if (pointsOnBoard[x][y] == 0){
                    while(!check){
                        num = random.nextInt(8)+1;
                        if(boardChecker.assumption(pointsOnBoard,x,y,num)){
                            pointsOnBoard[x][y] = num;
                            check = true;
                            numOfCells--;
                        }
                    }
                }
            }
            return pointsOnBoard;
        } else {
            numOfCells = 23;
            while(numOfCells>0){

                check = false;
                x = random.nextInt(9);
                y = random.nextInt(9);


                if (pointsOnBoard[x][y] == 0){
                    while(!check){
                        num = random.nextInt(8)+1;
                        if(boardChecker.assumption(pointsOnBoard,x,y,num)){
                            pointsOnBoard[x][y] = num;
                            check = true;
                            numOfCells--;
                        }
                    }
                }
            }
            return pointsOnBoard;
        }
    }*/
}

class BoardSolver {

    public boolean Solve(int[][] sudoku, int x, int y) {
        if (x == 9) {
            x = 0;
            y++;
            if (y == 9) return true;
        }
        if (sudoku[x][y] != 0) return Solve(sudoku, x + 1, y);
        for (int i = 1; i <= 9; i++) {
            if (assumption(sudoku, x, y, i)) {
                sudoku[x][y] = i;
                if (Solve(sudoku, x + 1, y)) return true;
            }
        }
        sudoku[x][y] = 0;
        return false;
    }

    public boolean assumption(int[][] sudoku, int x, int y, int num) {

        int tmp = sudoku[x][y];
        sudoku[x][y] = num;
        boolean result = validRow(sudoku) && validColumn(sudoku) && validSquare(sudoku);
        sudoku[x][y] = tmp;
        return result;
    }

    public static boolean validRow(int[][] sudoku) {
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                nums[j] = sudoku[i][j];
            }
            Arrays.sort(nums);
            for (int j = 0; j < 8; j++)
                if (nums[j] != 0 && nums[j] == nums[j + 1]) return false;
            nums = new int[9];
        }
        return true;
    }

    public static boolean validColumn(int[][] sudoku) {
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                nums[j] = sudoku[j][i];
            }
            Arrays.sort(nums);
            for (int j = 0; j < 8; j++)
                if (nums[j] != 0 && nums[j] == nums[j + 1]) return false;
            nums = new int[9];
        }
        return true;
    }

    public static boolean validSquare(int[][] sudoku) {
        int counter = 0;
        int[] nums = new int[9];

        for (int square = 0; square <= 8; square++) {
            for (int i = (square / 3) * 3; i < ((square / 3) * 3) + 3; i++) {
                for (int j = (square % 3) * 3; j < ((square % 3) * 3) + 3; j++)
                    nums[counter++] = sudoku[i][j];
            }
            counter = 0;
            Arrays.sort(nums);
            for (int m = 0; m < 8; m++)
                if (nums[m] != 0 && nums[m] == nums[m + 1]) return false;
            nums = new int[9];
        }
        return true;
    }
}
