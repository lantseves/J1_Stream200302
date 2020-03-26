package ru.geekbrains.java_one.lesson_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
Рисует панель игры
 */
public class FiledPanel extends JPanel {
    private Graphics2D g ;
    private TicTacToe ticTacToe ;

    private int widthCell;
    private int heightCell;

    private int paddingWidthCell;
    private int paddingHeightCell;

    public FiledPanel() {
        //setBackground(Color.BLUE);
    }

    public void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        ticTacToe = new TicTacToe(gameMode, fieldSizeX, fieldSizeY , winLength) ;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / widthCell ;
                int y = e.getY() / heightCell;
                ticTacToe.moveGame(x , y) ;
            }
        });

       // ticTacToe.moveGame(1 , 1);
       // ticTacToe.moveGame(2 , 2);
    }

    public void updatePanel() {
        if (ticTacToe != null) {
            if (ticTacToe.getGameStatus() == TicTacToe.GAME_STATUS_PLAYING) {
                deltaWidthAndHeight();
                drawLines();
                drawField();
            } else if (ticTacToe.getGameStatus() == TicTacToe.GAME_STATUS_NOTHING) {
                g.drawString("Ничья!!! Создайте новую игру.", 170, 100);
            } else if (ticTacToe.getGameStatus() == TicTacToe.GAME_STATUS_WIN_PLAYER1) {
                g.drawString("Победил игрок 1.", 200, 200);
            } else if (ticTacToe.getGameStatus() == TicTacToe.GAME_STATUS_WIN_PLAYER2) {
                g.drawString("Победил игрок 2.", 200, 200);
            }
        }
    }

    //Рисуем ходы игроков
    private void drawField() {
        char[][] field = ticTacToe.getField() ;
        for (int y = 0; y < field.length ; y++) {
            for (int x = 0; x < field[y].length ; x++) {
                if (TicTacToe.DOT_PLAYER1 == field[y][x])
                    drawX(x , y);
                else if(TicTacToe.DOT_PLAYER2 == field[y][x])
                    draw0(x , y);
            }
        }
    }

    //Рисуем крестик в нужной клетке
    private void drawX(int x, int y) {
        g.drawLine(widthCell * x + paddingWidthCell,
                heightCell * y + paddingHeightCell,
                widthCell * (x + 1) - paddingWidthCell,
                heightCell * (y + 1) - paddingHeightCell);

        g.drawLine(widthCell * x + paddingWidthCell,
                heightCell * (y + 1) - paddingHeightCell,
                widthCell * (x + 1) - paddingWidthCell,
                widthCell * y + paddingHeightCell);
    }

    //Рисуем нолик в нужной клетке
    private void draw0(int x, int y) {
        g.drawOval(widthCell * x + paddingWidthCell,
                heightCell * y + paddingHeightCell,
                widthCell - 2 * paddingWidthCell,
                heightCell - 2 * paddingHeightCell);
    }

    //Рисует линии игрового поля
    private void drawLines() {
        for (int i = 1; i < ticTacToe.getField()[0].length ; i++) {
            g.drawLine(widthCell * i, 0, widthCell * i, (int)getBounds().width);
            g.drawLine(0 , widthCell * i, (int)getBounds().height, heightCell * i);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = (Graphics2D) g ;
        this.g.setStroke(new BasicStroke(4.0f));  // толщина равна 10
        updatePanel();
        repaint();
    }

    private void deltaWidthAndHeight() {
        Rectangle bounds = getBounds() ;
        widthCell = bounds.width / ticTacToe.getField()[0].length ;
        heightCell = bounds.height / ticTacToe.getField().length ;
        paddingWidthCell = (int)(widthCell * 0.1) ;
        paddingHeightCell = (int)(heightCell * 0.1) ;
    }
}
