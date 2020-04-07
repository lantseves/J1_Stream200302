package ru.geekbrains.java_two.lesson_2;

public class Main {
    public static void main(String[] args) {
        //Задание 4
        try {
            //Задание 1
            String[][] strings = parseStringToArray("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0") ;

            //Задание 2
            float a = parseIntToString(strings) ;

            //Задание 4
            System.out.println(a);
        } catch (WrongSizeArrayExeption | StringNotParsingToIntExeption e) {
            e.printStackTrace();
        }
    }

    //Задание 1
    public static String[][] parseStringToArray(String s) throws WrongSizeArrayExeption {
        String[] stringRows = s.split("\n") ;
        //Задание 3
        if (stringRows.length != 4) throw new WrongSizeArrayExeption("Array size is not 4") ;

        String[][] result = new String[4][4] ;
        for (int i = 0 ; i < stringRows.length ; i++) {
            String[] row = stringRows[i].split(" ") ;

            //Задание 3
            if (row.length != 4) throw new WrongSizeArrayExeption("Array size is not 4") ;

            result[i] = row ;
        }
        return result ;
    }

    //Задание 2
    private static float parseIntToString(String[][] strings) throws StringNotParsingToIntExeption {
        int result = 0 ;
        for (int i = 0 ; i < strings.length ; i++) {
            for (int j = 0 ; j < strings[i].length ; j++) {
                //Задание 3
                try {
                    result += Integer.parseInt(strings[i][j]) ;
                } catch (NumberFormatException e) {
                    throw new StringNotParsingToIntExeption("Could not parse string") ;
                }

            }
        }

        return result * 0.5f;
    }
}
