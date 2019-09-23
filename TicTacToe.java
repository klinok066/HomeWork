import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    int sizeOfMap = 3;//Здесь можно изменять размер поля

    int numOfDotsToWin = 3;//Здесь условия победы (работает при любом размере >= условие)

    int[][] gameBoard = new int[sizeOfMap][sizeOfMap];

    Scanner scanner = new Scanner(System.in);

    Random random = new Random();

    String playerX = "X", playerO = "O", currentPlayer = playerX;

    boolean isWin, isDraw;

    TicTacToe() {
        while (true) {
            System.out.println("Если хотите сыграть с другом, введите Friend");
            System.out.println("Если хотите сыграть с ботом, введите Bot");
            String answer = scanner.nextLine();
            if (answer.equals("Friend")) {
                playGame();
            } else if (answer.equals("Bot")) {
                System.out.println("Вы будете играть за X или за O?");
                String answer1 = scanner.nextLine();
                if (answer1.equals("X")) {
                    currentPlayer = playerX;
                } else if (answer1.equals("O")) {
                    currentPlayer = playerO;
                }
                playGameWithBot();
            }
            System.out.println("Будете играть еще?");
            String continuee = scanner.nextLine();
            if (continuee.equals("Нет")){
                System.exit(0);
            }
            else if (continuee.equals("Да")){
            continue;
            }
        }
    }

    void playGame(){
        initGameBoard();
        drawGameBoard();
        initGameLoop();
    }

    void playGameWithBot(){
        initGameBoard();
        drawGameBoard();
        initGameLoopWithBot();
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
                break;
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
//        System.out.println(x + " икс");
//        System.out.println(y + " игрик");
//        System.out.println(playerWho + " игрок");



        int sumOfDotVertical=0;
        for(int i=0; i<numOfDotsToWin; i++) {
            if (gameBoard[x][y + i] == playerWho) {
                sumOfDotVertical += 1;
//                System.out.println(x+" икс "+ (y +i) + " игрик");

            }
        }
//        System.out.println(sumOfDotVertical);
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

    void initGameLoopWithBot(){
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
                break;
            }
            checkDraw();
            if(isDraw){
                break;
            }
            botTurn();
            drawGameBoard();
            if(checkWin(getCurrentPlayerBot())) {
                System.out.println("Выйграл бот!");
                break;
            }
            checkDraw();
        }
    }

    void botTurn(){
        int x = -1, y = -1;

        for (int i = 0; i < sizeOfMap; i++) {
            for (int j = 0; j < sizeOfMap; j++) {
                if (checkSpace(i, j)) {
                    gameBoard[i][j] = getCurrentPlayer();
                    if (checkWin(getCurrentPlayer())) {
                        x = j;
                        y = i;
                    }
                    gameBoard[i][j] = 0;
                }
            }
        }

        if (x == -1 && y == -1) {
            do {
                x = random.nextInt(sizeOfMap);
                y = random.nextInt(sizeOfMap);
            } while (!checkSpace(y, x));

        }
        if (getCurrentPlayer() == 1){
            gameBoard[y][x] = (2);
        }else{
            gameBoard[y][x] = (1);
        }

    }

    int getCurrentPlayerBot(){
            return currentPlayer.equalsIgnoreCase(playerX) ? 2 : 1;
    }

}


