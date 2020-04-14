package ru.geekbrains.java_two.lesson_3;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    private HashMap<String , ArrayList<Person>> phoneBook = new HashMap<>();

    public void addPerson(String surname, String email, String phone) {
        if(phoneBook.containsKey(surname))
            phoneBook.get(surname) ;
        else
            phoneBook.put(surname,new ArrayList<>()) ;

        phoneBook.get(surname).add(new Person(surname, email, phone)) ;
    }

    public void addPerson(Person person) {
        if(phoneBook.containsKey(person.getSurname()))
            phoneBook.get(person.getSurname()) ;
        else
            phoneBook.put(person.getSurname(),new ArrayList<>()) ;

        phoneBook.get(person.getSurname()).add(person) ;
    }

    //Задание 2 Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов)
    public ArrayList<String> getPhoneList(String surname) {
        ArrayList<String> result = new ArrayList<>() ;

        if (!phoneBook.containsKey(surname)) return null ;

        for (Person person : phoneBook.get(surname)) {
            result.add(person.getPhone()) ;
        }
        return result ;
    }

    //Задание 2 и отдельный метод для поиска e-mail по фамилии.
    public ArrayList<String> getEmailList(String surname) {
        ArrayList<String> result = new ArrayList<>() ;

        if (!phoneBook.containsKey(surname)) return null ;

        for (Person person : phoneBook.get(surname)) {
            result.add(person.getEmail()) ;
        }
        return result ;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBook=" + phoneBook +
                '}';
    }
}
