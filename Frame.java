import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Frame extends JFrame implements ActionListener{

    JFrame frame;
    JLabel label,FunctionLabel,NumsLabel;
    JTextField NotesTextField;
    JPanel NotesPanel;
    String Notes;
    JButton erase,notes,hint,num,buttonPom;
    JLabel Mistakes,HintsRemained;

    JButton button,newGame;
    ArrayList<JButton> buttons;
    ArrayList<JButton> numsCalculator;
    private boolean selected = false;
    SudokuBoard sudokuBoard;
    int mistakes = 0,hints = 3;
    int[] tab;

    public Frame(String difficulty) {

        sudokuBoard = new SudokuBoard(difficulty);
        int[][] grid = sudokuBoard.getGrid();
//        sudokuBoard.print(grid);
        NotesTextField = new JTextField();
        tab = new int[81];
        buttonPom = new JButton();
        Mistakes = new JLabel("Mistakes: " + String.valueOf(mistakes) + "/3");Mistakes.setFont(new Font("Serif", Font.BOLD, 20));
        HintsRemained = new JLabel("Hints remained: " + String.valueOf(hints)); HintsRemained.setFont(new Font("Serif", Font.BOLD, 20));
        buttons = new ArrayList<>();
        numsCalculator = new ArrayList<>();
        frame = new JFrame();
        FunctionLabel = new JLabel();
        NumsLabel = new JLabel();

        newGame = new JButton("NEW GAME");
        newGame.addActionListener(this);
        newGame.setFont(new Font("Serif", Font.BOLD, 50));
        newGame.setVisible(true);
//        newGame.setSize(new Dimension(100,50));

        int x = 100;
        int y = 100;
        int w = 100;
        int h = 100;
        int counterTAB = 0;
        for (int square = 0; square < 9; square++) {
            for (int i = (square / 3) * 3; i < ((square / 3) * 3) + 3; i++) {
                for (int j = (square % 3) * 3; j < ((square % 3) * 3) + 3; j++) {
                    tab[counterTAB++] = sudokuBoard.getFinishedGrid()[i][j];
                }
            }
        }
        erase = new JButton();
        ImageIcon eraseIMG = new ImageIcon(new ImageIcon("src/eraseIMG.jpg").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        erase.setIcon(eraseIMG);erase.addActionListener(this);
        erase.setVisible(true);

        notes = new JButton();
        ImageIcon notesIMG = new ImageIcon(new ImageIcon("src/notesIMG.jpg").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        notes.setIcon(notesIMG);notes.addActionListener(this);
        notes.setVisible(true);

        hint = new JButton();
        ImageIcon hintIMG = new ImageIcon(new ImageIcon("src/hintIMG.jpg").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        hint.setIcon(hintIMG);hint.addActionListener(this);
        hint.setVisible(true);

        FunctionLabel.add(erase);FunctionLabel.add(notes);FunctionLabel.add(hint);
        FunctionLabel.setVisible(true);
        GridLayout layoutFunction = new GridLayout(1,3);
        layoutFunction.setHgap(10);
        FunctionLabel.setLayout(layoutFunction);

        for(int i = 1;i<10;i++){
            num = new JButton(String.valueOf(i));
            num.setFont(new Font("Serif", Font.BOLD, 50));
//            num.setSize(100,100);
            num.setVisible(true);
            num.addActionListener(this);
            NumsLabel.add(num);
            numsCalculator.add(num);

        }
        NumsLabel.setVisible(true);
        GridLayout layout = new GridLayout(3,3);
        layout.setHgap(10); layout.setVgap(10);
        NumsLabel.setLayout(layout);

        int counter = 0;
        for (int square = 0; square < 9; square++) {
            label = new JLabel();
            for (int i = (square / 3) * 3; i < ((square / 3) * 3) + 3; i++) {
                for (int j = (square % 3) * 3; j < ((square % 3) * 3) + 3; j++) {

                    if (grid[i][j] != 0) {
                        button = new JButton(String.valueOf(grid[i][j]));
                        button.setEnabled(false);
                        button.setBackground(new Color(20, 100, 140));
                        button.setFont(new Font("Serif", Font.BOLD, 33));
                    } else {
                        button = new JButton();
                        button.addActionListener(this);
                    }
                    buttons.add(button);
                    label.add(button);
                    button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//                    label.add(button);
                }
            }
            label.setLayout(new GridLayout(3, 3));
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frame.add(label).setBounds(x,y,250,250);
            counter++;
            if(counter==3) frame.add(FunctionLabel).setBounds(x+300,y,450,150);
            else if(counter==6) frame.add(NumsLabel).setBounds(x+300,y-60,450,450);
            else if(counter==9) frame.add(newGame).setBounds(x+300,y+180,450,70);
            x+=250;
            if(x==850){
                x=100;y+=250;
                if(y==850);
            }
        }
        frame.add(Mistakes).setBounds(900,50,200,50);
        frame.add(HintsRemained).setBounds(1200,50,200,50);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1400, 1400));
        frame.setTitle("SUDOKU GAME");
        frame.setVisible(true);
    }
    boolean CellClicked = false, EraserClicked = false, NotesClicked = false, HintCLicked = false;

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==newGame) {new Menu();frame.dispose();}
        if(e.getSource()==erase) EraserClicked = true;
        if(e.getSource()==notes) {NotesClicked = true;buttonPom.setBackground(null);}
        if(e.getSource()==hint) HintCLicked = true;

        for (JButton button : buttons) {
            if (button.getBackground() == Color.YELLOW) {
                button.setBackground(null);
            }
            if (e.getSource() == button) {
                buttonPom = button;
                CellClicked = true;
                if(EraserClicked) EraserClicked = false;
            }
        }
        buttonPom.setBackground(Color.YELLOW);
        if(HintCLicked && buttonPom.getBackground()==Color.YELLOW){
            for(int i = 0;i<9;i++)
                for(int j = 0;j<9;j++)
                    if (buttons.get(i * 9 + j) == buttonPom) {
                        buttonPom.setText(String.valueOf(tab[i * 9 + j]));
                        buttonPom.setFont(new Font("Serif", Font.BOLD, 33));
                        buttonPom.setBackground(Color.GREEN);
                        buttonPom.setEnabled(false);
                        hints--;
                        HintsRemained.setText("Hints remained: " + String.valueOf(hints));
                        if(hints==0) {
                            hint.setEnabled(false);
                            HintCLicked = false;
                            JOptionPane.showMessageDialog(null, "You don't have any hints left", "No hints", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            HintCLicked = false;
                            CellClicked = false;
                            button = null;buttonPom = null;
                            break;
                        }
                    }
        }
        if(CellClicked) {
            if (!NotesClicked) {
                for (JButton button : numsCalculator) {
                    if (button == e.getSource()) {
                        buttonPom.setText(button.getText());
                        buttonPom.setFont(new Font("Serif", Font.BOLD, 33));
                        if (Integer.parseInt(buttonPom.getText()) != tab[buttons.indexOf(buttonPom)]) {
                            buttonPom.setBackground(Color.RED);
                            mistakes++;
                            Mistakes.setText("Mistakes: " + String.valueOf(mistakes) + "/3");
                            Mistakes.setFont(new Font("Serif", Font.BOLD, 20));
                            if (mistakes == 3) {
                                int dialogButton = JOptionPane.YES_NO_OPTION;
                                int dialogResult = JOptionPane.showConfirmDialog(this, "You've lost :(( Do you want to play again?", "YOU LOST!!", JOptionPane.YES_NO_OPTION);
                                if (dialogResult == 0) {
                                    new Menu();
                                    frame.dispose();
                                } else {
                                    frame.dispose();
                                }

                            }
                        } else {
                            buttonPom.setBackground(Color.GREEN);
                        }
                        buttonPom = null;
                        CellClicked = false;
                    }
                }
            } else { // notes mode is on
                // Show a pop-up window with a list of numbers from 1 to 9
                JPopupMenu notesMenu = new JPopupMenu();
                for (int i = 1; i <= 9; i++) {
                    JMenuItem noteItem = new JMenuItem(Integer.toString(i));
                    noteItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Add the selected note to the cell
                            if(button.getFont() == new Font("Serif", Font.BOLD, 33)){
                                button.setText("");
                                buttonPom.setText(buttonPom.getText() + " " + noteItem.getText());
                            }else buttonPom.setText(buttonPom.getText() + " " + noteItem.getText());
                        }
                    });
                    notesMenu.add(noteItem);
                }
                notesMenu.show(buttonPom, 0, buttonPom.getHeight());
            }
        }
        if(EraserClicked) {buttonPom.setText(null);buttonPom.setBackground(null);}
        if(NotesClicked && e.getSource()==notes) NotesClicked = false;
    }

    /*@Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int index = 0, x = 0, y = 0, pom = 0, pom2 = 0;
        if (e.getKeyCode() >= KeyEvent.VK_1 && e.getKeyCode() <= KeyEvent.VK_9)
            number = e.getKeyCode() - KeyEvent.VK_0;
            JButton button = (JButton) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
            button.setText("" + number);
            button.setFont(new Font("Serif", Font.BOLD, 33));
            button.setBackground(null);
            if (button.getText() != "0") {
                for (int i = 0; i < 81; i++) {
                    if (buttons.get(i) == button) {
                        index = i;
                        pom = i % 9;
                        pom2 = i / 9;
                        x = i > 53 ? (pom / 3) + 6 : (i > 26 ? (pom / 3) + 3 : (pom / 3));
                        y = pom % 3 + pom2 * 3 > 8 ? (pom % 3 + pom2 * 3) % 9 : pom % 3 + pom2 * 3;
                        sudokuBoard.setNum(x, y, number);
                        sudokuBoard.print(sudokuBoard.getGrid());
                        if(SudokuValidator.check(sudokuBoard.getGrid())) new Menu();
                    }
                }
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }*/
}

