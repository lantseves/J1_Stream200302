package ru.geekbrains.java_one.lesson_7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Рисует главный экран и управляет панелями
 */
public class GameWindow extends JFrame {
    private static final int WIN_WIDTH = 507 ;
    private static final int WIN_HEIGHT = 555 ;
    private static final int WIN_POSX = 650 ;
    private static final int WIN_POSY = 250 ;

    private FiledPanel filedPanel ;
    private SettingsWindow settingsWindow ;


    public GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH , WIN_HEIGHT);
        setLocation(WIN_POSX , WIN_POSY);
        setTitle("Tic tac toe");
        setResizable(false);
        settingsWindow = new SettingsWindow(this) ;

        JButton btnStart = new JButton("Start new game") ;
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);
            }
        });

        JButton btnStop = new JButton("Exit") ;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panelBtns = new JPanel(new GridLayout(1 , 2)) ;
        panelBtns.add(btnStart);
        panelBtns.add(btnStop);
        add(panelBtns , BorderLayout.SOUTH) ;

        filedPanel = new FiledPanel() ;
        add(filedPanel, BorderLayout.CENTER) ;
        setVisible(true);
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
        filedPanel.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLength) ;
    }
}
