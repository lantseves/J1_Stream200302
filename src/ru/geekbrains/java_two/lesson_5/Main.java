package ru.geekbrains.java_two.lesson_5;

import sun.net.idn.Punycode;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2 ;

    public static void main(String[] args) {
        System.out.print(equalsArr(oneThread(), twoThread()));
    }

    public static float[] oneThread() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();
        updateArray(arr , 0);
        long b = System.currentTimeMillis();

        System.out.printf("oneThread() - %.2f\n", (b - a) * 0.001f) ;
        return arr ;
    }

    public static float[] twoThread() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();
        float[] arr1 = Arrays.copyOfRange(arr , 0 , h) ;
        float[] arr2 = Arrays.copyOfRange(arr, h  , arr.length) ;
        MyThread t1 = new MyThread(arr1 , 0) ;
        MyThread t2 = new MyThread(arr2, h) ;
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(arr1 , 0 , arr, 0 , h);
        System.arraycopy(arr2 , 0 , arr, h , h);
        long b = System.currentTimeMillis();
        System.out.printf("twoThread() - %.2f\n", (b - a) * 0.001f) ;
        return arr ;
    }

    public static void updateArray(float[] arr , int srcPos) {
        for(int i = 0 ; i < arr.length ; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (i + srcPos) / 5f) * Math.cos(0.2f + (i + srcPos)  / 5f) * Math.cos(0.4f + (i + srcPos)  / 2f));
        }
    }

    private static boolean equalsArr(float[] arr1 , float[] arr2) {
        for (int i = 0 ; i < size ; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
}
