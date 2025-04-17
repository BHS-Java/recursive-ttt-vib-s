import java.util.Scanner;

public class TicTacToe {
  private char[][] board;
  private char currentPlayer;
  private Scanner scanner = new Scanner(System.in);

  public TicTacToe() {
      board = new char[3][3];
      currentPlayer = 'X';
      for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
              board[i][j] = ' ';
              System.out.print(board[i][j]);
          }System.out.println();
      }
  }

  public void playGame() {
      playTurn();
  }

  public void playTurn(){
      drawBoard(0, 0);
      System.out.println("Player " + currentPlayer + ", where do you want to move?");
      int move = Integer.parseInt(scanner.next());
      enterValueInSpot(move);
      if(winOccured()){
        drawBoard(0, 0);
        System.out.println("PLAYER " + this.currentPlayer + " WINS");
        return;
      }
      if(draw(0 , 0)){
        drawBoard(0, 0);
        System.out.println("DRAW");
        return;
      }
      if(this.currentPlayer == 'X') this.currentPlayer = 'O';
      else if(this.currentPlayer == 'O') this.currentPlayer = 'X';
      playTurn();
  }

  public boolean draw(int row, int column){
    if(column >= this.board.length){
      column = 0;
      System.out.println();
      row++;
    }
    if(row >= this.board.length) return true;
    if(this.board[row][column] == ' ') return false;
    return draw(row, column+1);
  }

  public boolean winOccured(){
    return(checkPosDiagonal(0,0) || checkNegDiagonal(2,0) || checkColumns() || checkRows() );
  }

  private boolean checkPosDiagonal(int row, int column){
    if(column >= this.board.length) return true;
    if(this.board[row][column] == ' ' || this.board[row][column] != this.currentPlayer) return false;
    return checkPosDiagonal(row+1, column+1);
  }

  private boolean checkNegDiagonal(int row, int column){
    if(column >= this.board.length) return true;
    if(this.board[row][column] == ' ' || this.board[row][column] != this.currentPlayer) return false;
    return checkNegDiagonal(row-1, column+1);
  }

  private boolean checkColumns(){
    return (checkColumn(0, 0) || checkColumn(1, 0) || checkColumn(2, 0));
  }

  private boolean checkRows(){
    return (checkRow(0, 0) || checkRow(1, 0) || checkRow(2, 0));
  }

  private boolean checkRow(int row, int column){
    if(column >= this.board.length) return true;
    if(this.board[row][column] == ' ' || this.board[row][column] != this.currentPlayer) return false;
    return checkRow(row, column+1);
  }

  private boolean checkColumn(int row, int column){
    if(row >= this.board.length) return true;
    if(this.board[row][column] == ' ' || this.board[row][column] != this.currentPlayer) return false;
    return checkColumn(row, column+1);
  }

  public void enterValueInSpot(int locationValue){
    int y = (int)((locationValue-1) / this.board.length);
    int x = locationValue % this.board.length;
    this.board[y][x] = this.currentPlayer;
  }

  public void drawBoard(int row, int column){
    if(column >= this.board.length){
       column = 0;
       System.out.println();
       row++;
    }
    if(row >= this.board.length) return;
    System.out.print("[" + this.board[row][column] + "] ");
    drawBoard(row, column+1);
  }
}
