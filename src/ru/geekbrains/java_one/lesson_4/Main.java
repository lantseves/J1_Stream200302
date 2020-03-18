package ru.geekbrains.java_one.lesson_4;

public class Main {

    public static void main(String[] args) {

        //4. Вывести при помощи методов из пункта 3 ФИО и должность.
        Employee employee = new Employee("Ланцев", 0 , 27, "Старший бизнес-аналитик") ;
        System.out.println("Фамилия: " + employee.getSurname() + " Должность: " + employee.getPosition());

        //5. Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
        Employee[] employees = {new Employee("Иванов", 20000 , 20 , "Разработчик Frontend") ,
                new Employee("Сидоров", 30000 , 30 , "Разработчик Backend"),
                new Employee("Петров", 40000 , 40 , "Тестировщик"),
                new Employee("Смирнов", 50000 , 50 , "Архитектор"),
                new Employee("Андреев", 60000 , 60 , "Руководитель проекта")} ;

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() >= 40)
                System.out.println("Фамилия: " + employees[i].getSurname() +
                        " Зарплата: " + employees[i].getSalary() +
                        " Возраст:" + employees[i].getAge() +
                        " Должность: " + employees[i].getPosition());
        }

        //7. * Подсчитать средние арифметические зарплаты и возраста сотрудников из п.5
        System.out.println("Средняя арифметическая зарплаты: " + Employee.avgSalaryEmployees(employees) +
                " Средняя арифметическая возраста: " + Employee.avgAgeEmployees(employees));
    }
}
