package ru.geekbrains.java_one.lesson_3;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '_';


    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int pointToWin;
    private static char[][] field;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    private static void initField() {
        fieldSizeY = 3;
        fieldSizeX = 3;
        pointToWin = 2;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.println("------");
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }

    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >=0 && y < fieldSizeY;
    }

    private static boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты хода X и Y (от 1 до 3) через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(x, y) || !isEmptyCell(x, y));
        field[y][x] = DOT_HUMAN;
    }

    private static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = DOT_AI;
    }


    private static boolean isFieldFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    private static boolean checkWin(char c) {

        // проверка по Y и X
        for (int y = 0; y < fieldSizeY ; y++) {
            for (int x = 0; x < fieldSizeX ; x++) {
                if (isVerticalOrHorizontalWin(c, x, y))
                    return true ;
            }
        }

        // Проверка диагоналей
        for (int y = 0 ; y <= fieldSizeY - pointToWin ; y++) {
            for (int x = 0 ; x <= fieldSizeX - pointToWin ; x++) {
                if(isDiagonalWin(c, x, y))
                    return true ;
            }
        }
        return false ;
    }

    //Проверка вертикали и горизонтали
    private static boolean isVerticalOrHorizontalWin(char c , int x, int y) {
        int countY = 0 ;
        int countX = 0 ;
        for (int i = 0; i < field.length; i++) {
            if (c == field[i][x]) {
                countY++ ;
                if (countY == pointToWin)
                    return true ;
            } else
                countY = 0 ;

            if (c == field[y][i]) {
                countX++ ;
                if (countX == pointToWin)
                    return true ;
            } else
                countX = 0 ;
        }
        return false ;
    }

    //Проверяет возможные квадраты и его диагонали
    private static boolean isDiagonalWin(char c , int offsetX, int offsetY) {
        int countDiagonalMain = 0 ;
        int countDiagonalOff = 0 ;
        for(int i = 0 ; i < pointToWin ; i++) {
            if(field[i + offsetY][i + offsetX] == c) {
                countDiagonalMain++;
                if (countDiagonalMain == pointToWin)
                    return true;
            } else
                countDiagonalMain = 0;
            if (field[i + offsetY][pointToWin + offsetX - 1 - i] == c) {
                countDiagonalOff++ ;
                if (countDiagonalOff == pointToWin)
                    return true ;
            } else
                countDiagonalOff = 0 ;
        }
        return false ;
    }

    public static void main(String[] args) {
//        while (true) {
        playOneRound();
//            System.out.println("Play again?");
//            if (SCANNER.next().equals("no"))
//                break;
//        }
    }

    private static void playOneRound() {
        initField();
        printField();
        while (true) {
            humanTurn();
            printField();
            if (checkWin(DOT_HUMAN)) {
                System.out.println("Выиграл игрок!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(DOT_AI)) {
                System.out.println("Выиграл компьютер!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья!");
                break;
            }
        }
    }
}