class SudokuBoard {

    int[][] grid;
    int[][] FinishedGrid;
    String difficulty;
    BoardMaker boardMaker = new BoardMaker();
    public SudokuBoard(String difficulty) {



        //System.out.println("s");
        boardMaker.fillBoard(boardMaker.getPomBoard());
        FinishedGrid = boardMaker.getPomBoard();
        this.grid = boardMaker.MakeBoard(difficulty);
//        print(boardMaker.getPomBoard());
            /*this.grid = new int[][]{{5, 3, 0, 0, 7, 0, 0, 0, 0},
                    {6, 0, 0, 1, 9, 5, 0, 0, 0},
                    {0, 9, 8, 0, 0, 0, 0, 6, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 3},
                    {4, 0, 0, 8, 0, 3, 0, 0, 1},
                    {7, 0, 0, 0, 2, 0, 0, 0, 6},
                    {0, 6, 0, 0, 0, 0, 2, 8, 0},
                    {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 9}};*/


    }

    public int[][] getGrid() {
        return grid;
    }

    public int[][] getFinishedGrid() {
        return FinishedGrid;
    }

    public void setNum(int x, int y, int num) {
        this.grid[x][y] = num;
    }

    public void print(int[][] grid) {
        for (int[] row : grid) {
            for (int i : row) System.out.print(i + " ");
            System.out.println();
        }
    }
}

