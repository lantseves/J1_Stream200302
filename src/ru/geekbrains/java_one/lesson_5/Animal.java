package ru.geekbrains.java_one.lesson_5;

/*
1. Создать классы Собака, Лошадь, Птица и Кот с наследованием от класса Животное.

2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания),
или высоту (для прыжков).
 */
public abstract class Animal {
    protected double maxDistanceRun ;
    protected double maxDistanceSwim ;
    protected double maxHeightJump ;

    public abstract String run(int distance) ;

    public abstract String swim(int distance) ;

    public abstract String jump(int height) ;
}
