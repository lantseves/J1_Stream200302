package ru.geekbrains.java_one.lesson_2;
import java.util.Arrays ;

public class Main {
    public static void main(String[] args) {
        //Задание 1
        int[] array1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0} ;
        reversValue(array1) ;
        System.out.println("Задание 1:" + Arrays.toString(array1)) ;

        //Задание 2
        int[] array2 = new int[8] ;
        initArray(array2) ;
        System.out.println("Задание 2:" + Arrays.toString(array2)) ;

        //Задание 3
        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1} ;
        multiplicationValue(array3) ;
        System.out.println("Задание 3:" + Arrays.toString(array3)) ;

        //Задание 4
        int[] array4 = {10, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1} ;
        System.out.println("Задание 4: max = " + maxValue(array4) + " ,min = " + minValue(array4)) ;

        //Задание 5
        int[][] array5 = new int[5][5] ;
        diagonalInit(array5) ;
        System.out.println("Задание 5:") ;
        printTwoDimensionalArray(array5) ;

        //Задание 6
        int[] array6 = {2, 1, 1, 2, 1} ;
        System.out.println("Задание 6:" + isBalancedArray(array6)) ;

        //Задание 7
        int[] array7 = {1, 2, 3, 4, 5} ;
        shiftValue(array7 , -2) ;
        System.out.println("Задание 7:" + Arrays.toString(array7)) ;

    }

    /*
    1 Задать целочисленный массив, состоящий из элементов 0 и 1.
    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0;
    */
    public static void reversValue(int[] array) {
        for(int i = 0 ; i < array.length ; i++) {
            if(array[i] == 0)
                array[i] = 1 ;
                // если в массиве будут не только 0 и 1
            else if(array[i] == 1)
                array[i] = 0 ;

            //array[i] = array[i] == 0 ? 1 : 0 ;
        }
    }

    /*
    2 Задать пустой целочисленный массив размером 8.
    Написать метод, который помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;
    */
    public static void initArray(int[] array) {
        int count = 1 ;
        for(int i = 0 ; i < array.length ; i++ , count+=3) {
            array[i] = count ;
        }
    }

    /*
    3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ],
    написать метод, принимающий на вход массив и умножающий числа меньше 6 на 2;
    */
    public static void multiplicationValue(int[] array) {
        for(int i = 0 ; i < array.length ; i++) {
            if(array[i] < 6)
                array[i] *= 2 ;
        }
    }

    /*
    4 Задать одномерный массив.
    Написать методы поиска в нём минимального и максимального элемента;
    */
    public static int minValue(int[] array) {
        int min = array[0] ;

        for(int i = 1 ; i < array.length ; i++) {
            if(min > array[i])
                min = array[i] ;
        }

        return min ;
    }

    public static int maxValue(int[] array) {
        int max = array[0] ;

        for(int i = 1 ; i < array.length ; i++) {
            if(max < array[i])
                max = array[i] ;
        }
        return max ;
    }

    /*
    5 * Создать квадратный целочисленный массив (количество строк и столбцов одинаковое),
    заполнить его диагональные элементы единицами, используя цикл(ы);
    */
    public static void diagonalInit(int[][] array) {
        for(int y = 0 ; y < array.length ; y++) {
            for(int x = 0 ; x < array.length ; x++) {
                if(x == y || x + y == array.length - 1)
                    array[y][x] = 1;
            }
        }
    }

    // Вывод для проверки
    private static void printTwoDimensionalArray(int[][] array) {
        for(int y = 0 ; y < array.length ; y++) {
            for(int x = 0 ; x < array[y].length ; x++) {
                System.out.print(array[y][x] + " ") ;
            }
            System.out.println() ;
        }
    }

    /*
    6 ** Написать метод, в который передается не пустой одномерный целочисленный массив,
    метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части массива равны.
    Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false,
    checkBalance ([10, || 1, 2, 3, 4]) → true.
    Абстрактная граница показана символами ||, эти символы в массив не входят.
    */
    public static boolean isBalancedArray(int[] array) {
        int sumAllValue = 0 ;
        for(int i = 0 ; i < array.length ; i++) {
            sumAllValue += array[i];
        }

        if(sumAllValue % 2 != 0) return false ;

        sumAllValue /= 2 ;
        int tempSum = 0 ;
        for(int i = 0 ; i < array.length ; i++) {
            tempSum += array[i] ;

            if(tempSum == sumAllValue)
                return true ;
            else if(tempSum > sumAllValue)
                return false ;
        }

        return false ;
    }


    /*
    7 *** Написать метод, которому на вход подаётся одномерный массив и число n (может быть положительным, или отрицательным),
    при этом метод должен циклически сместить все элементы массива на n позиций.
    8 **** Не пользоваться вспомогательным массивом при решении задачи 7.
    */
    public static void shiftValue(int[] array ,int n) {
        int lastIndex = array.length - 1 ;

        if(n > 0) {
            for(int i = 0 ; i < n ; i++) {
                int tempValue = array[lastIndex] ;

                for (int j = lastIndex ; j > 0 ; j--) {
                    array[j] = array[j - 1] ;
                }
                array[0] = tempValue ;
            }
        } else {
            for(int i = 0 ; i < Math.abs(n) ; i++) {
                int tempValue = array[0] ;

                for (int j = 0 ; j < lastIndex ; j++) {
                    array[j] = array[j + 1] ;
                }
                array[lastIndex] = tempValue ;
            }
        }
    }
}
