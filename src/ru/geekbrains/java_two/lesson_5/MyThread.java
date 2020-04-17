package ru.geekbrains.java_two.lesson_5;

public class MyThread extends Thread {
    private float[] arr ;

    public MyThread(float[] arr) {
        this.arr = arr ;
        start();
    }

    @Override
    public void run() {
        Main.updateArray(arr);
    }
}
