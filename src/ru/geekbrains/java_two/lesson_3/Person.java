package ru.geekbrains.java_two.lesson_3;


/*
В каждой записи всего два поля: phone, e-mail
Сделал 3 поля, считаю что объект человека должен знать свою фамилию */
public class Person {
    private String surname ;
    private String email ;
    private String phone ;

    public Person() {
    }

    public Person(String surname, String email, String phone) {
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
