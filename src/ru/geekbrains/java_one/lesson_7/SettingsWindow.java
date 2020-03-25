package ru.geekbrains.java_one.lesson_7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Окно настроек старта игры
 */
public class SettingsWindow extends JFrame {
    private static final int WINDOW_WIDTH = 350 ;
    private static final int WINDOW_HEIGHT = 270 ;

    private static final int MIN_WIN_LENGTH = 3 ;

    private static final int MIN_FIELD_SIZE = 3 ;
    private static final int MAX_FILED_SIZE = 10 ;

    private static final String FIELD_SIZE_PREFIX = "Field size is: " ;
    private static final String WIN_LENGTH_PREFIX = "Win length is: " ;

    private GameWindow gameWindow ;
    private JRadioButton humVSAI ;
    private JRadioButton humVSShum;
    private JSlider sliderWinLen ;
    private JSlider sliderFieldSize ;

    public SettingsWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow ;
        setSize(WINDOW_WIDTH , WINDOW_HEIGHT);
        Rectangle gameWindowBouds = gameWindow.getBounds() ;
        int posX = (int) gameWindowBouds.getCenterX() - WINDOW_WIDTH / 2 ;
        int posY = (int) gameWindowBouds.getCenterY() - WINDOW_HEIGHT / 2 ;
        setLocation(posX , posY);
        setResizable(false);
        setTitle("Creating new game");
        setLayout(new GridLayout(10 , 1));
        addControlsMode() ;
        addGameControlsField() ;

        JButton btnStart = new JButton("Start new game") ;
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartClick() ;
            }
        });

        add(btnStart) ;
    }

    private void addControlsMode() {
        add(new JLabel("Choose game mode")) ;
        humVSAI = new JRadioButton("Human vs. AI", true) ;
        humVSShum = new JRadioButton("human vs. Human") ;
        ButtonGroup gameMode = new ButtonGroup() ;
        gameMode.add(humVSAI);
        gameMode.add(humVSShum);
        add(humVSAI) ;
        add(humVSShum) ;
    }

    private void addGameControlsField() {
        JLabel lbFieldSize = new JLabel((FIELD_SIZE_PREFIX + MIN_FIELD_SIZE)) ;
        JLabel lbWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH) ;

        sliderFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FILED_SIZE, MIN_FIELD_SIZE) ;
        sliderWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE , MIN_FIELD_SIZE) ;

        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int curentValue = sliderFieldSize.getValue() ;
                lbFieldSize.setText(FIELD_SIZE_PREFIX + curentValue);
                sliderWinLen.setMaximum(curentValue);
            }
        });

        sliderWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGTH_PREFIX + sliderWinLen.getValue());
            }
        });

        add(new JLabel("Choose field size")) ;
        add(lbFieldSize) ;
        add(sliderFieldSize) ;
        add(new JLabel("Choose win length")) ;
        add(lbWinLength) ;
        add(sliderWinLen) ;
    }

    private void btnStartClick() {
        int gameMode ;
        if (humVSAI.isSelected())
            gameMode = TicTacToe.MODE_HVA ;
        else if(humVSShum.isSelected())
            gameMode = TicTacToe.MODE_HVH ;
        else
            throw new RuntimeException("Unexpected Spanish inquisition!") ;

        int fielSize = sliderFieldSize.getValue() ;
        int winLen = sliderWinLen.getValue() ;
        gameWindow.startNewGame(gameMode , fielSize , fielSize , winLen);
        setVisible(false);
    }
}
