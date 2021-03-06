package ru.geekbrains.java_one.lesson_5;

public class Horse extends Animal {

    /*
    3.У каждого животного есть ограничения на действия
    (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,;
    прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ;
    плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).

    4.При попытке животного выполнить одно из этих действий, оно должно сообщить результат. (Например, dog1.run(150); -> результат: 'Пёсик пробежал!')

    5.Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
     */

    public Horse() {
        this.maxDistanceRun = Math.random() * 1500 ;
        this.maxHeightJump = Math.random() * 3 ;
        this.maxDistanceSwim = Math.random() * 100 ;
    }

    @Override
    public String run(int distance) {
        return distance <= maxDistanceRun ?
                "Лошадь пробежала " + distance + " м." :
                "Лошадь пробежала " + maxDistanceRun + " м." ;
    }

    @Override
    public String swim(int distance) {
        return distance <= maxDistanceSwim ?
                "Лошадь проплыла " + distance + " м." :
                "Лошадь проплыла " + maxDistanceSwim + " м." ;
    }

    @Override
    public String jump(int height) {
        return height <= maxHeightJump ?
                "Лошадь прыгнула на " + height + " м." :
                "Лошадь прыгнула на " + maxHeightJump + " м." ;
    }
}
