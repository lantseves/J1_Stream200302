package ru.geekbrains.java_two.lesson_5;

public class MyThread extends Thread {
    private float[] arr ;
    private int srcPos ;

    public MyThread(float[] arr , int srcPos) {
        this.arr = arr ;
        this.srcPos = srcPos ;
        start();
    }

    @Override
    public void run() {
        Main.updateArray(arr, srcPos);
    }
}
