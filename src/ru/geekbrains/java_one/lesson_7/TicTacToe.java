package ru.geekbrains.java_one.lesson_7;

import java.util.Random;

/*
Класс игра, управляет игрой
 */
public class TicTacToe {
    public static final int MODE_HVH = 0 ;
    public static final int MODE_HVA = 1 ;

    public static final char DOT_PLAYER1 = 'X';
    public static final char DOT_PLAYER2 = 'O';
    public static final char DOT_EMPTY = '_' ;

    public static final int GAME_STATUS_PLAYING = 0 ;
    public static final int GAME_STATUS_WIN_PLAYER1 = 1 ;
    public static final int GAME_STATUS_WIN_PLAYER2 = 2 ;
    public static final int GAME_STATUS_NOTHING = -1 ;

    private int fieldSizeX ;
    private int fieldSizeY ;

    private int gameStatus ;
    private int gameMode ;
    private int winLength ;
    private char[][] field;
    private char currentPlayer;

    public TicTacToe(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.gameMode = gameMode;
        this.winLength = winLength;
        this.fieldSizeX = fieldSizeX ;
        this.fieldSizeY = fieldSizeY ;
        this.currentPlayer = DOT_PLAYER1 ;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    public char[][] getField() {
        return field;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    //сделать ход
    public void moveGame(int x, int y) {
        if(isEmptyCell(x , y) && isValidCell(x, y) && gameStatus == GAME_STATUS_PLAYING) {
            if (gameMode == MODE_HVH) {
                field[y][x] = currentPlayer ;

                if(checkWin(currentPlayer)) {
                    gameStatus = currentPlayer == DOT_PLAYER1 ? GAME_STATUS_WIN_PLAYER1 : GAME_STATUS_WIN_PLAYER2 ;
                } else if (isFieldFull()) {
                    gameStatus = GAME_STATUS_NOTHING;
                } else {
                    currentPlayer = currentPlayer == DOT_PLAYER1 ? DOT_PLAYER2 : DOT_PLAYER1 ;
                }
            } else if(gameMode == MODE_HVA) {
                field[y][x] = currentPlayer ;

                if(checkWin(DOT_PLAYER1)) {
                    gameStatus =  GAME_STATUS_WIN_PLAYER1 ;
                } else if (isFieldFull()) {
                    gameStatus = GAME_STATUS_NOTHING;
                } else {
                    aiMove();
                    if(checkWin(DOT_PLAYER2)) {
                        gameStatus = GAME_STATUS_WIN_PLAYER2 ;
                    } else if (isFieldFull()) {
                        gameStatus = GAME_STATUS_NOTHING;
                    }
                }
            } else {
                throw new RuntimeException("Not found gameMode");
            }
        }
    }

    private void aiMove() {
        int x;
        int y;

        //Пытается поставить выйгрышный ход если есть.
        if(isNextMoveWin(DOT_PLAYER2 , DOT_PLAYER2))
            return ;

        // Мешает игроку поставить победный ход
        if(isNextMoveWin(DOT_PLAYER1 , DOT_PLAYER2))
            return ;

        //Рандом если выйгрышных ход не найден и блокировать победу не требуется
        Random random = new Random();
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = DOT_PLAYER2;
    }

    //Проверяет есть ли возможность выйграть в следующем ходе (Копипаст урока 3)
    private boolean isNextMoveWin(char cWins , char valueMove) {
        for(int y = 0 ; y < fieldSizeY ; y++) {
            for(int x = 0 ; x < fieldSizeX ; x++ ) {
                if(isEmptyCell(x, y)) {
                    field[y][x] = cWins;

                    if(checkWin(cWins)) {
                        field[y][x] = valueMove;
                        return true ;
                    }
                    else
                        field[y][x] = DOT_EMPTY;
                }
            }
        }

        return false ;
    }

    private boolean checkWin(char c) {

        // проверка по Y и X
        for (int i = 0; i < fieldSizeY ; i++) {
            if (isVerticalOrHorizontalWin(c, i, i))
                return true ;
        }

        // Проверка диагоналей
        for (int y = 0 ; y <= fieldSizeY - winLength ; y++) {
            for (int x = 0 ; x <= fieldSizeX - winLength ; x++) {
                if(isDiagonalWin(c, x, y))
                    return true ;
            }
        }
        return false ;
    }

    //Проверка вертикали и горизонтали (Копипаст урока 3)
    private boolean isVerticalOrHorizontalWin(char c , int x, int y) {
        int countY = 0 ;
        int countX = 0 ;
        for (int i = 0; i < field.length; i++) {
            if (c == field[i][x]) {
                countY++ ;
                if (countY == winLength)
                    return true ;
            } else
                countY = 0 ;

            if (c == field[y][i]) {
                countX++ ;
                if (countX == winLength)
                    return true ;
            } else
                countX = 0 ;
        }
        return false ;
    }

    //Проверяет возможные квадраты и его диагонали (Копипаст урока 3)
    private boolean isDiagonalWin(char c , int offsetX, int offsetY) {
        int countDiagonalMain = 0 ;
        int countDiagonalOff = 0 ;
        for(int i = 0 ; i < winLength ; i++) {
            if(field[i + offsetY][i + offsetX] == c) {
                countDiagonalMain++;
                if (countDiagonalMain == winLength)
                    return true;
            } else
                countDiagonalMain = 0;
            if (field[i + offsetY][winLength + offsetX - 1 - i] == c) {
                countDiagonalOff++ ;
                if (countDiagonalOff == winLength)
                    return true ;
            } else
                countDiagonalOff = 0 ;
        }
        return false ;
    }

    //Есть пустые поля или нет
    private boolean isFieldFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    //Проверяет есть такие индексы в поле или нет
    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    //Проверяет свободна ячейка или нет
    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }
}
