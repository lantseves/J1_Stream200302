package ru.geekbrains.java_two.lesson_1;

import java.awt.*;

public class Background extends Sprite {
    private static final float MIN_GREEN_VALUE = 50f ;
    private static final float MIN_BLUE_VALUE = 50f ;

    private static final float MAX_GREEN_VALUE = 180f ;
    private static final float MAX_BLUE_VALUE = 180f ;

    private static final float V_GREEN_VALUE = 10 ;
    private static final float V_BLUE_VALUE = 10 ;

    private float greenValue = MAX_GREEN_VALUE ;
    private float blueValue = MAX_BLUE_VALUE ;
    private boolean isUpGreenValue = false ;
    private boolean isUpBlueValue = false ;
    private Color color ;


    @Override
    void update(GameCanvas canvas, float deltaTime) {
        float deltaGreen = getDelta(greenValue , V_GREEN_VALUE, isUpGreenValue , deltaTime , MIN_GREEN_VALUE , MAX_GREEN_VALUE) ;
        isUpGreenValue = deltaGreen > 0 ;
        float deltaBlue = getDelta(blueValue , V_BLUE_VALUE, isUpBlueValue , deltaTime , MIN_BLUE_VALUE, MAX_BLUE_VALUE) ;
        isUpBlueValue = deltaBlue > 0 ;

        greenValue += deltaGreen ;
        blueValue += deltaBlue ;

        color = new Color(80 , (int)greenValue , (int)blueValue) ;
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        canvas.setBackground(color);
    }

    private float getDelta(float value, float speed , boolean isUpValue, float deltaTime , float minValue , float maxValue) {
        float delta ;
        if (isUpValue)
            delta = speed * deltaTime ;
        else
            delta = -1f * (speed * deltaTime) ;

        if (value + delta < maxValue && value + delta > minValue)
            return delta ;
        else
            return -delta ;
    }
}