class SudokuValidator {
    public static boolean check(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            if (validRow(i, sudoku) == false) return false;
            if (validColumn(i, sudoku) == false) return false;
            if (validSquare(i, sudoku) == false) return false;
        }
        return true;
    }

    public static boolean validRow(int row, int[][] sudoku) {

        boolean[] presentNumbers = new boolean[9];

        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] != 0) presentNumbers[sudoku[row][i] - 1] = true;
        }
        for (boolean i : presentNumbers) {
            if (!i) return false;
        }
        return true;
    }

    public static boolean validColumn(int column, int[][] sudoku) {

        boolean[] presentNumbers = new boolean[9];

        for (int i = 0; i < 9; i++) {
            if (sudoku[i][column] != 0) presentNumbers[sudoku[i][column] - 1] = true;
        }
        for (boolean i : presentNumbers) {
            if (!i) return false;
        }
        return true;

    }

    public static boolean validSquare(int square, int[][] sudoku) {
        boolean[] presentNumbers = new boolean[9];

        for (int i = (square / 3) * 3; i < ((square / 3) * 3) + 3; i++) {
            for (int j = (square % 3) * 3; j < ((square % 3) * 3) + 3; j++) {
                if (sudoku[i][j] != 0) presentNumbers[sudoku[i][j] - 1] = true;
            }
        }
        for (boolean i : presentNumbers) {
            if (!i) return false;
        }
        return true;
    }
}
