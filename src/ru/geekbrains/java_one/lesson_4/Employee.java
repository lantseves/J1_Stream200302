package ru.geekbrains.java_one.lesson_4;


//1. Создать класс "Сотрудник" с полями: Фамилия, зарплата, возраст, должность;
public class Employee {
    /*
     8.*** Продумать конструктор таким образом, чтобы при создании каждому сотруднику
      присваивался личный уникальный идентификационный порядковый номер
     */
    private static int count = 0;
    private int id ;

    private String surname ;
    private int salary ;
    private int age ;
    private String position ;

    //2. Конструктор класса должен заполнять эти поля при создании объекта;
    public Employee(String surname, int salary, int age, String position) {
        this.surname = surname;
        this.salary = salary;
        this.age = age;
        this.position = position;

        //Задание 8.
        this.id = count++ ;
    }

    //3. Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
    public String getSurname() {
        return surname;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    //6. * Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000.
    public static void upSalaryEmployee(Employee[] employees) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].age > 45)
                employees[i].salary += 5000 ;
        }
    }

    //7. * Подсчитать средние арифметические зарплаты и возраста сотрудников из п.5
    //считает средние арифметическое зарплаты
    public static double avgSalaryEmployees(Employee[] employees) {
        int sum = 0 ;
        for (int i = 0; i < employees.length; i++) {
            sum += employees[i].salary ;
        }
        return sum * 1f / employees.length ;
    }

    //считает средние арифметическое возраста
    public static double avgAgeEmployees(Employee[] employees) {
        int sum = 0 ;
        for (int i = 0; i < employees.length; i++) {
            sum += employees[i].age ;
        }
        return sum * 1f / employees.length ;
    }
}
