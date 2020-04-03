package ru.geekbrains.java_two.lesson_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    int countSprites = 0 ;
    Sprite[] sprites = new Sprite[10];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch (e.getButton()) {
                    case 1:
                        addSprite(new Ball(e.getX() , e.getY())) ;
                        break;
                    case 3:
                        deleteBall(e.getX() , e.getY()) ;
                }
            }
        });
        add(canvas, BorderLayout.CENTER);
        setTitle("Circles");
        initApplication();
        setVisible(true);
    }

    private void addSprite(Sprite sprite) {
        if (countSprites < sprites.length) {
            sprites[countSprites++] = sprite;
        } else {
            sprites = Arrays.copyOf(sprites , sprites.length * 2) ;
        }
    }

    private void deleteBall(int x, int y) {
        for (int i = 0 ; i < countSprites ; i++) {
            if (sprites[i] instanceof Ball) {
                if (isClickedBall(x, y, (Ball) sprites[i])) {
                    sprites[i] = null ;
                    reindexSprites(i);
                    return;
                }
            }
        }
    }

    // Проверяет попали мы в круг или нет
    private boolean isClickedBall(int x , int y , Ball ball) {
        float vectorLength = (float)(Math.sqrt(Math.pow(x - ball.getX() ,2) + Math.pow(y - ball.getY(),2))) ;
        return vectorLength <= ball.halfHeight;
    }

    // Делает сдвиг при удаление элемента ма
    private void reindexSprites(int index) {
        for (int i = index ; i < countSprites ; i++) {
            if (i + 1 < countSprites) {
                sprites[i] = sprites[i + 1];
            } else {
                sprites[i] = null ;
            }
        }
    }

    private void initApplication() {
        addSprite(new Background()) ;
        for (int i = 1; i < 10; i++) {
            addSprite(new Ball());
        }
    }

    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < countSprites; i++) {
            if (sprites[i] != null)
                sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < countSprites; i++) {
            if (sprites[i] != null)
                sprites[i].render(canvas, g);
        }
    }
}
