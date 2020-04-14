package ru.geekbrains.java_two.lesson_3;

import com.sun.javafx.sg.prism.web.NGWebView;

import java.util.*;

public class Main {
    //Задание 1 Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
    private static final String[] STRINGS = {"about", "and", "even", "and", "him",
                                            "day", "for", "how", "for", "look",
                                            "his", "for", "about", "if", "man",
                                            "make", "how", "if", "if", "man"} ;
    public static void main(String[] args) {
        /* Задание 1 - Найти список слов, из которых состоит текст (дубликаты не считать);
        Проверка */
        System.out.println(delDuplicates(STRINGS));

        /* Задание 1 - Посчитать сколько раз встречается каждое слово (использовать HashMap);
        Проверка */
        System.out.println(countDuplicates(STRINGS));

        //Задание 2 Проверка
        PhoneBook phoneBook = new PhoneBook() ;
        phoneBook.addPerson("Сидоров", "sidorov1@mail.tu" , "+79181111111");
        phoneBook.addPerson("Иванов", "ivanov1@mail.ru" , "+79281111111");
        phoneBook.addPerson(new Person("Сидоров", "sidorov2@mail.tu" , "+79182222222"));
        System.out.println(phoneBook);

        System.out.println(phoneBook.getEmailList("Сидоров"));
        System.out.println(phoneBook.getPhoneList("Сидоров"));



    }

    //Задание 1 - Найти список слов, из которых состоит текст (дубликаты не считать);
    public static ArrayList<String> delDuplicates(String[] strings) {
        return new ArrayList<>(new HashSet<>(Arrays.asList(strings)));
    }

    //Задание 1 - Посчитать сколько раз встречается каждое слово (использовать HashMap);
    public static HashMap<String, Integer> countDuplicates(String[] strings) {
        ArrayList<String> dictionary = delDuplicates(strings) ;

        HashMap<String,Integer> result = new HashMap<>();
        for(String key : dictionary) {
            result.put(key , 0) ;
        }

        for (String key : strings) {
            result.put(key , result.get(key) + 1) ;
        }
        return result ;
    }
}
