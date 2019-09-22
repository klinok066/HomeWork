import java.util.Scanner;

public class TicTacToe {

    int sizeOfMap = 3;

    int numOfDotsToWin = 3;

    int[][] gameBoard = new int[sizeOfMap][sizeOfMap];

    Scanner scanner = new Scanner(System.in);

    String playerX = "X", playerO = "O", currentPlayer = playerX;

    boolean isWin, isDraw = false;

    TicTacToe() {
        playGame();

    }

    void playGame(){
        initGameBoard();
        drawGameBoard();
        initGameLoop();
    }

    void initGameLoop() {
        while (!isWin && !isDraw) {
            int rowTurn = readCoord("строку");
            if (!checkRange(rowTurn)) {
                System.out.println("Выход за границы поля");
                continue;
            }
            int colTurn = readCoord("столбец");
            if (!checkRange(colTurn)) {
                System.out.println("Выход за границы поля");
                continue;
            }
            if (checkSpace(rowTurn, colTurn)) {
                gameBoard[rowTurn][colTurn] = getCurrentPlayer();
            } else {
                System.out.println("Ячейка уже занята!");
                continue;
            }
            drawGameBoard();
            if(checkWin(getCurrentPlayer())) {
                System.out.println("Вы победили!");
            }
//            checkWin(rowTurn, colTurn, getCurrentPlayer());
            checkDraw();
            changePlayer();
        }
    }

    int readCoord(String description) {
        System.out.println("Введите " + description);
        return scanner.nextInt() - 1;
    }

    boolean checkRange(int coord) {
        return coord >= 0 && coord <= gameBoard.length - 1;
    }

    boolean checkSpace(int coordX, int coordY) {
        return gameBoard[coordX][coordY] == 0;
    }

    int getCurrentPlayer() {
        return currentPlayer.equalsIgnoreCase(playerX) ? 1 : 2;
    }

    void changePlayer() {
        currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
    }
    
    boolean checkWin(int playerWho) {
        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (checkGorizontalWin(i, j, playerWho) || checkVerticalWin(i, j, playerWho)
                    || checkDiagonalWin1(i, j, playerWho) || checkDiagonalWin2(i, j, playerWho)) {
                    return isWin = true;
                }
            }

        }
        return  isWin = false;
    }

    boolean checkGorizontalWin(int x, int y, int playerWho){
        if (x < 0 || y < 0 || x + numOfDotsToWin > sizeOfMap) {
            return false;
        }

        int sumOfDotGorizont=0;
        for(int i=0; i<numOfDotsToWin; i++) {
            if (gameBoard[i + x][y] == playerWho) {
                sumOfDotGorizont = +1;
            }
        }
        return sumOfDotGorizont == numOfDotsToWin;
    }

    boolean checkVerticalWin(int x, int y, int playerWho){
        if (x < 0 || y < 0 || y + numOfDotsToWin > sizeOfMap) {
            return false;
        }

        int sumOfDotVertical=0;
        for(int i=0; i<numOfDotsToWin; i++) {
            if (gameBoard[x][y + i] == playerWho) {
                sumOfDotVertical += 1;
            }
        }
        return sumOfDotVertical == numOfDotsToWin;
    }

    boolean checkDiagonalWin1(int x, int y, int playerWho){
        if (x < 0 || y < 0 || x + numOfDotsToWin > sizeOfMap || y + numOfDotsToWin > sizeOfMap) {
            return false;
        }

        int sumOfDotDiagonal1=0;
        for(int i=0; i<numOfDotsToWin; i++) {
            if (gameBoard[x + i][y + i] == playerWho) {
                sumOfDotDiagonal1 += 1;
            }
        }
        return sumOfDotDiagonal1 == numOfDotsToWin;
    }

    boolean checkDiagonalWin2(int x, int y, int playerWho){
        if (x < 0 || y + 1 - numOfDotsToWin < 0 || x + numOfDotsToWin > sizeOfMap) {
            return false;
        }

        int sumOfDotDiagonal2=0;
        for(int i=0; i<numOfDotsToWin; i++) {
            if (gameBoard[x + i][y - i] == playerWho) {
                sumOfDotDiagonal2 += 1;
            }
        }
        return sumOfDotDiagonal2 == numOfDotsToWin;
    }

    boolean checkDraw(){
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                if (gameBoard[row][col] == 0) {
                    return isDraw = false;
                }
            }
        }
        System.out.println("У вас ничья!");
        return isDraw = true;
        }

    void initGameBoard() {

        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                gameBoard[row][col] = 0;
            }
        }
    }

    void drawGameBoard() {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                switch (gameBoard[row][col]) {
                    case 0:
                        System.out.print("  ");
                        break;
                    case 1:
                        System.out.print(" X ");
                        break;
                    case 2:
                        System.out.print(" O ");
                        break;
                }
                if (col != gameBoard[row].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row != gameBoard.length - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

}
