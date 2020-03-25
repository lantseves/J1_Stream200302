package ru.geekbrains.java_one.lesson_6;

import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 2. Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
        String valueFile1 = readFile("file1.txt") ;
        String valueFile2 = readFile("file2.txt") ;
        writeFile("file3.txt" , valueFile1 + valueFile2);
    }

    public static String readFile(String fileName) {
        StringBuilder result = new StringBuilder() ;
        try {
            InputStream inputStream = new FileInputStream(fileName) ;
            int b ;
            while ( (b = inputStream.read()) != -1) {
                result.append((char) b) ;
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString() ;
    }

    public static void writeFile(String fileName , String value) {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(fileName, true)) ;
            printStream.println(value);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 3.Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле (работаем только с латиницей).
    public static boolean isConstraint(String fileName , String value) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(fileName)) ;
            while (scanner.hasNext()) {
                if (scanner.next().equals(value))
                    return true ;
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false ;
    }

    //** Написать метод, проверяющий, есть ли указанное слово в папке
    public static boolean isConstraintFolder(String path , String value) {
        File dir = new File(path); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        if (arrFiles != null) {
            for (int i = 0; i < arrFiles.length; i++) {
                if (isConstraint(arrFiles[i].getAbsolutePath() , value))
                    return true ;
            }
        }
        return false ;
    }
}
